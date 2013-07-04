package eclipse.controller.app;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import eclipse.controller.acqui.DataAcquisition;
import eclipse.controller.acqui.DesencapsulatorE8Serial;
import eclipse.controller.acqui.handlers.SimpleSerialHandler;
import eclipse.controller.util.TelemetrySettings;
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
 * All this apps is Interface based. For  futher version just add new version of file (every class and package with 8)
 * 
 * ETS, École de Technologie suppérieure.
 * 
 * @author Marco
 *
 */
public class EclipseTelemetry {
	
	private static ThreadGen threadGenInstance;
	private static final String SETTINGS_FILE = "telemetrySettings.properties";
	private static DataAcquisition dataAcqui;
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
		
				
		//=================================Acquisition============================================
		dataAcqui = DataAcquisition.getInstance();
		dataAcqui.Ititalize(new SimpleSerialHandler(),new DesencapsulatorE8Serial());
		threadGenInstance.addThread(dataAcqui);
		
		//==================================STUB==================================================
		//threadGenInstance.addThread(new TESTER());
		

		//=================================MATLAB===============================================
		threadGenInstance.addThread(new MatLab());
		
		//=================================ERROR===============================================
		threadGenInstance.addThread(new ErrorFinder());
		
		
		//=================================Start Gui===============================================
		threadGenInstance.addThread(DesktopManager.getIstance());
				
		//==========================Start the ThreadGenerator=======================================
		threadGenInstance.startThread();
	}
	
	
	/**
	 * Application killer, whit error log4j
	 */
	public static void abort() {
		logger.debug("App abort. X button pressed!!!");
		System.exit(1);
	}

}
