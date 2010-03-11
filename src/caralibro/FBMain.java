package caralibro;

import java.util.Collection;
import java.util.Iterator;

import caralibro.integrator.FBInitData;
import caralibro.integrator.Integrator;
import caralibro.integrator.feed.Feed;


public class FBMain {

	// KeepconIntegration app info
	private static final String API_KEY = "6d7a637376b3dcbf558fbabe52897e08";
	private static final String APPLICATION_SECRET = "63e2c64e7f3617a7cde6a966102c75cc";
	private static final Long APPLICATION_ID = 355692626362L;
	// Session info for KeepconIntegration Fan Page
	private static final String SESSION_KEY = "7d341e2d9924e27b418bb287-100000367053136";
	private static final String SESSION_SECRET = "1e818f05ca08bf58de137bee10edec05";
	// Source info
	private static final String GROUP_NAME = "KeepconIntegration";
	private static final String FAN_PAGE_NAME = "KeepconIntegration Fan Page";
	// User info for Simon Aberg Cobo
	private static final Long USER_ID = 100000367053136L;
	
	
	public static void main(String[] args) throws Exception {
		
		// Create necessary data to start a FB app
		FBInitData initData = new FBInitData(APPLICATION_ID, API_KEY, APPLICATION_SECRET, true,
				SESSION_KEY, SESSION_SECRET, USER_ID, GROUP_NAME, FBInitData.GROUP);
		
		// Start Integrator
		Integrator integrator = new Integrator(Integrator.FB_APP, initData);
		
		// Get posts and comments
		Collection<Feed> feeds = integrator.getFeeds();
		
		// Get posts and comments that should be removed
		Collection<Feed> toRemove = integrator.getFeedsToRemove(feeds);
		
		// Remove them
		if (toRemove != null) {
			Iterator<Feed> iterator = toRemove.iterator();
			while (iterator.hasNext()) {
				Feed feed = iterator.next();		
				integrator.remove(feed);
			}
		}
		
	}

}
