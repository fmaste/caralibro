package caralibro.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import caralibro.model.constants.Facebook;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class Response {
	private static final Logger logger = LoggerFactory.getLogger(Response.class);
	
	public static String get(Map<String,String> param) throws Exception {
		return get(getParamAsString(param));
	}

	public static String get(String param) throws Exception {
		logger.debug("Request: \"" + param + "\".");
		String url = Facebook.REST_SERVER;
		String responseBody = "";
		HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
		httpUrlConnection.setRequestMethod("POST");
		httpUrlConnection.setDoInput(true);
	    httpUrlConnection.setDoOutput(true);
	    httpUrlConnection.connect();
	    OutputStream outputStream = httpUrlConnection.getOutputStream();
	    outputStream.write(param.getBytes("UTF-8"));
	    if (httpUrlConnection.getResponseCode() != 200) {
	    	logger.error("Response code " + httpUrlConnection.getResponseCode() + " must be 200.");
	    	logger.error("Response message: \"" + httpUrlConnection.getResponseMessage() + "\"");
	    	throw new Exception("Response code must be 200.");
	    }
	    InputStream inputStream = httpUrlConnection.getInputStream();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	    	responseBody += line;
	    }
	    outputStream.close();
	    inputStream.close();
	    httpUrlConnection.disconnect();
	    logger.debug("Response: \"" + responseBody + "\".");
	    return responseBody;
	}
	
	private static String getParamAsString(Map<String,String> params) {
		String ans = "";
		boolean firstParam = true;
		for (Map.Entry<String,String> entry : params.entrySet()) {
			if (firstParam) {
				firstParam = false;
			}else{
				ans = ans + "&";
			}
			ans = ans + entry.getKey() + "=" + entry.getValue();
		}
		return ans;
	}
	
}
