package caralibro.dao;

import java.util.Map;

import caralibro.Rest;
import caralibro.Utils;
import caralibro.model.Application;
import caralibro.model.Session;


public class SessionDao {

	@Deprecated
	public static Boolean isInfinite(Session session) {
		if (session.getExpirationTime() == null ) {
			return null;
		}
		return session.getExpirationTime().equals(0L);
	}
	
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
	
}
