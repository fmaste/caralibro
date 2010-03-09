package caralibro.model;

import java.util.List;

// Almost everything in Facebook is a post.
// Posts can have links, photos, videos, likes and comments!
// The update time of the post refers to new comments and likes!
public class Post {
	// TODO: Can't be a Long ??
	private String id = null; // It's a string like "sourceId_postId", being both numbers
	private String text = null;
	private List<String> photoUrls = null;
	private List<String> videoUrls = null;
	private List<String> linkUrls = null;	
	// TODO: Add user??
	private Integer comments = null;
	private Integer likes = null;
	private Long updateTime = null;
	private Long creationTime = null;

	public Post() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<String> getVideoUrls() {
		return videoUrls;
	}

	public void setVideoUrls(List<String> videoUrls) {
		this.videoUrls = videoUrls;
	}

	public List<String> getLinkUrls() {
		return linkUrls;
	}	
	
	public void setLinkUrls(List<String> linkUrls) {
		this.linkUrls = linkUrls;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}
	
}
