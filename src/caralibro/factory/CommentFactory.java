package caralibro.factory;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import caralibro.model.data.stream.Comment;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class CommentFactory {
	private static final Logger logger = LoggerFactory.getLogger(CommentFactory.class);
	
	public static Comment create(String id, Long authorId, String text, Long creationTime) {
		Comment comment = new Comment();
		comment.setId(id);
		comment.setAuthorId(authorId);
		comment.setText(text);
		comment.setCreationTime(creationTime);
		return comment;
	}
	
	/*
	 * Parses a JSON string containing the comment and creates the comment object.
	 * If there is no id, message, text, fromid or time key an exception is thrown.
	 * 
	 * @param postJsonResponse 	One of the comments retrieved on the comments JSON array.
	 * @return 					A comment object or null if the string is not a JSON object.
	 */
	public static Comment create(String commentJsonResponse) throws Exception {
		logger.debug("Parsing JSON encoded Comment: \"" + commentJsonResponse + "\"");
		if (commentJsonResponse == null || commentJsonResponse.isEmpty() || !commentJsonResponse.startsWith("{")) {
			logger.error("Not a valid JSON encoded Comment: \"" + commentJsonResponse + "\"");
			return null;
		}
		JSONObject commentJsonObject = new JSONObject(commentJsonResponse);
		String id = commentJsonObject.getString("id");
		String text = commentJsonObject.getString("text");
		Long authorId = commentJsonObject.getLong("fromid");
		Long time = commentJsonObject.getLong("time");
		return create(id, authorId, text, time);
	}
	
}
