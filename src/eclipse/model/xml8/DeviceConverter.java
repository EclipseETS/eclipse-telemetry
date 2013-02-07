package eclipse.model.xml8;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import eclipse.model.data.DataManager;
import eclipse.model.data.Device;
import eclipse.model.data.DeviceItem;
import eclipse.model.data.Trame;

/**
 * This class is used to create Device from the xml using stream librairie
 * @author Marco
 *
 */
public class DeviceConverter implements Converter {

	static Logger logger = Logger.getLogger("main");
	
	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class type) {
		return type.equals(Device.class);
	}

	public void marshal(Object value, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		// No use for us
	}

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {

		
		// <char>
		while (reader.hasMoreChildren()) { // While there are some <device>
			reader.moveDown(); // Go down to this <device>
			
			// Set the <device> properties
			Integer deviceId = new Integer(reader.getAttribute("id"));
			String deviceName = reader.getAttribute("name");
			
			// Process the <device>
			Device dev = new Device(deviceId, deviceName);
			DataManager.getInstance().addDevice(dev);//Add the device
			
			logger.debug("Device :"+deviceId+":"+deviceName+" added.");
			
			while (reader.hasMoreChildren()) { // While there are
												// <trame>
				reader.moveDown(); // Go down to the <trame>

				// Process the <trame>
				Trame t = (Trame) context.convertAnother(new Object(), Trame.class);
				DataManager.getInstance().getDeviceByID(deviceId).addTrame(t);
				logger.debug("Trame :"+t.getIdentifier()+" added.");
				
				//To set item in this device
				for(DeviceItem item : t.getItems())
					dev.addItem(item); // copy items from tram to Device				
				/**
				 *  	/\
				 *  	|
				 *  	|
				 * Important to understand why we are copying items
				 * 
				 * We do NOT want to double DeviceItem so we add them in the tram first then when we come back here we copy the 
				 * reference from tram to the device
				 * 
				 * For the futur we can add data by using the Tram and read the data by using the Device
				 */

				reader.moveUp(); // Get back up to the <device>
			}
			reader.moveUp(); // Get back up to <char>
		}
		// </char>


		return null;
	}

}
