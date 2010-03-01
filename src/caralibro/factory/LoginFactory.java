package caralibro.factory;

import caralibro.model.Application;

public class LoginFactory {
	
	// TODO: Google code's Java Facebook API is using http://www.facebook.com/authorize.php?api_key=[YOUR_API_KEY]&v=1.0&ext_perm=[PERMISSION NAME]
	
	public static String createLoginUrl(Application application, String authenticationToken) {
		// This is the URL to allow the first access to your application
		return "http://www.facebook.com/login.php?api_key=" + application.getKey() + "&v=1.0" + "&auth_token=" + authenticationToken;
	}
	
	// Extended permissions like this, a comma-separated list: read_stream,publish_stream,offline_access
	public static String createLoginUrl(Application application, String authenticationToken, String extendedPermissions) {
		return createLoginUrl(application, authenticationToken) + "&req_perms=" + extendedPermissions;
	}
	
}
