package acquisition;

import java.io.Reader;
import java.util.Observable;

public abstract class AcquisitionHandler extends Observable {
	
	// Start/stop the handler
	public abstract void start();
	public abstract void stop();
	
	// Basic I/O. Must return Reader (FileReader, InputStreamReader..)
	public abstract Reader getReader();
	
	// Verifies if the handler is connected and reader to stream
	public abstract boolean isConnected();
	
	public abstract String getName();
}
