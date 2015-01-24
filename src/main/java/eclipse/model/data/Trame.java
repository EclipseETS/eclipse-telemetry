package eclipse.model.data;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import eclipse.controller.util.ByteManipulator;
import eclipse.controller.util.HexString;

/**
 * This class represent a tram sent by the car. From a logical point of view
 * 
 * 						Device
 * 						  |
 * 						  |
 * 						 \/
 * 				     Many Trame (in the good order)
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
public class Trame implements Serializable {

	private static final long serialVersionUID = -4922800160254050731L;
	private int identifier;	//Tram ID (from car)
	private int type; // Tram type (from car)
	private List<DeviceItem> items = Collections.synchronizedList(new ArrayList<DeviceItem>());
	
	/**
	 * Trame constructor
	 * @param parent Device linked to parent Device (the device link to this trame)
	 */
	public Trame(String identifier,int type){
		
		String value = Integer.toString(type);
		
		for(int i=0;i<7-identifier.length();i++)
			value=value+'0';
		value=value+identifier;
		double tmp =0;
		
		try {
			tmp = ByteManipulator.byteArrayToInt(HexString.hexToBuffer(value),0,4,false,false);
		} catch (Exception e) {
			StringWriter stack = new StringWriter();
			e.printStackTrace(new PrintWriter(stack));
			Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
		}
		
		
		this.identifier=(int) tmp;
		this.type=type;
	}
	
	/**
	 * Add item in the device
	 * @param item item to be added
	 */
	public void addItem(DeviceItem item) {
		items.add(item);
	}

	/**
	 * Return all items in this tram, used when new data arrived
	 * @return the item in the GOOD order, so has they are in the CAM BUS trame
	 */
	public List<DeviceItem> getItems(){
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
