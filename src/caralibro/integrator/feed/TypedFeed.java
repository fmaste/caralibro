package caralibro.integrator.feed;

import java.util.Collection;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
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
	public String getAuthorId() {
		return feed.getAuthorId();
	}
	
	@Override
	public String getText() {
		return feed.getText();
	}

	@Override
	public Collection<String> getPhotoUrls() {
		return feed.getPhotoUrls();
	}

	@Override
	public Collection<String> getVideoUrls() {
		return feed.getVideoUrls();
	}
	
	@Override
	public Collection<String> getLinkUrls() {
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
