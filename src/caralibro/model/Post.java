package caralibro.model;

import java.util.List;

import caralibro.integrator.Feed;

// Almost everything in Facebook is a post.
// Posts can have links, photos, videos, likes and comments!
// The update time of the post refers to new comments and likes!
public class Post extends Feed {
	
	private Integer comments = null;
	private Integer likes = null;

	public Post() {
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
}
