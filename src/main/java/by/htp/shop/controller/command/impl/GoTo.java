package by.htp.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.shop.controller.command.Command;
import by.htp.shop.controller.exception.ControllerException;

public class GoTo implements Command {
	private static final String GO_TO_PAGE = "go_to_page";
	private static final String URL = "url";
	private static final String PAGE = "/WEB-INF/jsp/";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		String page = request.getParameter(GO_TO_PAGE);
		HttpSession session = request.getSession(true);

		String query = request.getQueryString();
		String uri = request.getRequestURI();

		session.setAttribute(URL, uri + "?" + query);

		RequestDispatcher dispatcher = request.getRequestDispatcher(PAGE + page);

		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			throw new ControllerException("exeption in GoTo", e);
		}
	}
}
