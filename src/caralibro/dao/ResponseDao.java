package caralibro.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import caralibro.model.constants.Facebook;

public class ResponseDao {

	public static String get(Map<String,String> param) throws Exception {
		return get(getParamAsString(param));
	}

	public static String get(String param) throws Exception {
		String url = Facebook.REST_SERVER;
		String responseBody = "";
		HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
		httpUrlConnection.setRequestMethod("POST");
		httpUrlConnection.setDoInput(true);
	    httpUrlConnection.setDoOutput(true);
	    httpUrlConnection.connect();
	    //System.out.println("Request: " + url + "?" + param);
	    OutputStream outputStream = httpUrlConnection.getOutputStream();
	    outputStream.write(param.getBytes("UTF-8"));
	    InputStream inputStream = httpUrlConnection.getInputStream();
	    if (httpUrlConnection.getResponseCode() != 200) {
	    	System.out.println("Request " + url + "?" + param + " responde code is not 200.");
	    	throw new Exception("Response code must be 200.");
	    }
	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	    	responseBody += line;
	    }
	    outputStream.close();
	    inputStream.close();
	    httpUrlConnection.disconnect();
		//System.out.println("Response: " + responseBody);
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
