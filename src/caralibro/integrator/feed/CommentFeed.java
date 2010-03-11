package caralibro.integrator.feed;

import java.util.List;

import caralibro.model.data.Comment;

public class CommentFeed implements Feed {
	private Comment comment = null;
	
	public CommentFeed(Comment comment) {
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
	public String getUserId() {
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Long getCreationTime() {
		return comment.getCreationTime();
	}
	
	@Override
	public Long getUpdateTime() {
		return comment.getCreationTime();
	}
	
//	@Override
//	public int getType() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
