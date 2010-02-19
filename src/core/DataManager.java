package core;

import java.util.Hashtable;
import java.util.Vector;

import eclipseV7.data.Device;
import eclipseV7.data.DeviceItem;
import eclipseV7.data.TelemetryStorageV7;
import exception.DeviceAlreadyExistsException;
import exception.DeviceNotFoundException;
import exception.DeviceItemAlreadyExistsException;
import exception.DeviceItemNotFoundException;

// Singleton
public class DataManager {

	private static DataManager dataManager = null;
	private static TelemetryStorage storage;

	private DataManager() {
		// Use TelemetryStorageV7 as TelemetryStorage
		storage = new TelemetryStorageV7();
	}

	public static DataManager getInstance() {
		if (dataManager == null)
			dataManager = new DataManager();
		return dataManager;
	}

	// Object add
	public void addDevice(Integer deviceId, String deviceName)
			throws DeviceAlreadyExistsException {
		storage.addDevice(deviceId, deviceName);
	}

	public void addDeviceItem(Integer deviceId, DeviceItem deviceItem)
			throws DeviceNotFoundException,
			DeviceItemAlreadyExistsException {
		storage.addDeviceItem(deviceId, deviceItem);

	}
	
	

	// Data getters
	public double getIntData(String deviceName, String itemName)
			throws DeviceNotFoundException, DeviceItemNotFoundException {
		return storage.getIntData(deviceName, itemName);
	}

	public double getIntData(Integer deviceId, Integer itemId)
			throws DeviceNotFoundException, DeviceItemNotFoundException {
		return storage.getIntData(deviceId, itemId);
	}

	public double getDoubleData(String deviceName, String itemName)
			throws DeviceNotFoundException, DeviceItemNotFoundException {
		return storage.getDoubleData(deviceName, itemName);
	}

	public double getDoubleData(Integer deviceId, Integer itemId)
			throws DeviceNotFoundException, DeviceItemNotFoundException {
		return storage.getDoubleData(deviceId, itemId);
	}

	public Vector<String> getOnBitsDefinitions(String deviceName,
			String itemName) throws DeviceNotFoundException,
			DeviceItemNotFoundException {
		return storage.getOnBitsDefinitions(deviceName, itemName);
	}

	public Vector<String> getOnBitsDefinitions(Integer deviceId, Integer itemId)
			throws DeviceNotFoundException, DeviceItemNotFoundException {
		return storage.getOnBitsDefinitions(deviceId, itemId);
	}

	public Vector<String> getAllBitsDefinitions(String deviceName,
			String itemName) throws DeviceNotFoundException,
			DeviceItemNotFoundException {
		return storage.getAllBitsDefinitions(deviceName, itemName);
	}

	public Vector<String> getAllBitsDefinitions(Integer deviceId, Integer itemId)
			throws DeviceNotFoundException, DeviceItemNotFoundException {
		return storage.getAllBitsDefinitions(deviceId, itemId);
	}

	// Data setters
	public void setData(String device, String item, byte[] rawValue)
			throws DeviceNotFoundException, DeviceItemNotFoundException {
		storage.setData(device, item, rawValue);
	}

	public void setData(Integer deviceId, Integer itemId, byte[] rawValue)
			throws DeviceNotFoundException, DeviceItemNotFoundException {
		storage.setData(deviceId, itemId, rawValue);
	}

	// Object getters
	public Device getDevice(String device) throws DeviceNotFoundException {
		return storage.getDevice(device);
	}

	public Device getDevice(Integer deviceId)
			throws DeviceNotFoundException {
		return storage.getDevice(deviceId);
	}

	public DeviceItem getDeviceItem(String device, String item)
			throws DeviceNotFoundException, DeviceItemNotFoundException {
		return storage.getDeviceItem(device, item);
	}

	public DeviceItem getDeviceItem(Integer deviceId, Integer itemId)
			throws DeviceItemNotFoundException, DeviceNotFoundException {
		return storage.getDeviceItem(deviceId, itemId);
	}

	// Boolean getters to determine existance of devices/deviceitems
	public boolean deviceExists(Integer deviceId) {
		return storage.deviceExists(deviceId);
	}

	public boolean deviceItemExists(Integer deviceId, Integer itemId) {
		return storage.deviceItemExists(deviceId, itemId);
	}

	// Others
	public void printDeviceTree() {
		storage.printDeviceTree();
	}

	public Hashtable<Integer, Device> getDeviceMap() {
		return storage.getDeviceMap();
	}
	
	public void setParentDeviceIDs() {
		storage.setParentDeviceIDs();
	}
}
