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
// A Stream.get example (only one posts, no comments)
// Request: http://api.facebook.com/restserver.php?v=1.0&api_key=060c9d27db80e7bc1dab1f3ec1e48f63&ss=true&method=Stream.get&format=json&source_ids=326834508374&call_id=1267682545637&session_key=e9088abfc1b185b9e11b0bee-1443735325&sig=b6af78537a2da6d4b640c752824ac0b2
// Response: {"posts":[{"post_id":"326834508374_353743318374","viewer_id":1443735325,"source_id":326834508374,"type":56,"app_id":null,"attribution":null,"actor_id":100000751425511,"target_id":326834508374,"message":"Un post!","attachment":{"description":""},"app_data":{},"action_links":null,"comments":{"can_remove":true,"can_post":true,"count":0,"comment_list":{}},"likes":{"href":"http:\/\/www.facebook.com\/social_graph.php?node_id=353743318374&class=LikeManager","count":0,"sample":{},"friends":{},"user_likes":false,"can_like":true},"privacy":{"value":"NOT_EVERYONE"},"updated_time":1267681759,"created_time":1267681759,"tagged_ids":null,"is_hidden":false,"filter_key":"","permalink":"http:\/\/www.facebook.com\/pages\/testingAFanPage\/326834508374?v=feed&story_fbid=353743318374"}],"profiles":[{"id":100000751425511,"url":"http:\/\/www.facebook.com\/profile.php?id=100000751425511","name":"Fede Testeando","pic_square":"http:\/\/static.ak.fbcdn.net\/pics\/q_silhouette.gif","type":"user"},{"id":326834508374,"url":"http:\/\/www.facebook.com\/pages\/testingAFanPage\/326834508374","name":"testingAFanPage","pic_square":"http:\/\/static.ak.fbcdn.net\/pics\/q_default.gif","type":"page"}],"albums":{}}
public class PostDao {
	// By trial an error this was my calculated max number of posts that can be retrieved. More and you get an HTTP 500 error.
	private static final String MAX_LIMIT = "300";
	
	// Return a collection of posts or null if there are no posts.
	// Use time only if it is not null.
	private static Collection<Post> getFromSourceId(Application application, Session session, String sourceId, Long startTime, Long endTime) throws Exception {
		Map<String,String> params = RequestFactory.create(application, session, "Stream.get");
		params.put("source_ids", sourceId);
		params.put("limit", MAX_LIMIT); // Facebook default limit is 30
		if (startTime != null) {
			params.put("start_time", startTime.toString()); // Defaults to 1 day
		}
		if (endTime != null) {
			params.put("end_time", endTime.toString()); // Defaults to now
		}
		RequestFactory.sign(params, application, session);
		String streamJsonResponse = Rest.makeRequest(Facebook.REST_SERVER, params);
		// JSON response must be a JSON object with 'posts', 'profiles' and 'albums' as keys!
		if (streamJsonResponse == null || streamJsonResponse.isEmpty() || !streamJsonResponse.startsWith("{")) {
			return null;
		}
		JSONObject streamJsonObject = new JSONObject(streamJsonResponse);	
		String postsString = streamJsonObject.optString("posts");
		// Warning: When there are no posts, 'posts' is an empty object like this
		// Response: {"posts":{},"profiles":{},"albums":{}}
		// Otherwise, if there are posts, 'posts' is a JSON array.
		if (postsString == null || postsString.isEmpty() || !postsString.startsWith("[")) {
			return null;
		}
		Collection<Post> posts = new ArrayList<Post>();
		JSONArray postsJsonArray = streamJsonObject.getJSONArray("posts");
		for (int i = 0; i < postsJsonArray.length(); i++) {
			// Get that index value as a string and PostFactory must know how to parse it!
			String postString = postsJsonArray.optString(i);
			if (postString != null && !postString.isEmpty()) {
				Post post = PostFactory.create(postString);
				if (post != null) {
					posts.add(post);
				}
			}
		}
		// TODO: If there are more posts, re call this function and add to the list!
		return posts;
	}
	
	public static Collection<Post> getFromPage(Application application, Session session, Page page, Long startTime, Long endTime) throws Exception {
		return getFromSourceId(application, session, page.getId().toString(), startTime, endTime);
	}

	// TODO:
//	public static Collection<Post> getFromPageByFans(Application application, Session session, Page page, Long startTime, Long endTime) throws Exception {
//		Collection<Post> posts = getFromPage(application, session, page, startTime, endTime);
//		if (posts == null) {
//			return null;
//		}
//		Collection<Post> postsByFans = new ArrayList<Post>();
//		for (Post post : posts) {
//			if (post.ge)
//				// TODO: Compare pageId with actor_id !!
//		}
//		return postsByFans;
//	}

	// TODO:
//	public static Collection<Post> getFromPageByAdmin(Application application, Session session, Page page, Long startTime, Long endTime) throws Exception {
//		Collection<Post> posts = getFromPage(application, session, page, startTime, endTime);
//		if (posts == null) {
//			return null;
//		}
//		Collection<Post> postsByFans = new ArrayList<Post>();
//		for (Post post : posts) {
//			if (post.ge)
//				// TODO: Compare pageId with actor_id !!
//		}
//		return postsByFans;
//	}
	
	public static Collection<Post> getFromUser(Application application, Session session, User user, Long startTime, Long endTime) throws Exception {
		return getFromSourceId(application, session, user.getId().toString(), startTime, endTime);
	}
	
	public static boolean remove(Application application, Session session, Post post) throws Exception {
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
