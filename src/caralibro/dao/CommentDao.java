package caralibro.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import caralibro.factory.CommentFactory;
import caralibro.model.data.Application;
import caralibro.model.data.Session;
import caralibro.model.data.stream.Comment;
import caralibro.model.data.stream.Post;
import caralibro.rest.Request;
import caralibro.rest.Response;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class CommentDao {
	private static final Logger logger = LoggerFactory.getLogger(CommentDao.class);

	/*
	 * Retrieve Comments from source's last 1000 posts. Much more Posts and Facebook may not be able to handle the request.
	 * Use this method to make updates frequently. To make a complete initial dump, use PostDao.getPosts() in combination with getFromPost().
	 * 
	 * @param startTime		Unix time in seconds (not milliseconds).
	 * @param endTime		Unix time in seconds (not milliseconds).
	 * @return 				If there are no Comments returns null or empty.
	 */
	public static Collection<Comment> get(Application application, Session session, String sourceId, Long startTime, Long endTime) throws Exception {
		String debugMsg = "Retrieving Comments from " + sourceId + " starting from ";
		String startTimeAsString = "0";
		if (startTime != null) {
			startTimeAsString = startTime.toString();
			debugMsg = debugMsg + (new Date(startTime*1000)).toString() + " (" + startTime + ")";
		} else {
			debugMsg = debugMsg + "the begining";
		}
		debugMsg = debugMsg + " up to ";
		String endTimeAsString = null;
		if (endTime != null) {
			endTimeAsString = endTime.toString();
			debugMsg = debugMsg + (new Date(endTime*1000)).toString() + " (" + endTime + ")";
		} else {
			debugMsg = debugMsg + "now";
		}
		debugMsg = debugMsg + ".";
		logger.debug(debugMsg);
		Map<String,String> params = Request.create(application, session, "Fql.multiquery");
		String postsFql = "\"posts\":\"SELECT post_id FROM stream WHERE source_id = " + sourceId + " LIMIT 1000\"";
		String postCommentsFql = "\"comments\":\"SELECT id, fromid, text, time FROM comment WHERE post_id IN (SELECT post_id FROM #posts) AND time > " + startTimeAsString;
		if (endTimeAsString != null) {
			postCommentsFql = postCommentsFql + " AND time < " + endTimeAsString;
		}
		postCommentsFql = postCommentsFql + "\"";
		String multiQuery = "{" + postsFql + "," + postCommentsFql + "}";
		params.put("queries", multiQuery);
		Request.sign(params, application, session);
		String queriesJsonResponse = Response.get(params);
		if (queriesJsonResponse == null || queriesJsonResponse.isEmpty() || !queriesJsonResponse.startsWith("[")) {
			return null;
		}
		Collection<Comment> comments = null;
		JSONArray queriesJsonArray = new JSONArray(queriesJsonResponse);
		for (int i = 0; i < queriesJsonArray.length(); i++) {
			JSONObject queryJsonObject = queriesJsonArray.optJSONObject(i);
			if (queryJsonObject != null) {
				String name = queryJsonObject.optString("name", "");
				if (name.equals("comments")) {
					JSONArray commentsJsonArray = queryJsonObject.optJSONArray("fql_result_set");
					if (commentsJsonArray != null) {
						comments = parseReponse(commentsJsonArray.toString());
					} else {
						return null;
					}
				}
			}
		}
		return comments;
	}

	/*
	 * Retrieve comments from post.
	 * 
	 * @param post		The post.
	 * @return 			If there are no comments returns null or empty.
	 */
	public static Collection<Comment> getFromPost(Application application, Session session, Post post) throws Exception {
		logger.debug("Retrieving Comment from post: \"" + post.getId() + "\".");
		Map<String,String> params = Request.create(application, session, "Stream.getComments");
		params.put("post_id", post.getId());
		Request.sign(params, application, session);
		Collection<Comment> comments = parseReponse(Response.get(params));
		// Add the post permalink to the comment!
		if (comments != null) {
			for (Comment comment : comments) {
				comment.setPermaLink(post.getPermaLink());
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

	public static boolean remove(Application application, Session session, Comment comment) throws Exception {
		logger.debug("Removing Comment " + comment.getId());
		Map<String,String> params = Request.create(application, session, "Stream.removeComment");
		params.put("comment_id", comment.getId());
		Request.sign(params, application, session);
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
