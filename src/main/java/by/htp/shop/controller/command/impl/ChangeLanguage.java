package by.htp.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.htp.shop.controller.command.Command;
import by.htp.shop.controller.exception.ControllerException;
import by.htp.shop.page.PageSelector;
import by.htp.shop.page.exception.PageNotFoundException;
import by.htp.shop.page.factory.PageSelectorFactory;

public class ChangeLanguage implements Command {
	private final static Logger LOGGER = Logger.getLogger(ChangeLanguage.class);

	private static final String LOCALE = "locale";
	private static final String URL = "url";
	private static final String INDEX = "index";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		PageSelectorFactory selectJSPPageFactory = PageSelectorFactory.getInstance();

		PageSelector selectJSPPage = selectJSPPageFactory.getPageSelectorImpl();

		request.getSession(true).setAttribute(LOCALE, request.getParameter(LOCALE));

		String url = (String) request.getSession(true).getAttribute(URL);

		try {
			if (url == null) {
				url = selectJSPPage.getPageURL(INDEX);
			}

			response.sendRedirect(url);

		} catch (PageNotFoundException e) {
			throw new ControllerException(e.getMessage(), e);

		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ControllerException("exception in ChangeLanguage", e);
		}
	}
}
