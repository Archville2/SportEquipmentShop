package by.htp.shop.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import by.htp.shop.controller.exception.IncorrectCommandException;
import by.htp.shop.controller.exception.NullCommandException;

/**
 * For testing incoming commands
 *
 * @param	incoming command as a string
 * @return 	ControllerException if incorrect command
 */

public class CommandProviderTest extends Assert {
	CommandProvider provider;

	@Before
	public void init() {
		provider = new CommandProvider();
	}

	@After
	public void destroy() {
		provider = null;
	}
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test(expected = IncorrectCommandException.class)
	public void incorrectCommandTest() throws IncorrectCommandException, NullCommandException {

		provider.getCommand("show_reg page");
	}
	
	@Test(expected = IncorrectCommandException.class)
	public void emptyCommandTest() throws IncorrectCommandException, NullCommandException {

		provider.getCommand("");
	}
	
	@Test(expected = NullCommandException.class)
	public void nullCommandTest() throws IncorrectCommandException, NullCommandException {

		provider.getCommand(null);
	}

}
