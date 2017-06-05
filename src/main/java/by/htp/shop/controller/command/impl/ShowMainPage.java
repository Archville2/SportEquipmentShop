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
import by.htp.shop.service.exception.ServiceException;
import by.htp.shop.service.factory.ServiceFactory;
import by.htp.shop.service.impl.ItemImplService;

public class ShowMainPage implements Command {
	private final static Logger LOGGER = Logger.getLogger(ShowMainPage.class);

	private final static String ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	private final static String CLIENT_PAGE = "/WEB-INF/jsp/client_page.jsp";
	private final static String ADMIN_PAGE = "/WEB-INF/jsp/admin_page.jsp";

	final String URL = "url";
	final String PAGE = "index.jsp";
	final String USER = "user";
	final String CATEGORY = "category";
	final String ITEMS = "items";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ItemImplService itemService = serviceFactory.getItemImplService();

		RequestDispatcher dispatcher = null;

		try {
			HttpSession session = request.getSession(true);
			session.setAttribute(URL, PAGE);
			dispatcher = request.getRequestDispatcher(PAGE);

			String currentCategory = (String) request.getParameter(CATEGORY);
			
			if (currentCategory == null) {
				currentCategory = "%";
			}

			if (session.getAttribute(USER) != null) {
				ClientData clientData = (ClientData) session.getAttribute(USER);

				if (clientData != null) {

					List<Item> itemList = itemService.formItemList(currentCategory);
					List<String> categoryList = itemService.formCategoryElementList();

					request.setAttribute(CATEGORY, categoryList);
					request.setAttribute(ITEMS, itemList);

					dispatcher = request.getRequestDispatcher(selectMainPage(clientData.getStatus()));
				}
			}
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e);
			
		} catch (ServletException | IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ControllerException("exception in ShowMainPage", e);
		}

	}

	private String selectMainPage(String status) {
		if (status.equals("user")) {
			return CLIENT_PAGE;
		}
		if (status.equals("admin")) {
			return ADMIN_PAGE;
		}
		return ERROR_PAGE;
	}
}
