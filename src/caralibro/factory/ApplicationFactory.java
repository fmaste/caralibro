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
		// TODO: Add name ??
		application.setKey(key);
		application.setSecret(secret);
		application.setDesktop(desktop);
		return application;
	}
	
}
