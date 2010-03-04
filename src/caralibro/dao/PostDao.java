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
import caralibro.model.constants.Facebook;

public class PostDao {

	// Before you can get data from a user's stream, you need the extended permission read_stream.
	public static Collection<Post> getPostsByPage(Application application, Session session, Page page) throws Exception {
		Map<String,String> params = RequestFactory.create(application, session, "Stream.get");
		params.put("source_ids", page.getId().toString());
		RequestFactory.sign(params, application, session);
		String streamJsonResponse = Rest.makeRequest(Facebook.REST_SERVER, params);
		Collection<Post> posts = new ArrayList<Post>();
		JSONObject streamJsonObject = new JSONObject(streamJsonResponse);
		JSONArray postsJsonArray = streamJsonObject.getJSONArray("posts");
		for (int i = 0; i < postsJsonArray.length(); i++) {
			posts.add(PostFactory.create(postsJsonArray.getJSONObject(i).toString()));
		}
		return posts;
	}
	
	// Response Example!
	// Response: {"posts":[{"post_id":"326834508374_326877138374","viewer_id":1443735325,"source_id":326834508374,"type":56,"app_id":null,"attribution":null,"actor_id":809448857,"target_id":326834508374,"message":"semi sobrio","attachment":{"description":""},"app_data":{},"action_links":null,"comments":{"can_remove":true,"can_post":true,"count":1,"comment_list":[{"fromid":326834508374,"time":1266953422,"text":"comment 1","id":"326834508374_326877138374_14414154"}]},"likes":{"href":"http:\/\/www.facebook.com\/social_graph.php?node_id=326877138374&class=LikeManager","count":0,"sample":{},"friends":{},"user_likes":false,"can_like":true},"privacy":{"value":"NOT_EVERYONE"},"updated_time":1266953422,"created_time":1266537163,"tagged_ids":null,"is_hidden":false,"filter_key":"","permalink":"http:\/\/www.facebook.com\/pages\/testingAFanPage\/326834508374?v=feed&story_fbid=326877138374"},{"post_id":"326834508374_319137741859","viewer_id":1443735325,"source_id":326834508374,"type":46,"app_id":null,"attribution":null,"actor_id":326834508374,"target_id":null,"message":"testingAFanPage First Post","attachment":{"description":""},"app_data":{},"action_links":null,"comments":{"can_remove":true,"can_post":true,"count":1,"comment_list":[{"fromid":326834508374,"time":1267080722,"text":"comment 1","id":"326834508374_319137741859_11561976"}]},"likes":{"href":"http:\/\/www.facebook.com\/social_graph.php?node_id=319137741859&class=LikeManager","count":0,"sample":{},"friends":{},"user_likes":false,"can_like":true},"privacy":{"description":"Everyone","value":"EVERYONE","friends":"","networks":"","allow":"","deny":""},"updated_time":1267080722,"created_time":1266535857,"tagged_ids":null,"is_hidden":false,"filter_key":"","permalink":"http:\/\/www.facebook.com\/pages\/testingAFanPage\/326834508374?v=feed&story_fbid=319137741859"}],"profiles":[{"id":809448857,"url":"http:\/\/www.facebook.com\/profile.php?id=809448857","name":"Belu Mastellone","pic_square":"http:\/\/profile.ak.fbcdn.net\/v228\/1306\/15\/q809448857_4713.jpg","type":"user"},{"id":326834508374,"url":"http:\/\/www.facebook.com\/pages\/testingAFanPage\/326834508374","name":"testingAFanPage","pic_square":"http:\/\/static.ak.fbcdn.net\/pics\/q_default.gif","type":"page"}],"albums":{}}

	public static void removePost(Application application, Session session, Post post) throws Exception {
		Map<String,String> params = RequestFactory.create(application, session, "Stream.remove");
		params.put("post_id", post.getId());
		RequestFactory.sign(params, application, session);
		Rest.makeRequest(Facebook.REST_SERVER, params);
	}
	
}
