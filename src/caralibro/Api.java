package caralibro;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import caralibro.model.Application;
import caralibro.model.Page;
import caralibro.model.Session;
import caralibro.model.User;


public class Api {
	private static final String REST_SERVER = "http://api.facebook.com/restserver.php";
	private Application application;
	
	public Api(Application application) {
		this.application = application;
	}
	
	public String getAppUrl() {
		return "http://www.facebook.com/developers/apps.php?app_id=" + application.getId();
	}
	
	@Deprecated
	public String generateLoginUrl() throws Exception {
		return getLoginUrl(generateAuthToken());
	}
	
	@Deprecated
	public String generateLoginUrl(String extendedPermissions) throws Exception {
		return getLoginUrl(generateAuthToken(), extendedPermissions);
	}
	
	public String getLoginUrl(String authToken) {
		return "http://www.facebook.com/login.php?api_key=" + application.getKey() + "&v=1.0" + "&auth_token=" + authToken;
	}
	
	public String getLoginUrl(String authToken, String extendedPermissions) {
		return getLoginUrl(authToken) + "&v=1.0&req_perms=" + extendedPermissions; // read_stream,publish_stream,offline_access
	}
	
	public String generateAuthToken() throws Exception {
		Map<String,String> params = Utils.initParams(application, "Auth.createToken");
		Utils.finalizeParams(params, application);
		String authToken = Rest.makeRequest(REST_SERVER, params);
		// System.err.println("JSON Auth token:" + authToken);
		// FIXME: Use JSONString. This is taking out the \" from the JSONString
		authToken = authToken.substring(1, authToken.length() - 1);
		// System.err.println("Auth token: " + authToken);	
		return authToken;
	}
	
	public Session getSession(String authToken) throws Exception {
		Map<String,String> params = Utils.initParams(application, "Auth.getSession");
		params.put("auth_token", authToken);
		params.put("generate_session_secret", "1");
		Utils.finalizeParams(params, application);
		String sessionResponse = Rest.makeRequest(REST_SERVER, params);
		JSONObject sessionJson = null;
		try {
			sessionJson = new JSONObject(sessionResponse);
			String sessionKey = sessionJson.getString("session_key");
			Long userId = sessionJson.getLong("uid");
			Long expires = sessionJson.getLong("expires");
			String sessionSecret = sessionJson.getString("secret");
			// I don't need this!
			// String baseDomain = sessionJson.getString("base_domain");
			return new Session(sessionKey, sessionSecret, new User(userId), expires);
		} catch (JSONException e) {
			System.err.println("Invalid session response: " + sessionResponse);
			e.printStackTrace();
		}
		return null;
	}
	
	// Prompt extended permissions
	// http://wiki.developers.facebook.com/index.php/Extended_permissions
	// http://www.facebook.com/connect/prompt_permissions.php
	
	public String getFriendsLists(Session session) throws Exception {
		Map<String,String> params = Utils.initParams(application, session, "Friends.getLists");
		Utils.finalizeParams(params, application);
		String friendsStr = Rest.makeRequest(REST_SERVER, params);
		System.out.println("Your friends: " + friendsStr);
		return friendsStr;
	}

	// You can only make 100 calls to stream per 600 seconds.
	
	public Map<String,Page> getFanPages(Session session) throws Exception {
		Map<String,String> params = Utils.initParams(application, session, "Pages.getInfo");
		params.put("fields", "page_id,name");
		Utils.finalizeParams(params, application);
		String fanPagesJSON = Rest.makeRequest(REST_SERVER, params);
		JSONArray fanPages = new JSONArray(fanPagesJSON);
		Map<String,Page> ans = new HashMap<String,Page>();
		for (int i = 0; i < fanPages.length(); i++) {
			JSONObject fanPage = fanPages.getJSONObject(i);
			String name = fanPage.getString("name");
			ans.put(name, new Page(fanPage.getLong("page_id"),name));
		}
		return ans;
	}
	
	// Before you can get data from a user's stream, you need the extended permission read_stream.
	public String getStream(Session session, String source) throws Exception {
		Map<String,String> params = Utils.initParams(application, session, "Stream.get");
		params.put("source_ids", source);
		Utils.finalizeParams(params, application);
		String streamsJSON = Rest.makeRequest(REST_SERVER, params);
		return streamsJSON;
	}
	
}
