package caralibro;

import java.util.Collection;
import java.util.Map;
import caralibro.dao.CommentDao;
import caralibro.dao.PageDao;
import caralibro.dao.PostDao;
import caralibro.factory.ApplicationFactory;
import caralibro.factory.SessionFactory;
import caralibro.factory.UserFactory;
import caralibro.model.data.Application;
import caralibro.model.data.Comment;
import caralibro.model.data.Page;
import caralibro.model.data.Post;
import caralibro.model.data.Session;

public class Main2 {
	// Application: testingAnApplication	
	private static final String API_KEY = "060c9d27db80e7bc1dab1f3ec1e48f63";
	private static final String APPLICATION_SECRET = "8bf30da8712d1bc67962cd5d73c75634";
	private static final Long APPLICATION_ID = 354877556123L;
	// User: Fede Testing, fmaste@yahoo.com
	private static final Long USER_ID = 100000751425511L;
	// Session: Created infinite session with read_stream, and publich_stream permissions
	private static final String SESSION_KEY = "09bdcc2318ac4f94265955bd-100000751425511";
	private static final String SESSION_SECRET = "4d8763e5bd00352c521c6ef2e8e65c6e";
	// Page: {"page_id":55432788477,"name":"Mauricio Macri"}
	private static final Long PAGE_ID = 55432788477L;
	private static final String PAGE_NAME = "Mauricio Macri";
	
	public static void main(String[] args) throws Exception {
		Application application = ApplicationFactory.create(APPLICATION_ID, API_KEY, APPLICATION_SECRET, true);
		Session session = SessionFactory.create(SESSION_KEY, SESSION_SECRET, UserFactory.create(USER_ID), 0L);		
		Map<String,Page> fanPages = PageDao.getFromUserByName(application, session);
		Page fanPage = fanPages.get(PAGE_NAME);
		if (fanPage != null) {
			// Start time default to 1 day and end time defaults to none!
			Collection<Post> posts = PostDao.getFromPage(application, session, fanPage, null, null);
			if (posts != null) {
				System.out.println("Page " + PAGE_NAME + " has " + posts.size() + " posts");
				for (Post post : posts) {
					System.out.println("Post:" + post.getText());
					Collection<Comment> comments = CommentDao.getFromPost(application, session, post);
					if (comments != null) {
						System.out.println("Number of comments: " + comments.size());
						for(Comment comment : comments) {
							System.out.println("Comment:" + comment.getText());
						}
					} else {
						System.out.println("Number of comments: " + 0);
					}
					// Method to remove, but I'm not the admin of that page!
					//PostDao.removePost(application, session, post);
				}
			} else {
				System.out.println("Page " + PAGE_NAME + " has no new posts on the time given");
			}
		} else {
			System.out.println("You are not a fan of " + PAGE_NAME);
		}
	}
	
}
