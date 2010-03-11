package caralibro.factory;

import org.json.JSONObject;

import caralibro.model.data.Comment;
import caralibro.model.data.User;

public class CommentFactory {

	// TODO: And the user??
	public static Comment create(String id, String text, User user, Long creationTime) {
		Comment comment = new Comment();
		comment.setId(id);
		comment.setText(text);
		comment.setUser(user);
		comment.setCreationTime(creationTime);;
		return comment;
	}
	
	// Comment request response example
	// Request: http://api.facebook.com/restserver.php?v=1.0&api_key=060c9d27db80e7bc1dab1f3ec1e48f63&ss=true&method=Stream.getComments&format=json&post_id=1443735325_155668449205&call_id=1267683315247&session_key=7a85a2cbaf58c823c450a95d-1443735325&sig=56afb28d2d3aa042f4ac2dba6fc885f5
	// Response: [{"fromid":1062262331,"time":1255708755,"text":"No me quiero imaginar cuan al pedo estarias para ponerte con el facebook.. es la primera vez q apareces aca papaaaaa!! como andas??!!!","id":"1443735325_155668449205_6025124"},{"fromid":1443735325,"time":1255709295,"text":"Lo uso para jugar a esta porqueria que soy malisimo! jajaja","id":"1443735325_155668449205_6025449"}]
	public static Comment create(String commentJsonResponse) throws Exception {
		if (commentJsonResponse == null || commentJsonResponse.isEmpty() || !commentJsonResponse.startsWith("{")) {
			return null;
		}
		// TODO: Check for this keys or keep it like an exception ?
		JSONObject commentJsonObject = new JSONObject(commentJsonResponse);
		String id = commentJsonObject.getString("id");
		String text = commentJsonObject.getString("text");
		User user = UserFactory.create(commentJsonObject.getLong("fromid"));
		Long time = commentJsonObject.getLong("time");
		return create(id, text, user, time);
	}
	
}
