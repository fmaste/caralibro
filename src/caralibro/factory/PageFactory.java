package caralibro.factory;

import org.json.JSONObject;
import caralibro.model.data.Page;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class PageFactory {

	public static Page create(Long id, String name, String username) {
		Page page = new Page();
		page.setId(id);
		page.setName(name);
		page.setUsername(username);
		return page;
	}
	
	public static Page create(String pageJsonResponse) throws Exception {
		if (pageJsonResponse == null || pageJsonResponse.isEmpty() || !pageJsonResponse.startsWith("{")) {
			return null;
		}
		JSONObject pageJsonObject = new JSONObject(pageJsonResponse);
		Long id = pageJsonObject.getLong("page_id");
		String name = pageJsonObject.optString("name", null);
		String username = pageJsonObject.optString("username", null);
		return PageFactory.create(id, name, username);
	}
	
	public static String createUrl(Page page) {
		return "http://www.facebook.com/pages/" + page.getName() + "/" + page.getId();
	}
	
}
