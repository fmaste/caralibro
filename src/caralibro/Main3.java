package caralibro;

import java.util.Collection;
import caralibro.factory.ApplicationFactory;
import caralibro.factory.PageFactory;
import caralibro.factory.SessionFactory;
import caralibro.factory.UserFactory;
import caralibro.integrator.ContentManager;
import caralibro.integrator.FBContentManager;
import caralibro.integrator.feed.Feed;
import caralibro.model.data.Application;
import caralibro.model.data.Page;
import caralibro.model.data.Session;

public class Main3 {
	private static final String API_KEY = "060c9d27db80e7bc1dab1f3ec1e48f63";
	private static final String APPLICATION_SECRET = "8bf30da8712d1bc67962cd5d73c75634";
	private static final Long APPLICATION_ID = 354877556123L;
	private static final String SESSION_KEY = "09bdcc2318ac4f94265955bd-100000751425511";
	private static final String SESSION_SECRET = "4d8763e5bd00352c521c6ef2e8e65c6e";
	private static final Long USER_ID = 100000751425511L; // Fede Testing, fmaste@yahoo.com
	private static final Long PAGE_ID = 55432788477L;
	private static final String PAGE_NAME = "Mauricio Macri";
	
	public static void main(String[] args) throws Exception {
		Application application = ApplicationFactory.create(APPLICATION_ID, API_KEY, APPLICATION_SECRET, true);
		Session session = SessionFactory.create(SESSION_KEY, SESSION_SECRET, UserFactory.create(USER_ID), 0L);
		Page page = PageFactory.create(PAGE_ID, PAGE_NAME);
		
		ContentManager feeder = new FBContentManager(application, session, page);
		
		int i = 0;
		while(true) {
			System.err.println(i++);
			Collection<Feed> newFeeds = feeder.getFeeds();
			if (newFeeds != null) {
				for (Feed feed : newFeeds) {
					System.out.println("Feed:" + feed.getText());
					System.out.println("Feed:" + feed.getUpdateTime());
				}
			}
			Thread.sleep(6000);
		}
	}
	
}
