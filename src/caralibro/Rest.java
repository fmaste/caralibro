package caralibro;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Rest {
	
	public static String makeRequest(String url, Map<String,String> param) throws Exception {
		return makeRequest(url, getParamAsString(param));
	}
	
	public static String makeRequest(String url, String param) throws Exception {
		String responseBody = "";
		HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
		httpUrlConnection.setRequestMethod("POST");
		httpUrlConnection.setDoInput(true);
	    httpUrlConnection.setDoOutput(true);
	    httpUrlConnection.connect();
	    OutputStream outputStream = httpUrlConnection.getOutputStream();
	    outputStream.write(param.getBytes("UTF-8"));
	    InputStream inputStream = httpUrlConnection.getInputStream();
	    if (httpUrlConnection.getResponseCode() != 200) {
	    	System.err.println("Request " + url + "?" + param + " responde code is not 200.");
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
	    System.err.println("Request: " + url + "?" + param);
		System.err.println("Response: " + responseBody);
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
