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
import by.htp.shop.service.EquipmentService;
import by.htp.shop.service.exception.ServiceException;
import by.htp.shop.service.factory.ServiceFactory;

public class ReturnEquipment implements Command {
	private final static Logger LOGGER = Logger.getLogger(ReturnEquipment.class);
	
	private final static String MY_ITEMS_PAGE = "/WEB-INF/jsp/my_items.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		EquipmentService equipmentService = serviceFactory.getEquipmentServiceImpl();
		RequestDispatcher dispatcher = null;

		int equipmentId = Integer.parseInt(request.getParameter("equipment_id"));
		int clientId = Integer.parseInt(request.getParameter("client_id"));

		try {
			equipmentService.removeRentedEquipment(clientId, equipmentId);

			List<Item> clientEquipment = equipmentService.formClientEquipment(clientId);

			request.setAttribute("client_items", clientEquipment);
			dispatcher = request.getRequestDispatcher(MY_ITEMS_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e);

		} catch (ServletException | IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ControllerException("Exception in ReturnEquipmentItem", e);
		}
	}
}