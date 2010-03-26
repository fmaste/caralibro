package caralibro.factory;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import caralibro.model.data.stream.Link;
import caralibro.model.data.stream.Post;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class PostFactory {
	private static final Logger logger = LoggerFactory.getLogger(CommentFactory.class);
	
	public static Post create(String id, Long authorId, String text, Link link, Integer comments, Integer likes, Long creationTime, Long updateTime, String permaLink) {
		Post post = new Post();
		post.setId(id);
		post.setAuthorId(authorId);
		post.setText(text);
		post.setLink(link);
		post.setComments(comments);
		post.setLikes(likes);
		post.setCreationTime(creationTime);
		post.setUpdateTime(updateTime);
		post.setPermaLink(permaLink);
		return post;
	}
	
	/*
	 * Parses a JSON string containing the post and creates the post object.
	 * If there is no post_id, message, actor_id, created_time, updated_time or permalink key an exception is thrown.
	 * Link default value is null, for comments and likes is zero.
	 * 
	 * @param postJsonResponse 	One of the posts retrieved on the posts JSON array.
	 * @return 					A post object or null if the string is not a JSON object.
	 */
	public static Post create(String postJsonResponse) throws Exception {
		logger.debug("Parsing JSON encoded Post: \"" + postJsonResponse + "\"");
		if (postJsonResponse == null || postJsonResponse.isEmpty() || !postJsonResponse.startsWith("{")) {
			logger.error("Not a valid JSON encoded Post: \"" + postJsonResponse + "\"");
			return null;
		}
		JSONObject postJsonObject = new JSONObject(postJsonResponse);
		String id = postJsonObject.getString("post_id");
		String text = postJsonObject.getString("message");
		Long authorId = postJsonObject.getLong("actor_id");
		Long creationTime = postJsonObject.getLong("created_time");
		Long updateTime = postJsonObject.getLong("updated_time");
		String permaLink = postJsonObject.getString("permalink");
		Link link = null;
		String attachmentJsonObject = postJsonObject.optString("attachment");
		if (attachmentJsonObject != null && !attachmentJsonObject.isEmpty()) {
			link = createLink(attachmentJsonObject);
		}
		// If there is no comment count, zero is assumed.
		JSONObject commentsJsonObject = postJsonObject.optJSONObject("comments");
		Integer comments = 0;
		if (commentsJsonObject != null) {
			comments = commentsJsonObject.optInt("count");	
		}
		// If there is no like count, zero is assumed.		
		JSONObject likesJsonObject = postJsonObject.optJSONObject("likes");
		Integer likes = 0;
		if (likesJsonObject != null) {
			likes = likesJsonObject.optInt("count");	
		}
		// TODO: Long viewerId = postJsonObject.getLong("viewer_id");
		// TODO: Long sourceId = postJsonObject.getLong("source_id");
		// FIXME: Gives error sometimes, but the string is there!
		// Maybe the key is sometimes a string sometimes a long like with page id and post id
		// Long targetId = postJsonObject.getLong("target_id");
		return create(id, authorId, text, link, comments, likes, creationTime, updateTime, permaLink);
	}

	/*
	 * Parses a JSON string containing the post's attachment object.
	 * If there is no media key with a JSON array, null is returned.
	 * If there is no type key or unknown type on the media object, the link is ignored.
	 * Web links are a link to Facebook that redirects to the original web link.
	 * Photos and videos are direct links to the files.
	 * 
	 * @param postJsonResponse 	The attachment JSON object on one of the posts retrieved on the posts JSON array.
	 * @return 					A link object or null if the string is not a JSON object or if there are no valid links.
	 */
	private static Link createLink(String attachmentJsonResponse) throws Exception {
		if (attachmentJsonResponse == null || attachmentJsonResponse.isEmpty() || !attachmentJsonResponse.startsWith("{")) {
			return null;
		}
		JSONObject attachmentJsonObject = new JSONObject(attachmentJsonResponse);
		JSONArray mediaJsonArray = attachmentJsonObject.optJSONArray("media");
		if (mediaJsonArray == null) {
			return null;
		}
		Link link = new Link();
		ArrayList<String> web = new ArrayList<String>();
		ArrayList<String> photo = new ArrayList<String>();
		ArrayList<String> video = new ArrayList<String>();
		for (int i = 0; i < mediaJsonArray.length(); i++) {
			JSONObject mediaJsonObject = mediaJsonArray.optJSONObject(i);
			if (mediaJsonObject != null) {
				String type = mediaJsonObject.optString("type");
				if (type != null && !type.isEmpty()) {
					if (type.equalsIgnoreCase("link")) {
						// This link goes through Facebook and redirects to the real link.
						String linkUrl = mediaJsonObject.optString("href");
						if (linkUrl != null && !linkUrl.isEmpty()) {
							web.add(linkUrl);
						}
						// TODO: The page icon can be retrieved too. Facebook is using Akamai!
						// String iconUrl = mediaJsonObject.optString("src");
					} else if (type.equalsIgnoreCase("photo")) {
						// Direct link to the photo file! Facebook is using Akamai!
						String fileUrl = mediaJsonObject.optString("src");
						if (fileUrl != null && !fileUrl.isEmpty()) {
							photo.add(fileUrl);
						}
						// TODO: Url of the photo to watch it inside Facebook
						// String facebookUrl = mediaJsonObject.optString("href");				
					} else if (type.equalsIgnoreCase("video")) {
						// Direct link to the video file! Facebook is using Akamai!
						String fileUrl = mediaJsonObject.optString("src");
						if (fileUrl != null && !fileUrl.isEmpty()) {
							video.add(fileUrl);
						}
						// TODO: Url of the photo to watch it inside Facebook
						// String facebookUrl = mediaJsonObject.optString("href");	
					}
				}
			}
		}
		if (web.isEmpty() && photo.isEmpty() && video.isEmpty()) {
			return null;
		}
		if (!web.isEmpty()) {
			link.setWeb(web);
		}
		if (!photo.isEmpty()) {
			link.setPhoto(photo);
		}
		if (!video.isEmpty()) {
			link.setVideo(video);
		}
		return link;
	}
	
}
