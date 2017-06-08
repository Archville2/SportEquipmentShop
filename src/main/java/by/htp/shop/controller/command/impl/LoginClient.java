package by.htp.shop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.htp.shop.bean.ClientData;
import by.htp.shop.bean.Item;
import by.htp.shop.controller.command.Command;
import by.htp.shop.controller.exception.ControllerException;
import by.htp.shop.page.SelectJSPPage;
import by.htp.shop.page.exception.PageException;
import by.htp.shop.page.factory.SelectJSPPageFactory;
import by.htp.shop.service.ClientService;
import by.htp.shop.service.EquipmentService;
import by.htp.shop.service.exception.LoginException;
import by.htp.shop.service.exception.ServiceException;
import by.htp.shop.service.factory.ServiceFactory;

/**
 * Perform login class. Execution order - get client's info from db, check
 * clients's status, show start page according to user status. If there is no
 * info about client in db - show error page.
 */

public class LoginClient implements Command {
	final static Logger LOGGER = Logger.getLogger(LoginClient.class);

	private final static String LOGIN_PARAMETER = "login";
	private final static String PASSWORD_PARAMETER = "password";
	private final static String INDEX = "index";

	private final static String INCORRECT_LOGIN_CODE = "login_incorrect";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		SelectJSPPageFactory selectJSPPageFactory = SelectJSPPageFactory.getInstance();
		
		SelectJSPPage selectJSPPage = selectJSPPageFactory.getSelectJSPPageImpl();
		ClientService clientService = serviceFactory.getClientServiceImpl();
		EquipmentService equipmentService = serviceFactory.getEquipmentServiceImpl();

		RequestDispatcher dispatcher = null;
		String login = request.getParameter(LOGIN_PARAMETER);
		String password = request.getParameter(PASSWORD_PARAMETER);

		try {
			ClientData clientData = clientService.loginClient(login, password);
	
			if (clientData != null) {

				List<Item> equipmentList = equipmentService.formEquipmentList("%");
				List<String> categoryList = equipmentService.formCategoryElementList();
				request.setAttribute("category", categoryList);
				request.setAttribute("equipment", equipmentList);

				HttpSession session = request.getSession(true);
				session.setAttribute("user", clientData);
				session.setAttribute("url", "Controller?command=show_main_page");
			}
			dispatcher = request.getRequestDispatcher(selectJSPPage.GetPageURL(clientData.getStatus()));
			dispatcher.forward(request, response);

		} catch (LoginException ex) {

			try {
				request.setAttribute("message", INCORRECT_LOGIN_CODE);
				dispatcher = request.getRequestDispatcher(selectJSPPage.GetPageURL(INDEX));
				dispatcher.forward(request, response);

			} catch (PageException e) {
				throw new ControllerException(e.getMessage(), e);

			} catch (ServletException | IOException e1) {
				LOGGER.error(e1.getMessage(), e1);
				throw new ControllerException("exception in LoginClient", e1);
			}

		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e);

		} catch (PageException e) {
			throw new ControllerException(e.getMessage(), e);

		} catch (ServletException | IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ControllerException("exception in LoginClient", e);
		}

	}
}
