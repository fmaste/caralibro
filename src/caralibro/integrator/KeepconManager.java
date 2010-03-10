package caralibro.integrator;

import java.util.Collection;

public class KeepconManager implements IKeepconManager {

	//TODO: Should send data to Keepcon platform in a way it understands it and receive an answer with feeds that should be removed
	@Override
	public Collection<Feed> getFeedsToRemove(Collection<Feed> feeds)
			throws Exception {
		
		// Robot: remove all posts
		return feeds;
	}

}
