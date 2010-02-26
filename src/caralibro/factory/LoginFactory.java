package caralibro.factory;

import caralibro.model.Application;

public class LoginFactory {
	
	public static String createLoginUrl(Application application, String authenticationToken) {
		return "http://www.facebook.com/login.php?api_key=" + application.getKey() + "&v=1.0" + "&auth_token=" + authenticationToken;
	}
	
	public static String createLoginUrl(Application application, String authenticationToken, String extendedPermissions) {
		return createLoginUrl(application, authenticationToken) + "&v=1.0&req_perms=" + extendedPermissions; // read_stream,publish_stream,offline_access
	}
	
}
