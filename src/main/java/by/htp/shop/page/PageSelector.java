package by.htp.shop.page;

import by.htp.shop.page.exception.PageException;

public interface PageSelector {

	String getPageURL (String message) throws PageException;
}

