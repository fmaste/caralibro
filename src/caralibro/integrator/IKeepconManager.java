package caralibro.integrator;

import java.util.Collection;
import caralibro.integrator.feed.Feed;

public interface IKeepconManager {

	public Collection<Feed> getFeedsToRemove(Collection<Feed> feeds) throws Exception;
	
}
