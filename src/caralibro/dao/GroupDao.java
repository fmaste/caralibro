package caralibro.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import caralibro.Rest;
import caralibro.factory.GroupFactory;
import caralibro.factory.RequestFactory;
import caralibro.model.constants.Facebook;
import caralibro.model.data.Application;
import caralibro.model.data.Group;
import caralibro.model.data.Session;

public class GroupDao {
	
	public static Collection<Group> getFromUser(Application application, Session session) throws Exception {
		Map<String,Group> groupsMap = getFromUserByName(application, session);
		return groupsMap.values();
	}
	
	public static Map<String,Group> getFromUserByName(Application application, Session session) throws Exception {
		Map<String,String> params = RequestFactory.create(application, session, "Groups.get");
		RequestFactory.sign(params, application, session);
		String groupsJsonResponse = Rest.makeRequest(Facebook.REST_SERVER, params);
		// Warning: If there are no groups the response is like this
		// Response: {}
		if (groupsJsonResponse == null || groupsJsonResponse.isEmpty() || !groupsJsonResponse.startsWith("[")) {
			return null;
		}
		Map<String,Group> groups = new HashMap<String,Group>();
		JSONArray groupsJsonArray = new JSONArray(groupsJsonResponse);
		for (int i = 0; i < groupsJsonArray.length(); i++) {
			// The group index is retrieved as a String and GroupFactory must know how to handle it!
			String groupString = groupsJsonArray.optString(i);
			if (groupString != null && !groupString.isEmpty()) {
				Group group = GroupFactory.create(groupString);
				if (group != null) {
					groups.put(group.getName(), group);
				}
			}
		}
		return groups;
	}
}
