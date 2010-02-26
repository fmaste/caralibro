package caralibro.factory;

import org.json.JSONObject;

import caralibro.model.Comment;
import caralibro.model.User;

public class CommentFactory {

	public static Comment createComment(String id, String text, User user, Long creationTime) {
		Comment comment = new Comment();
		comment.setId(id);
		comment.setText(text);
		comment.setUser(user);
		comment.setCreationTime(creationTime);
		return comment;
	}
	
	public static Comment createComment(String commentJsonResponse) throws Exception {
		Comment comment = new Comment();
		JSONObject commentJsonObject = new JSONObject(commentJsonResponse);
		comment.setId(commentJsonObject.getString("id"));
		comment.setText(commentJsonObject.getString("text"));
		comment.setUser(UserFactory.createUser(commentJsonObject.getLong("fromid")));
		comment.setCreationTime(commentJsonObject.getLong("time"));
		return comment;
	}
	
}
