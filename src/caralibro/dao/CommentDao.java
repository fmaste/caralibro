package caralibro.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.json.JSONArray;

import caralibro.Rest;
import caralibro.Utils;
import caralibro.factory.CommentFactory;
import caralibro.model.Application;
import caralibro.model.Comment;
import caralibro.model.Post;
import caralibro.model.Session;
import caralibro.model.constants.Facebook;

public class CommentDao {

	public static Collection<Comment> getPostComments(Application application, Session session, Post post) throws Exception {
		Map<String,String> params = Utils.initParams(application, session, "Stream.getComments");
		params.put("post_id", post.getId());
		Utils.finalizeParams(params, application, session);
		String commentsJsonResponse = Rest.makeRequest(Facebook.REST_SERVER, params);
		Collection<Comment> comments = new ArrayList<Comment>();
		JSONArray commentsJsonArray = new JSONArray(commentsJsonResponse);
		for (int i = 0; i < commentsJsonArray.length(); i++) {
			comments.add(CommentFactory.createComment(commentsJsonArray.getJSONObject(i).toString()));
		}
		return comments;
	}
	
}
