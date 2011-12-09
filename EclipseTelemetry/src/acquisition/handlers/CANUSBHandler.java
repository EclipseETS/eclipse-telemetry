package acquisition.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import acquisition.AcquisitionHandler;
import gnu.io.*;





public class CANUSBHandler extends AcquisitionHandler {

final protected static String NAME = "XBEE";
static CommPortIdentifier portId;
static SerialPort	      serialPort;
static OutputStream       out;
static InputStream		  in;
static boolean	      outputBufferEmptyFlag = false;
boolean processusOuvert=true;
static String  defaultPort = "/dev/ttyUSB0";

	// Important flags used for exeption management
	private boolean connected = false;


	static Logger logger = Logger.getLogger("telemetry");

	public CANUSBHandler() {
	}

	public Reader getReader() {

			try {
				return new InputStreamReader(in,"ASCII");
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			}
			return null;
	}

	public void start() {
		try {
			portId = CommPortIdentifier.getPortIdentifier(defaultPort);
			serialPort = (SerialPort) portId.open("Drive", 2000);
			out = serialPort.getOutputStream();
			serialPort.setSerialPortParams(57600, 
				       SerialPort.DATABITS_8, 
				       SerialPort.STOPBITS_1, 
				       SerialPort.PARITY_NONE);
			in = serialPort.getInputStream();
			serialPort.notifyOnDataAvailable(true);
			//serialPort.notifyOnOutputEmpty(true);
			
			out.write('S');
			out.write('8');
			out.write('\r');
			out.write('O');
			out.write('\r');
			in.read();
			in.read();
			
			connected=true;
	    	setChanged();
	    	notifyObservers();
			logger.info("Connected to CANBUS successfully.");
	    	
	    } 
	    catch (NoSuchPortException e) {
			logger.info("No port Found");	
	    }catch(PortInUseException e){
			logger.info("Port in use");		    	
	    }
	    catch (final UnsupportedCommOperationException e) {}
	    catch (final IOException e) {}

	    catch (final Exception e) {
	    	System.out.println("Error setting event notification");
	    	System.out.println(e.toString());
	    	System.exit(-1);
	    }
	}

	@Override
	public void stop() {
		if(true == connected)
		{
		    try {
				out.write('C');
				out.write('\r');
		    } catch (IOException e) {
				// 
				e.printStackTrace();
			}
		    serialPort.close();
	    	connected=false;
		}
		logger.info("CANBUS stop");	
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

}
