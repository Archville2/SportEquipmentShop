package by.htp.shop.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.shop.controller.command.Command;
import by.htp.shop.controller.exception.ControllerException;
import by.htp.shop.service.EquipmentService;
import by.htp.shop.service.exception.ServiceException;
import by.htp.shop.service.factory.ServiceFactory;

public class RentEquipment implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		EquipmentService equipmentService = serviceFactory.getEquipmentServiceImpl();

		int equipmentId = Integer.parseInt(request.getParameter("equipment_id"));
		int clientId = Integer.parseInt(request.getParameter("client_id"));
		int days = Integer.parseInt(request.getParameter("days"));

		try {
			equipmentService.addRentedItem(clientId, equipmentId, days);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e);
		}

	}
}