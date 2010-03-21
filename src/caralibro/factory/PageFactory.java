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
	
	/*
	 * Parses a json string containing the page and creates the page object.
	 * If there is no apge_id, name or username an exception is thrown.
	 * 
	 * @param postJsonResponse 	One of the pages retrieved on the pages json array.
	 * @return 					A page object or null if the string is not a json object.
	 */
	public static Page create(String pageJsonResponse) throws Exception {
		if (pageJsonResponse == null || pageJsonResponse.isEmpty() || !pageJsonResponse.startsWith("{")) {
			return null;
		}
		JSONObject pageJsonObject = new JSONObject(pageJsonResponse);
		Long id = pageJsonObject.getLong("page_id");
		String name = pageJsonObject.getString("name");
		String username = pageJsonObject.getString("username");
		return PageFactory.create(id, name, username);
	}
	
	/*
	 * @return The fan page Facebook page.
	 */
	public static String createUrl(Page page) {
		return "http://www.facebook.com/pages/" + page.getName() + "/" + page.getId();
	}
	
}
