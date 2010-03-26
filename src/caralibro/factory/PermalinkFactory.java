package caralibro.factory;

import caralibro.model.data.Group;
import caralibro.model.data.Page;
import caralibro.model.data.User;
import caralibro.model.data.stream.Comment;
import caralibro.model.data.stream.Post;

/*
 * Only the posts have a permalink.
 * 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */
public class PermalinkFactory {

	private static boolean isValid(Post post) {
		if (post == null || post.getId() == null || !post.getId().matches("^\\d+_\\d+$")) {			
			return false;
		} else {
			return true;
		}
	}
	
	private static boolean isValid(Comment comment) {
		if (comment == null || comment.getId() == null || !comment.getId().matches("^\\d+_\\d+_\\d+$")) {			
			return false;
		} else {
			return true;
		}	
	}

	private static String create(Page source, String postIdPart) {
		if (source == null || source.getUsername() == null || source.getUsername().isEmpty()) {
			return null;
		}
		return "http://www.facebook.com/" + source.getUsername() + "?v=feed&story_fbid=" + postIdPart;
	}

	private static String create(Group source, String postIdPart) {
		if (source == null || source.getId() == null) {
			return null;
		}
		return "http://www.facebook.com/group.php?v=feed&story_fbid=" + postIdPart + "&gid=" + source.getId();
	}
	
	private static String create(User source, String postIdPart) {
		if (source == null || source.getId() == null) {
			return null;
		}
		return "http://www.facebook.com/profile.php?v=feed&story_fbid=" + postIdPart + "&id=" + source.getId();
	}
	
	public static String create(Page source, Post post) {
		if (!isValid(post)) {
			return null;
		}
		return create(source, post.getId().split("_")[1]);
	}

	public static String create(Group source, Post post) {
		if (!isValid(post)) {
			return null;
		}
		return create(source, post.getId().split("_")[1]);
	}
	
	public static String create(User source, Post post) {
		if (!isValid(post)) {
			return null;
		}
		return create(source, post.getId().split("_")[1]);
	}
	
	// TODO:
	// public static String create(Application source, Post post) {
	// }
	
	public static String create(Page source, Comment comment) {
		if (!isValid(comment)) {
			return null;
		}
		return create(source, comment.getId().split("_")[1]);
	}
	
	public static String create(Group source, Comment comment) {
		if (!isValid(comment)) {
			return null;
		}
		return create(source, comment.getId().split("_")[1]);		
	}

	public static String create(User source, Comment comment) {
		if (!isValid(comment)) {
			return null;
		}
		return create(source, comment.getId().split("_")[1]);	
	}

	// TODO
	//public static String create(Application source, Comment comment) {
	//}
	
//	public static String create(Application application) {
//		if (application == null || application.getId() == null) {
//			return null;
//		}
//		// For the app developer is "http://www.facebook.com/developers/apps.php?app_id=" + application.getId();
//		return "http://www.facebook.com/apps/application.php?id=" + application.getId();
//	}
	
}
