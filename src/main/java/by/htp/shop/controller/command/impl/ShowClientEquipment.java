package by.htp.shop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.htp.shop.bean.Item;
import by.htp.shop.controller.command.Command;
import by.htp.shop.controller.exception.ControllerException;
import by.htp.shop.page.PageSelector;
import by.htp.shop.page.exception.PageNotFoundException;
import by.htp.shop.page.factory.PageSelectorFactory;
import by.htp.shop.service.EquipmentService;
import by.htp.shop.service.exception.ServiceException;
import by.htp.shop.service.factory.ServiceFactory;

public class ShowClientEquipment implements Command {
	private final static Logger LOGGER = Logger.getLogger(ShowClientEquipment.class);
	
	private final static String MY = "my";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		EquipmentService equipmentService = serviceFactory.getEquipmentServiceImpl();
		PageSelectorFactory pageSelectorFactory = PageSelectorFactory.getInstance();

		PageSelector pageSelector = pageSelectorFactory.getPageSelectorImpl();
		
		RequestDispatcher dispatcher = null;
		int clientId = Integer.parseInt(request.getParameter("client_id"));
		
		try {
			List<Item> clientItems = equipmentService.formClientEquipment(clientId);
			request.setAttribute("client_items", clientItems);
			dispatcher = request.getRequestDispatcher(pageSelector.getPageURL(MY));
			dispatcher.forward(request, response);
			
		} catch (ServiceException | PageNotFoundException e) {
			throw new ControllerException(e.getMessage(), e);
			
		} catch (ServletException | IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ControllerException("exception in ShowClientItems", e);
		}
	}
}
