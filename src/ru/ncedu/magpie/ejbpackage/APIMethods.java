package ru.ncedu.magpie.ejbpackage;

import java.net.URISyntaxException;
import java.util.Collection;

import javax.ejb.Local;

import ru.ncedu.magpie.basicClasses.VKEvent;
import ru.ncedu.magpie.basicClasses.VKUser;
/**
 * Interface for sending requests to VK for getting some info
 * @author IrisM
 *
 */
@Local
public interface APIMethods {
	/**
	 * Method for getting user's info
	 * @param accessToken
	 * @param userId
	 * @return User's info
	 * @throws URISyntaxException
	 */
	public VKUser getUserInfo(String accessToken, String userId) throws URISyntaxException;
	/**
	 * Method for getting all user's friends and their info
	 * @param accessToken
	 * @param userId
	 * @return Collection of user's info
	 * @throws URISyntaxException
	 */
	public Collection<VKUser> getFriends(String accessToken, String userId) throws URISyntaxException;
	/**
	 * Method for getting all user's events
	 * @param accessToken
	 * @param userId
	 * @return Collection of events
	 * @throws URISyntaxException
	 */
	public Collection<VKEvent> getEvents(String accessToken, String userId) throws URISyntaxException;
}
