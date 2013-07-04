package eclipse.controller.app;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import eclipse.controller.util.TelemetrySettings;
import eclipse.model.data.DataManager;
/**
 * This class provide specific information for matlab file, for mecanical analyses
 * @author Marco
 *
 */
public class MatLab implements Runnable{

	private String sep = ",";
	
	public void run() {
		DataManager dd = DataManager.getInstance();
		
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("_yyyy_MM_dd_hh_mm_ss");

		String filename = ft.format(dNow)+".txt";
		try {
			FileWriter fstream = new FileWriter(TelemetrySettings.getInstance().getSetting("MATLABFILE")+filename,true);
			BufferedWriter out = new BufferedWriter(fstream);
			//Copy value with this format
			 // out.write("HEURE,TENSION,COURANT,RPMFR,RPMFL,RPMRR,RPMRL,LAT,LON,X,Y,X,INCL\r\n");
		    out.close();
		    fstream.close();
		    while(true){
		    	fstream = new FileWriter(TelemetrySettings.getInstance().getSetting("MATLABFILE")+filename,true);
		    	out = new BufferedWriter(fstream);
					 out.write(
		    			dd.getDeviceByID(6).getItemByID(4).getLastData()+sep+//HEURE
		    			dd.getDeviceByID(7).getItemByID(44).getLastData()+sep+//TENSION
		    			dd.getDeviceByID(7).getItemByID(47).getLastData()+sep+//COURANT
		    			dd.getDeviceByID(3).getItemByID(11).getLastData()+sep+//FR
		 				dd.getDeviceByID(3).getItemByID(12).getLastData()+sep+//FL
		 				dd.getDeviceByID(3).getItemByID(13).getLastData()+sep+//RR
		 				dd.getDeviceByID(3).getItemByID(14).getLastData()+sep+//RL
		 				dd.getDeviceByID(6).getItemByID(2).getLastData()+sep+//LAT
		 				dd.getDeviceByID(6).getItemByID(3).getLastData()+sep+//LON
		 				dd.getDeviceByID(6).getItemByID(9).getLastData()+sep+//X
		 				dd.getDeviceByID(6).getItemByID(10).getLastData()+sep+//Y
		 				dd.getDeviceByID(6).getItemByID(11).getLastData()+sep+//Z
		 				dd.getDeviceByID(6).getItemByID(7).getLastData()+"\r\n"//INCL
		 				);
					 out.close();
					 fstream.close();
				try {
					Thread.sleep(Integer.parseInt(TelemetrySettings.getInstance().getSetting("MATLABTIMER")));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
		}
		
	}

}
