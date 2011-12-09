package acquisition;

import org.apache.log4j.Logger;

public class DataAcquisitionController {
	
	// Logger
	//static Logger logger = Logger.getLogger("comm");
	static Logger logger = Logger.getLogger("telemetry");
	// TODO : Fixer logger pour quil log sur tpa
	
	// Singleton
	static private DataAcquisitionController dac = null;
	
	private AcquisitionHandler handler = null;
	private FrameDesencapsulator frameDesencapsulator = null;
	
	private DataAcquisition acquisition = null;
	
	private DataAcquisitionController() {
	}
	
	public static DataAcquisitionController getInstance() {
		if (dac == null) dac = new DataAcquisitionController();
		return dac;
	}
	
	public void setAcquisitionHandler(AcquisitionHandler ah) {
		handler = ah;
	}
	
	public void setDesencapsulator(FrameDesencapsulator fd) {
		this.frameDesencapsulator = fd;
	}
	
	public void setEventProcessor() { // EventProcessor ep
		
	}
	
	public void start() {
		acquisition = new DataAcquisition(handler, frameDesencapsulator);
		acquisition.start();
		logger.info("Acquisition started.");
	}
	
	public void stop() {
		acquisition.stopAcquiring();
		logger.info("Acquisition stopped.");
	}

}
