package by.htp.shop.page.exception;

public class PageNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public PageNotFoundException() {
		super();
	}

	public PageNotFoundException(String message) {
		super(message);
	}

	public PageNotFoundException(Exception e) {
		super(e);
	}

	public PageNotFoundException(String message, Exception e) {
		super(message, e);
	}

}
