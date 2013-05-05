package eclipse.controller.acqui;

import java.io.DataInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import eclipse.model.data.Data;

/**
 * Acquisition thread, this is the most important thread,
 * 
 * It is responsable to get data from the OS socket
 * 
 * @author Eclipse
 *
 */
public class DataAcquisition implements Runnable {

	private AcquisitionHandler handler = null;
	private Desencapsulator de = null;
	private Byte currentByte = null;
	DataInputStream input = null;
	static Logger logger = Logger.getLogger("main");
	static private DataAcquisition acqui = new DataAcquisition();
	
	public void Ititalize(AcquisitionHandler ah, Desencapsulator de) {
		this.handler = ah;
		this.de=de;
	}
	
	private DataAcquisition(){
		
	}
	
	public static DataAcquisition getInstance(){
		return acqui;
	}
	
	/**
	 * Start the thread
	 */
	public void run() {
		logger.debug("Acquisition start");
		startAcquiring();
	}
	
	/**
	 * Stop acquisition and reset curent array
	 */
	public void stopAcquiring(){
		logger.debug("Acquisition stop");
		handler.stop();
		de.clearData();		
	}

	/**
	 * Start acquisition
	 */
	public void startAcquiring() {
		handler.start();
		input=new DataInputStream(this.handler.getReader());
		listen();
	}
	
	
	/**
	 * Program wil loop here forever, will decode byte and transfer it to createArray
	 */
	public void listen(){
		while(true){
			try {
				currentByte=input.readByte();
				de.receiveChar(currentByte);
			} catch (IOException e) {
			}
		}
		
	}
	
	
	
		
}

