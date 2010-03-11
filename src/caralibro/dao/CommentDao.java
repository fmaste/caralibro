package caralibro.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.json.JSONArray;

import caralibro.Rest;
import caralibro.factory.CommentFactory;
import caralibro.factory.RequestFactory;
import caralibro.model.constants.Facebook;
import caralibro.model.data.Application;
import caralibro.model.data.Comment;
import caralibro.model.data.Post;
import caralibro.model.data.Session;

// If there are no comments returns null or empty
public class CommentDao {

	// Comment request response example
	// Request: http://api.facebook.com/restserver.php?v=1.0&api_key=060c9d27db80e7bc1dab1f3ec1e48f63&ss=true&method=Stream.getComments&format=json&post_id=1443735325_155668449205&call_id=1267683315247&session_key=7a85a2cbaf58c823c450a95d-1443735325&sig=56afb28d2d3aa042f4ac2dba6fc885f5
	// Response: [{"fromid":1062262331,"time":1255708755,"text":"No me quiero imaginar cuan al pedo estarias para ponerte con el facebook.. es la primera vez q apareces aca papaaaaa!! como andas??!!!","id":"1443735325_155668449205_6025124"},{"fromid":1443735325,"time":1255709295,"text":"Lo uso para jugar a esta porqueria que soy malisimo! jajaja","id":"1443735325_155668449205_6025449"}]
	public static Collection<Comment> getFromPost(Application application, Session session, Post post) throws Exception {
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
		String response = Rest.makeRequest(Facebook.REST_SERVER, params);
		if (response != null && !response.isEmpty() && response.equals("true")) {
			return true;
		} else {
			return false;
		}
	}
	
}
