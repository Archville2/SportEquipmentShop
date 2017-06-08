package by.htp.shop.page.impl;

import java.util.HashMap;
import java.util.Map;

import by.htp.shop.page.PageSelector;
import by.htp.shop.page.exception.PageNotFoundException;

public class PageSelectorImpl implements PageSelector {
	private final Map<String, String> pages = new HashMap<>();

	public PageSelectorImpl() {
		pages.put("admin", "/WEB-INF/jsp/admin_page.jsp");
		pages.put("user", "/WEB-INF/jsp/client_page.jsp");
		pages.put("error", "/WEB-INF/jsp/error_page.jsp");
		pages.put("reg", "/WEB-INF/jsp/reg_client.jsp");
		pages.put("cart", "/WEB-INF/jsp/show_cart.jsp");
		pages.put("my", "/WEB-INF/jsp/my_items.jsp");
		pages.put("index", "index.jsp");
	}

	public String getPageURL(String message) throws PageNotFoundException {

		String result = pages.get(message);

		if (result != null) {
			return result;
		}
		throw new PageNotFoundException("incorrect key value");
	}
}
