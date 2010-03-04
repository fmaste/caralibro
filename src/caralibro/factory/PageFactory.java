package caralibro.factory;

import caralibro.model.Page;

public class PageFactory {

	public static Page create(Long id, String name) {
		Page page = new Page();
		page.setId(id);
		page.setName(name);
		return page;
	}
	
	public static String createUrl(Page page) {
		return "http://www.facebook.com/pages/" + page.getName() + "/" + page.getId();
	}
	
}
