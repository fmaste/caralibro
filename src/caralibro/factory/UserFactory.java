package caralibro.factory;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import caralibro.model.data.User;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class UserFactory {

	private static final Logger logger = LoggerFactory.getLogger(UserFactory.class);
	
	public static User create(Long id) {
		User user = new User();
		user.setId(id);
		return user;
	}
	
	public static User create(Long id, String name, String gender) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setGender(gender);
		return user;
	}
	
	/*
	 * Parses a JSON string containing the User and creates the User object.
	 * If there is no id, name or gender key an exception is thrown.
	 * 
	 * @param postJsonResponse 	The user info retrieved.
	 * @return 					A User object or null if the string is not a valid JSON object.
	 */
	public static User create(String userJsonResponse) throws Exception {
		logger.debug("Parsing JSON encoded User: \"" + userJsonResponse + "\"");
		if (userJsonResponse == null || userJsonResponse.isEmpty() || !userJsonResponse.startsWith("{")) {
			logger.error("Not a valid JSON encoded User: \"" + userJsonResponse + "\"");
			return null;
		}
		JSONObject pageJsonObject = new JSONObject(userJsonResponse);
		Long id = pageJsonObject.getLong("id");
		String name = pageJsonObject.getString("name");
		String gender = pageJsonObject.getString("gender");
		return UserFactory.create(id, name, gender);
	}
	
}
