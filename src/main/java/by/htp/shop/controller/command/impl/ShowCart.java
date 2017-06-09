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

public class ShowCart implements Command {
	private final static Logger LOGGER = Logger.getLogger(ShowCart.class);
	private final static String CART = "cart";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		EquipmentService equipmentService = serviceFactory.getEquipmentServiceImpl();
		PageSelectorFactory selectJSPPageFactory = PageSelectorFactory.getInstance();

		PageSelector selectJSPPage = selectJSPPageFactory.getPageSelectorImpl();
		
		HttpSession session = request.getSession(true);
		ClientData clientData = (ClientData) session.getAttribute("user");
		List<Integer> cart = clientData.getCart();
		RequestDispatcher dispatcher = null;
		
		try {
			List<Item> cartItems = equipmentService.formCartEquipment(cart);
			session.setAttribute("cart", cartItems);
			
			dispatcher = request.getRequestDispatcher(selectJSPPage.getPageURL(CART));
			dispatcher.forward(request, response);
			
		} catch (ServiceException | PageNotFoundException e) {
			throw new ControllerException(e.getMessage(), e);
			
		} catch (ServletException | IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ControllerException("exception in ShowCart", e);
		}
	}
}
