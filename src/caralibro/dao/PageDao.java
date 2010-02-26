package caralibro.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import caralibro.Rest;
import caralibro.Utils;
import caralibro.factory.PageFactory;
import caralibro.model.Application;
import caralibro.model.Page;
import caralibro.model.Session;
import caralibro.model.constants.Facebook;


public class PageDao {

	public static Collection<Page> getUserFanPages(Application application, Session session) throws Exception {
		Map<String,Page> pagesMap = getUserFanPagesByName(application, session);
		return pagesMap.values();
	}
	
	public static Map<String,Page> getUserFanPagesByName(Application application, Session session) throws Exception {
		Map<String,String> params = Utils.initParams(application, "Pages.getInfo");
		params.put("session_key", session.getKey());
		params.put("fields", "page_id,name"); // page_id is always returned (whether included in fields or not, and always as the first subelement)
		Utils.finalizeParams(params, application);
		String jsonResponse = Rest.makeRequest(Facebook.REST_SERVER, params);
		JSONArray jsonPagesArray = new JSONArray(jsonResponse);
		Map<String,Page> ans = new HashMap<String,Page>();
		for (int i = 0; i < jsonPagesArray.length(); i++) {
			JSONObject jsonPageObject = jsonPagesArray.getJSONObject(i);
			String name = jsonPageObject.getString("name");
			ans.put(name, PageFactory.createPage(jsonPageObject.getLong("page_id"),name));
		}
		return ans;
	}
	
}
