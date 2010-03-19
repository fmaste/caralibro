package caralibro.model.data;

/* 
 * Comments are attached to posts and can only contain text (No photos, videos, likes, etc).
 * Can't be edited, only deleted if you have the right permissions.
 * 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
*/
public class Comment {
	// TODO: Find why it is not a Long instead of String!
	// It is composed of sourceId_postId_commentId
	private String id = null;
	private User user = null;
	private String text = null;
	// Comments can't be updated and only have a creation time.
	private Long creationTime = null; // Unix time in seconds, not milliseconds

	public Comment() {
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

	public Long getCreationTime() {
		return creationTime;
	}

	/*
	 * @return Unix time in seconds, not milliseconds
	 */
	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}
	
}
