package acquisition.handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import acquisition.AcquisitionHandler;

import util.TelemetrySettings;


public class TCPHandler extends AcquisitionHandler {
	
	final protected static String NAME = "TCP";
	
	Socket clientSocket = null;
	InetSocketAddress socketAddress = null;
	InputStreamReader in = null;
	
	// Maximum retries
	final int RETRIES = Integer.parseInt(TelemetrySettings.getInstance()
			.getSetting("HANDLER_TCP_RETRIES"));
	int tryCounter = RETRIES;

	// Important flags used for exeption management
	private boolean connected = false;
	private boolean mustRetry = false;
	private boolean mustReconnect = false;

	// Connect Info
	final protected String HOST =
		TelemetrySettings.getInstance().getSetting("HANDLER_TCP_HOST");
	final protected int PORT =
		Integer.parseInt(TelemetrySettings.getInstance().getSetting("HANDLER_TCP_PORT"));
	final protected int TIMEOUT = 
		Integer.parseInt(TelemetrySettings.getInstance().getSetting("HANDLER_TCP_TIMEOUT"));
	
	// Logger
	//static Logger logger = Logger.getLogger("comm");
	static Logger logger = Logger.getLogger("telemetry");
	// TODO : Fixer logger pour quil log sur tpa
	
	public TCPHandler() {
		socketAddress = new InetSocketAddress(HOST, PORT);
	}

	@Override
	public Reader getReader() {
		return in;
	}

	@Override
	public void start() {
		
		do {
		try {
			logger.info("Attempting to connect to " + HOST + ":" + PORT);
			
			clientSocket = new Socket();
			//clientSocket.setKeepAlive(true);
			//clientSocket.setTcpNoDelay(true);
			clientSocket.setSoTimeout(TIMEOUT);
			clientSocket.connect(socketAddress, TIMEOUT);
			logger.info("Connected to " + HOST + ":" + PORT + " successfully.");
			
			in = new InputStreamReader(clientSocket.getInputStream());
			while (!in.ready()) {} // Wait till stream is ready
			connected = true;
			
			if (in.ready()) { // Connected: notify observer to start listening
				setChanged();
				notifyObservers();
			}
			
		} catch (UnknownHostException e) {
			logger.error("Unkown host "+ e.getMessage());
		} catch (SocketTimeoutException e) {
			// Timed out: retry
			logger.error("Timeout error!");
			if (tryCounter > 0) {
				connected = false;
				mustReconnect = true;
				retry();
			} else {mustReconnect = false;}
		} catch (SocketException e) {
			logger.error("Connection error: " + e.getMessage());
			if (tryCounter > 0 && !isConnected()) {
				// If not connected and tries left
				mustRetry = true;
				retry();
			} else {mustRetry = false;}
			if (isConnected()) {
				// If was supposed to be connected: Reconnect
				connected = false;
				mustReconnect = true;
				reconnect();
			} else {mustReconnect = false;}
		} catch (IOException e) {
			logger.error("IO error: " + e.getMessage());
			if (tryCounter > 0 && !isConnected()) {
				// If not connected and tries left
				mustRetry = true;
				retry();
			} else {mustRetry = false;}
			if (isConnected()) {
				// If was supposed to be connected: Reconnect
				connected = false;
				mustReconnect = true;
				reconnect();
			} else {mustReconnect = false;}
		}
		
		//System.out.println("mustRetry: " + mustRetry + " ** mustReconnect: " + mustReconnect);
		} while (mustRetry || mustReconnect);
		stop();
	}

	@Override
	public void stop() {
		try {
			if (in != null) in.close();
			clientSocket.close();
			connected = false;
		} catch (IOException e) {
			logger.error("Error while disconnecting.");
		}

	}
	
	public void retry() {
		try {
			logger.info(tryCounter + " retries remaining. Retrying in " + TIMEOUT/1000 + "s");
			Thread.sleep(TIMEOUT);
			tryCounter --;
			stop();
		} catch (InterruptedException e) {stop();}
	}
	
	public void reconnect() {
		logger.warn("Connection lost. Reconnecting...");
		tryCounter = RETRIES; // Reset try counter
		stop();
	}
	
	public boolean isConnected() {
		return connected;
	}

	@Override
	public String getName() {
		return NAME;
	}

}
