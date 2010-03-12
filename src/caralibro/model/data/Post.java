package caralibro.model.data;

import java.util.ArrayList;
import java.util.List;

// Almost everything in Facebook is a post.
// Posts can have links, photos, videos, likes and comments!
// The update time of the post refers to new comments and likes!
public class Post {
	private String id = null;
	private User user = null;
	private String text = null;	
	private ArrayList<String> photoUrls = null;
	private ArrayList<String> videoUrls = null;
	private ArrayList<String> linkUrls = null;
	private Integer comments = null;
	private Integer likes = null;	
	private Long creationTime = null;
	private Long updateTime = null;
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

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}
	
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
