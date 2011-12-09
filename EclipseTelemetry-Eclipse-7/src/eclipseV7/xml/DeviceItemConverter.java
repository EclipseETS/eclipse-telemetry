package eclipseV7.xml;

import java.util.Vector;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import eclipseV7.data.DeviceItem;

public class DeviceItemConverter implements Converter {

	@SuppressWarnings("unchecked")
	@Override
    public boolean canConvert(Class type) {
        return type.equals(DeviceItem.class);
    }

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer,
                MarshallingContext context) {
		// N/A
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
                UnmarshallingContext context) {
		
//		System.out.println("     +--- Processing "
//				+ reader.getNodeName()
//				+ reader.getAttribute("id")
//				+ "-" + reader.getAttribute("name"));
		
		// <deviceItem> property list.
		// Define definitions to null in case
		// the DeviceItem has no definitions
		Integer itemId = 0;
		String name = null;
		String unit = null;
		int minValue = 0;
		int maxValue = 0;
		int status=0;
		double resolution = 0;
		double factor = 0;
		int offset = 0;
		int range = 0;
		int numBits = 0;
		boolean signed = false;
		boolean isFloat = false;
		Vector<String> definitionsOn = null; 
		Vector<String> definitionsOff = null; 
		
		// Set the <deviceItem> attributes
		itemId = Integer.parseInt(reader.getAttribute("id"));
		name = reader.getAttribute("name");
		
		while (reader.hasMoreChildren()) { // While there are some <deviceItem> properties
			reader.moveDown(); // Go down to this property
			
			// Set the <deviceItem> properties
			if ("unit".equals(reader.getNodeName())) unit = reader.getValue();
			else if ("minvalue".equals(reader.getNodeName())) minValue = Integer.parseInt(reader.getValue());
			else if ("maxvalue".equals(reader.getNodeName())) maxValue = Integer.parseInt(reader.getValue());
			else if ("status".equals(reader.getNodeName())) status = Integer.parseInt(reader.getValue());
			else if ("resolution".equals(reader.getNodeName())) resolution = Double.parseDouble(reader.getValue());
			else if ("factor".equals(reader.getNodeName())) factor = Double.parseDouble(reader.getValue());
			else if ("offset".equals(reader.getNodeName())) offset = Integer.parseInt(reader.getValue());
			else if ("range".equals(reader.getNodeName())) range = Integer.parseInt(reader.getValue());
			else if ("numbits".equals(reader.getNodeName())) numBits = Integer.parseInt(reader.getValue());
			else if ("signed".equals(reader.getNodeName())) signed = Boolean.parseBoolean(reader.getValue());
			else if ("isFloat".equals(reader.getNodeName())) isFloat = Boolean.parseBoolean(reader.getValue());
			else if ("definitionsBitsOn".equals(reader.getNodeName())) {
				// XStream is not Java 5 aware: ignore unsafe warning
				definitionsOn = (Vector<String>)context.convertAnother(new Object(), Vector.class);
			}
			else if ("definitionsBitsOff".equals(reader.getNodeName())) {
				// XStream is not Java 5 aware: ignore unsafe warning
				definitionsOff = (Vector<String>)context.convertAnother(new Object(), Vector.class);
			}

			reader.moveUp(); // Go back up to the <deviceItem>
		}
		
		// Return a new DeviceItem
        return new DeviceItem(itemId, name, unit, minValue, maxValue,status, 
        					resolution, factor, offset, range, numBits,
        					signed,isFloat, definitionsOn, definitionsOff);
	}

}
