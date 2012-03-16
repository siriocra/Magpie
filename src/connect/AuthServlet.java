package connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jdom.JDOMException;

import basicClasses.VKUser;

import com.google.gson.Gson;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/auth" })
public class AuthServlet extends HttpServlet {
	private static final String CLIENT_ID = "2837023";

	// TODO: CLIENT_SECRET must not be in code!
	private static final String CLIENT_SECRET = "no6gpjnzk85e9FEV2RbP";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		String code = request.getParameter("code");
		if (code != null) {
			request.setAttribute("code", code);
		}

		HttpClient httpclient = new DefaultHttpClient();

		try {

			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			qparams.add(new BasicNameValuePair("client_id", CLIENT_ID));
			qparams.add(new BasicNameValuePair("code", code));
			qparams.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));

			URI uri = URIUtils.createURI("https", "oauth.vkontakte.ru", -1,
					"/access_token", URLEncodedUtils.format(qparams, "UTF-8"),
					null);

			HttpGet httpget = new HttpGet(uri);
			HttpResponse VKresponse = httpclient.execute(httpget);
			HttpEntity entity = VKresponse.getEntity();

			if (entity != null) {
				InputStream inputStream = entity.getContent();

				String accessToken;
				String userId;
				try {

					BufferedReader reader = new BufferedReader(new InputStreamReader(
							inputStream, "UTF-8"));

					Gson gson = new Gson();
					AccessResponse accessResponse = gson.fromJson(reader,
							AccessResponse.class);
					
					accessToken = accessResponse.getAccessToken();
					userId = accessResponse.getUserId();
					request.setAttribute("accessToken", accessResponse.getAccessToken());
					request.setAttribute("expiresIn", accessResponse.getExpires_in());
					request.setAttribute("userId", userId);
                    
					APIMethods apiMethods = new APIMethods(userId, accessToken);
					VKUser user = apiMethods.getUserInfo(accessToken, userId);
					request.setAttribute("userInfo", user);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/user.jsp");
			        try {
						rd.forward(request, response);
					} catch (ServletException e) {
						PrintWriter out = response.getWriter();
						out.print("ServletException");
						e.printStackTrace();
					} catch (IOException e) {
						PrintWriter out = response.getWriter();
						out.print("IOException");
						e.printStackTrace();
					}
				} catch (JDOMException e) {
					PrintWriter out = response.getWriter();
					out.print("JDOMException");
					e.printStackTrace();
				} finally {
					inputStream.close();
				}

			}
		} catch (URISyntaxException e) {
			PrintWriter out = response.getWriter();
			out.print("URISyntaxException");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			PrintWriter out = response.getWriter();
			out.print("ClientProtocolException");
			e.printStackTrace();
		} catch (IOException e) {
			PrintWriter out = response.getWriter();
			out.print("IOException");
			e.printStackTrace();
		}
	}

}
