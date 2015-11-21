package eclipse.controller.acqui.handlers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

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


	@Override
	public Boolean start() 
	{
		
		
		try 
		{
			clientSocket = new Socket(TelemetrySettings.getInstance().getSetting("HANDLER_TCP_IP"), Integer.parseInt(TelemetrySettings.getInstance().getSetting("HANDLER_TCP_PORT")));
			Boolean isOk = true;
			
			isOk = authenticate();
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = clientSocket.getInputStream();
			
			if (isOk == true)
			{
				isConnected=true;	
			}
			else
			{
				isConnected=false;
				clientSocket.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return isConnected;
	}
	
	public boolean authenticate()
	{
		boolean returnValue = true;
		
		try 
		{
			OutputStream tempOutputStream =  clientSocket.getOutputStream();
			InputStream  tempInputStream = clientSocket.getInputStream();
			
			DataOutputStream out = new DataOutputStream(tempOutputStream);			
			DataInputStream in = new DataInputStream(tempInputStream);
			
			byte[] toSend;
			byte[] received = new byte[16];
				
			in.read(received, 0, 16);
			
			System.out.println(Arrays.toString(received));
		   
			toSend = xorString(received);
			
			out.write(toSend);
			out.flush();
		} 
		catch (IOException e) 
		{
			System.out.println("Authenticate IOException");
			System.out.println(e.toString());
			e.printStackTrace();
			returnValue = false;
			System.out.println(e.toString());
		}
		
		
		return returnValue;
	}
	
	public byte[] xorString(byte[] received)
	{
		
		
		
		
		byte [] xored = new byte[16];
		byte [] toxor = received;
		byte [] key =  ("kperryisourqueen").getBytes();
		
		
		for(int i = 0; i < 16; ++i)
		{
			xored[i]  = (byte) ((toxor[i]) ^ (key[i]));
			
		}
		
		return xored;
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
	public byte readByte() 
	{
		byte[] rtn = {0};		
		if(isConnected){
			try 
			{
				int i = inFromServer.read(rtn);
				
				if(i==-1) 
				{
					rtn[0] = 0;
					stop();				
				}
					
				return rtn[0];
			} 
			catch (IOException e) 
			{
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