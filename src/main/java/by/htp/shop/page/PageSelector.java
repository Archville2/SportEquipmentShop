package by.htp.shop.page;

import by.htp.shop.page.exception.PageNotFoundException;

public interface PageSelector {

	String getPageURL (String message) throws PageNotFoundException;
}

