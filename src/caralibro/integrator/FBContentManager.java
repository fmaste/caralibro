package caralibro.integrator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import caralibro.dao.CommentDao;
import caralibro.dao.PostDao;
import caralibro.integrator.feed.CommentFeed;
import caralibro.integrator.feed.Feed;
import caralibro.integrator.feed.PostFeed;
import caralibro.integrator.feed.TypedFeed;
import caralibro.model.data.Application;
import caralibro.model.data.Comment;
import caralibro.model.data.Group;
import caralibro.model.data.Page;
import caralibro.model.data.Post;
import caralibro.model.data.Session;
import caralibro.model.data.User;

public class FBContentManager implements ContentManager {	
	private static enum FeedType {POST, COMMENT};
	private Application application;
	private Session session;
	private String sourceId;
	private Long nextUpdateStartTime = null;
	private Map<String,Integer> postIdCommentCount;
	
	public FBContentManager(Application application, Session session, User user) {
		this(application, session, user.getId().toString());
	}

	public FBContentManager(Application application, Session session, Page page) {
		this(application, session, page.getId().toString());
	}
	
	public FBContentManager(Application application, Session session, Group group) {
		this(application, session, group.getId().toString());
	}

	private FBContentManager(Application application, Session session, String sourceId) {
		this.application = application;
		this.session = session;
		this.sourceId = sourceId;
		this.postIdCommentCount = new HashMap<String,Integer>();
	}
	
	@Override
	public void setNextUpdateStartTime(Long startTime) {
		this.nextUpdateStartTime = startTime;
	}

	@Override
	public Collection<Feed> getFeeds() throws Exception {
		Long startTime = nextUpdateStartTime;
		if (startTime == null) {
			// Start time defaults to the past 24 hours
			startTime = System.currentTimeMillis()/1000 - 14L*60L*60L;
		}
		nextUpdateStartTime = System.currentTimeMillis()/1000;
		Collection<Post> posts = PostDao.getFromSourceId(application, session, sourceId, startTime, System.currentTimeMillis()/1000 - 12L*60L*60L);	
		Collection<Feed> feeds = new ArrayList<Feed>();
		if (posts != null) {
			for (Post post : posts) {
				if (post.getCreationTime() != null && post.getCreationTime() > startTime) {
					feeds.add(new TypedFeed(new  PostFeed(post), FeedType.POST.hashCode()));
				}
				if (post.getComments() == null || post.getComments() > 0) {
					Integer postComments = postIdCommentCount.get(post.getId());
					if (postComments == null || (post.getComments() != null && postComments < post.getComments())) {
						// Calls to Stream.getComments do not affect the limit of 100 request per 600 seconds!
						Collection<Comment> comments = CommentDao.getFromPost(application, session, post);				
						if (comments != null) {
							for (Comment comment : comments) {
								if (comment.getCreationTime() != null && comment.getCreationTime() > startTime) {
									feeds.add(new TypedFeed(new CommentFeed(comment), FeedType.COMMENT.hashCode()));
								}
							}
						}
					}
				}
				postIdCommentCount.put(post.getId(), post.getComments());
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
