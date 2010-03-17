package caralibro.dao;

import java.util.Map;
import caralibro.factory.RequestFactory;
import caralibro.factory.SessionFactory;
import caralibro.model.data.Application;
import caralibro.model.data.Session;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class SessionDao {

	public static Session getFromToken(Application application, String authToken) throws Exception {
		Map<String,String> params = RequestFactory.create(application, "Auth.getSession");
		params.put("auth_token", authToken);
		params.put("generate_session_secret", "1");
		RequestFactory.sign(params, application);
		String sessionJsonResponse = ResponseDao.get(params);
		return SessionFactory.create(sessionJsonResponse);		
	}
	
	public static Session getFromCode(Application application, String code) throws Exception {
		return getFromToken(application, code);
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
