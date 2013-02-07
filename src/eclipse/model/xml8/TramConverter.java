package eclipse.model.xml8;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import eclipse.model.data.DeviceItem;
import eclipse.model.data.Trame;


/**
 * Converter standard for Tram
 * @author Marco
 *
 */
public class TramConverter implements Converter {

	static Logger logger = Logger.getLogger("main");
	
	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class type) {
		return type.equals(Trame.class);
	}

	public void marshal(Object value, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		// N/A
	}

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {

			Integer identifier = new Integer(reader.getAttribute("identifier"));
			Integer type = new Integer(reader.getAttribute("type"));

			Trame tram =new Trame(identifier, type);
			
			while (reader.hasMoreChildren()) { // While there are
												// <deviceItem>
				reader.moveDown(); // Go down to the <deviceItem>

				// Process the <deviceItem>
				DeviceItem di = (DeviceItem) context.convertAnother(new Object(), DeviceItem.class);
				tram.addItem(di);//add deviceItem to the tram
				logger.debug("DeviceItem :"+di.getItemId()+":"+di.getName()+" added.");
				reader.moveUp(); // Get back up to the <trame>
			}
			

		return tram;
	}

}
