package caralibro.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import caralibro.factory.GroupFactory;
import caralibro.model.data.Application;
import caralibro.model.data.Group;
import caralibro.model.data.Session;
import caralibro.rest.Request;
import caralibro.rest.Response;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class GroupDao {
	private static final Logger logger = LoggerFactory.getLogger(GroupDao.class);
	
	/*
	 * @return 		If there are no groups returns null or empty
	 */
	public static Collection<Group> getFromUser(Application application, Session session) throws Exception {
		Map<String,Group> groupsMap = getFromUserByName(application, session);
		return groupsMap.values();
	}
	
	/*
	 * @return If there are no groups returns null or empty
	 */
	public static Map<String,Group> getFromUserByName(Application application, Session session) throws Exception {
		logger.debug("Retrieving Groups from session \"" + session.getKey() + "\".");
		Map<String,String> params = Request.create(application, session, "Groups.get");
		Request.sign(params, application, session);
		String groupsJsonResponse = Response.get(params);
		// Warning: If there are no groups the response is like this
		// Response: {}
		if (groupsJsonResponse == null || groupsJsonResponse.isEmpty() || !groupsJsonResponse.startsWith("[")) {
			return null;
		}
		Map<String,Group> groups = new HashMap<String,Group>();
		JSONArray groupsJsonArray = new JSONArray(groupsJsonResponse);
		for (int i = 0; i < groupsJsonArray.length(); i++) {
			// The Group is retrieved as a String and GroupFactory must know how to handle it!
			String groupString = groupsJsonArray.optString(i);
			if (groupString != null && !groupString.isEmpty()) {
				Group group = null;
				try {
					group = GroupFactory.create(groupString);
				} catch (Exception e) {
					group = null;
					logger.error("Not a valid JSON encoded Group: \"" + groupString + "\".");
					e.printStackTrace();
				}
				if (group != null) {
					groups.put(group.getName(), group);
				}
			}
		}
		return groups;
	}
}
