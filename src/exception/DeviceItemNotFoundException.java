package exception;

public class DeviceItemNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DeviceItemNotFoundException(int deviceId, int itemId) {
		super("Item " + itemId + " not found in device " + deviceId + ".");
	}
	
	public DeviceItemNotFoundException(String deviceName, String itemName) {
		super("Item " + itemName + " not found in device " + deviceName + ".");
	}

}
