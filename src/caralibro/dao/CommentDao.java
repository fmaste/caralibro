package caralibro.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import caralibro.factory.CommentFactory;
import caralibro.factory.RequestFactory;
import caralibro.model.data.Application;
import caralibro.model.data.Session;
import caralibro.model.data.stream.Comment;
import caralibro.model.data.stream.Post;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class CommentDao {
	private static final Logger logger = LoggerFactory.getLogger(CommentDao.class);
	
	/*
	 * Retrieve comments from source
	 * 
	 * @return If there are no comments returns null or empty
	 */
	public static Collection<Comment> get(Application application, Session session, String sourceId, Long startTime, Long endTime) throws Exception {
		String startTimeAsString = "0";
		if (startTime != null) {
			startTimeAsString = startTime.toString();
		}
		String endTimeAsString = null;
		if (endTime != null) {
			endTimeAsString = endTime.toString();
		}
		Map<String,String> params = RequestFactory.create(application, session, "Fql.multiquery");
		String posts = "\"posts\":\"SELECT post_id FROM stream WHERE source_id = " + sourceId + " LIMIT 100\"";
		String comments = "\"comments\":\"SELECT id, fromid, text, time FROM comment WHERE post_id IN (SELECT post_id FROM #posts) AND time > " + startTimeAsString;
		if (endTimeAsString != null) {
			comments = comments + " AND time < " + endTimeAsString;
		}
		comments = comments + "\"";
		String multiQuery = "{" + posts + "," + comments + "}";
		params.put("queries", multiQuery);
		RequestFactory.sign(params, application, session);
		String queriesJsonResponse = ResponseDao.get(params);
		if (queriesJsonResponse == null || queriesJsonResponse.isEmpty() || !queriesJsonResponse.startsWith("[")) {
			return null;
		}
		JSONArray queriesJsonArray = new JSONArray(queriesJsonResponse);
		for (int i = 0; i < queriesJsonArray.length(); i++) {
			JSONObject queryJsonObject = queriesJsonArray.optJSONObject(i);
			if (queryJsonObject != null) {
				String name = queryJsonObject.optString("name", "");
				if (name.equals("comments")) {
					JSONArray commentsJsonArray = queryJsonObject.optJSONArray("fql_result_set");
					if (commentsJsonArray != null) {
						return parseReponse(commentsJsonArray.toString());
					} else {
						return null;
					}
				}
			}
		}
		return null;
	}
	
	/*
	 * @return If there are no comments returns null or empty
	 */
	public static Collection<Comment> getFromPost(Application application, Session session, Post post) throws Exception {
		Map<String,String> params = RequestFactory.create(application, session, "Stream.getComments");
		params.put("post_id", post.getId());
		RequestFactory.sign(params, application, session);
		return parseReponse(ResponseDao.get(params));
	}

	private static Collection<Comment> parseReponse(String commentsJsonResponse) throws Exception {
		// Warning: If there are no comments the response is like this
		// Response: {}
		if (commentsJsonResponse == null || commentsJsonResponse.isEmpty() || !commentsJsonResponse.startsWith("[")) {
			return null;
		}
		Collection<Comment> comments = new ArrayList<Comment>();
		JSONArray commentsJsonArray = new JSONArray(commentsJsonResponse);
		for (int i = 0; i < commentsJsonArray.length(); i++) {
			// The comment is retrieved as a String and CommentFactory must know how to handle it!
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
		logger.debug("Removing comment " + comment.getId());
		Map<String,String> params = RequestFactory.create(application, session, "Stream.removeComment");
		params.put("comment_id", comment.getId());
		RequestFactory.sign(params, application, session);
		String response = ResponseDao.get(params);
		if (response != null && !response.isEmpty() && response.equalsIgnoreCase("true")) {			
			logger.debug("Comment " + comment.getId() + " was removed succesfully");
			return true;
		} else {
			logger.error("Comment " + comment.getId() + " was not removed, response was: " + response);
			return false;
		}
	}
	
}
