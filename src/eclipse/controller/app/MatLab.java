package eclipse.controller.app;


import java.io.BufferedWriter;
import java.io.File;
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
	private static final int PSU_ID = 7;
	
	private static final int DRIVE_ABUS_ID = 7;
	private static final int DRIVE_VBUS_ID = 8;
	private static final int DRIVE_RPM_ID = 10;
	
	private static final int BMS_AH_ID = 47;
	private static final int BMS_PACKA_ID = 72;
	private static final int BMS_PACKV_ID = 73;
	
	private static final int INSTRU_LAT_ID = 2;
	private static final int INSTRU_LON_ID = 3;
	private static final int INSTRU_TIME_ID = 4;
	
	private static final int PSU_AHP_ID = 4;
	private static final int PSU_VHP_ID = 5;

	private String sep = ",";
	
	public void run() {
		DataManager dd = DataManager.getInstance();
		FileWriter fstream;
		BufferedWriter out;
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy_MM_dd_hh_mm_ss");
	    
		String dirName = "MathLab_" + ft.format(dNow);
		File dir = new File("log/" + dirName);
		dir.mkdir();

		try {

		    while(true){
		    	if(DataAcquisition.getInstance().getAcquiStatus()){
			    	fstream = new FileWriter(dir.getPath() + "/" + TelemetrySettings.getInstance().getSetting("MATLABFILE")+".dat",true);
			    	out = new BufferedWriter(fstream);
						 out.write(
			    			dd.getRawValue(INSTRU_ID, INSTRU_TIME_ID)+sep+//HEURE
			    			dd.getRawValue(INSTRU_ID, INSTRU_LAT_ID)+sep+//LAT
			 				dd.getRawValue(INSTRU_ID, INSTRU_LON_ID)+sep+//LON
			 				((dd.getRawValue(BMS_ID, BMS_PACKA_ID) * dd.getRawValue(BMS_ID, BMS_PACKV_ID)) - 
							(dd.getRawValue(DRIVE_ID, DRIVE_ABUS_ID) * dd.getRawValue(DRIVE_ID, DRIVE_VBUS_ID)) - 
							(dd.getRawValue(PSU_ID, PSU_AHP_ID) * dd.getRawValue(PSU_ID, PSU_VHP_ID)))+sep+//PANEL_POWER
			 				dd.getRawValue(DRIVE_ID, DRIVE_RPM_ID)+sep+//DRIVE_RPM
			 				dd.getRawValue(DRIVE_ID, DRIVE_ABUS_ID)+sep+//DRIVE_C
			 				dd.getRawValue(DRIVE_ID, DRIVE_VBUS_ID)+sep+//DRIVE_T
			 				dd.getRawValue(BMS_ID, BMS_AH_ID)+sep+//BMS_AH
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
