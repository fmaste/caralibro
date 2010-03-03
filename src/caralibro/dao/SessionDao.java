package caralibro.dao;

import java.util.Map;

import caralibro.Rest;
import caralibro.Utils;
import caralibro.factory.SessionFactory;
import caralibro.model.Application;
import caralibro.model.Session;
import caralibro.model.constants.Facebook;

public class SessionDao {

	public static Session getSessionFromToken(Application application, String authToken) throws Exception {
		Map<String,String> params = Utils.initParams(application, "Auth.getSession");
		params.put("auth_token", authToken);
		params.put("generate_session_secret", "1");
		Utils.finalizeParams(params, application);
		String sessionJsonResponse = Rest.makeRequest(Facebook.REST_SERVER, params);
		return SessionFactory.createSession(sessionJsonResponse);		
	}
	
	public static Session getSessionFromCode(Application application, String code) throws Exception {
		return getSessionFromToken(application, code);
	}
	
	// TODO:
	@Deprecated
	public static Boolean isInfinite(Session session) {
		if (session.getExpirationTime() == null ) {
			return null;
		}
		return session.getExpirationTime().equals(0L);
	}
	
	// TODO:
	@Deprecated
	public static Boolean isExpired(Session session, Long currentTime) {
		if (session.getExpirationTime() == null ) {
			return null;
		}
		return currentTime >= session.getExpirationTime();
	}
	
//	@Deprecated
//	public static boolean isExpired(Application application, Session session) throws Exception {
//		Map<String,String> params = Utils.initParams(application, "Auth.createToken");
//		params.put("session_key", session.getKey());
//		Utils.finalizeParams(params, application);
//		// FIXME: The string returned is different from the userId!!
//		String returnedUserId = Rest.makeRequest("http://api.facebook.com/restserver.php", params);
//		return !returnedUserId.equals(userId);
//	}
	
	// TODO: Check/get permissions!
	
}
