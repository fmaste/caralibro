package caralibro.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import caralibro.Rest;
import caralibro.factory.GroupFactory;
import caralibro.factory.PageFactory;
import caralibro.factory.RequestFactory;
import caralibro.model.Application;
import caralibro.model.Group;
import caralibro.model.Page;
import caralibro.model.Session;
import caralibro.model.constants.Facebook;

public class GroupDao {
	
	public static Collection<Group> getFromUser(Application application, Session session) throws Exception {
		Map<String,Group> groupsMap = getFromUserByName(application, session);
		return groupsMap.values();
	}
	
	public static Map<String,Group> getFromUserByName(Application application, Session session) throws Exception {
		Map<String,String> params = RequestFactory.create(application, "groups.get");
		params.put("session_key", session.getKey());
		//params.put("fields", "group_id,name"); // group_id is always returned (whether included in fields or not, and always as the first subelement)
		RequestFactory.sign(params, application, session);
		String jsonResponse = Rest.makeRequest(Facebook.REST_SERVER, params);
		JSONArray jsonGroupsArray = new JSONArray(jsonResponse);
		Map<String,Group> ans = new HashMap<String,Group>();
		for (int i = 0; i < jsonGroupsArray.length(); i++) {
			JSONObject jsonGroupObject = jsonGroupsArray.getJSONObject(i);
			String name = jsonGroupObject.getString("name");
			ans.put(name, GroupFactory.create(jsonGroupObject.getLong("gid"),name));
		}
		return ans;
	}
}
