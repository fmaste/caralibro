package caralibro.dao;

import java.util.Map;
import caralibro.model.data.Application;
import caralibro.rest.Request;
import caralibro.rest.Response;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class LoginDao {

	public static String generateAuthenticationToken(Application application) throws Exception {
		Map<String,String> params = Request.create(application, "Auth.createToken");
		Request.sign(params, application);
		String jsonResponse = Response.get(params);
		// FIXME: This is taking out the first and last \" from the JSON string response
		// TODO: Checl errors!!
		String authToken = jsonResponse.substring(1, jsonResponse.length() - 1);
		return authToken;
	}
	
}
