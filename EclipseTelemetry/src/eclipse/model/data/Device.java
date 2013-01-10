package eclipse.model.data;

import java.util.ArrayList;


/**
 * A Device is a physical Item in the car such as Drive, Motor, MPPTs, etc
 * @author Marco
 *
 */
public class Device {

	//Properties of the Device
	private Integer deviceId;
	private String deviceName;
	
	//List of all Item incide this Device
	private ArrayList<DeviceItem> items;
	
	/**
	 * Create device 
	 * @param deviceId id name
	 * @param deviceName device name
	 */
	public Device(Integer deviceId, String deviceName) {
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		
		// Create the device's DeviceItem arraylist
		items = new ArrayList<DeviceItem>();
	}
	
	/**
	 * Add item in the device
	 * @param item item to be added
	 */
	public void addItem(DeviceItem item) {
		items.add(item);
	}
	
	/**
	 * Return first Item with name
	 * @param itemName name that we search
	 * @return
	 */
	public DeviceItem getItemByName(String itemName) {
		for (DeviceItem iDev : items)
			if (iDev.getName().toUpperCase().compareTo(itemName.toUpperCase())==0)
				return iDev;
		return null;
	}
	
	/**
	 * Return item by ID
	 * @param itemId
	 * @return
	 */
	public DeviceItem getItemByID (Integer itemId) {
		return items.get(itemId);
	}

	/**
	 * Return all items
	 * @return
	 */
	public ArrayList<DeviceItem> getItems() {
		return items;
	}

	/**
	 * Load value from old files 
	 */
	public void load(){
		
	}
	
	/**
	 * Save curent value to XYZ format
	 */
	public void save(){
		
	}
	
	//Getter and Setter
	public Integer getDeviceId() {
		return deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	
}
