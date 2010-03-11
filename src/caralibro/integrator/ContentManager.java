package caralibro.integrator;

import java.util.Collection;
import caralibro.integrator.feed.Feed;

public interface ContentManager {
	
	public void setNextUpdateStartTime(Long startTime);
	
	// Return a collection of feeds (Can be null or empty)
	public Collection<Feed> getFeeds() throws Exception;
	
	// Remove the given Feed (returns false on error)
	public boolean remove(Feed feed) throws Exception;

}
