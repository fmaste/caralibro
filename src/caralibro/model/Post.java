package caralibro.model;

// Almost everything in Facebook is a post.
// Posts can have links, photos, videos, likes and comments!
// The update time of the post refers to new comments and likes!
public class Post {
	// TODO: Can't be a Long ??
	private String id = null; // It's a string like "sourceId_postId", being both numbers
	private String text = null;
	// TODO: Add user!!
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
