package caralibro.factory;

import caralibro.model.User;

public class UserFactory {

	public static User create(Long id) {
		User user = new User();
		user.setId(id);
		return user;
	}
	
}
