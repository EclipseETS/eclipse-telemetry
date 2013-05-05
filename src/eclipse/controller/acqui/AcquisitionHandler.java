package eclipse.controller.acqui;

import java.io.InputStream;
import java.util.Observable;

/**
 * Abstract class that provide handler of different type, such as serial, tcp, canbus or whateva
 * @author Marco
 *
 */
public abstract class AcquisitionHandler extends Observable {
	
	// Start/stop the handler
	public abstract Boolean start();
	public abstract void stop();
	
	// Basic I/O. Must return Reader (FileReader, InputStreamReader..)
	public abstract InputStream getReader();
	
	// Verifies if the handler is connected and reader to stream
	public abstract boolean isConnected();
	
	public abstract String getName();
}
