package caralibro.dao;

import java.util.Map;
import caralibro.factory.RequestFactory;
import caralibro.model.data.Application;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class LoginDao {

	public static String generateAuthenticationToken(Application application) throws Exception {
		Map<String,String> params = RequestFactory.create(application, "Auth.createToken");
		RequestFactory.sign(params, application);
		String jsonResponse = ResponseDao.get(params);
		// FIXME: This is taking out the first and last \" from the JSON string response
		// TODO: Checl errors!!
		String authToken = jsonResponse.substring(1, jsonResponse.length() - 1);
		return authToken;
	}
	
}
