package by.htp.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.shop.controller.command.Command;
import by.htp.shop.controller.exception.ControllerException;

public class ChangeLanguage implements Command {
	private static final String LOCALE = "locale";
	private static final String URL = "url";
	private static final String PAGE = "index.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		request.getSession(true).setAttribute(LOCALE, request.getParameter(LOCALE));

		String url = (String) request.getSession(true).getAttribute(URL);

		try {
			if (url == null) {
				response.sendRedirect(PAGE);
			}else{
				response.sendRedirect(url);
			}
		} catch (IOException e) {
			throw new ControllerException("IO problems", e);
		}
	}
}
