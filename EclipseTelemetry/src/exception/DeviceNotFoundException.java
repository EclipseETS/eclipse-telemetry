package exception;

public class DeviceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DeviceNotFoundException(int deviceId) {
		super("Device " + deviceId + " not found.");
	}
	
	public DeviceNotFoundException(String deviceName) {
		super("Device " + deviceName + " not found.");
	}

}
