package core;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import eclipseV7.data.Device;
import eclipseV7.data.DeviceItem;
import exception.DeviceAlreadyExistsException;
import exception.DeviceNotFoundException;
import exception.DeviceItemAlreadyExistsException;
import exception.DeviceItemNotFoundException;

// Defines the necessary methods TelemetryStorage-based classes must provide
public interface TelemetryStorage {

	public void addDevice(Integer deviceId, String name) throws DeviceAlreadyExistsException;
	public void addDeviceItem(Integer deviceId, DeviceItem deviceItem) throws DeviceNotFoundException, DeviceItemAlreadyExistsException;;
	
	// Get/Set data by device/deviceItem name or id
	public double getIntData(String deviceName, String itemName) throws DeviceNotFoundException, DeviceItemNotFoundException;
	public double getDoubleData(String deviceName, String itemName) throws DeviceNotFoundException, DeviceItemNotFoundException;
	public double getIntData(Integer deviceId, Integer itemId) throws DeviceNotFoundException,DeviceItemNotFoundException;
	public double getDoubleData(Integer deviceId, Integer itemId) throws DeviceNotFoundException, DeviceItemNotFoundException;
	public Vector<String> getOnBitsDefinitions(String deviceName, String itemName) throws DeviceNotFoundException, DeviceItemNotFoundException;
	public Vector<String> getOnBitsDefinitions(Integer deviceId, Integer itemId) throws DeviceNotFoundException, DeviceItemNotFoundException;
	public Vector<String> getAllBitsDefinitions(String deviceName, String itemName) throws DeviceNotFoundException, DeviceItemNotFoundException;
	public Vector<String> getAllBitsDefinitions(Integer deviceId, Integer itemId) throws DeviceNotFoundException, DeviceItemNotFoundException;
	public void setData(String deviceName, String itemName, byte[] rawValue) throws DeviceNotFoundException, DeviceItemNotFoundException;
	public void setData(Integer deviceId, Integer itemId, byte[] rawValue) throws DeviceNotFoundException, DeviceItemNotFoundException;
	
	// Get whole object -- easier for getters
	public Device getDevice(Integer deviceId) throws DeviceNotFoundException;
	public Device getDevice(String deviceName) throws DeviceNotFoundException;
	public DeviceItem getDeviceItem(Integer deviceId, Integer itemId) throws DeviceNotFoundException, DeviceItemNotFoundException;
	public DeviceItem getDeviceItem(String deviceName, String itemName) throws DeviceNotFoundException, DeviceItemNotFoundException;
	
	public boolean deviceExists(Integer deviceId);
	public boolean deviceExists(String deviceName);
	public boolean deviceItemExists(Integer deviceId, Integer itemId);
	public boolean deviceItemExists(String deviceName, String itemName);
	
	// More (too?) specific method so that the app (mostly UI) can parse what's available
	public Hashtable<Integer, Device> getDeviceMap();
	
	// Dump
	public void printDeviceTree();
	
	// Set Parent DeviceId to DeviceItems
	public void setParentDeviceIDs();
	
	public ArrayList<String> serialize();
}
