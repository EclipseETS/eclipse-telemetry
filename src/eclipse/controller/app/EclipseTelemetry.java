package eclipse.controller.app;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import eclipse.view.gui.DesktopManager;

/**
 * Eclipse Telemetry, Start Point, Where you can find the main of this application. This application, 
 * receive data from solar car and show them in different windows.
 * 
 * This telemetry is used for Eclipse solar car 8.
 * 
 * ETS, École de Technologie suppérieure.
 * 
 * @author Marco
 *
 */
public class EclipseTelemetry {
	
	private static ThreadGen threadGenInstance;
	
	
	// Root Logger
	static Logger logger = Logger.getLogger("main");



	public static void main(String[] args) {
		
		
		//init threadGen
		threadGenInstance = ThreadGen.getInstance();
		
		
		initApp();
		
		logger.debug("App started");
	}
	
	private static void initApp(){
		
		//Logger configuration
		PropertyConfigurator.configure("log4j.configuration");		
		logger.info("App initializing..");

		threadGenInstance.addThread(new TESTER());
		
		
		//Start Gui
		threadGenInstance.addThread(new DesktopManager());
		
		//Start the ThreadGenerator
		threadGenInstance.startThread();
	}

}
