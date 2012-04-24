package ru.ncedu.magpie.ejbpackage;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import ru.ncedu.magpie.basicClasses.VKEvent;
import ru.ncedu.magpie.basicClasses.VKUser;
import ru.ncedu.magpie.connect.HTTPRequestJson;
import ru.ncedu.magpie.connect.VKResponse;

import com.google.gson.reflect.TypeToken;

@Stateless(name="APIMethodsBean", mappedName="APIMethodsBean") 
public class APIMethodsBean implements APIMethods{
	@SuppressWarnings("unchecked")
	public VKUser getUserInfo(String accessToken, String userId) throws URISyntaxException{
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();

		qparams.add(new BasicNameValuePair("uids", userId));
		qparams.add(new BasicNameValuePair("access_token", accessToken));
		qparams.add(new BasicNameValuePair("fields", "uid, first_name, last_name, nickname, screen_name, sex, bdate, city, country, photo, photo_medium, photo_big, photo_rec, has_mobile, contacts, online"));

		URI uri = URIUtils.createURI("https", "api.vk.com", -1,
				"/method/users.get",
				URLEncodedUtils.format(qparams, "UTF-8"), null);

		Type type = new TypeToken<VKResponse<VKUser>>(){}.getType();
		VKResponse<VKUser> vkres = (VKResponse<VKUser>) HTTPRequestJson.getJson(uri, type);
		VKUser user = vkres.getResponse().iterator().next();
		return user;
	}
	@SuppressWarnings("unchecked")
	public Collection<VKUser> getFriends(String accessToken, String userId) throws URISyntaxException{
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();

		qparams.add(new BasicNameValuePair("uid", userId));
		qparams.add(new BasicNameValuePair("access_token", accessToken));
		//qparams.add(new BasicNameValuePair("count", "50"));
		qparams.add(new BasicNameValuePair("fields", "uid, first_name, last_name, nickname, screen_name, sex, bdate, city, country, photo, photo_medium, photo_big, photo_rec, has_mobile, contacts, online"));
		
		URI uri = URIUtils.createURI("https", "api.vk.com", -1,
				"/method/friends.get",
				URLEncodedUtils.format(qparams, "UTF-8"), null);

		Type type = new TypeToken<VKResponse<VKUser>>(){}.getType();
		VKResponse<VKUser> vkres = (VKResponse<VKUser>) HTTPRequestJson.getJson(uri, type);
		return vkres.getResponse();
	}
	@SuppressWarnings("unchecked")
	public Collection<VKEvent> getEvents(String accessToken, String userId) throws URISyntaxException{
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		
		qparams.add(new BasicNameValuePair("uid", userId));
		qparams.add(new BasicNameValuePair("access_token", accessToken));
		qparams.add(new BasicNameValuePair("extended", "1"));
		qparams.add(new BasicNameValuePair("filter", "events"));
		qparams.add(new BasicNameValuePair("fields", "start_date, end_date"));
		
		URI uri = URIUtils.createURI("https", "api.vk.com", -1,
				"/method/getGroupsFull",
				URLEncodedUtils.format(qparams, "UTF-8"), null);
		Type type = new TypeToken<VKResponse<VKEvent>>(){}.getType();
		return ((VKResponse<VKEvent>) HTTPRequestJson.getJson(uri, type)).getResponse();
	}
}
