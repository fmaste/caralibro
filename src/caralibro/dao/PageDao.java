package caralibro.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import caralibro.factory.PageFactory;
import caralibro.factory.RequestFactory;
import caralibro.model.data.Application;
import caralibro.model.data.Page;
import caralibro.model.data.Session;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class PageDao {
	private static final Logger logger = LoggerFactory.getLogger(PageDao.class);
	
	public static Collection<Page> getFromUser(Application application, Session session) throws Exception {
		Map<String,Page> pagesMap = getFromUserByName(application, session);
		return pagesMap.values();
	}
	
	public static Map<String,Page> getFromUserByName(Application application, Session session) throws Exception {
		Map<String,String> params = RequestFactory.create(application, session, "Pages.getInfo");
		params.put("fields", "page_id,name,username"); // page_id is always returned (whether included in fields or not, and always as the first subelement)
		RequestFactory.sign(params, application, session);
		String pagesJsonResponse = ResponseDao.get(params);
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
				Page page = PageFactory.create(pageString);
				if (page != null) {
					pages.put(page.getName(), page);
				}
			}
		}
		return pages;
	}
	
}
