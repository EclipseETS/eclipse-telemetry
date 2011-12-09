package simulator.comm;


import java.io.*;

import gnu.io.*;

import org.apache.log4j.Logger;

import util.TelemetrySettings;

public class SimulatorSerial extends Thread{
	
	static CommPortIdentifier portId;
	static SerialPort	      serialPort;
	static OutputStream       out;
	static InputStream		  in;
	static boolean	      outputBufferEmptyFlag = false;
	boolean processusOuvert=true;
	static String  defaultPort = TelemetrySettings.getInstance().getSetting("SIM_SERIAL_PORT");
	
	// Logger
	static Logger logger = Logger.getLogger("simulator");
	

	
	public SimulatorSerial(){
		try {
	    	portId = CommPortIdentifier.getPortIdentifier(defaultPort);
	    	
	    } 
	    catch (NoSuchPortException e) {
	    		System.out.println("No port found");			    		
	    }
	    try{
	    	serialPort = (SerialPort) portId.open("Drive", 2000);
	    }
	    catch(PortInUseException e){
	    	System.out.println("Port in use.");			    	
	    }
	    try {
	    	out = serialPort.getOutputStream();
	    } 
	    catch (final IOException e) {}

	    try {
	    	
	    	serialPort.setSerialPortParams((int) Double.parseDouble(TelemetrySettings.getInstance().getSetting("HANDLER_SERIAL_BAULT")), 
					       SerialPort.DATABITS_8, 
					       SerialPort.STOPBITS_1, 
					       SerialPort.PARITY_NONE);
	    } 
	    catch (final UnsupportedCommOperationException e) {}
	    
		try {
	    	in = serialPort.getInputStream();
	    } 
	    catch (final IOException e) {}

	    try {
	    	serialPort.notifyOnDataAvailable(true);
	    }
	    catch (final Exception e) {
	    	System.out.println("Error setting event notification");
	    	System.out.println(e.toString());
	    	System.exit(-1);
	    }
	    try {
	    	serialPort.notifyOnOutputEmpty(true);
	    }
	    catch (final Exception e) {
	    	System.out.println("Error setting event notification");
	    	System.out.println(e.toString());
	    	System.exit(-1);
	    }
	}
	
	public void run(){
		
	}
	
	public void send(String frame) {
		try {
			out.write(frame.getBytes("ASCII"));
			out.write(13);	                                
		} catch (final IOException e) {}
		logger.info("Sending " + frame);
	}

	
}