package caralibro.integrator;

import java.util.Collection;

public class Integrator {

	public static final int FB_APP = 0;
	public static final int OS_APP = 1;
	// etc..
	
	private IContentManager contentManager;
	private IKeepconManager keepconManager;
	private int type; 
	private InitData initData;
	
	public Integrator(int type, InitData initData) {
		
		try {
			this.type = type;
			this.initData = initData;
			
			switch (type) {
				case FB_APP: 
					contentManager = new FBContentManager((FBInitData) initData); 
					keepconManager = new KeepconManager();
					break;
				case OS_APP: 
					// not implemented yet
					; break;
				default: throw new Exception("Integrator received invalid content type");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Collection<Feed> getFeeds() throws Exception {
		return contentManager.getFeeds();
	}
	
	public Collection<Feed> getFeedsFromTime(Long time) {
		return contentManager.getFeedsFromTime(time);
	}
	
	public Collection<Feed> getFeedsToRemove(Collection<Feed> feeds) throws Exception {
		return keepconManager.getFeedsToRemove(feeds);
	}

	public boolean remove(Feed feed) throws Exception {
		return contentManager.remove(feed);
	}
}
