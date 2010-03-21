package caralibro.integrator.feed;

import java.util.List;

import caralibro.model.data.stream.Comment;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class CommentFeed implements Feed {
	private Comment comment = null;
	private String permalink = null;
	
	public CommentFeed(Comment comment, String permalink) {
		this.comment = comment;
	}
	
	public Comment getComment() {
		return comment;
	}
	
	@Override
	public String getId() {
		return comment.getId();
	}

	@Override
	public String getAuthorId() {
		if (comment.getUser() != null) {
			if (comment.getUser().getId() != null) {
				return comment.getUser().getId().toString();
			}
		}
		return null;
	}
	
	@Override
	public String getText() {
		return comment.getText();
	}
	
	@Override
	public List<String> getPhotoUrls() {
		return null;
	}

	@Override
	public List<String> getVideoUrls() {
		return null;
	}
	
	@Override
	public List<String> getLinkUrls() {
		return null;
	}

	@Override
	public String getPermaLink() {
		return permalink;
	}
	
	@Override
	public Long getCreationTime() {
		return comment.getCreationTime();
	}
	
	@Override
	public Long getUpdateTime() {
		// There is no update time, use the creation time!
		return comment.getCreationTime();
	}

}
