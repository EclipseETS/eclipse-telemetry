package eclipse.controller.app;

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//init threadGen
		threadGenInstance = ThreadGen.getInstance();
		
		
		initApp();
	}
	
	private static void initApp(){
		
		threadGenInstance.addThread(new TESTER());
		
		
		//Start the ThreadGenerator
		threadGenInstance.startThread();
	}

}
