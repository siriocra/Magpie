package ru.ncedu.magpie.connect;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.ncedu.magpie.basicClasses.DBInterface;
import ru.ncedu.magpie.basicClasses.VKUser;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/showfriends" })
public class ShowFriendsServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ShowFriendsServlet.class);
	
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String userId = request.getParameter("user_id"); 
		try {
			Collection<VKUser> friends = DBInterface.getInstance().loadFriends(userId);
			
			request.setAttribute("friends", friends);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/friends.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				logger.error("Error while loading friends page. ServletException", e);
			} catch (IOException e) {
				logger.error("Error while loading friends page. IOException", e);
			}
		} catch (SQLException e) {
			logger.error("Error while loading friends from database. SQLException", e);
		}
	}
}
