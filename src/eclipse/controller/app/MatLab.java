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
	
	private static final int DRIVE_ID = 2;
	private static final int BMS_ID = 3;
	private static final int INSTRU_ID = 6;
	private static final int MPPT1_ID = 8;
	private static final int MPPT2_ID = 12;
	private static final int MPPT3_ID = 10;
	
	private static final int DRIVE_VOLTAGE_ID = 7;
	private static final int DRIVE_CURRENT_ID = 8;
	private static final int DRIVE_RPM_ID = 10;
	
	private static final int BMS_CAPACITY_ID = 50;
	private static final int BMS_PACKA_ID = 72;
	private static final int BMS_PACKV_ID = 73;
	
	private static final int INSTRU_LAT_ID = 2;
	private static final int INSTRU_LON_ID = 3;
	private static final int INSTRU_TIME_ID = 4;
	
	private static final int MPPT_VOUT_ID = 2;
	private static final int MPPT_IOUT_ID = 3;

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
			    			dd.getRawValue(INSTRU_ID, INSTRU_TIME_ID)+sep+//HEURE
			    			dd.getRawValue(INSTRU_ID, INSTRU_LAT_ID)+sep+//LAT
			 				dd.getRawValue(INSTRU_ID, INSTRU_LON_ID)+sep+//LON
			 				dd.getRawValue(MPPT1_ID, MPPT_VOUT_ID)+sep+//MPPT_1_V
			 				dd.getRawValue(MPPT1_ID, MPPT_IOUT_ID)+sep+//MPPT_1_C
			 				dd.getRawValue(MPPT2_ID, MPPT_VOUT_ID)+sep+//MPPT_2_V
			 				dd.getRawValue(MPPT2_ID, MPPT_IOUT_ID)+sep+//MPPT_2_C
			 				dd.getRawValue(MPPT3_ID, MPPT_VOUT_ID)+sep+//MPPT_3_V
			 				dd.getRawValue(MPPT3_ID, MPPT_IOUT_ID)+sep+//MPPT_3_C
			 				dd.getRawValue(DRIVE_ID, DRIVE_RPM_ID)+sep+//FR_RPM
			 				dd.getRawValue(DRIVE_ID, DRIVE_CURRENT_ID)+sep+//FR_C
			 				dd.getRawValue(DRIVE_ID, DRIVE_VOLTAGE_ID)+sep+//FR_T
			 				dd.getRawValue(BMS_ID, BMS_CAPACITY_ID)+sep+//BMS_AH
			    			dd.getRawValue(BMS_ID, BMS_PACKA_ID)+sep+//BMS_COURANT
			    			dd.getRawValue(BMS_ID, BMS_PACKV_ID)+//BMS_TENSION
			    			"\r\n"
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
