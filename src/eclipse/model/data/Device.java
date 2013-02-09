package eclipse.model.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
	private List<DeviceItem> items;
	private List<Trame> trames;
	
	/**
	 * Create device 
	 * @param deviceId id name
	 * @param deviceName device name
	 */
	public Device(Integer deviceId, String deviceName) {
		this.deviceId = deviceId;
		this.deviceName = deviceName;

		// Create the device's DeviceItem arraylist
		items = Collections.synchronizedList(new ArrayList<DeviceItem>());
		// Create the device's Trame arraylist
		trames = Collections.synchronizedList(new ArrayList<Trame>());
	}
	
	/**
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
		for (DeviceItem iDev : items)
			if (iDev.getItemId()==itemId)
				return iDev;
		return null;
	}

	/**
	 * Return all items
	 * @return
	 */
	public List<DeviceItem> getItems() {
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
	
	/**
	 * Add trame to the tram list of this device
	 * Use this tram to create Item and add data
	 * @param trame to be add
	 */
	public void addTrame(Trame trame){
		trames.add(trame);
	}
	
	/**
	 * Return a tram with a particular Identifier (tram identifier from the CAN itself)
	 * Can return null if not exist
	 * @param identifier
	 * @return
	 */
	public Trame getTrameByidentifier(int identifier){
		for(Trame t : trames)
			if(t.getIdentifier()==identifier)
				return t;
		return null;
	}
	
	//Getter and Setter
	public Integer getDeviceId() {
		return deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}

}
