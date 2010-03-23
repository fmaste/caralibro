package caralibro.integrator;

import java.util.ArrayList;
import java.util.Collection;
import caralibro.dao.CommentDao;
import caralibro.dao.PostDao;
import caralibro.integrator.feed.CommentFeed;
import caralibro.integrator.feed.Feed;
import caralibro.integrator.feed.PostFeed;
import caralibro.integrator.feed.TypedFeed;
import caralibro.model.data.Application;
import caralibro.model.data.Group;
import caralibro.model.data.Page;
import caralibro.model.data.Session;
import caralibro.model.data.User;
import caralibro.model.data.stream.Comment;
import caralibro.model.data.stream.Post;

public class FBContentManager implements ContentManager {	
	private static enum SourceType {PAGE, GROUP, USER};
	private static enum FeedType {POST, COMMENT};
	private Application application;
	private Session session;
	private SourceType sourceType;
	private String sourceId;
	private Long nextUpdateStartTime = null;
	
	public FBContentManager(Application application, Session session, Page page) {
		this(application, session, page.getId().toString());
		sourceType = SourceType.PAGE;
	}
	
	public FBContentManager(Application application, Session session, Group group) {
		this(application, session, group.getId().toString());
		sourceType = SourceType.GROUP;
	}

	public FBContentManager(Application application, Session session, User user) {
		this(application, session, user.getId().toString());
		sourceType = SourceType.USER;
	}
	
	private FBContentManager(Application application, Session session, String sourceId) {
		this.application = application;
		this.session = session;
		this.sourceId = sourceId;
	}
	
	@Override
	public void setNextUpdateStartTime(Long startTime) {
		this.nextUpdateStartTime = startTime;
	}

	@Override
	public Collection<Feed> getFeeds() throws Exception {
		Long startTime = nextUpdateStartTime;
		if (startTime == null) {
			startTime = 0L;
		}
		nextUpdateStartTime = System.currentTimeMillis()/1000;		
		// This request uses the post's update_time. Posts with more than ~~ 50 comments have their update broken and
		// wont' be retrieved. However, it is still useful to add recently created posts and Fan's posts comments (usually have few comments)
		Collection<Post> posts = PostDao.getFromSourceId(application, session, sourceId, startTime, nextUpdateStartTime);	
		Collection<Feed> feeds = new ArrayList<Feed>();
		if (posts != null) {
			for (Post post : posts) {
				// Add recently created posts
				// We do not use the Post update_time because Facebook has bugs with it
				if (post.getCreationTime() != null && post.getCreationTime() > startTime) {
					feeds.add(new TypedFeed(new  PostFeed(post), FeedType.POST.hashCode()));
				}
				// Add recently created comments
				if (post.getComments() == null || post.getComments() > 0) {
					// Calls to Stream.getComments do not affect the limit of 100 request per 600 seconds!
					Collection<Comment> comments = CommentDao.getFromPost(application, session, post);				
					if (comments != null) {
						for (Comment comment : comments) {
							if (comment.getCreationTime() != null && comment.getCreationTime() > startTime) {
								feeds.add(new TypedFeed(new CommentFeed(comment, post.getPermaLink()), FeedType.COMMENT.hashCode()));
							}
						}
					}
				}
			}
		}		
		// TODO: avoid retrieving in FQL comments that were already added
		// This may happend with posts from the Fan Page that still don't have more than ~~ 50 comments
		// How does it treat repeated values?
		// ....
		// Make a second request to get new comments in posts with more than ~~ 50 comments and
		// whose author is the Fan page (Ex: Mauricio Macri)
		// to do this we ask for comments creation time instead of the post update_time. 
		// These functionality isn't available through the REST api so it must be done through FQL
		Collection<Comment> comments = CommentDao.get(application, session, sourceId, startTime, null);
		if (comments != null) {
			for (Comment comment : comments) {
				// This should have been checked in the query, do it anyway
				if (comment.getCreationTime() != null && comment.getCreationTime() > startTime) {
					feeds.add(new TypedFeed(new CommentFeed(comment, null), FeedType.COMMENT.hashCode()));
				}
			}
		}		
		return feeds;
	}
	
	@Override
	public boolean remove(Feed feed) throws Exception {
		if (feed instanceof TypedFeed) {
			TypedFeed typedFeed = (TypedFeed)feed;
			if (typedFeed.getType() == FeedType.POST.hashCode()) {
				Post post = ((PostFeed)typedFeed.getFeed()).getPost();
				return PostDao.remove(application, session, post);
			} else if (typedFeed.getType() == FeedType.COMMENT.hashCode()) {
				Comment comment = ((CommentFeed)typedFeed.getFeed()).getComment();
				return CommentDao.remove(application, session, comment);
			}
		}
		throw new Exception("Invalid feed! Was not created with this conten manager");
	}
	
}
