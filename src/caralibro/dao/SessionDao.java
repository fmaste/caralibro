package caralibro.dao;

import java.util.Map;
import caralibro.factory.SessionFactory;
import caralibro.model.data.Application;
import caralibro.model.data.Session;
import caralibro.rest.Request;
import caralibro.rest.Response;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class SessionDao {

	public static Session getFromToken(Application application, String authToken) throws Exception {
		Map<String,String> params = Request.create(application, "Auth.getSession");
		params.put("auth_token", authToken);
		params.put("generate_session_secret", "1");
		Request.sign(params, application);
		String sessionJsonResponse = Response.get(params);
		return SessionFactory.create(sessionJsonResponse);		
	}
	
	public static Session getFromCode(Application application, String code) throws Exception {
		return getFromToken(application, code);
	}
	
}
