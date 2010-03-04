package caralibro.factory;

import caralibro.model.Application;

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
