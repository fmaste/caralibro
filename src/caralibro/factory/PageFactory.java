package caralibro.factory;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import caralibro.model.data.Page;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class PageFactory {
	private static final Logger logger = LoggerFactory.getLogger(PageFactory.class);
	
	public static Page create(Long id, String name, String username) {
		Page page = new Page();
		page.setId(id);
		page.setName(name);
		page.setUsername(username);
		return page;
	}
	
	/*
	 * Parses a JSON string containing the Page and creates the Page object.
	 * If there is no apge_id, name or username key an exception is thrown.
	 * 
	 * @param postJsonResponse 	One of the pages retrieved on the Pages JSON array.
	 * @return 					A Page object or null if the string is not a JSON object.
	 */
	public static Page create(String pageJsonResponse) throws Exception {
		logger.debug("Parsing JSON encoded Page: \"" + pageJsonResponse + "\"");
		if (pageJsonResponse == null || pageJsonResponse.isEmpty() || !pageJsonResponse.startsWith("{")) {
			logger.error("Not a valid JSON encoded Page: \"" + pageJsonResponse + "\"");
			return null;
		}
		JSONObject pageJsonObject = new JSONObject(pageJsonResponse);
		Long id = pageJsonObject.getLong("page_id");
		String name = pageJsonObject.getString("name");
		String username = pageJsonObject.getString("username");
		return PageFactory.create(id, name, username);
	}
	
	/*
	 * @return 		The fan page Facebook page.
	 */
	public static String createUrl(Page page) {
		if (page != null && page.getName() != null && page.getId() != null ) {
			return "http://www.facebook.com/pages/" + page.getName() + "/" + page.getId();
		} else {
			return null;
		}
	}
	
}
