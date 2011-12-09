package acquisition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;

import core.DataManager;
import exception.DeviceItemNotFoundException;
import exception.DeviceNotFoundException;

public class DataAcquisition extends Thread implements Observer {
	
	// Logger
	//static Logger logger = Logger.getLogger("comm");
	static Logger logger = Logger.getLogger("telemetry");
	// TODO : Fixer logger pour quil log sur tpa
	
	// Temp
	BufferedWriter out;
	
	private AcquisitionHandler handler = null;
	private FrameDesencapsulator frameDesencapsulator = null;
	BufferedReader input = null;
	
	public DataAcquisition(AcquisitionHandler ah, FrameDesencapsulator fd) {
		// Sets AcquisitionHandler and observes it
		this.handler = ah;
		ah.addObserver(this);
		
		// Sets Frame Desencapsulator
		this.frameDesencapsulator = fd;
		
		// Sets Event Processor
		//EventProcessor ep
	}
	
	public void stopAcquiring(){
		handler.stop();
		try {
			input.close();
		} catch (IOException e) {e.printStackTrace();}
	}

	public void startAcquiring() {
		//fileInit(); // temp
		handler.start();
	}
	
	public void fileInit() {
		// Create file
		FileWriter fstream;
		try {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
			fstream = new FileWriter("logs/telemetry-" + format.format(date) + "-log.txt");
			out = new BufferedWriter(fstream);
		} catch (IOException e) {e.printStackTrace();}
		
	}
	
	public void writeToFile(String line) {
		try {
			long epoch = System.currentTimeMillis()/1000;
			out.write(epoch + " " + line);
			out.newLine();
			out.flush();
		} catch (IOException e) {e.printStackTrace();}
		catch (Exception e) {e.printStackTrace();}
	}
	
	public void listen() {
		try {
			String line = null;
			
			int deviceId = 0;
			int deviceItemId = 0;
			byte[] data = null;
			
			input.readLine(); // Discard first line to be sure
			while ((line = input.readLine()) != null) {
				//TODO: Check CRC frameDesencapsulator.isCRCok(data)

				//System.out.println(line);
//			writeToFile(line); // log temp
				
				// Read line and get the fields
				deviceId = frameDesencapsulator.getDeviceId(line);
				deviceItemId = frameDesencapsulator.getDeviceItemId(line);
				data = frameDesencapsulator.getData(line);
								
				// Do things with collected data
				processFrame(deviceId, deviceItemId, data);
			}
		}
//		 catch (SocketTimeoutException e) { // Detects timeout on handlers with Socket
//			logger.error("Error: no data in too long. Resetting connection");
//			//stopAcquiring();
//			//startAcquiring();}
		 catch (IOException e) { // Other errors
			logger.error("IO Error: " + e.getMessage());
			//stopAcquiring();
			//startAcquiring();
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// 
				e1.printStackTrace();
			}
			listen();
		}
		
	}
	
	private void processFrame(int deviceId, int itemId, byte[] data) {
		try {
			
			String deviceName = DataManager.getInstance().getDevice(deviceId).getName();
			String deviceItemName = DataManager.getInstance().getDeviceItem(deviceId, itemId).getName();
			DataManager.getInstance().setData(deviceId, itemId, data);
			double dataToPrint = DataManager.getInstance().getDoubleData(deviceId, itemId);
			logger.trace(deviceName + "(" + deviceId + ") " + deviceItemName + "(" + itemId + ") = " + dataToPrint);
		
		} catch (DeviceNotFoundException e) {
			logger.error("Unknown data received: " + e .getMessage());
		} catch (DeviceItemNotFoundException e) {
			logger.error("Unknown data received: " + e.getMessage());
		}
	}

	@Override
	public void update(Observable o, Object obj) {
		input = new BufferedReader(handler.getReader());
		listen();
	}
	
	public void run() {
		startAcquiring();
	}
}

