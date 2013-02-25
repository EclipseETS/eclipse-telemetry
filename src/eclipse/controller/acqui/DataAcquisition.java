package eclipse.controller.acqui;

import java.io.DataInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

public class DataAcquisition implements Runnable {

	private AcquisitionHandler handler = null;
	private Desencapsulator de = null;
	private Byte currentByte = null;
	DataInputStream input = null;
	static Logger logger = Logger.getLogger("telemetry");
	
	public DataAcquisition(AcquisitionHandler ah, Desencapsulator de) {
		this.handler = ah;
		this.de=de;
	}
	
	/**
	 * Start the thread
	 */
	public void run() {
		startAcquiring();
	}
	
	/**
	 * Stop acquisition and reset curent array
	 */
	public void stopAcquiring(){
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

