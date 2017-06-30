package by.htp.shop.page.exception;

public class PageException extends Exception {
	private static final long serialVersionUID = 1L;

	public PageException() {
		super();
	}

	public PageException(String message) {
		super(message);
	}

	public PageException(Exception e) {
		super(e);
	}

	public PageException(String message, Exception e) {
		super(message, e);
	}

}
