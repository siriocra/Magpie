package ru.ncedu.magpie.ejbpackage;

import java.net.URISyntaxException;
import java.util.Collection;

import javax.ejb.Remote;

import ru.ncedu.magpie.basicClasses.VKEvent;
import ru.ncedu.magpie.basicClasses.VKUser;

@Remote
public interface APIMethodsRemote {
	public VKUser getUserInfo(String accessToken, String userId) throws URISyntaxException;
	public Collection<VKUser> getFriends(String accessToken, String userId) throws URISyntaxException;
	public Collection<VKEvent> getEvents(String accessToken, String userId) throws URISyntaxException;
}
