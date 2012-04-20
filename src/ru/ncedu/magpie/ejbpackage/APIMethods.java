package ru.ncedu.magpie.ejbpackage;

import java.net.URISyntaxException;
import java.util.Collection;

import javax.ejb.Local;

import ru.ncedu.magpie.basicClasses.VKEvent;
import ru.ncedu.magpie.basicClasses.VKUser;

@Local
public interface APIMethods {
	public VKUser getUserInfo(String accessToken, String userId) throws URISyntaxException;
	public Collection<VKUser> getFriends(String accessToken, String userId) throws URISyntaxException;
	public Collection<VKEvent> getEvents(String accessToken, String userId) throws URISyntaxException;
}
