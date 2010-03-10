package caralibro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;

import caralibro.dao.CommentDao;
import caralibro.dao.LoginDao;
import caralibro.dao.PageDao;
import caralibro.dao.PostDao;
import caralibro.dao.SessionDao;
import caralibro.factory.ApplicationFactory;
import caralibro.factory.AuthorizationFactory;
import caralibro.factory.LoginFactory;
import caralibro.factory.SessionFactory;
import caralibro.model.Application;
import caralibro.model.Comment;
import caralibro.model.Page;
import caralibro.model.Post;
import caralibro.model.Session;

// Setting Up Your Application:

// YouÕll need a Facebook account to do this.

// First you need to log in to the Facebook Developer application: www.facebook.com/developers
// After following the link, click ÒAllowÓ to let the Developer application access your profile.

// Begin setting up a new application. Go to the Developer application and click ÒSet Up New ApplicationÓ. 
// Give your application a name, check to accept the Terms of Service, then click Submit. 
// You'll see some basic information about your application.
// Your API key: this key identifies your application to Facebook. You pass it with all your API calls.
// Your application secret: Facebook uses this key to authenticate the requests you make. As you can tell by its name, you should never share this key with anyone.

// When configuring your desktop application in the Facebook Developer application, you must specify that it's a desktop application.
// On the Advanced tab in the application settings editor, select Desktop for Application Type.
// If you do this, the session secret instead of the application secret is used to sign the requests by passing the ss=true parameter.
// You can also specify that it is installable to users and pages on the authenticatin tab.

public class Main {
	private static final String API_KEY = "6d7a637376b3dcbf558fbabe52897e08";
	private static final String APPLICATION_SECRET = "63e2c64e7f3617a7cde6a966102c75cc";
	private static final Long APPLICATION_ID = 355692626362L;
	
	//private static final String SESSION_KEY = "66d86ea9865f9966f5801ea8-1443735325";
	//private static final String SESSION_SECRET = "acd23da171e4b436136c2f951e917099";
	private static final Long SESSION_EXPIRATION_TIME = 0L;
	private static final Long USER_ID = 1443735325L;
	private static final String FAN_PAGE_NAME = "KeepconIntegration Fan Page"; // Page id is 326834508374
	
	// www.facebook.com/connect/prompt_permissions.php?api_key=060c9d27db80e7bc1dab1f3ec1e48f63&fbconnect=true&v=1.0&display=popup&extern=1&next=www.facebook.com/connect/login_success.html&ext_perm=read_stream,offline_access&enable_profile_selector=1profile_selector_ids=326834508374
	// http://www.facebook.com/authorize.php?api_key=060c9d27db80e7bc1dab1f3ec1e48f63&v=1.0&ext_perm=read_stream,offline_access&enable_profile_selector=1profile_selector_ids=326834508374
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// Create my Application
		// Facebook applications must be created manually. Create and provide the data here!
		Application application = ApplicationFactory.create(APPLICATION_ID, API_KEY, APPLICATION_SECRET, true);
		
		// Authenticate!
		//String authenticationToken = LoginDao.generateAuthenticationToken(application);		
		// Using the token to open the browser to your application's client to log in.
		//String loginUrl = LoginFactory.createUrlFromAuthenticationToken(application, authenticationToken, "read_stream,offline_access"); 
		String loginUrl = LoginFactory.createUrlForCodeGenerator(application);
		System.out.println("Opening browser to this url: " + loginUrl);
		//Runtime.getRuntime().exec("Firefox " + loginUrl); // FIXME: OS X only!
		
		System.out.println("Please login and press enter when finished.");
		System.out.println("Grant access to your Page!!.");
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		String code = in.readLine();
		//Session session = SessionDao.getSessionFromToken(application, authenticationToken);
		Session session = SessionDao.getFromCode(application, code);
		
		System.out.println("Session key generated: " + session.getKey());
		System.out.println("Session secrect generated: " + session.getSecret());
		
		String authorizationUrl = AuthorizationFactory.createUrl(application, "read_stream,publish_stream");
		System.out.println("Authorization url: " + authorizationUrl);
		//Runtime.getRuntime().exec("Firefox " + authorizationUrl); // FIXME: OS X only!
		System.in.read();
//		Session session = SessionFactory.createSession(SESSION_KEY, SESSION_SECRET, UserFactory.createUser(USER_ID), SESSION_EXPIRATION_TIME);
		
		Map<String,Page> fanPages = PageDao.getFromUserByName(application, session);
		Page fanPage = fanPages.get(FAN_PAGE_NAME);
		Collection<Post> posts = PostDao.getFromPage(application, session, fanPage, null, null);
		if (posts != null) {
			for (Post post : posts) {
				System.out.println("Post text: " + post.getText());
				Collection<Comment> comments = CommentDao.getFromPost(application, session, post);
				if (comments != null) {
					for (Comment comment : comments) {
						System.out.println("Comment: " + comment.getText());
					}				
				}
				//PostDao.remove(application, session, post);
			}
		}
	}
	
}

// Requests made with user: 100000751425511
// Macri fan page:
// {"page_id":55432788477,"name":"Mauricio Macri"}
// Macri post:
// {"post_id":"55432788477_366577523477","viewer_id":100000751425511,"source_id":55432788477,"type":247,"app_id":null,"attribution":"","actor_id":55432788477,"target_id":null,"message":"Ayer inauguramos las obras de remodelaci\u00f3n del Parque Pereyra, en Barracas. Incorporamos bebederos, cestos, bancos, bicicleteros, luminarias y trabajos de pintura y herrer\u00eda. Ahora necesitamos la colaboraci\u00f3n de todos ustedes para mantenerlo y cuidarlo.","attachment":{"media":[{"href":"http:\/\/www.facebook.com\/photo.php?pid=4854009&id=55432788477","alt":"","type":"photo","src":"http:\/\/photos-e.ak.fbcdn.net\/hphotos-ak-snc3\/hs498.snc3\/27208_366576048477_55432788477_4854009_8231427_s.jpg","photo":{"aid":"55432788477_193359","pid":"55432788477_4854009","owner":55432788477,"index":1,"width":720,"height":490}},{"href":"http:\/\/www.facebook.com\/photo.php?pid=4854010&id=55432788477","alt":"","type":"photo","src":"http:\/\/photos-b.ak.fbcdn.net\/hphotos-ak-snc3\/hs498.snc3\/27208_366576088477_55432788477_4854010_3846460_s.jpg","photo":{"aid":"55432788477_193359","pid":"55432788477_4854010","owner":55432788477,"index":2,"width":720,"height":480}},{"href":"http:\/\/www.facebook.com\/photo.php?pid=4854011&id=55432788477","alt":"","type":"photo","src":"http:\/\/photos-h.ak.fbcdn.net\/hphotos-ak-snc3\/hs498.snc3\/27208_366576123477_55432788477_4854011_6649628_s.jpg","photo":{"aid":"55432788477_193359","pid":"55432788477_4854011","owner":55432788477,"index":3,"width":720,"height":487}}],"name":"Parque Pereyra remodelado","href":"http:\/\/www.facebook.com\/album.php?aid=193359&id=55432788477","description":"","properties":{},"icon":"http:\/\/static.ak.fbcdn.net\/rsrc.php\/z4848\/hash\/8as8iqdm.png","fb_object_type":"album","fb_object_id":"55432788477_193359"},"app_data":{},"action_links":null,"comments":{"can_remove":false,"can_post":true,"count":52,"comment_list":{}},"likes":{"href":"http:\/\/www.facebook.com\/social_graph.php?node_id=366573733477&class=LikeManager","count":144,"sample":[1077605928],"friends":{},"user_likes":false,"can_like":true},"privacy":{"value":"NOT_EVERYONE"},"updated_time":1267643934,"created_time":1267643934,"tagged_ids":{},"is_hidden":false,"filter_key":"","permalink":"http:\/\/www.facebook.com\/album.php?aid=193359&id=55432788477&comments"}
// Fan post:
// {"post_id":"55432788477_367545758477","viewer_id":100000751425511,"source_id":55432788477,"type":56,"app_id":null,"attribution":null,"actor_id":100000551962965,"target_id":55432788477,"message":"Hoa Mauricio Fuerza !!!!!!!!!!!!!!!!!!!!!1, dale que la oposici\u00f3n hoy parece que vienen pisando fuerte, espero no se descuelguen los opositores que no sirven deben estar todos unidos !!!!!!!!!!!!!1\nUn beso\nMarit\u00e9","attachment":{"description":""},"app_data":{},"action_links":null,"comments":{"can_remove":false,"can_post":true,"count":0,"comment_list":{}},"likes":{"href":"http:\/\/www.facebook.com\/social_graph.php?node_id=367545758477&class=LikeManager","count":0,"sample":{},"friends":{},"user_likes":false,"can_like":true},"privacy":{"value":"NOT_EVERYONE"},"updated_time":1267678574,"created_time":1267678574,"tagged_ids":null,"is_hidden":false,"filter_key":"","permalink":"http:\/\/www.facebook.com\/mauriciomacri?v=feed&story_fbid=367545758477"}
