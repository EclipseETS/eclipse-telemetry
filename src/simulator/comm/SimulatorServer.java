package simulator.comm;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class SimulatorServer extends Thread {
	
	// Logger
	static Logger logger = Logger.getLogger("simulator");
	
	ServerSocket ss;
	Socket cs;
	PrintStream pstream;
	
	public final static int PORT = 2000;
	
	public SimulatorServer(){
	}
	
	public void send(String frame) {
		pstream.println(frame);
		logger.info("Sending " + frame);
	}
	
	public void run() {
		logger.info("Starting Server..");
		try {
		    ss = new ServerSocket(PORT);
		    cs = ss.accept();
		    ss.close();
		    
		    //out = new PrintWriter(cs.getOutputStream(), true);
		    pstream = new PrintStream(cs.getOutputStream());
//		    
//		    for (int i = 1; i<100; i++) {
//		    	pstream.println(i);
//		    }
		    
		    //pstream.println("Connected");
		    
		} catch (IOException e) {
		    //logger.error("Could not listen on port " + PORT);
		    e.printStackTrace();
		}
	}
	
}
