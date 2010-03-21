package caralibro.factory;

import org.json.JSONObject;
import caralibro.model.data.User;
import caralibro.model.data.stream.Comment;

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
	
	/*
	 * Parses a json string containing the comment and creates the comment object.
	 * If there is no id, message, text, fromid or time an exception is thrown.
	 * 
	 * @param postJsonResponse 	One of the comments retrieved on the comments json array.
	 * @return 					A comment object or null if the string is not a json object.
	 */
	public static Comment create(String commentJsonResponse) throws Exception {
		if (commentJsonResponse == null || commentJsonResponse.isEmpty() || !commentJsonResponse.startsWith("{")) {
			return null;
		}
		JSONObject commentJsonObject = new JSONObject(commentJsonResponse);
		String id = commentJsonObject.getString("id");
		String text = commentJsonObject.getString("text");
		User user = UserFactory.create(commentJsonObject.getLong("fromid"));
		Long time = commentJsonObject.getLong("time");
		return create(id, user, text, time);
	}
	
}
