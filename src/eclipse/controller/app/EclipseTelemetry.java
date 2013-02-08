package eclipse.controller.app;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import eclipse.controller.util.TelemetrySettings;
import eclipse.model.data.DataManager;
import eclipse.model.xml.ProtocolLoader;
import eclipse.model.xml.ProtocolValidator;
import eclipse.model.xml8.ProtocolLoaderV8;
import eclipse.model.xml8.ProtocolValidatorV8;
import eclipse.view.gui.DesktopManager;

/**
 * Eclipse Telemetry, Start Point, Where you can find the main of this application. This application, 
 * receive data from solar car and show them in different windows.
 * 
 * This telemetry is used for Eclipse solar car 8.
 * 
 * ETS, �cole de Technologie supp�rieure.
 * 
 * @author Marco
 *
 */
public class EclipseTelemetry {
	
	private static ThreadGen threadGenInstance;
	private static final String SETTINGS_FILE = "telemetrySettings.properties";
	static Logger logger = Logger.getLogger("main");



	public static void main(String[] args) {
		
		
		//init threadGen
		threadGenInstance = ThreadGen.getInstance();
		
		//Initialisation
		initApp();
		
		logger.debug("App started");
	}
	
	/**
	 * Initialise all information and thread for this app
	 * XML, Acquisition, gui, persistance, etc
	 */
	private static void initApp(){
		
		//==============================Logger configuration========================================
		PropertyConfigurator.configure("log4j.configuration");		
		logger.info("App initializing..");
		
		// =============================Load global settings========================================
		logger.info("Loading global telemetry settings..");
		TelemetrySettings.getInstance().load(SETTINGS_FILE);

		
		
		//===================================Load XML===============================================
		logger.debug("XML verification");
		
		// Load needed settings for init
		String protocolSchema = TelemetrySettings.getInstance().getSetting("XML_PROTOCOL_SCHEMA");
		String protocolXML = TelemetrySettings.getInstance().getSetting("XML_PROTOCOL_FILE");

		// Create ProtocolValidator using ProtocolValidatorV7
		ProtocolValidator protocolValidator;
		
		// Making sure nothing goes wrong in the init.
		try {
			protocolValidator = new ProtocolValidatorV8(protocolXML, protocolSchema);
			
			// Validate and load protocol into data structure using ProtocolLoaderV7
			if (protocolValidator.xmlIsValid()) {
				ProtocolLoader protocolLoader = new ProtocolLoaderV8(protocolXML);
				protocolLoader.load();
			}
			else {
				logger.error("Initialization failure. XML validation eror. Exiting.");
				abort();
			}
			
		} catch (Exception e) {
			logger.error("Initialization failure. " + e.getMessage() + ". Exiting.");
			 abort();
		} 
		
				
		
		
		//==================================STUB==================================================
		//TODO: TO BE REMOVED
		threadGenInstance.addThread(new TESTER());
		

		DataManager data = DataManager.getInstance();
		//=================================Start Gui===============================================
		threadGenInstance.addThread(new DesktopManager());
		
		//==========================Start the ThreadGenerator=======================================
		threadGenInstance.startThread();
	}
	
	
	/**
	 * Application killer, whit error log4j
	 */
	public static void abort() {
		logger.error("App abort.");
		System.exit(1);
	}

}