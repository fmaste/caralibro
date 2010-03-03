package caralibro.factory;

import caralibro.model.Application;

public class ApplicationFactory {

	public static Application createApplication(Long id, String key, String secret) {
		Application application = new Application();
		application.setId(id);
		application.setKey(key);
		application.setSecret(secret);
		return application;
	}
	
	public static String createApplicationUrl(Application application) {
		return "http://www.facebook.com/developers/apps.php?app_id=" + application.getId();
	}
	
}
