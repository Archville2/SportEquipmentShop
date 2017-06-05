package by.htp.shop.dao.exception;

public class ItemAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public ItemAlreadyExistsException() {
		super();
	}

	public ItemAlreadyExistsException(String message) {
		super(message);
	}

	public ItemAlreadyExistsException(Exception e) {
		super(e);
	}

	public ItemAlreadyExistsException(String message, Exception e) {
		super(message, e);
	}
}
