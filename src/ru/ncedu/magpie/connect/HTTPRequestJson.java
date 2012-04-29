package ru.ncedu.magpie.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * Class for making a request to VK and parsing answers from json
 * @author IrisM
 *
 */
public class HTTPRequestJson {
	
	private static final Logger logger = LoggerFactory.getLogger(HTTPRequestJson.class);
	
	public static Object getJson(URI uri, Type type){
		try {
			HttpGet httpget = new HttpGet(uri);
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						inputStream, "UTF-8"));
				Gson gson = new Gson();
				return gson.fromJson(reader, type);
			}
		} catch (JsonIOException e) {
			logger.error("Error while parsing json. JsonIOException", e, uri);
		} catch (JsonSyntaxException e) {
			logger.error("Error while parsing json. JsonSyntaxException", e, uri);
		} catch (ClientProtocolException e) {
			logger.error("Error while parsing json. ClientProtocolException", e, uri);
		} catch (IllegalStateException e) {
			logger.error("Error while parsing json. IllegalStateException", e, uri);
		} catch (UnsupportedEncodingException e) {
			logger.error("Error while parsing json. UnsupportedEncodingException", e, uri);
		} catch (IOException e) {
			logger.error("Error while parsing json. IOException", e, uri);
		}
		return null;
	}
}
