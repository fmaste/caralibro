package caralibro.model.data;

import java.util.ArrayList;
import java.util.List;

/*
 * Almost everything in Facebook is a post.
 * Posts can have links, photos, videos, likes and comments!
 * The update time of the post refers to new comments and likes!
 *  
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class Post {
	// TODO: Find why it is not a Long instead of String!
	// It is composed of sourceId_postId
	private String id = null;
	private User user = null;
	private String text = null;	
	private ArrayList<String> photoUrls = null;
	private ArrayList<String> videoUrls = null;
	private ArrayList<String> linkUrls = null;
	private Integer comments = null; // Comments count
	private Integer likes = null;	 // Likes count
	private Long creationTime = null; // Unix time in seconds, not milliseconds
	private Long updateTime = null; // Unix time in seconds, not milliseconds
	private String permaLink = null;

	public Post() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(ArrayList<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<String> getVideoUrls() {
		return videoUrls;
	}

	public void setVideoUrls(ArrayList<String> videoUrls) {
		this.videoUrls = videoUrls;
	}

	public List<String> getLinkUrls() {
		return linkUrls;
	}

	public void setLinkUrls(ArrayList<String> linkUrls) {
		this.linkUrls = linkUrls;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	/*
	 * @return Unix time in seconds, not milliseconds
	 */
	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}
	
	/*
	 * @return Unix time in seconds, not milliseconds
	 */
	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getPermaLink() {
		return permaLink;
	}

	public void setPermaLink(String permaLink) {
		this.permaLink = permaLink;
	}

}
