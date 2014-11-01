package eclipse.controller.acqui.handlers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import eclipse.controller.acqui.AcquisitionHandler;
import eclipse.controller.util.TelemetrySettings;

/**
 * This Handler is used to connect to a TCP port to receive the data, It is meant to be use with the Cellular connection with eclipse 9
 * 
 * @author Marco
 *
 */
public class TCPHandler extends AcquisitionHandler {

	private String name = "TCP";
	private boolean isConnected = false;
	Socket clientSocket;
	DataOutputStream outToServer;
	InputStream inFromServer;


	@SuppressWarnings("resource")
	@Override
	public Boolean start() {
		Socket clientSocket;
		try {
			clientSocket = new Socket(TelemetrySettings.getInstance().getSetting("HANDLER_TCP_IP"), Integer.parseInt(TelemetrySettings.getInstance().getSetting("HANDLER_SERIAL_BAULT")));
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = clientSocket.getInputStream();
			isConnected=true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return isConnected;
	}

	@Override
	public void stop() {
		if(isConnected){
			try {
				inFromServer.close();
				inFromServer=null;

				outToServer.close();
				outToServer=null;

				clientSocket.close();
				clientSocket=null;

				isConnected=false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("null")
	@Override
	public byte readByte() {
		byte[] rtn = {0};		
		if(isConnected){
			try {
				int i = inFromServer.read(rtn);
				if(i==-1) 
					rtn[0] = 0;
				return rtn[0];
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (Byte) null;
	}

	@Override
	public void writeByte(Byte bt) {
		if(isConnected){
			byte[] tbl = {bt};
			try {
				outToServer.write(tbl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean isConnected() {
		return isConnected;
	}

	@Override
	public String getName() {
		return name;
	}

}
