package caralibro.factory;

import org.json.JSONObject;
import caralibro.model.data.Comment;
import caralibro.model.data.User;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class CommentFactory {

	public static Comment create(String id, User user, String text, Long creationTime) {
		Comment comment = new Comment();
		comment.setId(id);
		comment.setUser(user);
		comment.setText(text);
		comment.setCreationTime(creationTime);
		return comment;
	}
	
	public static Comment create(String commentJsonResponse) throws Exception {
		if (commentJsonResponse == null || commentJsonResponse.isEmpty() || !commentJsonResponse.startsWith("{")) {
			return null;
		}
		// TODO: Check for this keys or keep it like an exception ?
		JSONObject commentJsonObject = new JSONObject(commentJsonResponse);
		String id = commentJsonObject.getString("id");
		String text = commentJsonObject.getString("text");
		User user = UserFactory.create(commentJsonObject.getLong("fromid"));
		Long time = commentJsonObject.getLong("time");
		return create(id, user, text, time);
	}
	
}
