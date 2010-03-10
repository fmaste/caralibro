package caralibro.factory;

import caralibro.model.Group;

public class GroupFactory {
	
	public static Group create(Long id, String name) {
		Group group = new Group();
		group.setId(id);
		group.setName(name);
		return group;
	}
	
	public static String createUrl(Group group) {
		return "http://www.facebook.com/group.php?gid=" + group.getId();
	}
}
