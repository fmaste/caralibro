package caralibro.factory;

import org.json.JSONObject;
import caralibro.model.Session;
import caralibro.model.User;

public class SessionFactory {

	public static Session create(String key, String secret, User user, Long expirationTime) {
		Session session = new Session();
		session.setKey(key);
		session.setSecret(secret);
		session.setUser(user);
		session.setExpirationTime(expirationTime);
		return session;
	}
	
	public static Session create(String sessionJsonResponse) throws Exception {
		JSONObject sessionJson = new JSONObject(sessionJsonResponse);
		String key = sessionJson.getString("session_key");
		Long userId = sessionJson.getLong("uid");
		Long expirationTime = sessionJson.getLong("expires");
		String secret = sessionJson.optString("secret");
		if (secret.isEmpty()) {
			secret = null;
		}
		return create(key, secret, UserFactory.create(userId), expirationTime);
	}
	
}
