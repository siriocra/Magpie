package connect;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
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

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/auth"})
public class Authorization extends HttpServlet{
	private static final String CLIENT_ID = "2837023";

	// TODO: CLIENT_SECRET must not be in code!
	private static final String CLIENT_SECRET = "no6gpjnzk85e9FEV2RbP";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException
	{
		String code = request.getParameter("code");
		if (code != null) {
			request.setAttribute("code", code);
		}
		try {
			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			qparams.add(new BasicNameValuePair("client_id", CLIENT_ID));
			qparams.add(new BasicNameValuePair("code", code));
			qparams.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));

			URI uri;
			uri = URIUtils.createURI("https", "oauth.vkontakte.ru", -1,
					"/access_token", URLEncodedUtils.format(qparams, "UTF-8"),
					null);
			
			Type type = new TypeToken<AccessResponse>(){}.getType();
			AccessResponse accessResponse = (AccessResponse) HTTPRequestJson.getJson(uri, type);
			String accessToken = accessResponse.getAccessToken();
			String userId = accessResponse.getUserId();
			request.setAttribute("expiresIn", accessResponse.getExpires_in());
			request.setAttribute("accessToken", accessToken);
			request.setAttribute("userId", userId);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp");
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
