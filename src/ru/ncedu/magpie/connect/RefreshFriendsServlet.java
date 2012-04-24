package ru.ncedu.magpie.connect;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/refreshfriends" })
public class RefreshFriendsServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(RefreshFriendsServlet.class);
	
    @Resource(name = "jms/QueueConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(name = "jms/FriendsQueue", mappedName = "FriendsQueue")
    private Destination friendsQueue;
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String accessToken = request.getParameter("access_token");
		String userId = request.getParameter("user_id"); 
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(friendsQueue);

			MapMessage message = session.createMapMessage();
			message.setString("access_token", accessToken);
			message.setString("user_id", userId);

			producer.send(message);
			producer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			logger.error("Error occured while sending message getFriends. JMSException", e);
		}
        
        logger.trace("JMS getFriends sent");
                    
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/ready.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			PrintWriter out = response.getWriter();
			out.print("ServletException");
			logger.error("Exception occurred while refreshing friends' contacts. ServletException", e);
		} catch (IOException e) {
			PrintWriter out = response.getWriter();
			out.print("IOException");
			logger.error("Exception occurred while refreshing friends' contacts. IOException", e);
		}
	}
}
