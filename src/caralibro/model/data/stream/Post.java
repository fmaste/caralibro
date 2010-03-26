package caralibro.model.data.stream;

/*
 * Almost everything in Facebook is a post. Is the main part of Facebook's stream.
 * Things left can be Discussions, Events, Messages and Notes.
 * Posts can have links, photos, videos, likes and comments.
 * The update time of the post refers to new comments and likes.
 * The problem is that this time can't be trusted because of this bug: http://bugs.developers.facebook.com/show_bug.cgi?id=5624
 * Only users with an active session can post.
 *  
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class Post implements Stream {
	// It is composed of sourceId_postId
	private String id = null;
	private Long authorId = null;
	private String text = null;	
	private Link link = null;
	private Integer comments = null; // Comments count
	private Integer likes = null;	 // Likes count
	private Long creationTime = null; // Unix time in seconds, not milliseconds
	private Long updateTime = null; // Unix time in seconds, not milliseconds
	private String permaLink = null;

	public Post() {
	}

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public Long getAuthorId() {
		return authorId;
	}

	@Override
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
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
