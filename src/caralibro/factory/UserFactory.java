package caralibro.factory;

import caralibro.model.data.User;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class UserFactory {

	public static User create(Long id) {
		User user = new User();
		user.setId(id);
		return user;
	}
	
	// TODO: The rest!!
	
}
