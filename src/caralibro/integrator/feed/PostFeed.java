package caralibro.integrator.feed;

import java.util.Collection;
import caralibro.model.data.stream.Post;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
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
	public String getAuthorId() {
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
	public Collection<String> getPhotoUrls() {
		if (post.getLink() != null) {
			return post.getLink().getPhoto();
		} else {
			return null;
		}
	}

	@Override
	public Collection<String> getVideoUrls() {
		if (post.getLink() != null) {
			return post.getLink().getVideo();
		} else {
			return null;
		}
	}
	
	@Override
	public Collection<String> getLinkUrls() {
		if (post.getLink() != null) {
			return post.getLink().getWeb();
		} else {
			return null;
		}
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
