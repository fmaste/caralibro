package caralibro.dao;

import java.util.Collection;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import caralibro.Rest;
import caralibro.Utils;
import caralibro.model.Application;
import caralibro.model.Facebook;
import caralibro.model.Page;
import caralibro.model.Post;
import caralibro.model.Session;


public class PostDao {

	// Before you can get data from a user's stream, you need the extended permission read_stream.
	public static Collection<Post> getPostsByPage(Application application, Session session, Page page) throws Exception {
		Map<String,String> params = Utils.initParams(application, session, "Stream.get");
		params.put("source_ids", page.getId().toString());
		Utils.finalizeParams(params, application);
		String streamJsonResponse = Rest.makeRequest(Facebook.REST_SERVER, params);
		JSONObject streamJsonObject = new JSONObject(streamJsonResponse);
		// POSTS
		JSONArray postsJsonArray = streamJsonObject.getJSONArray("posts");
		for (int i = 0; i < postsJsonArray.length(); i++) {
			JSONObject postJsonObject = postsJsonArray.getJSONObject(i);
			String postId = postJsonObject.getString("post_id");
			Long viewerId = postJsonObject.getLong("viewer_id");
			Long sourceId = postJsonObject.getLong("source_id");
			Long actorId = postJsonObject.getLong("actor_id");
			// FIXME: Gives error sometimes, but the string is there!
			// Long targetId = postJsonObject.getLong("target_id");
			String message = postJsonObject.getString("message");
			System.err.println("Post: " + message);
			Long updateTime = postJsonObject.getLong("updated_time");
			Long createTime = postJsonObject.getLong("created_time");
			String permaLink = postJsonObject.getString("permalink");
			// TODO: Get "privacy"
			// TODO: Get "attachment"
			// TODO: Get "type"
			// TODO: Get "action_links"
			// TODO: Get "tagged_ids"
			// TODO: Get "is_hidden"
			// TODO: Get "likes"
			// TODO: Get "app_data"
			// TODO: Get "app_id"
			// TODO: Get "attribution"
			// COMMENTS
			JSONObject commentsHeaderJsonObject = postJsonObject.getJSONObject("comments");
			Boolean canRemoveComments = commentsHeaderJsonObject.getBoolean("can_remove");
			Boolean canPostComments = commentsHeaderJsonObject.getBoolean("can_post");
			Integer commentCount = commentsHeaderJsonObject.getInt("count");
			JSONArray commentsJsonArray = commentsHeaderJsonObject.getJSONArray("comment_list");
			for (int j = 0; j < commentsJsonArray.length(); j++) {
				// COMMENT
				JSONObject commentJsonObject = commentsJsonArray.getJSONObject(j);
				Long fromId = commentJsonObject.getLong("fromid");
				Long time = commentJsonObject.getLong("time");
				String text = commentJsonObject.getString("text");
				System.err.println("Comment: " + text);
				String id = commentJsonObject.getString("id");
			}
		}
		// TODO: Get "albums"
		// TODO: Get "profiles"
		return null;
	}
	
	// Response: {"posts":[{"post_id":"326834508374_326877138374","viewer_id":1443735325,"source_id":326834508374,"type":56,"app_id":null,"attribution":null,"actor_id":809448857,"target_id":326834508374,"message":"semi sobrio","attachment":{"description":""},"app_data":{},"action_links":null,"comments":{"can_remove":true,"can_post":true,"count":1,"comment_list":[{"fromid":326834508374,"time":1266953422,"text":"comment 1","id":"326834508374_326877138374_14414154"}]},"likes":{"href":"http:\/\/www.facebook.com\/social_graph.php?node_id=326877138374&class=LikeManager","count":0,"sample":{},"friends":{},"user_likes":false,"can_like":true},"privacy":{"value":"NOT_EVERYONE"},"updated_time":1266953422,"created_time":1266537163,"tagged_ids":null,"is_hidden":false,"filter_key":"","permalink":"http:\/\/www.facebook.com\/pages\/testingAFanPage\/326834508374?v=feed&story_fbid=326877138374"},{"post_id":"326834508374_319137741859","viewer_id":1443735325,"source_id":326834508374,"type":46,"app_id":null,"attribution":null,"actor_id":326834508374,"target_id":null,"message":"testingAFanPage First Post","attachment":{"description":""},"app_data":{},"action_links":null,"comments":{"can_remove":true,"can_post":true,"count":1,"comment_list":[{"fromid":326834508374,"time":1267080722,"text":"comment 1","id":"326834508374_319137741859_11561976"}]},"likes":{"href":"http:\/\/www.facebook.com\/social_graph.php?node_id=319137741859&class=LikeManager","count":0,"sample":{},"friends":{},"user_likes":false,"can_like":true},"privacy":{"description":"Everyone","value":"EVERYONE","friends":"","networks":"","allow":"","deny":""},"updated_time":1267080722,"created_time":1266535857,"tagged_ids":null,"is_hidden":false,"filter_key":"","permalink":"http:\/\/www.facebook.com\/pages\/testingAFanPage\/326834508374?v=feed&story_fbid=319137741859"}],"profiles":[{"id":809448857,"url":"http:\/\/www.facebook.com\/profile.php?id=809448857","name":"Belu Mastellone","pic_square":"http:\/\/profile.ak.fbcdn.net\/v228\/1306\/15\/q809448857_4713.jpg","type":"user"},{"id":326834508374,"url":"http:\/\/www.facebook.com\/pages\/testingAFanPage\/326834508374","name":"testingAFanPage","pic_square":"http:\/\/static.ak.fbcdn.net\/pics\/q_default.gif","type":"page"}],"albums":{}}

}
