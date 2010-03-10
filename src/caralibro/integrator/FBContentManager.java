package caralibro.integrator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import caralibro.dao.CommentDao;
import caralibro.dao.GroupDao;
import caralibro.dao.LoginDao;
import caralibro.dao.PageDao;
import caralibro.dao.PostDao;
import caralibro.dao.SessionDao;
import caralibro.factory.ApplicationFactory;
import caralibro.factory.SessionFactory;
import caralibro.factory.UserFactory;
import caralibro.model.Application;
import caralibro.model.Comment;
import caralibro.model.Group;
import caralibro.model.Page;
import caralibro.model.Post;
import caralibro.model.Session;
import caralibro.model.User;

public class FBContentManager implements IContentManager {
	
	private Application application;
	private Session session;
	private User user;
	private Page page = null;
	private Group group = null;

	public FBContentManager(FBInitData initData) throws Exception {
		
		this.application = ApplicationFactory.create(initData.getId(), initData.getKey(), initData.getSecrect(), initData.isDesktop());
		this.session = SessionFactory.create(initData.getSessionKey(), initData.getSessionSecret(), UserFactory.create(initData.getUserId()),
				initData.getExpirationTime());
		this.user = UserFactory.create(initData.getUserId());
		
		switch(initData.getSourceType()) {
			case FBInitData.FAN_PAGE: 
				Map<String,Page> fanPages = PageDao.getFromUserByName(application, session);
		 		this.page = fanPages.get(initData.getSourceName());
				break;
			case FBInitData.GROUP:
				Map<String,Group> groups = GroupDao.getFromUserByName(application, session);
		 		this.group = groups.get(initData.getSourceName()); ;
				break;
			default: throw new Exception("Invalid source type");
		}
	}

	@Override
	public Collection<Feed> getFeeds() throws Exception {
		
		// TODO: Brings posts from a determined fan page only
		Collection<Post> posts;
		
		if (page != null) {
			posts = PostDao.getFromPage(application, session, page, null, null);
		} else if (group != null) {
			posts = PostDao.getFromGroup(application, session, group, null, null);
		} else {
			return null;
		}
		
		Collection<Feed> answer = new ArrayList<Feed>();
		
		if (posts != null) {
			for (Post post : posts) {
				System.out.println("Post text: " + post.getText());
				answer.add(post);
				
				Collection<Comment> comments = CommentDao.getFromPost(application, session, post);
				if (comments != null) {
					for (Comment comment : comments) {
						System.out.println("Comment: " + comment.getText());
						answer.add(comment);
					}				
				}
			}
		}
		
		return answer;
	}

	@Override
	public Collection<Feed> getFeedsFromTime(Long time) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean remove(Feed feed) throws Exception {
		if (feed instanceof Post) {
			Post post = (Post) feed;
			if (post.getVideoUrls() != null) {
				System.out.println("*Removing* post with video, video url: " + post.getVideoUrls().get(0));
				return false;
			}
			return PostDao.remove(application, session, post);
		} else if (feed instanceof Comment) {
			Comment comment = (Comment) feed;
			return CommentDao.remove(application, session, comment);
		} else {
			return false;
		}
	}
}
