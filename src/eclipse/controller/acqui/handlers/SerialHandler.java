package eclipse.controller.acqui.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import eclipse.controller.acqui.AcquisitionHandler;
import eclipse.controller.util.TelemetrySettings;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;





/**
 * Handler that use serial connection to get information
 * Work with zigbee for eclipse
 * @author Marco
 *
 */
public class SerialHandler extends AcquisitionHandler {

	
//All connection speed info come from setting file
final protected static String NAME = "XBEE";
static CommPortIdentifier portId;
static SerialPort	      serialPort;
static OutputStream       out;
static InputStream		  in;
static boolean	      outputBufferEmptyFlag = false;
static Logger logger = Logger.getLogger("main");

boolean processusOuvert=true;

	// Important flags used for exeption management
	private boolean connected = false;

	
	public SerialHandler() {
		
	}


	/**
	 * Reader use for all comunication
	 */
	public InputStream getReader() {

			return in;

	}

	
	/**
	 * Start the reading can take 1 or 2 second. 
	 * Open the port
	 * choose selected port
	 * Handle errors
	 */
	public void start(){
		try {
	    	portId = CommPortIdentifier.getPortIdentifier(selectSerialPort());
	    	
	    } 
	    catch (NoSuchPortException e) {
	    		System.out.println("No port found try in one sec.");	
	    		logger.info("No port found try in one sec.");
	    		TelemetrySettings.getInstance().setSetting("HANDLER_SERIAL_PORT", "XXX");
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
    	logger.info("Handler Ready");
	    
	}

	/**
	 * stop communication
	 */
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

	public String getName() {
		return NAME;
	}
	
	/**
	 * Resturn selected port according to property files or port selector
	 * @return
	 */
	public String selectSerialPort() {
		
		if(!TelemetrySettings.getInstance().getSetting("HANDLER_SERIAL_PORT").contains("XXX"))
			return TelemetrySettings.getInstance().getSetting("HANDLER_SERIAL_PORT");

        ArrayList<String> possibilities = new ArrayList<String>();
        possibilities.add("Emulator");
        for (CommPortIdentifier commportidentifier : getAvailableSerialPorts()) {
            possibilities.add(commportidentifier.getName());
        }

        int startPosition = 0;
        if (possibilities.size() > 1) {
            startPosition = 1;
        }
        
        String retour = (String) JOptionPane.showInputDialog(
                null,
                "Télémetrie",
                "Select serial port",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities.toArray(),
                possibilities.toArray()[startPosition]);
        
        TelemetrySettings.getInstance().setSetting("HANDLER_SERIAL_PORT", retour);
        
       return retour;
        
    }
	
	/**
	 * List available port
	 * @return
	 */
	public HashSet<CommPortIdentifier> getAvailableSerialPorts() {
        HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
        @SuppressWarnings("rawtypes")
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
