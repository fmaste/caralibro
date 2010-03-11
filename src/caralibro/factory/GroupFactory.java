package caralibro.factory;

import org.json.JSONObject;

import caralibro.model.data.Group;

public class GroupFactory {
	
	public static Group create(Long id, String name) {
		Group group = new Group();
		group.setId(id);
		group.setName(name);
		return group;
	}
	
	public static Group create(String groupJsonResponse) throws Exception {
		if (groupJsonResponse == null || groupJsonResponse.isEmpty() || !groupJsonResponse.startsWith("{")) {
			return null;
		}
		JSONObject groupJsonObject = new JSONObject(groupJsonResponse);
		String name = groupJsonObject.optString("name", null);
		Long id = null;
		if (groupJsonObject.has("gid")) {
			id = groupJsonObject.getLong("gid");
		} else {
			if (name == null || name.isEmpty()) {
				// Has no id and no name!!
				return null;
			}
		}
		return GroupFactory.create(id, name);		
	}
	
	public static String createUrl(Group group) {
		return "http://www.facebook.com/group.php?gid=" + group.getId();
	}
}
