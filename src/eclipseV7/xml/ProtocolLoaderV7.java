package eclipseV7.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import core.ProtocolLoader;
import eclipseV7.data.Device;
import eclipseV7.data.DeviceItem;

public class ProtocolLoaderV7 implements ProtocolLoader {

	static Logger logger = Logger.getLogger("xml");
	FileInputStream fiStream;
	
	// XStream to load XML
	XStream stream;
	
	public ProtocolLoaderV7(String xmlFile) throws FileNotFoundException {
		init();
		fiStream = new FileInputStream(xmlFile);
	}
	
	private void init() {
		// Create new XStream instance with DomDriver parser
		stream = new XStream(new DomDriver());
		
		// Register the XML Converters
		stream.registerConverter(new DeviceConverter());
		stream.registerConverter(new DeviceItemConverter());
		
		// Create alias for V7 XML markup
		stream.alias("devices", Device.class);
		stream.alias("deviceItem", DeviceItem.class);
		
		stream.alias("unit", String.class);
		stream.alias("minvalue", Integer.class);
		stream.alias("maxvalue", Integer.class);
		stream.alias("resolution", Integer.class);
		stream.alias("factor", Double.class);
		stream.alias("offset", Integer.class);
		stream.alias("range", Integer.class);
		stream.alias("numbits", Integer.class);
		stream.alias("signed", Boolean.class);
		
		stream.alias("definitions", Vector.class);
		stream.alias("definition", String.class);
	}
	
	@Override
	public void load() {
		stream.fromXML(fiStream);
		logger.info("Protocol loaded successfully.");
	}


}

// //System.out.println(stream.toXML(DataManager.getInstance().getTelemetryStorage()));
