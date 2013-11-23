package eclipse.controller.app;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import eclipse.controller.acqui.DataAcquisition;
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
		FileWriter fstream;
		BufferedWriter out;
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("_yyyy_MM_dd_hh_mm_ss");

		String filename = ft.format(dNow)+".txt";
		//String filename = "MATLAB.txt";
		try {
//			FileWriter fstream = new FileWriter(TelemetrySettings.getInstance().getSetting("MATLABFILE")+filename,true);
//			BufferedWriter out = new BufferedWriter(fstream);
//			//Copy value with this format
//			 // out.write("HEURE,TENSION,COURANT,RPMFR,RPMFL,RPMRR,RPMRL,LAT,LON,X,Y,X,INCL\r\n");
//		    out.close();
//		    fstream.close();
		    while(true){
		    	if(DataAcquisition.getInstance().getAcquiStatus()){
			    	fstream = new FileWriter(TelemetrySettings.getInstance().getSetting("MATLABFILE")+filename,true);
			    	out = new BufferedWriter(fstream);
						 out.write(
			    			dd.getDeviceByID(6).getItemByID(4).getLastData()+sep+//HEURE
			    			dd.getDeviceByID(6).getItemByID(2).getLastData()+sep+//LAT
			 				dd.getDeviceByID(6).getItemByID(3).getLastData()+sep+//LON
			 				dd.getDeviceByID(8).getItemByID(2).getLastData()+sep+//MPPT_1_V
			 				dd.getDeviceByID(8).getItemByID(3).getLastData()+sep+//MPPT_1_C
			 				dd.getDeviceByID(9).getItemByID(2).getLastData()+sep+//MPPT_2_V
			 				dd.getDeviceByID(9).getItemByID(3).getLastData()+sep+//MPPT_2_C
			 				dd.getDeviceByID(10).getItemByID(2).getLastData()+sep+//MPPT_3_V
			 				dd.getDeviceByID(10).getItemByID(3).getLastData()+sep+//MPPT_3_C
			 				dd.getDeviceByID(12).getItemByID(9).getLastData()+sep+//FR_RPM
			 				dd.getDeviceByID(12).getItemByID(8).getLastData()+sep+//FR_C
			 				dd.getDeviceByID(12).getItemByID(7).getLastData()+sep+//FR_T
			 				dd.getDeviceByID(12).getItemByID(22).getLastData()+sep+//FR_AH
			 				dd.getDeviceByID(13).getItemByID(9).getLastData()+sep+//FL_RPM
			 				dd.getDeviceByID(13).getItemByID(8).getLastData()+sep+//FL_C
			 				dd.getDeviceByID(13).getItemByID(7).getLastData()+sep+//FL_T
			 				dd.getDeviceByID(13).getItemByID(22).getLastData()+sep+//FL_AH
			 				dd.getDeviceByID(14).getItemByID(9).getLastData()+sep+//RR_RPM
			 				dd.getDeviceByID(14).getItemByID(8).getLastData()+sep+//RR_C
			 				dd.getDeviceByID(14).getItemByID(7).getLastData()+sep+//RR_T
			 				dd.getDeviceByID(14).getItemByID(22).getLastData()+sep+//RR_AH
			 				dd.getDeviceByID(15).getItemByID(9).getLastData()+sep+//RL_RPM
			 				dd.getDeviceByID(15).getItemByID(8).getLastData()+sep+//RL_C
			 				dd.getDeviceByID(15).getItemByID(7).getLastData()+sep+//RL_AT
			 				dd.getDeviceByID(15).getItemByID(22).getLastData()+sep+//RL_AH
			    			dd.getDeviceByID(7).getItemByID(44).getLastData()+sep+//COURANT
			    			dd.getDeviceByID(7).getItemByID(48).getLastData()+sep//TENSION
			 				);
						 out.close();
						 fstream.close();
					try {
						Thread.sleep(Integer.parseInt(TelemetrySettings.getInstance().getSetting("MATLABTIMER")));
					} catch (InterruptedException e) {
						StringWriter stack = new StringWriter();
//						e.printStackTrace(new PrintWriter(stack));
						Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
					}
		    	}
		    	else
		    	{
		    		try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						StringWriter stack = new StringWriter();
						e.printStackTrace(new PrintWriter(stack));
						Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
					}
		    	}
			}
		} catch (IOException e) {
		}
		
	}

}
