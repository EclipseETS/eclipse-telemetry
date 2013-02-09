package eclipse.model.xml8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import eclipse.model.data.Device;
import eclipse.model.data.DeviceItem;
import eclipse.model.data.Trame;
import eclipse.model.xml.ProtocolLoader;

/**
 * Load the XML DATA in the DataManager
 * @author Marco
 *
 */
public class ProtocolLoaderV8 implements ProtocolLoader {

	static Logger logger = Logger.getLogger("main");
	FileInputStream fiStream;
	
	// XStream to load XML
	XStream stream;
	
	public ProtocolLoaderV8(String xmlFile) throws FileNotFoundException {
		init();
		fiStream = new FileInputStream(xmlFile);
	}
	
	private void init() {
		// Create new XStream instance with DomDriver parser
		stream = new XStream(new DomDriver());
		
		// Register the XML Converters
		stream.registerConverter(new DeviceConverter());
		stream.registerConverter(new TramConverter());
		stream.registerConverter(new DeviceItemConverter());
		
		// Create alias for V8 XML markup
		stream.alias("char", Device.class);
		stream.alias("deviceItem", DeviceItem.class);
		stream.alias("trame", Trame.class);
		
		stream.alias("unit", String.class);
		stream.alias("bitsize", Integer.class);
		stream.alias("minvalue", Integer.class);
		stream.alias("maxvalue", Integer.class);
		stream.alias("resolution", Integer.class);
		stream.alias("factor", Double.class);
		stream.alias("offset", Integer.class);
		stream.alias("numbits", Integer.class);
		stream.alias("signed", Boolean.class);
		stream.alias("isFloat", Boolean.class);
		
	}
	
	public void load() {
		stream.fromXML(fiStream);
		logger.info("Protocol loaded successfully.");
	}


}

