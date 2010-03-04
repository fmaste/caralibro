package caralibro.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.json.JSONArray;

import caralibro.Rest;
import caralibro.factory.CommentFactory;
import caralibro.factory.RequestFactory;
import caralibro.model.Application;
import caralibro.model.Comment;
import caralibro.model.Post;
import caralibro.model.Session;
import caralibro.model.constants.Facebook;

// If there are no comments returns null
public class CommentDao {

	public static Collection<Comment> getByPost(Application application, Session session, Post post) throws Exception {
		Map<String,String> params = RequestFactory.create(application, session, "Stream.getComments");
		params.put("post_id", post.getId());
		RequestFactory.sign(params, application, session);
		String commentsJsonResponse = Rest.makeRequest(Facebook.REST_SERVER, params);
		// Warning: If there are no comments the response is like this
		// Response: {}
		if (commentsJsonResponse == null || commentsJsonResponse.isEmpty() || !commentsJsonResponse.startsWith("[")) {
			return null;
		}
		Collection<Comment> comments = new ArrayList<Comment>();
		JSONArray commentsJsonArray = new JSONArray(commentsJsonResponse);
		for (int i = 0; i < commentsJsonArray.length(); i++) {
			String commentString = commentsJsonArray.optString(i);
			if (commentString != null && !commentString.isEmpty()) {
				Comment comment = CommentFactory.comment(commentString);
				if (comment != null ) {
					comments.add(comment);
				}
			}			
		}
		return comments;
	}
	
}
