package caralibro.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import caralibro.factory.PageFactory;
import caralibro.model.data.Application;
import caralibro.model.data.Page;
import caralibro.model.data.Session;
import caralibro.rest.Request;
import caralibro.rest.Response;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class PageDao {
	private static final Logger logger = LoggerFactory.getLogger(PageDao.class);
	
	/*
	 * @return 		If there are no Pages returns null or empty.
	 */
	public static Collection<Page> getFromUser(Application application, Session session) throws Exception {
		Map<String,Page> pagesMap = getFromUserByName(application, session);
		return pagesMap.values();
	}

	/*
	 * @return 		If there are no Pages returns null or empty.
	 */
	public static Map<String,Page> getFromUserByName(Application application, Session session) throws Exception {
		logger.debug("Retrieving Pages from session \"" + session.getKey() + "\".");
		Map<String,String> params = Request.create(application, session, "Pages.getInfo");
		// page_id is always returned (whether included in fields or not, and always as the first subelement)
		params.put("fields", "page_id,name,username");
		Request.sign(params, application, session);
		String pagesJsonResponse = Response.get(params);
		// Warning: If there are no pages the response is like this
		// Response: {}
		if (pagesJsonResponse == null || pagesJsonResponse.isEmpty() || !pagesJsonResponse.startsWith("[")) {
			return null;
		}
		Map<String,Page> pages = new HashMap<String,Page>();
		JSONArray pagesJsonArray = new JSONArray(pagesJsonResponse);
		for (int i = 0; i < pagesJsonArray.length(); i++) {
			// The page index is retrieved as a String and PageFactory must know how to handle it!
			String pageString = pagesJsonArray.optString(i);
			if (pageString != null && !pageString.isEmpty()) {
				Page page = null;
				try {
					page = PageFactory.create(pageString);
				} catch (Exception e) {
					page = null;
					logger.error("Not a valid JSON encoded Page: \"" + pageString + "\".");
					e.printStackTrace();
				}
				if (page != null) {
					pages.put(page.getName(), page);
				}
			}
		}
		return pages;
	}
	
}
