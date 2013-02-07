package eclipse.controller.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TelemetrySettings {

	static Logger logger = Logger.getLogger("main");
	static TelemetrySettings telemetrySettings = null;
	static Properties settings = null;
	static String path = null;
	
	private TelemetrySettings() {
		settings = new Properties();
	}
	
	public static TelemetrySettings getInstance() {
		if (telemetrySettings == null) telemetrySettings = new TelemetrySettings();
		return telemetrySettings;
	}
	
	public void load(String propertiesFile) {
		path=propertiesFile;
		try {
			settings.load(new FileReader(propertiesFile));
		} catch (FileNotFoundException e) {
			logger.fatal(e.getMessage());
		} catch (IOException e) {
			logger.fatal(e.getMessage());
		}
	}
	
	public String getSetting(String propertyName) {			
		if (settings.getProperty(propertyName) == null)
			logger.warn("Setting property '" + propertyName + "' is null. Unexpected behavior probable.");
		return settings.getProperty(propertyName);
	}
	
	public void setSetting(String propertyName,String value) {			
		if (settings.getProperty(propertyName) == null)
			logger.warn("Setting property '" + propertyName + "' is null. Unexpected behavior probable.");
		if(propertyName!=null&&value!=null)
			settings.setProperty(propertyName, value);
		try {
			settings.store(new FileOutputStream(path), null);
		} catch (FileNotFoundException e) {
			logger.fatal(e.getMessage());
		} catch (IOException e) {
			logger.fatal(e.getMessage());
		}
		//load(path);
	}
}
