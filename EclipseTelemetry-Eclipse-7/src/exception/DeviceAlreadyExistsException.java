package exception;

public class DeviceAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	public DeviceAlreadyExistsException(int deviceId) {
		super("Device " + deviceId + " already exists.");
	}

}