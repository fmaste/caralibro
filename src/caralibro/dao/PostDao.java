package caralibro.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import caralibro.Rest;
import caralibro.factory.PostFactory;
import caralibro.factory.RequestFactory;
import caralibro.model.Application;
import caralibro.model.Page;
import caralibro.model.Post;
import caralibro.model.Session;
import caralibro.model.User;
import caralibro.model.constants.Facebook;

// Before you can get data from a user's stream, you need the extended permission "read_stream".
// Return a collection of posts or null if there are no posts.
// A Stream.get example (only one posts, no comments)
// Request: http://api.facebook.com/restserver.php?v=1.0&api_key=060c9d27db80e7bc1dab1f3ec1e48f63&ss=true&method=Stream.get&format=json&source_ids=326834508374&call_id=1267682545637&session_key=e9088abfc1b185b9e11b0bee-1443735325&sig=b6af78537a2da6d4b640c752824ac0b2
// Response: {"posts":[{"post_id":"326834508374_353743318374","viewer_id":1443735325,"source_id":326834508374,"type":56,"app_id":null,"attribution":null,"actor_id":100000751425511,"target_id":326834508374,"message":"Un post!","attachment":{"description":""},"app_data":{},"action_links":null,"comments":{"can_remove":true,"can_post":true,"count":0,"comment_list":{}},"likes":{"href":"http:\/\/www.facebook.com\/social_graph.php?node_id=353743318374&class=LikeManager","count":0,"sample":{},"friends":{},"user_likes":false,"can_like":true},"privacy":{"value":"NOT_EVERYONE"},"updated_time":1267681759,"created_time":1267681759,"tagged_ids":null,"is_hidden":false,"filter_key":"","permalink":"http:\/\/www.facebook.com\/pages\/testingAFanPage\/326834508374?v=feed&story_fbid=353743318374"}],"profiles":[{"id":100000751425511,"url":"http:\/\/www.facebook.com\/profile.php?id=100000751425511","name":"Fede Testeando","pic_square":"http:\/\/static.ak.fbcdn.net\/pics\/q_silhouette.gif","type":"user"},{"id":326834508374,"url":"http:\/\/www.facebook.com\/pages\/testingAFanPage\/326834508374","name":"testingAFanPage","pic_square":"http:\/\/static.ak.fbcdn.net\/pics\/q_default.gif","type":"page"}],"albums":{}}
public class PostDao {

	private static Collection<Post> getForSourceId(Application application, Session session, String sourceId) throws Exception {
		Map<String,String> params = RequestFactory.create(application, session, "Stream.get");
		params.put("source_ids", sourceId);
		RequestFactory.sign(params, application, session);
		String streamJsonResponse = Rest.makeRequest(Facebook.REST_SERVER, params);				
		if (streamJsonResponse == null || streamJsonResponse.isEmpty() || !streamJsonResponse.startsWith("{")) {
			return null;
		}
		// Warning: When there are no posts, 'posts' is an empty object like this
		// Response: {"posts":{},"profiles":{},"albums":{}}
		JSONObject streamJsonObject = new JSONObject(streamJsonResponse);	
		String postsString = streamJsonObject.optString("posts");
		if (postsString == null || postsString.isEmpty() || !postsString.startsWith("[")) {
			return null;
		}
		Collection<Post> posts = new ArrayList<Post>();
		JSONArray postsJsonArray = streamJsonObject.getJSONArray("posts");
		for (int i = 0; i < postsJsonArray.length(); i++) {
			String postString = postsJsonArray.optString(i);
			if (postString != null && !postString.isEmpty()) {
				Post post = PostFactory.create(postString);
				if (post != null) {
					posts.add(post);
				}				
			}
		}
		return posts;
	}
	
	public static Collection<Post> getByPage(Application application, Session session, Page page) throws Exception {
		return getForSourceId(application, session, page.getId().toString());
	}

	public static Collection<Post> getByUser(Application application, Session session, User user) throws Exception {
		return getForSourceId(application, session, user.getId().toString());
	}
	
	public static boolean removePost(Application application, Session session, Post post) throws Exception {
		Map<String,String> params = RequestFactory.create(application, session, "Stream.remove");
		params.put("post_id", post.getId());
		RequestFactory.sign(params, application, session);
		String response = Rest.makeRequest(Facebook.REST_SERVER, params);
		if (response != null && !response.isEmpty() && response.equals("true")) {
			return true;
		} else {
			return false;
		}
	}
	
}
