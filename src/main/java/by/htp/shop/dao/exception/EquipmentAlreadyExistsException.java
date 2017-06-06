package by.htp.shop.dao.exception;

public class EquipmentAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public EquipmentAlreadyExistsException() {
		super();
	}

	public EquipmentAlreadyExistsException(String message) {
		super(message);
	}

	public EquipmentAlreadyExistsException(Exception e) {
		super(e);
	}

	public EquipmentAlreadyExistsException(String message, Exception e) {
		super(message, e);
	}
}
