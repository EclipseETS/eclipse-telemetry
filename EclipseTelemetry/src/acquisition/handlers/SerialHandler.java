package acquisition.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import util.TelemetrySettings;

import acquisition.AcquisitionHandler;
import gnu.io.*;





public class SerialHandler extends AcquisitionHandler {

final protected static String NAME = "XBEE";
static CommPortIdentifier portId;
static SerialPort	      serialPort;
static OutputStream       out;
static InputStream		  in;
static boolean	      outputBufferEmptyFlag = false;
static Logger logger = Logger.getLogger("telemetry");

boolean processusOuvert=true;
static String  defaultPort = TelemetrySettings.getInstance().getSetting("HANDLER_SERIAL_PORT");

	// Important flags used for exeption management
	private boolean connected = false;

	

	public SerialHandler() {
		
	}


	public Reader getReader() {

			try {
				return new InputStreamReader(in,"ASCII");
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			}
			return null;

	}



	public void start(){
		try {
	    	portId = CommPortIdentifier.getPortIdentifier(selectSerialPort());
	    	
	    } 
	    catch (NoSuchPortException e) {
	    		System.out.println("No port found try in one sec.");	
	    		logger.info("No port found try in one sec.");
	    		try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// 
					e1.printStackTrace();
				}
				start();
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
			logger.info("Connected to SERIAL successfully.");
	    } 
	    catch (final UnsupportedCommOperationException e) {}
	    try {
			serialPort.enableReceiveThreshold(0);
		} catch (UnsupportedCommOperationException e1) {
			// 
			e1.printStackTrace();
		}
		try {
			serialPort.enableReceiveTimeout(Integer.MAX_VALUE);
		} catch (UnsupportedCommOperationException e1) {
			// 
			e1.printStackTrace();
		}
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
	    
	    
    	connected=true;
    	setChanged();
    	notifyObservers();
	    
	}

	@Override
	public void stop() {
		serialPort.close();
    	connected=false;

		logger.info("SERIAL stop");	
		
	}
	
	public void retry() {
		
	}
	
	public void reconnect() {
	
	}
	
	public boolean isConnected() {
		return connected;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	public String selectSerialPort() {
		
		if(!defaultPort.contains("XXX"))
			return defaultPort;

        ArrayList<String> possibilities = new ArrayList<String>();
        possibilities.add("Emulator");
        for (CommPortIdentifier commportidentifier : getAvailableSerialPorts()) {
            possibilities.add(commportidentifier.getName());
        }

        int startPosition = 0;
        if (possibilities.size() > 1) {
            startPosition = 1;
        }
        
       return (String) JOptionPane.showInputDialog(
               null,
               "Télémetrie",
               "Select serial port",
               JOptionPane.PLAIN_MESSAGE,
               null,
               possibilities.toArray(),
               possibilities.toArray()[startPosition]);
        
    }
	
	public HashSet<CommPortIdentifier> getAvailableSerialPorts() {
        HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
        Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
        while (thePorts.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
            switch (com.getPortType()) {
                case CommPortIdentifier.PORT_SERIAL:
                    try {
                        CommPort thePort = com.open("CommUtil", 50);
                        thePort.close();
                        h.add(com);
                    } catch (PortInUseException e) {
                        System.out.println("Port, " + com.getName() + ", is in use.");
                    } catch (Exception e) {
                        System.err.println("Failed to open port " + com.getName());
                        e.printStackTrace();
                    }
            }
        }
        return h;
    }

}
