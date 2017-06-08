package by.htp.shop.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.shop.bean.ClientData;
import by.htp.shop.controller.command.Command;
import by.htp.shop.controller.exception.ControllerException;
import by.htp.shop.service.EquipmentService;
import by.htp.shop.service.factory.ServiceFactory;

/**
 * Add equipment to cart command implementation. Using list of integer id's to
 * store equipment id. Checking if equipment id already in list and if it is not
 * add new id to cart.
 */

public class AddToCart implements Command {

	private final static String CART = "cart";
	private final static String USER = "user";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		EquipmentService equipmentService = serviceFactory.getEquipmentServiceImpl();
		int id = Integer.parseInt(request.getParameter(CART));

		HttpSession session = request.getSession(true);
		ClientData clientData = (ClientData) session.getAttribute(USER);

		List<Integer> cart = clientData.getCart();
		if (!equipmentService.isInCart(cart, id)) {

			clientData.setCartValue(id);
			session.setAttribute(USER, clientData);
		}
	}
}
