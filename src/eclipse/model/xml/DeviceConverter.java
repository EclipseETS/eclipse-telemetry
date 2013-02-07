package eclipse.model.xml;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;


import eclipseV7.data.Device;
import eclipseV7.data.DeviceItem;
import exception.DeviceAlreadyExistsException;
import exception.DeviceNotFoundException;
import exception.DeviceItemAlreadyExistsException;

public class DeviceConverter implements Converter {

	static Logger logger = Logger.getLogger("xml");
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return type.equals(Device.class);
	}

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		// N/A
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {

		try {
			// <devices>
			while (reader.hasMoreChildren()) { // While there are some <device>
				reader.moveDown(); // Go down to this <device>

				// System.out.println("Processing "
				// + reader.getNodeName()
				// + reader.getAttribute("id")
				// + "-" + reader.getAttribute("name"));

				// Set the <device> properties
				Integer deviceId = new Integer(reader.getAttribute("id"));
				String deviceName = reader.getAttribute("name");

				// Process the <device>
				DataManager.getInstance().addDevice(deviceId, deviceName);

				while (reader.hasMoreChildren()) { // While there are
													// <deviceItem>
					reader.moveDown(); // Go down to the <deviceItem>

					// Process the <deviceItem>
					DeviceItem di = (DeviceItem) context.convertAnother(new Object(), DeviceItem.class);
					DataManager.getInstance().addDeviceItem(deviceId, di);

					reader.moveUp(); // Get back up to the <device>
				}
				reader.moveUp(); // Get back up to <devices>
			}
			// </devices>

		} catch (DeviceAlreadyExistsException e) {
			logger.error("Device add error: " + e.getMessage() + " Ignoring.");
		} catch (DeviceNotFoundException e) {
			logger.error("Device add error: " + e.getMessage() + " Ignoring.");
		} catch (DeviceItemAlreadyExistsException e) {
			logger.error("Device item add error: " + e.getMessage() + " Ignoring.");
		}

		return null;
	}

}
