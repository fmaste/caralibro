package caralibro.integrator.feed;

import java.util.List;

public class TypedFeed implements Feed {
	private Feed feed;
	private int type;
	
	public TypedFeed(Feed feed, int type) {
		this.feed = feed;
		this.type = type;
	}
	
	public Feed getFeed() {
		return feed;
	}
	
	public int getType() {
		return type;
	}
	
	@Override
	public String getId() {
		return feed.getId();
	}
	
	@Override
	public String getUserId() {
		return feed.getUserId();
	}
	
	@Override
	public String getText() {
		return feed.getText();
	}

	@Override
	public List<String> getPhotoUrls() {
		return feed.getPhotoUrls();
	}

	@Override
	public List<String> getVideoUrls() {
		return feed.getVideoUrls();
	}
	
	@Override
	public List<String> getLinkUrls() {
		return feed.getLinkUrls();
	}
	
	@Override
	public String getPermaLink() {
		return feed.getPermaLink();
	}
	
	@Override
	public Long getCreationTime() {
		return feed.getCreationTime();
	}
	
	@Override
	public Long getUpdateTime() {
		return feed.getUpdateTime();
	}

}
