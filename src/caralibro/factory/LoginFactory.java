package caralibro.factory;

import caralibro.model.data.Application;

/*
 *Used to login your application (get a valid session). You can pass some extended permissions to the login url.
 *
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class LoginFactory {
	
	public static String createUrlFromAuthenticationToken(Application application, String authenticationToken) {
		// This is the URL to allow the first access to your application
		return "http://www.facebook.com/login.php?api_key=" + application.getKey() + "&v=1.0" + "&auth_token=" + authenticationToken;
	}
	
	public static String createUrlForCodeGenerator(Application application) {
		return "http://www.facebook.com/code_gen.php?v=1.0&api_key=" + application.getKey();
	}
	
}
