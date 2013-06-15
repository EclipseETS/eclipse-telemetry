package eclipse.model.xml8;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import eclipse.model.data.DeviceItem;

/**
 * Standard Converter for the DeviceItem
 * @author Marco
 *
 */
public class DeviceItemConverter implements Converter {

	@SuppressWarnings("rawtypes")
    public boolean canConvert(Class type) {
        return type.equals(DeviceItem.class);
    }

	public void marshal(Object value, HierarchicalStreamWriter writer,
                MarshallingContext context) {
		// N/A
	}

	public Object unmarshal(HierarchicalStreamReader reader,
                UnmarshallingContext context) {

		
		Integer itemId = 0;
		String name = null;
		String unit = null;
		int bitsize = 0;
		double minValue = 0;
		double maxValue = 0;
		double resolution = 0;
		double factor = 0;
		int offset = 0;
		boolean signed = false;
		boolean isFloat = false;
		
		// Set the <deviceItem> attributes
		itemId = Integer.parseInt(reader.getAttribute("id"));
		name = reader.getAttribute("name");
		
		while (reader.hasMoreChildren()) { // While there are some <deviceItem> properties
			reader.moveDown(); // Go down to this property
			
			// Set the <deviceItem> properties
			if ("unit".equals(reader.getNodeName())) unit = reader.getValue();
			else if ("bitsize".equals(reader.getNodeName())) bitsize = Integer.parseInt(reader.getValue());
			else if ("minvalue".equals(reader.getNodeName())) minValue = Double.parseDouble(reader.getValue());
			else if ("maxvalue".equals(reader.getNodeName())) maxValue = Double.parseDouble(reader.getValue());
			else if ("resolution".equals(reader.getNodeName())) resolution = Double.parseDouble(reader.getValue());
			else if ("factor".equals(reader.getNodeName())) factor = Double.parseDouble(reader.getValue());
			else if ("offset".equals(reader.getNodeName())) offset = Integer.parseInt(reader.getValue());
			else if ("signed".equals(reader.getNodeName())) signed = Boolean.parseBoolean(reader.getValue());
			else if ("isFloat".equals(reader.getNodeName())) isFloat = Boolean.parseBoolean(reader.getValue());

			reader.moveUp(); // Go back up to the <deviceItem>
		}
		
		// Return a new DeviceItem
        return new DeviceItem(itemId, name, unit, bitsize, minValue, maxValue,
        					resolution, factor, offset,
        					signed,isFloat);
	}

}
