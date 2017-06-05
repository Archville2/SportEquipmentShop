package by.htp.shop.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.htp.shop.bean.ClientData;
import by.htp.shop.controller.command.Command;
import by.htp.shop.controller.exception.ControllerException;
import by.htp.shop.service.exception.ServiceException;
import by.htp.shop.service.factory.ServiceFactory;
import by.htp.shop.service.impl.ItemImplService;

public class AddToCart implements Command {
	final static Logger LOGGER = Logger.getLogger(LoginClient.class);
	
	private final static String CART = "cart";
	private final static String USER = "user";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ItemImplService itemService = serviceFactory.getItemImplService();
		int id = Integer.parseInt(request.getParameter(CART));
		
		HttpSession session = request.getSession(true);
		ClientData clientData = (ClientData) session.getAttribute(USER);

		List<Integer> cart = clientData.getCart();
		try {
			if (!itemService.isInCart(cart, id)) {

				clientData.setCartValue(id);
				session.setAttribute(USER, clientData);
			}
			
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}
}
