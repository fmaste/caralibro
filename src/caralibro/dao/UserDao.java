package caralibro.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import caralibro.factory.UserFactory;
import caralibro.model.data.User;
import caralibro.rest.Response;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class UserDao {
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	/*
	 * @return 		If there is no info or id is not of a User returns null.
	 */
	public static User getUserInfo(String id) throws Exception {
		String userJsonResponse = Response.getFromGraphAPI(id);
		if (userJsonResponse == null || userJsonResponse.isEmpty()) {
			return null;
		}
		User user = null;
		try {
			user = UserFactory.create(userJsonResponse);
		} catch (Exception e) {
			user = null;
			logger.error("Not a valid JSON encoded User: \"" + userJsonResponse + "\".");
		}
		return user;
	}

}
