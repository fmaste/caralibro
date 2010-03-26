package caralibro;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import caralibro.dao.CommentDao;
import caralibro.dao.PageDao;
import caralibro.dao.PostDao;
import caralibro.factory.ApplicationFactory;
import caralibro.factory.SessionFactory;
import caralibro.factory.UserFactory;
import caralibro.model.data.Application;
import caralibro.model.data.Page;
import caralibro.model.data.Session;
import caralibro.model.data.stream.Comment;
import caralibro.model.data.stream.Post;

public class Main2 {
	// Application: testingAnApplication
	private static final String API_KEY = "060c9d27db80e7bc1dab1f3ec1e48f63";
	private static final String APPLICATION_SECRET = "8bf30da8712d1bc67962cd5d73c75634";
	private static final Long APPLICATION_ID = 354877556123L;
	// User: Fede Testing, fmaste@yahoo.com
	private static final Long USER_ID = 100000751425511L;
	// Session: Created infinite session with read_stream, and public_stream permissions
	private static final String SESSION_KEY = "09bdcc2318ac4f94265955bd-100000751425511";
	private static final String SESSION_SECRET = "4d8763e5bd00352c521c6ef2e8e65c6e";
	// Page: {"page_id":55432788477,"name":"Mauricio Macri"}
	private static final Long PAGE_ID = 55432788477L;
	private static final String PAGE_NAME = "Mauricio Macri";
	
	public static void main(String[] args) throws Exception {
		Application application = ApplicationFactory.create(APPLICATION_ID, API_KEY, APPLICATION_SECRET, true);
		Session session = SessionFactory.create(SESSION_KEY, SESSION_SECRET, USER_ID, 0L);		
		Map<String,Page> fanPages = PageDao.getFromUserByName(application, session);
		Page fanPage = fanPages.get(PAGE_NAME);
		if (fanPage != null) {
			// Start time default to 1 day and end time defaults to none!
			// Long startTime = System.currentTimeMillis()/1000 - 10L*60L*60L;
			Long startTime = null;
			Collection<Post> posts = PostDao.getFromPage(application, session, fanPage, startTime, null);
			if (posts != null) {
				int commentCount = 0;
				System.out.println(PAGE_NAME + " - " + new Date());
				System.out.println("Number of fectched posts: " + posts.size());
				System.out.println();
				System.out.println();
				for (Post post : posts) {
					if (startTime == null || post.getCreationTime() == null || post.getCreationTime() > startTime) {
						int postComments = 0;
						int postLikes = 0;
						if (post.getComments() != null) {
							postComments = post.getComments();
						}
						if (post.getLikes() != null) {
							postLikes = post.getLikes();
						}
						System.out.println("-------------------- POST " + post.getId() + " --------------------");
						System.out.println("Date: " + new Date(post.getCreationTime()*1000));
						System.out.println("Likes: " + postLikes);
						System.out.println("Comments: " + postComments);
						String from = "";
						if (post.getAuthorId() != null) {
							if (post.getAuthorId().equals(PAGE_ID) ) {
								from = PAGE_NAME;
							} else {
								from = String.valueOf(post.getAuthorId());
							}
						}
						System.out.println("From: " + from);
						System.out.println("Text: " + post.getText());
						Collection<Comment> comments = CommentDao.getFromPost(application, session, post);
						if (comments != null) {
							System.out.println("Number of fetched comments: " + comments.size());
							if (comments.size() != postComments) {
								System.out.println("ERROR!!!!!!!!!!!!!!");
							}
							for(Comment comment : comments) {
								System.out.println();
								System.out.println("-------------------- COMMENT " + comment.getId() + " --------------------");
								System.out.println("Date: " + new Date(comment.getCreationTime()*1000));
								from = "";
								if (comment.getAuthorId() != null) {
									if (comment.getAuthorId().equals(PAGE_ID)) {
										from = PAGE_NAME;
									} else {
										from = String.valueOf(comment.getAuthorId());
									}
								}
								System.out.println("From: " + from);
								System.out.println("Text: " + comment.getText());
								System.out.println();
							}
						} else {
							System.out.println("Number of fetched comments: " + 0);
						}
						// Method to remove, but I'm not the admin of that page!
						//PostDao.removePost(application, session, post);
						System.out.println();
						System.out.println();
					}	
				}
			} else {
				System.out.println("Page " + PAGE_NAME + " has no new posts on the time given");
			}
		} else {
			System.out.println("You are not a fan of " + PAGE_NAME);
		}
	}
	
}

// Macri's fan page older pots:

// By him:

// Text: Mauricio Macri Mauricio recorri� las intersecciones de las calles Nazca y Avellaneda junto al Secretario General de Gobierno, Marcos Pe�a.
// Video: Recorrida por Flores [HQ]
// Length:6:39
// Date: February 6, 2009 at 1:27am � Comment � Like � Share
// Extra Pablo Sebasti�n Herrera likes this.

// By fans:

// Elvira Lamarca 
// NO HAY CON QUE DARLE, MAURICIO ES UN HACEDOR!!! NO UN HABLADOR!! SUERTE QUE LO TENEMOS COMO JEFE DE GOBIERNO DE LA CIUDAD DE BS AS!!! VAMOS PASO X PASO, CON CLARIDAD Y SIEMPRE MIRANDO PARA ADELANTE, AUNQUE EL GOBIERNO NO LE DE LOS FONDOS QUE NECESITA PARA TOOOODOOOO LO QUE HAY QUE HACER, �L PUEDE Y LO VA A HACER, VAMOS MAURICIO Y GABRIELA!!
// January 27, 2009 at 1:15pm

// Maria Florencia Pasarin Aristegui 
// Mauricio 2011!
// un abrazo.
// January 27, 2009 at 1:12pm
