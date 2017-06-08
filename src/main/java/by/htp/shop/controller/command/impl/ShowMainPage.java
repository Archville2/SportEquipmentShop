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
import by.htp.shop.page.PageSelector;
import by.htp.shop.page.exception.PageNotFoundException;
import by.htp.shop.page.factory.PageSelectorFactory;
import by.htp.shop.service.EquipmentService;
import by.htp.shop.service.exception.ServiceException;
import by.htp.shop.service.factory.ServiceFactory;

public class ShowMainPage implements Command {
	private final static Logger LOGGER = Logger.getLogger(ShowMainPage.class);

	private final String URL = "url";
	private final String USER = "user";
	private final String INDEX = "index";
	private final String CATEGORY = "category";
	private final String EQUIPMENT = "equipment";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		PageSelectorFactory selectJSPPageFactory = PageSelectorFactory.getInstance();

		PageSelector selectJSPPage = selectJSPPageFactory.getPageSelectorImpl();
		EquipmentService eqipmentService = serviceFactory.getEquipmentServiceImpl();

		RequestDispatcher dispatcher = null;

		try {
			HttpSession session = request.getSession(true);
			session.setAttribute(URL, selectJSPPage.getPageURL(INDEX));
			dispatcher = request.getRequestDispatcher(selectJSPPage.getPageURL(INDEX));

			String currentCategory = (String) request.getParameter(CATEGORY);
			
			if (currentCategory == null) {
				currentCategory = "%";
			}

			if (session.getAttribute(USER) != null) {
				ClientData clientData = (ClientData) session.getAttribute(USER);

				if (clientData != null) {

					List<Item> equipmentList = eqipmentService.formEquipmentList(currentCategory);
					List<String> categoryList = eqipmentService.formCategoryElementList();

					request.setAttribute(CATEGORY, categoryList);
					request.setAttribute(EQUIPMENT, equipmentList);

					dispatcher = request.getRequestDispatcher(selectJSPPage.getPageURL(clientData.getStatus()));
				}
			}
			dispatcher.forward(request, response);
			
		} catch (ServiceException | PageNotFoundException e) {
			throw new ControllerException(e.getMessage(), e);

		} catch (ServletException | IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ControllerException("exception in ShowMainPage", e);
		}

	}
}
