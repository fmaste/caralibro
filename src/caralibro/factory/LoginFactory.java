package caralibro.factory;

import java.util.Map;

import caralibro.Rest;
import caralibro.Utils;
import caralibro.model.Application;
import caralibro.model.Facebook;

public class LoginFactory {

	public static String createAuthenticationToken(Application application) throws Exception {
		Map<String,String> params = Utils.initParams(application, "Auth.createToken");
		Utils.finalizeParams(params, application);
		String jsonResponse = Rest.makeRequest(Facebook.REST_SERVER, params);
		// FIXME: This is taking out the first and last \" from the JSON string response
		String authToken = jsonResponse.substring(1, jsonResponse.length() - 1);
		return authToken;
	}
	
	public static String createLoginUrl(Application application, String authenticationToken) {
		return "http://www.facebook.com/login.php?api_key=" + application.getKey() + "&v=1.0" + "&auth_token=" + authenticationToken;
	}
	
	public static String createLoginUrl(Application application, String authenticationToken, String extendedPermissions) {
		return createLoginUrl(application, authenticationToken) + "&v=1.0&req_perms=" + extendedPermissions; // read_stream,publish_stream,offline_access
	}
	
}
