package by.htp.shop.page.impl;

import java.util.HashMap;
import java.util.Map;

import by.htp.shop.page.SelectJSPPage;
import by.htp.shop.page.exception.PageException;

public class SelectJSPPageImpl implements SelectJSPPage {

	public String GetPageURL(String message) throws PageException {
		Map<String, String> pages = new HashMap<>();

		pages.put("admin", "/WEB-INF/jsp/admin_page.jsp");
		pages.put("user", "/WEB-INF/jsp/client_page.jsp");
		pages.put("error", "/WEB-INF/jsp/error_page.jsp");
		pages.put("index", "index.jsp");

		String result = pages.get(message);
		
		if (result != null) {
			return result;
		}
		throw new PageException("incorrect key value");
	}
}
