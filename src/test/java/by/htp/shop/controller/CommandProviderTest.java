package by.htp.shop.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import by.htp.shop.controller.exception.ControllerException;

/**
 * For testing incoming commands
 *
 * @param	incoming command as a string
 * @return 	ControllerException if incorrect command
 */

/*public class CommandProviderTest extends Assert {
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

	@Test
	public void correctCommandTest() throws ControllerException{

		thrown.expect(ControllerException.class);
		
		provider.getCommand("show_reg_page");
	}
	
	@Test(expected = ControllerException.class)
	public void incorrectCommandTest() throws ControllerException {

		provider.getCommand("show_reg page");
	}
	
	@Test(expected = ControllerException.class)
	public void emptyCommandTest() throws ControllerException {

		provider.getCommand("");
	}

}*/
