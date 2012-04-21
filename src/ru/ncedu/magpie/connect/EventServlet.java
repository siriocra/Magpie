package ru.ncedu.magpie.connect;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.ncedu.magpie.basicClasses.VKEvent;
import ru.ncedu.magpie.ejbpackage.APIMethods;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/events" })
public class EventServlet extends HttpServlet implements javax.servlet.Servlet {
	@EJB
	private APIMethods apiMethods;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		String accessToken = request.getParameter("access_token");
		String userId = request.getParameter("user_id"); 
		try {
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
	}
}
