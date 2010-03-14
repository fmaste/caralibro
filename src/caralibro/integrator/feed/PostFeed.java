package caralibro.integrator.feed;

import java.util.List;

import caralibro.model.data.Post;

public class PostFeed implements Feed {
	private Post post = null;
	
	public PostFeed(Post post) {
		this.post = post;
	}

	public Post getPost() {
		return post;
	}
	
	@Override
	public String getId() {
		return post.getId();
	}
	
	@Override
	public String getUserId() {
		if (post.getUser() != null) {
			if (post.getUser().getId() != null) {
				return post.getUser().getId().toString();
			}
		}
		return null;
	}
	
	@Override
	public String getText() {
		return post.getText();
	}

	@Override
	public List<String> getPhotoUrls() {
		return post.getPhotoUrls();
	}

	@Override
	public List<String> getVideoUrls() {
		return post.getVideoUrls();
	}
	
	@Override
	public List<String> getLinkUrls() {
		return post.getLinkUrls();
	}

	@Override
	public Long getCreationTime() {
		return post.getCreationTime();
	}
	
	@Override
	public Long getUpdateTime() {
		return post.getUpdateTime();
	}

	@Override
	public String getPermaLink() {
		return post.getPermaLink();
	}

}
