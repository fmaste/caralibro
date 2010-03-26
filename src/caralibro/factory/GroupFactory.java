package caralibro.factory;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import caralibro.model.data.Group;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class GroupFactory {
	private static final Logger logger = LoggerFactory.getLogger(GroupFactory.class);
	
	public static Group create(Long id, String name) {
		Group group = new Group();
		group.setId(id);
		group.setName(name);
		return group;
	}
	
	/*
	 * Parses a JSON string containing the Group and creates the Group object.
	 * If there is no gid or name key an exception is thrown.
	 * 
	 * @param postJsonResponse 	One of the groups retrieved on the Groups JSON array.
	 * @return 					A Group object or null if the string is not a JSON object.
	 */
	public static Group create(String groupJsonResponse) throws Exception {
		logger.debug("Parsing JSON encoded Group: \"" + groupJsonResponse + "\"");
		if (groupJsonResponse == null || groupJsonResponse.isEmpty() || !groupJsonResponse.startsWith("{")) {
			logger.error("Not a valid JSON encoded Group: \"" + groupJsonResponse + "\"");
			return null;
		}
		JSONObject groupJsonObject = new JSONObject(groupJsonResponse);
		String name = groupJsonObject.getString("name");
		Long id = groupJsonObject.getLong("gid");		
		return GroupFactory.create(id, name);		
	}
	
	/*
	 * @return 		The group's Facebook page.
	 */
	public static String createUrl(Group group) {
		if (group != null && group.getId() != null ) {
			return "http://www.facebook.com/group.php?gid=" + group.getId();
		} else {
			return null;
		}
	}
	
}
