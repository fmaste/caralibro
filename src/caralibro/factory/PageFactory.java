package caralibro.factory;

import org.json.JSONObject;

import caralibro.model.data.Page;

public class PageFactory {

	public static Page create(Long id, String name) {
		Page page = new Page();
		page.setId(id);
		page.setName(name);
		return page;
	}
	
	public static Page create(String pageJsonResponse) throws Exception {
		if (pageJsonResponse == null || pageJsonResponse.isEmpty() || !pageJsonResponse.startsWith("{")) {
			return null;
		}
		JSONObject pageJsonObject = new JSONObject(pageJsonResponse);
		String name = pageJsonObject.optString("name", null);
		Long id = null;
		if (pageJsonObject.has("page_id")) {
			id = pageJsonObject.getLong("page_id");
		} else {
			if (name == null || name.isEmpty()) {
				// Has no id and no name!!
				return null;
			}
		}
		return PageFactory.create(id, name);
	}
	
	public static String createUrl(Page page) {
		return "http://www.facebook.com/pages/" + page.getName() + "/" + page.getId();
	}
	
}
