package caralibro.factory;

import java.util.Map;

import org.json.JSONObject;

import caralibro.Rest;
import caralibro.Utils;
import caralibro.model.Application;
import caralibro.model.Facebook;
import caralibro.model.Session;
import caralibro.model.User;


public class SessionFactory {

	public static Session createSession(String sessionJsonResponse) throws Exception {
		JSONObject sessionJson = new JSONObject(sessionJsonResponse);
		String key = sessionJson.getString("session_key");
		Long userId = sessionJson.getLong("uid");
		Long expirationTime = sessionJson.getLong("expires");
		String secret = sessionJson.optString("secret");
		if (secret.isEmpty()) {
			secret = null;
		}
		// I don't need this!
		// String baseDomain = sessionJson.getString("base_domain");
		return new Session(key, secret, new User(userId), expirationTime);
	}
	
	public static Session createSession(Application application, String authToken) throws Exception {
		Map<String,String> params = Utils.initParams(application, "Auth.getSession");
		params.put("auth_token", authToken);
		params.put("generate_session_secret", "1");
		Utils.finalizeParams(params, application);
		String sessionJsonResponse = Rest.makeRequest(Facebook.REST_SERVER, params);
		return createSession(sessionJsonResponse);		
	}
	
}
