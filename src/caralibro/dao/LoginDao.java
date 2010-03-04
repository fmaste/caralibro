package caralibro.dao;

import java.util.Map;

import caralibro.Rest;
import caralibro.factory.RequestFactory;
import caralibro.model.Application;
import caralibro.model.constants.Facebook;

public class LoginDao {

	public static String generateAuthenticationToken(Application application) throws Exception {
		Map<String,String> params = RequestFactory.create(application, "Auth.createToken");
		RequestFactory.sign(params, application);
		String jsonResponse = Rest.makeRequest(Facebook.REST_SERVER, params);
		// FIXME: This is taking out the first and last \" from the JSON string response
		String authToken = jsonResponse.substring(1, jsonResponse.length() - 1);
		return authToken;
	}
	
}
