package by.htp.shop.page.factory;

import by.htp.shop.page.PageSelector;
import by.htp.shop.page.impl.PageSelectorImpl;

public class PageSelectorFactory {
	private final static PageSelectorFactory instance = new PageSelectorFactory();

	private final PageSelector pageSelector = new PageSelectorImpl();

	public static PageSelectorFactory getInstance() {
		return instance;
	}
	
	public PageSelector getPageSelectorImpl() {
		return pageSelector;
	}

}
