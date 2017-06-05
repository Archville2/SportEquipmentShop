package by.htp.shop.controller.exception;

public class IncorrectCommandException extends Exception {
	private static final long serialVersionUID = 1L;

	public IncorrectCommandException() {
		super();
	}

	public IncorrectCommandException(String message) {
		super(message);
	}

	public IncorrectCommandException(Exception e) {
		super(e);
	}

	public IncorrectCommandException(String message, Exception e) {
		super(message, e);
	}

}
