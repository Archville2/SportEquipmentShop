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

public class ShowCart implements Command {
	final static Logger LOGGER = Logger.getLogger(LoginClient.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ItemImplService itemService = serviceFactory.getItemImplService();
		HttpSession session = request.getSession(true);
		ClientData clientData = (ClientData) session.getAttribute("user");
		List<Integer> cart = clientData.getCart();
		RequestDispatcher dispatcher = null;
		
		try {
			List<Item> cartItems = itemService.formCartItems(cart);
			session.setAttribute("cart", cartItems);
			
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/show_cart.jsp");
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e);
			
		} catch (ServletException | IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ControllerException("exception in ShowCart", e);
		}
	}
}
