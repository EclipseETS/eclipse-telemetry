package core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import util.HexString;

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
	
	public void serialize(String path){
		
		FileWriter outFile = null;
		try {
			outFile = new FileWriter(path);
		} catch (IOException e) {
			// 
			e.printStackTrace();
		}
		PrintWriter file = new PrintWriter(outFile);
		
		ArrayList<String> out = storage.serialize();
		
		for(int i=0;i<out.size();i++){
			file.println(out.get(i));
		}
		file.close();
		try {
			outFile.close();
		} catch (IOException e) {
			// 
			e.printStackTrace();
		}
	}
	
	public void load(String path){
		try{
			  FileInputStream fstream = new FileInputStream(path);
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  while ((strLine = br.readLine()) != null)   {
			  String[] tbl;
			  tbl=strLine.split(";");
			  if(tbl.length==4){
				  getDevice(Integer.parseInt(tbl[0])).getItemByID(Integer.parseInt(tbl[1]))
				  .setValue(HexString.hexToBuffer(tbl[3]),Long.parseLong(tbl[2]));
			  }
			  }
			  in.close();
			    }catch (Exception e){
			  System.err.println("Error: " + e.getMessage());
			  }
	}
	
}
