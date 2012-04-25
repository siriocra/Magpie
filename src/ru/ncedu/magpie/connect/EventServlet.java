package ru.ncedu.magpie.connect;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.ncedu.magpie.basicClasses.VKEvent;
import ru.ncedu.magpie.ejbpackage.APIMethods;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/events" })
public class EventServlet extends HttpServlet {
	@EJB
	private APIMethods apiMethods;
	
	private static final Logger logger = LoggerFactory.getLogger(EventServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		String accessToken = request.getParameter("access_token");
		String userId = request.getParameter("user_id"); 
		try {
			logger.trace("Getting user events " + userId);
			Collection<VKEvent> events = apiMethods.getEvents(accessToken, userId);
			request.setAttribute("events", events);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/events.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				logger.error("Error while loading events page. ServletException", e);
			} catch (IOException e) {
				logger.error("Error while loading events page. IOException", e);
			}
		} catch (URISyntaxException e1) {
			logger.error("Error while getting events. URISyntaxException", e1);
		}
	}
}
