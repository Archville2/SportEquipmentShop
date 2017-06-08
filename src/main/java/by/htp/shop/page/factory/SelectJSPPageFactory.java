package by.htp.shop.page.factory;

import by.htp.shop.page.SelectJSPPage;
import by.htp.shop.page.impl.SelectJSPPageImpl;

public class SelectJSPPageFactory {
	private final static SelectJSPPageFactory instance = new SelectJSPPageFactory();

	private final SelectJSPPage selectJSPPage = new SelectJSPPageImpl();

	public static SelectJSPPageFactory getInstance() {
		return instance;
	}
	
	public SelectJSPPage getSelectJSPPageImpl() {
		return selectJSPPage;
	}

}
