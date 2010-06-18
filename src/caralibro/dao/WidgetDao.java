package caralibro.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import caralibro.factory.CommentFactory;
import caralibro.model.data.Application;
import caralibro.model.data.stream.Comment;
import caralibro.rest.Request;
import caralibro.rest.Response;

/*
 * Widgets operates on an xid, which uniquely identifies a set of comments for a specific application.
 * XIDs are application dependent, not session dependent.
 */
public class WidgetDao {
	private static final Logger logger = LoggerFactory.getLogger(WidgetDao.class);

	/*
	 * Retrieve comments from xid.
	 * Widget comments have no permalink.
	 * 
	 * @param xid		The xid.
	 * @return 			If there are no comments returns null or empty.
	 */
	public static Collection<Comment> getFromXid(Application application, String xid) throws Exception {
		logger.debug("Retrieving Comment from xid: \"" + xid + "\".");
		Map<String,String> params = Request.create(application, "Fql.query");
		params.put("query", "SELECT id, fromid, text, time FROM comment WHERE xid = '" + xid + "'");
		Request.sign(params, application);
		Collection<Comment> comments = parseReponse(Response.get(params));
		// Add the post permalink to the comment!
		if (comments != null) {
			for (Comment comment : comments) {
				comment.setPermaLink("");
			}
		}
		return comments;
	}

	private static Collection<Comment> parseReponse(String commentsJsonResponse) throws Exception {
		logger.debug("Parsing JSON encoded Comments: \"" + commentsJsonResponse + "\".");
		// Warning: If there are no comments the response is like this
		// Response: {}
		if (commentsJsonResponse == null || commentsJsonResponse.isEmpty() || !commentsJsonResponse.startsWith("[")) {
			return null;
		}
		Collection<Comment> comments = new ArrayList<Comment>();
		JSONArray commentsJsonArray = new JSONArray(commentsJsonResponse);
		for (int i = 0; i < commentsJsonArray.length(); i++) {
			// The Comment is retrieved as a String and CommentFactory must know how to handle it!
			String commentString = commentsJsonArray.optString(i);
			if (commentString != null && !commentString.isEmpty()) {
				Comment comment = null;
				try {
					comment = CommentFactory.create(commentString);
				} catch (Exception e) {
					comment = null;
					logger.error("Skipping invalid JSON encoded Comment: \"" + commentString + "\".");
					e.printStackTrace();
				}
				if (comment != null) {
					comments.add(comment);
				}
			}			
		}
		return comments;
	}

	public static boolean removeFromXid(Application application, String xid, Comment comment) throws Exception {
		logger.debug("Removing Comment " + comment.getId());
		Map<String,String> params = Request.create(application, "Comments.remove");
		params.put("xid", xid);
		params.put("comment_id", comment.getId());
		// Desktop applications must pass a valid session key, and only comments made by the user can be removed by that user. 
		// When using the app secret, an application may remove any of its comments.
		Request.sign(params, application);
		String response = Response.get(params);
		if (response != null && !response.isEmpty() && response.equalsIgnoreCase("true")) {			
			logger.debug("Comment " + comment.getId() + " was removed succesfully.");
			return true;
		} else {
			logger.error("Comment " + comment.getId() + " was not removed, response was: \"" + response + "\".");
			return false;
		}
	}

}
