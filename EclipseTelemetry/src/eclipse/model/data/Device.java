package eclipse.model.data;

import java.util.Hashtable;
import java.util.Map;


public class Device {


	private Integer deviceId;
	private String deviceName;
	private Map<Integer, DeviceItem> items;
	
	public Device(Integer deviceId, String deviceName) {
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		
		// Create the device's DeviceItem map
		items = new Hashtable<Integer, DeviceItem>();
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
