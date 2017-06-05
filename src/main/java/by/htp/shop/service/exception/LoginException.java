package by.htp.shop.service.exception;

public class LoginException extends Exception {
	private static final long serialVersionUID = 1L;

	public LoginException() {
		super();
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(Exception e) {
		super(e);
	}

	public LoginException(String message, Exception e) {
		super(message, e);
	}

}
