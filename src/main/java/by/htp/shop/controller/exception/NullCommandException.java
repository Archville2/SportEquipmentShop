package by.htp.shop.controller.exception;

public class NullCommandException extends Exception {
	private static final long serialVersionUID = 1L;

	public NullCommandException() {
		super();
	}

	public NullCommandException(String message) {
		super(message);
	}

	public NullCommandException(Exception e) {
		super(e);
	}

	public NullCommandException(String message, Exception e) {
		super(message, e);
	}

}
