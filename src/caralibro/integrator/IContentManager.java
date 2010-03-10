package caralibro.integrator;

import java.util.Collection;
import java.util.Map;

import caralibro.dao.LoginDao;
import caralibro.dao.SessionDao;
import caralibro.factory.ApplicationFactory;
import caralibro.factory.RequestFactory;
import caralibro.factory.SessionFactory;
import caralibro.factory.UserFactory;
import caralibro.model.Application;
import caralibro.model.Comment;
import caralibro.model.Page;
import caralibro.model.Post;
import caralibro.model.Session;
import caralibro.model.User;
import caralibro.model.constants.Facebook;

public interface IContentManager {
	
	// This method should be invoked before performing other operations
	// public boolean initManager(InitData initData);
	
	// Return a collection of feeds
	public Collection<Feed> getFeeds() throws Exception;
	
	// Remove the given Feed
	public boolean remove(Feed feed) throws Exception;

	public Collection<Feed> getFeedsFromTime(Long time);

}
