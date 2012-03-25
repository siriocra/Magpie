package connect;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basicClasses.VKEvent;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/events" })
public class EventServlet extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		String accessToken = request.getParameter("access_token");
		String userId = request.getParameter("user_id"); 
		try {
			APIMethods apiMethods = new APIMethods(userId, accessToken);
			Collection<VKEvent> events = apiMethods.getEvents(accessToken, userId);
			request.setAttribute("events", events);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/events.jsp");
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
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*String code = request.getParameter("code");
		if (code != null) {
			request.setAttribute("code", code);
		}
		try {
			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			qparams.add(new BasicNameValuePair("client_id", CLIENT_ID));
			qparams.add(new BasicNameValuePair("code", code));
			qparams.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));

			URI uri = URIUtils.createURI("https", "oauth.vkontakte.ru", -1,
					"/access_token", URLEncodedUtils.format(qparams, "UTF-8"),
					null);
			Type type = new TypeToken<AccessResponse>(){}.getType();
			AccessResponse accessResponse = (AccessResponse) HTTPRequestJson.getJson(uri, type);
			String accessToken = accessResponse.getAccessToken();
			String userId = accessResponse.getUserId();
			request.setAttribute("accessToken", accessResponse.getAccessToken());
			request.setAttribute("expiresIn", accessResponse.getExpires_in());
			request.setAttribute("userId", userId);
                
			APIMethods apiMethods = new APIMethods(userId, accessToken);
			Collection<VKEvent> events = apiMethods.getEvents(accessToken, userId);
			request.setAttribute("events", events);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/events.jsp");
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
		}*/
	}
}
