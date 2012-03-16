package connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jdom.JDOMException;

import basicClasses.VKUser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class APIMethods {

	private String userId;
	private String accessToken;

	public APIMethods(String userId, String accessToken) {
		this.setUserId(userId);
		this.setAccessToken(accessToken);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public VKUser getUserInfo(String accessToken, String userId)
			throws URISyntaxException, IOException, JDOMException {

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();

		qparams.add(new BasicNameValuePair("uids", userId));
		qparams.add(new BasicNameValuePair("access_token", accessToken));
		qparams.add(new BasicNameValuePair("fields", "uid, first_name, last_name, sex, bdate, photo_rec, contacts"));

		/* for events:
		qparams.add(new BasicNameValuePair("uid", userId));
		qparams.add(new BasicNameValuePair("access_token", accessToken));
		qparams.add(new BasicNameValuePair("extended", "1"));
		qparams.add(new BasicNameValuePair("filter", "events"));*/

		URI uri = URIUtils.createURI("https", "api.vk.com", -1,
				"/method/users.get",
				URLEncodedUtils.format(qparams, "UTF-8"), null);

		HttpGet httpget = new HttpGet(uri);
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		
		if (entity != null) {
			InputStream inputStream = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
	
			Gson gson = new Gson();
			Type type = new TypeToken<VKResponse<VKUser>>(){}.getType();
			VKResponse<VKUser> vkres = gson.fromJson(reader, type);
			VKUser user = vkres.getResponse().iterator().next();

			System.err.println(user.getFirstName());
			return user;
		}
		/*InputStream inputStream = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream, "UTF-8"));
		return reader.readLine();*/
		return null;
	}

}
