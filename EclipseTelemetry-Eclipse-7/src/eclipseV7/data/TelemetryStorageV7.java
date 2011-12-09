package eclipseV7.data;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import core.TelemetryStorage;
import exception.DeviceAlreadyExistsException;
import exception.DeviceNotFoundException;
import exception.DeviceItemAlreadyExistsException;
import exception.DeviceItemNotFoundException;

public class TelemetryStorageV7 implements TelemetryStorage {
	
	private Map<Integer, Device> devices;
	
	public TelemetryStorageV7() {
		// Create the storage's Device map
		devices = new Hashtable<Integer, Device>();
	}
	
	@Override
	public void addDevice(Integer deviceId, String deviceName) throws DeviceAlreadyExistsException {
		if (deviceExists(deviceId)) throw new DeviceAlreadyExistsException(deviceId);
		
		devices.put(deviceId, new Device(deviceId, deviceName));
	}
	
	@Override
	public void addDeviceItem(Integer deviceId, DeviceItem deviceItem) throws DeviceNotFoundException, DeviceItemAlreadyExistsException {
		if (!deviceExists(deviceId)) throw new DeviceNotFoundException(deviceId);
		if (deviceItemExists(deviceId, deviceItem.getItemId())) throw new DeviceItemAlreadyExistsException(deviceId, deviceItem.getItemId());
		
		devices.get(deviceId).addItem(deviceItem);

	}
	
	@Override
	public double getIntData(String deviceName, String itemName) throws DeviceNotFoundException, DeviceItemNotFoundException {
		if (!deviceExists(deviceName)) throw new DeviceNotFoundException(deviceName);
		if (!deviceItemExists(deviceName, itemName)) throw new DeviceItemNotFoundException(deviceName, itemName);
		
		for (Integer key:devices.keySet()) {
			if (devices.get(key).getName().equals(deviceName))
				return devices.get(key).getItemByName(itemName).getIntValue();
		}
		return 0; // Unreachable considering exeptions
	}
	
	@Override
	public double getDoubleData(String deviceName, String itemName) throws DeviceNotFoundException, DeviceItemNotFoundException {
		if (!deviceExists(deviceName)) throw new DeviceNotFoundException(deviceName);
		if (!deviceItemExists(deviceName, itemName)) throw new DeviceItemNotFoundException(deviceName, itemName);
		
		for (Integer key:devices.keySet()) {
			if (devices.get(key).getName().equals(deviceName))
				return devices.get(key).getItemByName(itemName).getDoubleValue();
		}
		return 0; // Unreachable considering exeptions
	}
	
	@Override
	public double getIntData(Integer deviceId, Integer itemId) throws DeviceNotFoundException, DeviceItemNotFoundException {
		if (!deviceExists(deviceId)) throw new DeviceNotFoundException(deviceId);
		if (!deviceItemExists(deviceId, itemId)) throw new DeviceItemNotFoundException(deviceId, itemId);
		
		return devices.get(deviceId).getItemByID(itemId).getIntValue();
	}
	
	@Override
	public double getDoubleData(Integer deviceId, Integer itemId) throws DeviceNotFoundException, DeviceItemNotFoundException {
		if (!deviceExists(deviceId)) throw new DeviceNotFoundException(deviceId);
		if (!deviceItemExists(deviceId, itemId)) throw new DeviceItemNotFoundException(deviceId, itemId);
		
		return devices.get(deviceId).getItemByID(itemId).getDoubleValue();
	}
	
	@Override
	public Vector<String> getOnBitsDefinitions(String deviceName, String itemName) throws DeviceNotFoundException, DeviceItemNotFoundException {
		if (!deviceExists(deviceName)) throw new DeviceNotFoundException(deviceName);
		if (!deviceItemExists(deviceName, itemName)) throw new DeviceItemNotFoundException(deviceName, itemName);
		
		for (Integer key:devices.keySet()) {
			if (devices.get(key).getName().equals(deviceName))
				return devices.get(key).getItemByName(itemName).getOnBitsDefinitions();
		}
		return null; // Unreachable considering exeptions
	}
	
	@Override
	public Vector<String> getOnBitsDefinitions(Integer deviceId, Integer itemId) throws DeviceNotFoundException, DeviceItemNotFoundException {
		if (!deviceExists(deviceId)) throw new DeviceNotFoundException(deviceId);
		if (!deviceItemExists(deviceId, itemId)) throw new DeviceItemNotFoundException(deviceId, itemId);
		
		return devices.get(deviceId).getItemByID(itemId).getOnBitsDefinitions();
	}
	
	@Override
	public Vector<String> getAllBitsDefinitions(String deviceName, String itemName) throws DeviceNotFoundException, DeviceItemNotFoundException {
		if (!deviceExists(deviceName)) throw new DeviceNotFoundException(deviceName);
		if (!deviceItemExists(deviceName, itemName)) throw new DeviceItemNotFoundException(deviceName, itemName);
		
		for (Integer key:devices.keySet()) {
			if (devices.get(key).getName().equals(deviceName))
				return devices.get(key).getItemByName(itemName).getAllBitsDefinitions();
		}
		return null; // Unreachable considering exeptions
	}
	
	@Override
	public Vector<String> getAllBitsDefinitions(Integer deviceId, Integer itemId) throws DeviceNotFoundException, DeviceItemNotFoundException {
		if (!deviceExists(deviceId)) throw new DeviceNotFoundException(deviceId);
		if (!deviceItemExists(deviceId, itemId)) throw new DeviceItemNotFoundException(deviceId, itemId);
		
		return devices.get(deviceId).getItemByID(itemId).getAllBitsDefinitions();
	}
	
	@Override
	public void setData(String deviceName, String itemName, byte[] rawValue) throws DeviceNotFoundException, DeviceItemNotFoundException {
		if (!deviceExists(deviceName)) throw new DeviceNotFoundException(deviceName);
		if (!deviceItemExists(deviceName, itemName)) throw new DeviceItemNotFoundException(deviceName, itemName);

		for (Integer key:devices.keySet()) {
			if (devices.get(key).getName().equals(deviceName))
				devices.get(key).getItemByName(itemName).setValue(rawValue);
		}
	}
	
	@Override
	public void setData(Integer deviceId, Integer itemId, byte[] rawValue) throws DeviceNotFoundException, DeviceItemNotFoundException {
		if (!deviceExists(deviceId)) throw new DeviceNotFoundException(deviceId);
		if (!deviceItemExists(deviceId, itemId)) throw new DeviceItemNotFoundException(deviceId, itemId);

		devices.get(deviceId).getItemByID(itemId).setValue(rawValue);
	}
	
	@Override
	public Device getDevice(Integer deviceId) throws DeviceNotFoundException {
		if (!deviceExists(deviceId)) 
			throw new DeviceNotFoundException(deviceId);
		
		return devices.get(deviceId);
	}

	@Override
	public Device getDevice(String deviceName) throws DeviceNotFoundException {
		if (!deviceExists(deviceName)) throw new DeviceNotFoundException(deviceName);
		
		for (Integer key:devices.keySet()) {
			if (devices.get(key).getName().equals(deviceName))
				return devices.get(key);
		}
		return null; // Unreachable considering exeptions
	}

	@Override
	public DeviceItem getDeviceItem(Integer deviceId, Integer itemId) throws DeviceNotFoundException, DeviceItemNotFoundException {
		if (!deviceExists(deviceId)) throw new DeviceNotFoundException(deviceId);
		if (!deviceItemExists(deviceId, itemId)) throw new DeviceItemNotFoundException(deviceId, itemId);

		return devices.get(deviceId).getItemByID(itemId);
	}

	@Override
	public DeviceItem getDeviceItem(String deviceName, String itemName) throws DeviceNotFoundException, DeviceItemNotFoundException {
		if (!deviceExists(deviceName)) throw new DeviceNotFoundException(deviceName);
		if (!deviceItemExists(deviceName, itemName)) throw new DeviceItemNotFoundException(deviceName, itemName);
		
		for (Integer key:devices.keySet()) {
			if (devices.get(key).getName().equals(deviceName))
				return devices.get(key).getItemByName(itemName);
		}
		return null; // Unreachable considering exeptions
	}
	
	public boolean deviceExists(Integer deviceId) {
		if (devices.containsKey(deviceId)) return true;
		else return false;
	}
	
	public boolean deviceExists(String deviceName) {
		for (Integer key : devices.keySet()) {
			if (devices.get(key).getName().equals(deviceName)) return true;
		}
		return false;
	}
	
	public boolean deviceItemExists(Integer deviceId, Integer itemId) {
		if (!deviceExists(deviceId)) return false;
		else if (devices.get(deviceId).getDeviceItemMap().containsKey(itemId)) return true;
		else return false;
	}
	
	public boolean deviceItemExists(String deviceName, String itemName) {
		if (!deviceExists(deviceName)) return false;
		for (Integer key:devices.keySet()) {
			for (Integer itemKey:devices.get(key).getDeviceItemMap().keySet()) {
				if (devices.get(key).getDeviceItemMap().get(itemKey).getName().equals(itemName))
					return true;
			}
		}
		return false;
	}
	
	public Hashtable<Integer, Device> getDeviceMap() {
		return (Hashtable<Integer, Device>) devices;
	}
	
	public void printDeviceTree() {
		System.out.println("Device tree dump:");
		for (Device key:devices.values()) {
			System.out.println("-- Device("+ key.getId() + ") : " + key.getName());
			key.printDeviceItemTree();
		}
	}

	@Override
	public void setParentDeviceIDs() {
		for (Device key:devices.values()) {
			for (DeviceItem itemKey:key.getDeviceItemMap().values()) {
				itemKey.setParentDeviceId(key.getId());
			}
		}

	}
	
	public ArrayList<String> serialize(){
		ArrayList<String> out =new ArrayList<String>();
		
		for (Device deviceItem:getDeviceMap().values()) {
			ArrayList<String> tmp = deviceItem.serialize();
			
			for(int j=0;j<tmp.size();j++)
			{
				out.add(tmp.get(j));
			}
		}
		return out;
		
	}
	
	
}
