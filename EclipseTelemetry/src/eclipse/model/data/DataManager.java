package eclipse.model.data;

import java.util.ArrayList;

public class DataManager {
	
	private static DataManager dataMgr = new DataManager();
	private ArrayList<Device> devices;
	
	//Private instance of data manager
	private DataManager(){
		devices = new ArrayList<Device>();
	}	
	
	/**
	 * Singleton that provide datamrg for the hole application
	 * @return the only and single instance of the DataMgr
	 */
	public DataManager getInstance(){
		return dataMgr;
	}

	 /** Add device in the mgr
	 * @param devices device to be added
	 */
	public void addDevice(Device device) {
		devices.add(device);
	}
	
	/**
	 * Return first device with name
	 * @param device name that we search
	 * @return
	 */
	public Device getDeviceByName(String deviceName) {
		//TODO code ca criss
		return null;
	}
	
	/**
	 * Return device by ID
	 * @param deviceId
	 * @return
	 */
	public Device getDeviceByID (Integer itemId) {
		return devices.get(itemId);
	}

	/**
	 * Load value from old files 
	 * TODO: determined input output
	 */
	public void load(){
		
	}
	
	/**
	 * Save curent value to XYZ format
	 * TODO: determined input output
	 */
	public void save(){
		
	}
		
	//GETTER AND SETTER
	public ArrayList<Device> getDevices() {
		return devices;
	}

}
