package caralibro.factory;

import caralibro.model.Page;

public class PageFactory {

	public static String createPageUrl(Page page) {
		return "http://www.facebook.com/pages/" + page.getName() + "/" + page.getId();
	}
	
	// TODO:
//	public static String createPage(String jsonResponse) {
//		
//	}
	
}
