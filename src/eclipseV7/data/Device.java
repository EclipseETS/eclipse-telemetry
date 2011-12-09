package eclipseV7.data;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

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
	
	public Integer getId() {
		return deviceId;
	}
	
	public String getName() {
		return deviceName;
	}
	
	public void addItem(DeviceItem item) {
		//System.out.println("Attempting to put: " + item.getItemId() + " " + item.getName());
		items.put(item.getItemId(), item);
	}
	
	public DeviceItem getItemByID (Integer itemId) {
		return items.get(itemId);
	}

	// Returns 1st with that name
	public DeviceItem getItemByName(String itemName) {
		for (Integer key:items.keySet()) {
			if (items.get(key).getName().equals(itemName))
				return items.get(key);
		}
		return null;
	}
	
	public void printDeviceItemTree() {
		Vector<Integer> v = new Vector<Integer>(items.keySet());
		Collections.sort(v);
		
		for (DeviceItem key: items.values()) {
			System.out.println("   +-- DeviceItem("+ key.getItemId() + ") : " + key.getName());
			System.out.println("       +-- Unit: "+ key.getUnit());
			System.out.println("       +-- MinValue: "+ key.getMinValue());
			System.out.println("       +-- MaxValue: "+ key.getMaxValue());
			System.out.println("       +-- Resolution: "+ key.getResolution());
			System.out.println("       +-- Factor: "+ key.getFactor());
			System.out.println("       +-- Offset: "+ key.getOffset());
			System.out.println("       +-- Range: "+ key.getRange());
			System.out.println("       +-- numBits: "+ key.getNumBits());
			System.out.println("       +-- Signed: "+ key.isSigned());
			
			// Definitions
			if (key.getDefinitionsBitsOn() == null) System.out.println("       +-- Definitions: Null");
			else if (key.getDefinitionsBitsOff() == null) System.out.println("       +-- Definitions: Null");
			else {
				Vector<String> definitionsBitsOn = key.getDefinitionsBitsOn();
				Vector<String> definitionsBitsOff = key.getDefinitionsBitsOff();
				
				System.out.println("       +-- Definitions: ");
				for (int i = 0 ; i < key.getNumBits(); i++) {
					System.out.print("           +-- [bit" + i + "] ");
					System.out.println("[0] "+ definitionsBitsOff.get(i));
					System.out.println("                      [1] "+ definitionsBitsOn.get(i));
				}
			}
			
		}
		
	}
	
	public ArrayList<String> serialize(){
		ArrayList<String> out =new ArrayList<String>();
		
		for (DeviceItem deviceItem:this.getDeviceItemMap().values()) {
			String[] tmp = deviceItem.serialize();
			if(tmp!=null){
				for(int j=0;j<tmp.length;j++)
				{
					out.add(tmp[j]);
				}
			}
		}
		return out;
		
	}
	
	public Hashtable<Integer, DeviceItem> getDeviceItemMap() {
		return (Hashtable<Integer, DeviceItem>) items;
	}
	
}
