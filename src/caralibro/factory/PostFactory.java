package caralibro.factory;

import org.json.JSONObject;

import caralibro.model.Post;

public class PostFactory {

	public static Post create(String id, String message, Long creationTime, Long updateTime) {
		Post post = new Post();
		post.setId(id);
		post.setText(message);
		post.setCreationTime(creationTime);
		post.setUpdateTime(updateTime);
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
		Long updateTime = postJsonObject.getLong("updated_time");
		Long creationTime = postJsonObject.getLong("created_time");
		String permaLink = postJsonObject.getString("permalink");
		// TODO: Take out photos, links, videos, etc!
		return create(id, text, creationTime, updateTime);
	}
	
}
