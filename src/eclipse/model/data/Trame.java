package eclipse.model.data;

import java.util.ArrayList;

/**
 * This class represent a tram sent by the car. From a logical point of view
 * 
 * 						Device
 * 						  |
 * 						  |
 * 						 \/
 * 				     Many Trame
 * 						  |
 * 						  |
 * 						 \/
 * 					 Many Items
 * 
 * 
 *  Device is also linked to all Item for easy getting for data. Tram is only used when a new tram comes in. 
 *  We use that trame to split the new information
 *  
 *  
 * @author Marco
 *
 */
public class Trame {
	
	private Device parent;
	private int identifier;	//Tram ID (from car)
	private int type; // Tram type (from car)
	private ArrayList<DeviceItem> items;
	
	/**
	 * Trame constructor
	 * @param parent Device linked to parent Device (the device link to this trame)
	 */
	public Trame(Device parent, int identifier,int type){
		this.parent=parent;
		this.identifier=identifier;
		this.type=type;
	}
	
	/**
	 * Add item in the device
	 * @param item item to be added
	 */
	public void addItem(DeviceItem item) {
		items.add(item);
		parent.addItem(item);
	}

	/**
	 * Return all items in this tram, used when new data arrived
	 * @return
	 */
	public ArrayList<DeviceItem> getItems(){
		return items;
	}

	/**
	 * 
	 * @return the tram identifier linked to this particular tram
	 */
	public int getIdentifier(){
		return identifier;
	}

	/**
	 * 
	 * @return the type of tram 1 or 2, 1 stand for standard 2 extended.
	 */
	public int getType(){
		return type;
	}
}
