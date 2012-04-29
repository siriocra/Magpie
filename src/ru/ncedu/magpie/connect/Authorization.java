package ru.ncedu.magpie.connect;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.ncedu.magpie.basicClasses.VKUser;
import ru.ncedu.magpie.ejbpackage.APIMethods;

import com.google.gson.reflect.TypeToken;

/**
 * Servlet for authorizing user and getting user info 
 * @author IrisM
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/auth"})
public class Authorization extends HttpServlet{
	@Resource(name = "AuthorizationProperties", mappedName = "AuthorizationProperties")
	private Properties properties;
	
	private String CLIENT_ID;
	private String CLIENT_SECRET;
	
	@EJB
	private APIMethods apiMethods;
	
	private static final Logger logger = LoggerFactory.getLogger(Authorization.class);
    
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException
	{
		String code = request.getParameter("code");
		if (code != null) {
			request.setAttribute("code", code);
		}
		CLIENT_ID = properties.getProperty("CLIENT_ID");
		CLIENT_SECRET = properties.getProperty("CLIENT_SECRET");
		try {
			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			qparams.add(new BasicNameValuePair("client_id", CLIENT_ID));
			qparams.add(new BasicNameValuePair("code", code));
			qparams.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));

			URI uri;
			uri = URIUtils.createURI("https", "oauth.vkontakte.ru", -1,
					"/access_token", URLEncodedUtils.format(qparams, "UTF-8"),
					null);
			
			Type type = new TypeToken<AccessResponse>(){}.getType();
			AccessResponse accessResponse = (AccessResponse) HTTPRequestJson.getJson(uri, type);
			String accessToken = accessResponse.getAccessToken();
			String userId = accessResponse.getUserId();
			request.setAttribute("expiresIn", accessResponse.getExpires_in());
			request.setAttribute("accessToken", accessToken);
			request.setAttribute("userId", userId);
		
			logger.trace("Getting user info {}", userId);
			VKUser user = apiMethods.getUserInfo(accessToken, userId);
			request.setAttribute("userInfo", user);
		
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				logger.error("Error while loading welcome page. ServletException", e);
			} catch (IOException e) {
				logger.error("Error while loading welcome page. IOException", e);
			}
		} catch (URISyntaxException e1) {
			logger.error("Authorization failed. URISyntaxException", e1);
		}
	}
}
