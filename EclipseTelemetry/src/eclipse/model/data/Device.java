package eclipse.model.data;

import java.util.Hashtable;
import java.util.Map;


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
	private Map<Integer, DeviceItem> items;
	
	public Device(Integer deviceId, String deviceName) {
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		
		// Create the device's DeviceItem map
		items = new Hashtable<Integer, DeviceItem>();
	}
	
	public void addItem(DeviceItem item) {
		//System.out.println("Attempting to put: " + item.getItemId() + " " + item.getName());
		items.put(item.getItemId(), item);
	}
	
	// Returns 1st with that name
	public DeviceItem getItemByName(String itemName) {
		for (Integer key:items.keySet()) {
			if (items.get(key).getName().equals(itemName))
				return items.get(key);
		}
		return null;
	}
	
	public DeviceItem getItemByID (Integer itemId) {
		return items.get(itemId);
	}
	
	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Map<Integer, DeviceItem> getItems() {
		return items;
	}

	public void setItems(Map<Integer, DeviceItem> items) {
		this.items = items;
	}

}
