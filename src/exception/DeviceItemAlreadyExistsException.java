package exception;

public class DeviceItemAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	public DeviceItemAlreadyExistsException(int deviceId, int itemId) {
		super("Item " + itemId + " already exists in device " + deviceId + ".");
	}

}
