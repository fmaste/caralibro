package caralibro.factory;

import caralibro.model.data.Application;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class ApplicationFactory {

	public static Application create(Long id, String key, String secret, Boolean desktop) {
		Application application = new Application();
		application.setId(id);
		application.setKey(key);
		application.setSecret(secret);
		application.setDesktop(desktop);
		return application;
	}
	
	public static String createUrl(Application application) {
		return "http://www.facebook.com/developers/apps.php?app_id=" + application.getId();
	}
	
}
