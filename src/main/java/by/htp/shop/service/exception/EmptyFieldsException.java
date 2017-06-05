package by.htp.shop.service.exception;

public class EmptyFieldsException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmptyFieldsException() {
		super();
	}

	public EmptyFieldsException(String message) {
		super(message);
	}

	public EmptyFieldsException(Exception e) {
		super(e);
	}

	public EmptyFieldsException(String message, Exception e) {
		super(message, e);
	}

}
