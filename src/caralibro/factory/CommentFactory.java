package caralibro.factory;

import org.json.JSONObject;

import caralibro.model.Comment;
import caralibro.model.User;

public class CommentFactory {

	public static Comment create(String id, String text, User user, Long time) {
		Comment comment = new Comment();
		comment.setId(id);
		comment.setText(text);
		comment.setUser(user);
		comment.setTime(time);
		return comment;
	}
	
	public static Comment createComment(String commentJsonResponse) throws Exception {
		JSONObject commentJsonObject = new JSONObject(commentJsonResponse);
		String id = commentJsonObject.getString("id");
		String text = commentJsonObject.getString("text");
		User user = UserFactory.create(commentJsonObject.getLong("fromid"));
		Long time = commentJsonObject.getLong("time");
		return create(id, text, user, time);
	}
	
}
