package caralibro.factory;

import caralibro.model.data.Application;
import caralibro.model.data.Group;
import caralibro.model.data.Page;
import caralibro.model.data.User;
import caralibro.model.data.stream.Post;

/*
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */
public class PermalinkFactory {

	private boolean isValidPost(Post post) {
		if (post == null || post.getId() == null || !post.getId().contains("_") || post.getId().startsWith("_") || post.getId().endsWith("_")) {
			return false;
		} else {
			return true;
		}
	}
	
	public String create(Post post, Page source) {
		if (!isValidPost(post)) {
			return null;
		}
		if (source == null || source.getUsername() == null || source.getUsername().isEmpty()) {
			return null;
		}
		return "http://www.facebook.com/" + source.getUsername() + "?v=feed&story_fbid=" + post.getId().split("_")[1];
	}

	public String create(Post post, Group source) {
		if (!isValidPost(post)) {
			return null;
		}
		if (source == null || source.getId() == null) {
			return null;
		}
		return "http://www.facebook.com/group.php?v=feed&story_fbid=" + post.getId().split("_")[1] + "&gid=" + source.getId();
	}
	
	public String create(Post post, User source) {
		if (!isValidPost(post)) {
			return null;
		}
		if (source == null || source.getId() == null) {
			return null;
		}
		return "http://www.facebook.com/profile.php?v=feed&story_fbid=" + post.getId().split("_")[1] + "&id=" + source.getId();
	}
	
	// TODO:
	// public String create(Post post, Application source) {
	// }
	
	public static String create(Page page) {
		// TODO:
		return null;
	}
	
	public static String create(Group group) {
		// TODO:
		return null;		
	}

	public static String create(User user) {
		// TODO:
		return null;		
	}
	
	public static String create(Application application) {
		if (application == null || application.getId() == null) {
			return null;
		}
		// For the app developer is "http://www.facebook.com/developers/apps.php?app_id=" + application.getId();
		return "http://www.facebook.com/apps/application.php?id=" + application.getId();
	}
	
}
