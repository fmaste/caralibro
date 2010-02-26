package caralibro.model;

public class Comment {
	private String id = null; // Unique comment id for a given object
	private String text = null;
	private User user = null;
	private Long creationTime = null; // Unit time!

	public Comment() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}
	
}
