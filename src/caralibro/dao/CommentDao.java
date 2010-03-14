package caralibro.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import caralibro.factory.CommentFactory;
import caralibro.factory.RequestFactory;
import caralibro.model.data.Application;
import caralibro.model.data.Comment;
import caralibro.model.data.Post;
import caralibro.model.data.Session;

public class CommentDao {

	/*
	 * @return If there are no comments returns null or empty
	 */
	public static Collection<Comment> getFromPost(Application application, Session session, Post post) throws Exception {
		Map<String,String> params = RequestFactory.create(application, session, "Stream.getComments");
		params.put("post_id", post.getId());
		return makeRequest(application, session, params);
	}
	
	/*
	 * Retrieve comments recently created ìn posts whose author is the Fan Page (Ex: Mauricio Macri)
	 * 
	 * @return If there are no comments returns null or empty
	 */
	public static Collection<Comment> get(Application application, Session session, Long startTime, String sourceId) throws Exception {
		Map<String,String> params = RequestFactory.create(application, session, "fql.query");
		String query = "SELECT id, fromid, text, time FROM comment " +
						"WHERE post_id IN " +
							"(SELECT post_id FROM stream WHERE source_id = " + sourceId + " AND actor_id = " + sourceId + " LIMIT 2000) " +
						"AND time > " + startTime;
		params.put("query", query);
		return makeRequest(application, session, params);
	}
	
	private static Collection<Comment> makeRequest(Application application, Session session, Map<String, String> params) throws Exception {
		RequestFactory.sign(params, application, session);
		String commentsJsonResponse = ResponseDao.get(params);
		// Warning: If there are no comments the response is like this
		// Response: {}
		if (commentsJsonResponse == null || commentsJsonResponse.isEmpty() || !commentsJsonResponse.startsWith("[")) {
			return null;
		}
		Collection<Comment> comments = new ArrayList<Comment>();
		JSONArray commentsJsonArray = new JSONArray(commentsJsonResponse);
		for (int i = 0; i < commentsJsonArray.length(); i++) {
			// The comment index is retrieved as a String and CommentFactory must know how to handle it!
			String commentString = commentsJsonArray.optString(i);
			if (commentString != null && !commentString.isEmpty()) {
				Comment comment = CommentFactory.create(commentString);
				if (comment != null) {
					comments.add(comment);
				}
			}			
		}
		return comments;
	}
	
	public static boolean remove(Application application, Session session, Comment comment) throws Exception {
		Map<String,String> params = RequestFactory.create(application, session, "Stream.removeComment");
		params.put("comment_id", comment.getId());
		RequestFactory.sign(params, application, session);
		String response = ResponseDao.get(params);
		if (response != null && !response.isEmpty() && response.equals("true")) {
			return true;
		} else {
			return false;
		}
	}
	
}
