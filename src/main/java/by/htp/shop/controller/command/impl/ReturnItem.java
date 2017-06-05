package by.htp.shop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.shop.bean.Item;
import by.htp.shop.controller.command.Command;
import by.htp.shop.controller.exception.ControllerException;
import by.htp.shop.service.exception.ServiceException;
import by.htp.shop.service.factory.ServiceFactory;
import by.htp.shop.service.impl.ItemImplService;

public class ReturnItem implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ItemImplService itemService = serviceFactory.getItemImplService();
		RequestDispatcher dispatcher = null;

		int equipmentId = Integer.parseInt(request.getParameter("equipment_id"));
		int clientId = Integer.parseInt(request.getParameter("client_id"));

		try {
			itemService.removeRentedItem(clientId, equipmentId);
			
			List<Item> myItems = itemService.formClientItems(clientId);
			
			request.setAttribute("my_items", myItems);
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_items.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException | ServletException | IOException e) {
			e.printStackTrace();
		}

	}
}