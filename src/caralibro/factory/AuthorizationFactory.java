package caralibro.factory;

import caralibro.model.data.Application;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class AuthorizationFactory {
	
	public static String createUrl(Application application, String extendedPermissions) {
		return "http://www.facebook.com/connect/prompt_permissions.php?api_key=" + application.getKey() + "&v=1.0&next=http://www.facebook.com/connect/login_success.html?xxRESULTTOKENxx&display=popup&ext_perm=" + extendedPermissions;
	}
	
}
