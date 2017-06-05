package by.htp.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.htp.shop.controller.command.Command;
import by.htp.shop.controller.exception.ControllerException;

public class ShowRegPage implements Command {
	private final static Logger LOGGER = Logger.getLogger(ShowRegPage.class);
	
	private final static String REG_PAGE_COMMAND = "Controller?command=show_reg_page";
	private final static String REG_PAGE = "/WEB-INF/jsp/reg_client.jsp"; 
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

		HttpSession session = request.getSession(true);
		session.setAttribute("url", REG_PAGE_COMMAND);
		RequestDispatcher dispatcher = request.getRequestDispatcher(REG_PAGE);
		
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ControllerException("exception in ShowRegPage", e);
		}

	}
}
