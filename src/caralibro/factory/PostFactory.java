package caralibro.factory;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import caralibro.model.data.Post;

public class PostFactory {

	// TODO: And the user??
	public static Post create(String id, String message, ArrayList<String> photoUrls, ArrayList<String> videoUrls, ArrayList<String> linkUrls, Integer comments, Integer likes, Long creationTime, Long updateTime) {
		Post post = new Post();
		post.setId(id);
		post.setText(message);
		post.setCreationTime(creationTime);
		post.setUpdateTime(updateTime);
		if (photoUrls != null && !photoUrls.isEmpty()) {
			post.setPhotoUrls(photoUrls);
		}
		if (videoUrls != null && !videoUrls.isEmpty()) {
			post.setVideoUrls(videoUrls);
		}
		if (linkUrls != null && !linkUrls.isEmpty()) {
			post.setLinkUrls(linkUrls);
		}
		post.setComments(comments);
		post.setLikes(likes);
		return post;
	}
	
	public static Post create(String streamJsonResponse) throws Exception {
		if (streamJsonResponse == null || streamJsonResponse.isEmpty() || !streamJsonResponse.startsWith("{")) {
			return null;
		}
		JSONObject postJsonObject = new JSONObject(streamJsonResponse);
		// TODO: Check for this keys or keep it like an exception ?
		String id = postJsonObject.getString("post_id");
		String text = postJsonObject.getString("message");
		// TODO: Which one is the user that created the post ??
		Long viewerId = postJsonObject.getLong("viewer_id");
		Long sourceId = postJsonObject.getLong("source_id");
		Long actorId = postJsonObject.getLong("actor_id");
		// FIXME: Gives error sometimes, but the string is there!
		// Maybe the key is sometimes a string sometimes a long like with page id and post id
		// Long targetId = postJsonObject.getLong("target_id");
		// TODO: The permalink must be created later based on the post id!
		// String permaLink = postJsonObject.getString("permalink");
		ArrayList<String> photoUrls = getPhotoUrls(postJsonObject);
		ArrayList<String> videoUrls = getVideoUrls(postJsonObject);
		ArrayList<String> linkUrls = getLinkUrls(postJsonObject);
		// TODO: Add number of comments and number of likes
		JSONObject commentsJsonObject = postJsonObject.optJSONObject("comments");
		Integer comments = 0;
		if (commentsJsonObject != null) {
			comments = commentsJsonObject.optInt("count");	
		}
		JSONObject likesJsonObject = postJsonObject.optJSONObject("likes");
		Integer likes = 0;
		if (likesJsonObject != null) {
			likes = likesJsonObject.optInt("count");	
		}				
		Long updateTime = postJsonObject.getLong("updated_time");
		Long creationTime = postJsonObject.getLong("created_time");
		return create(id, text, photoUrls, videoUrls, linkUrls, comments, likes, creationTime, updateTime);
	}

	private static ArrayList<String> getPhotoUrls(JSONObject postJsonObject) {		
		ArrayList<String> photoUrls = new ArrayList<String>();		
		try {
			JSONArray array = postJsonObject.getJSONObject("attachment").getJSONArray("media");
			int index;
			int len = array.length();
			String url;
			for (index = 0; index < len; index++) {
				url = array.getJSONObject(index).getString("href");
				photoUrls.add(url);
			}
		} catch (JSONException e) {
			// no photos
			return photoUrls;
		}
		return photoUrls;
	}
	
	private static ArrayList<String> getVideoUrls(JSONObject postJsonObject) {
		ArrayList<String> videoUrls = new ArrayList<String>();		
		try {
			JSONArray array = postJsonObject.getJSONObject("attachment").getJSONArray("media");
			int index;
			int len = array.length();
			String url;
			for (index = 0; index < len; index++) {
				url = array.getJSONObject(index).getJSONObject("video").getString("source_url");
				videoUrls.add(url);
			}
		} catch (JSONException e) {
			// no videos
			return videoUrls;
		}
		return videoUrls;
	}
	
	private static ArrayList<String> getLinkUrls(JSONObject postJsonObject) {		
		ArrayList<String> linkUrls = new ArrayList<String>();		
		try {
			String url = postJsonObject.getJSONObject("attachment").getString("name");
			linkUrls.add(url);
		} catch (JSONException e) {
			// no links
			return linkUrls;
		}
		return linkUrls;
	}
	
}
