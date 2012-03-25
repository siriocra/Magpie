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

import basicClasses.VKUser;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/friends" })
public class FriendsServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String accessToken = request.getParameter("access_token");
		String userId = request.getParameter("user_id"); 
		try {
			APIMethods apiMethods = new APIMethods(userId, accessToken);
			VKUser user = apiMethods.getUserInfo(accessToken, userId);
			request.setAttribute("userInfo", user);
		
			APIMethods apiMethods2 = new APIMethods(userId, accessToken);
			Collection<VKUser> friends = apiMethods2.getFriends(accessToken, userId);
			request.setAttribute("friends", friends);
			
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
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
