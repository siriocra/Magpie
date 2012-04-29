package ru.ncedu.magpie.ejbpackage;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Collection;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.ncedu.magpie.basicClasses.DBInterface;
import ru.ncedu.magpie.basicClasses.VKUser;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationName", propertyValue = "FriendsQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
}, mappedName = "FriendsQueue")
public class FriendsMDB implements MessageListener{

	private static final Logger logger = LoggerFactory.getLogger(FriendsMDB.class);
	@EJB
	private APIMethods apiMethods;
	
	@Override
	public void onMessage(Message message) {
		String accessToken, userId;
		try {
			logger.trace("New message received");
			accessToken = ((MapMessage)message).getString("access_token");
			userId = ((MapMessage)message).getString("user_id");
			logger.trace("Getting friends by {}", userId);
			Collection<VKUser> friends = apiMethods.getFriends(accessToken, userId);
			logger.trace("Saving to database");
			DBInterface.getInstance().saveFriends(userId, friends);
			logger.trace("End of message processing");
		} catch (JMSException e) {
			logger.error("Error while processing message. JMSException", e);
		} catch (URISyntaxException e) {
			logger.error("Error while processing message. URISyntaxException", e);
		} catch (SQLException e) {
			logger.error("Error while processing message. SQLException", e);
		}
	}
	
}
