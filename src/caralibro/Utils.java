package caralibro;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import caralibro.model.Application;
import caralibro.model.Session;


public class Utils {

	public static Map<String,String> initParams(Application application, String method) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("v", "1.0");
		params.put("format", "json");
		params.put("api_key", application.getKey());
		params.put("call_id", String.valueOf(System.currentTimeMillis())); // A auto-generated call id
		params.put("method", method);
		return params;
	}
	
	public static Map<String,String> initParams(Application application, Session session, String method) {
		Map<String,String> params = initParams(application, method);
		params.put("session_key", session.getKey());
		return params;
	}
	
	public static void finalizeParams(Map<String,String> params, Application application) {
		// This one must be the last one! A generated md5 based on the other params.
		params.put("sig", generateSig(application.getSecret(),params));
	}
	
	public static String generateSig(String applicationSecret, Map<String,String> parameters) {
		ArrayList<String >parameterNames = new ArrayList<String>(parameters.keySet());
		Collections.sort(parameterNames);
		String requestString = "";
		for (String parameterName : parameterNames) {
			requestString = requestString + parameterName + "=" + parameters.get(parameterName);
		}
		requestString = requestString + applicationSecret;
		return generateMd5(requestString);
	}
	
	public static String generateMd5(String str) {
		StringBuilder ans = new StringBuilder();
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			for (byte b : messageDigest.digest(str.getBytes("UTF-8"))) {
				ans.append(Integer.toHexString((b & 0xf0) >>> 4));
				ans.append(Integer.toHexString(b & 0x0f));
		    }
		} catch (Exception e) {
			// Exception when there's no MD5 implementation!
			System.err.println("No MD5 algorithm on the default packages.");
			e.printStackTrace();
		}
		return ans.toString();
	}
	
}
