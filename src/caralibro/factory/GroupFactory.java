package caralibro.factory;

import org.json.JSONObject;
import caralibro.model.data.Group;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class GroupFactory {
	
	public static Group create(Long id, String name) {
		Group group = new Group();
		group.setId(id);
		group.setName(name);
		return group;
	}
	
	/*
	 * Parses a json string containing the group and creates the group object.
	 * If there is no gid or name an exception is thrown.
	 * 
	 * @param postJsonResponse 	One of the groups retrieved on the groups json array.
	 * @return 					A group object or null if the string is not a json object.
	 */
	public static Group create(String groupJsonResponse) throws Exception {
		if (groupJsonResponse == null || groupJsonResponse.isEmpty() || !groupJsonResponse.startsWith("{")) {
			return null;
		}
		JSONObject groupJsonObject = new JSONObject(groupJsonResponse);
		String name = groupJsonObject.getString("name");
		Long id = groupJsonObject.getLong("gid");		
		return GroupFactory.create(id, name);		
	}
	
	/*
	 * @return The group's Facebook page.
	 */
	public static String createUrl(Group group) {
		if (group != null && group.getId() != null ) {
			return "http://www.facebook.com/group.php?gid=" + group.getId();
		} else {
			return null;
		}
	}
	
}
