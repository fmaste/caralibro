package caralibro.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import caralibro.factory.PostFactory;
import caralibro.factory.RequestFactory;
import caralibro.model.data.Application;
import caralibro.model.data.Group;
import caralibro.model.data.Page;
import caralibro.model.data.Post;
import caralibro.model.data.Session;
import caralibro.model.data.User;

// Before you can get data from a user's stream, you need the extended permission "read_stream".
public class PostDao {
	// By trial an error this was my calculated max number of posts that can be retrieved. More and you get an HTTP 500 error or an empty array.
	private static final String MAX_LIMIT = "300";
	private static final String DELTA = "50";
	
	// Return a collection of posts or null if there are no posts.
	// Use time only if it is not null.
	public static Collection<Post> getFromSourceId(Application application, Session session, String sourceId, Long startTime, Long endTime) throws Exception {
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
		String streamJsonResponse = ResponseDao.get(params);
		// JSON response must be a JSON object with 'posts', 'profiles' and 'albums' as keys!
		if (streamJsonResponse == null || streamJsonResponse.isEmpty() || !streamJsonResponse.startsWith("{")) {
			return null;
		}
		JSONObject streamJsonObject = new JSONObject(streamJsonResponse);	
		// Warning: When there are no posts, 'posts' is an empty object like this
		// Response: {"posts":{},"profiles":{},"albums":{}}
		// Otherwise, if there are posts, 'posts' is a JSON array.
		JSONArray postsJsonArray = streamJsonObject.optJSONArray("posts");
		if (postsJsonArray == null) {
			return null;
		}
		ArrayList<Post> posts = new ArrayList<Post>();
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
		// TODO: Consider repeated values
		System.out.println("Posts request has " + posts.size() + " posts");
		if (!posts.isEmpty() && posts.size() > (Integer.parseInt(MAX_LIMIT) - Integer.parseInt(DELTA))) {
			System.out.println("LIMIT REACHED: MAKING ANOTHER REQUEST!!!!!!!!!!!!!");
			Collection<Post> morePosts = getFromSourceId(application, session, sourceId, startTime, posts.get(posts.size() - 1).getUpdateTime());
			if (morePosts != null) {
				posts.addAll(morePosts);
			}
		}
		return posts;
	}
	
	public static Collection<Post> getFromPage(Application application, Session session, Page page, Long startTime, Long endTime) throws Exception {
		return getFromSourceId(application, session, page.getId().toString(), startTime, endTime);
	}
	
	public static Collection<Post> getFromGroup(Application application, Session session, Group group, Long startTime, Long endTime) throws Exception {
		return getFromSourceId(application, session, group.getId().toString(), startTime, endTime);
	}
	
	public static Collection<Post> getFromUser(Application application, Session session, User user, Long startTime, Long endTime) throws Exception {
		return getFromSourceId(application, session, user.getId().toString(), startTime, endTime);
	}
	
	public static boolean remove(Application application, Session session, Post post) throws Exception {
		Map<String,String> params = RequestFactory.create(application, session, "Stream.remove");
		params.put("post_id", post.getId());
		RequestFactory.sign(params, application, session);
		String response = ResponseDao.get(params);
		if (response != null && !response.isEmpty() && response.equals("true")) {
			return true;
		} else {
			return false;
		}
	}
	
}
