package caralibro.model;

public class Comment {
	private Long id;
	private String text;
	private String externalId;
	private User user;
	private Long creationTime; // Unit time!
	
	public Comment(Long id, String text, String externalId, User user, Long creationTime) {
		this.id = id;
		this.text = text;
		this.externalId = externalId;
		this.user = user;
		this.creationTime = creationTime;
	}
	
	public Long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getExternalId() {
		return externalId;
	}

	public User getUser() {
		return user;
	}

	public Long getCreationTime() {
		return creationTime;
	}
	
}
