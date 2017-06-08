package by.htp.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.htp.shop.controller.command.Command;
import by.htp.shop.controller.exception.ControllerException;
import by.htp.shop.page.PageSelector;
import by.htp.shop.page.exception.PageNotFoundException;
import by.htp.shop.page.factory.PageSelectorFactory;

public class LogOutClient implements Command {
	private final static Logger LOGGER = Logger.getLogger(LogOutClient.class);
	private final static String INDEX = "index";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		PageSelectorFactory selectJSPPageFactory = PageSelectorFactory.getInstance();

		PageSelector selectJSPPage = selectJSPPageFactory.getPageSelectorImpl();

		HttpSession session = request.getSession(true);
		session.removeAttribute("user");

		try {
			session.setAttribute("url", selectJSPPage.getPageURL(INDEX));
			response.sendRedirect(selectJSPPage.getPageURL(INDEX));

		} catch (PageNotFoundException e) {
			throw new ControllerException(e.getMessage(), e);

		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ControllerException("exception in LogOutClient", e);
		}
	}
}
