package by.htp.shop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.htp.shop.controller.command.Command;
import by.htp.shop.controller.exception.ControllerException;
import by.htp.shop.controller.exception.IncorrectCommandException;
import by.htp.shop.controller.exception.NullCommandException;

public final class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static Logger logger = Logger.getLogger(Controller.class);
	private final static String COMMAND = "command";
	private final static String MESSAGE = "message";

	private final static String NULL_COMMAND_CODE = "cmd_null";
	private final static String INCORRECT_COMMAND_CODE = "cmd_incorrect";

	private final static String ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";

	private final CommandProvider provider = new CommandProvider();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName = request.getParameter(COMMAND);

		try {
			Command command = provider.getCommand(commandName);
			command.execute(request, response);

		} catch (NullCommandException e) {
			request.setAttribute(MESSAGE, NULL_COMMAND_CODE);
			RequestDispatcher diapatcher = request.getRequestDispatcher(ERROR_PAGE);
			diapatcher.forward(request, response);

		} catch (IncorrectCommandException e) {
			request.setAttribute(MESSAGE, INCORRECT_COMMAND_CODE);
			RequestDispatcher diapatcher = request.getRequestDispatcher(ERROR_PAGE);
			diapatcher.forward(request, response);

		} catch (ControllerException e) {
			logger.error(e.getMessage(), e);
			
			request.setAttribute(MESSAGE, e.getMessage());
			RequestDispatcher diapatcher = request.getRequestDispatcher(ERROR_PAGE);
			diapatcher.forward(request, response);
		}

	}
}
