package by.htp.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.htp.shop.bean.ClientData;
import by.htp.shop.controller.command.Command;
import by.htp.shop.controller.exception.ControllerException;
import by.htp.shop.service.ClientService;
import by.htp.shop.service.exception.EmptyFieldsException;
import by.htp.shop.service.exception.LoginException;
import by.htp.shop.service.exception.ServiceException;
import by.htp.shop.service.factory.ServiceFactory;

public class RegNewClient implements Command {
	private final static Logger LOGGER = Logger.getLogger(RegNewClient.class);
	
	private final static String EMPTY_FIELD_MESSAGE = "field_empty";
	private final static String LOGIN_IN_USE_MESSAGE = "login_in_use";
	private final static String REGISTRATION_DONE_MESSAGE = "registration_done";
	
	private static final String REG_CLIENT_PAGE = "/WEB-INF/jsp/reg_client.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientServiceImpl();
		
		ClientData clientData = new ClientData(
				0,
				request.getParameter("name"),
				request.getParameter("surname"), 
				request.getParameter("email"),
				request.getParameter("phone"),
				request.getParameter("login"), 
				request.getParameter("password"), 
				"user");

		String message = REGISTRATION_DONE_MESSAGE;

		try {
			clientService.checkEmptyFields(clientData);
			clientService.checkLogin(request.getParameter("login"));
			clientService.registerClient(clientData);

		} catch (EmptyFieldsException e) {
			message = EMPTY_FIELD_MESSAGE;

		} catch (LoginException e) {
			message = LOGIN_IN_USE_MESSAGE;

		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e);
		}

		request.setAttribute("message", message);
		request.setAttribute("client", clientData);
		RequestDispatcher diapatcher = request.getRequestDispatcher(REG_CLIENT_PAGE);

		HttpSession session = request.getSession(true);

		if (clientData.getId() != 0) {
			session.setAttribute("user", clientData);
		}

		request.setAttribute("user", clientData);

		try {
			diapatcher.forward(request, response);

		} catch (IOException | ServletException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ControllerException("exception in RegNewClient", e);
		}
	}
}
