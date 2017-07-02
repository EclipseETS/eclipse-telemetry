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
	
	
	private static final int DRIVE_L_ID = 2;
	private static final int DRIVE_R_ID = 2;
	private static final int BMS_ID = 3;
	private static final int VOLANT_ID = 6;
	private static final int MUPPET_ID = 10;
	
	
	private static final int DRIVE_L_BUS_VOLTAGE_ID = 7;
	private static final int DRIVE_L_BUS_CURRENT_ID = 8;
	private static final int DRIVE_L_MOTOR_VELOCITY_ID = 9;
	private static final int DRIVE_L_VEHICLE_VELOCITY_ID = 10;
	private static final int DRIVE_L_ODOMETER_ID = 22;
	private static final int DRIVE_L_DCBUS_AMPHOUR_ID = 23;
	private static final int DRIVE_R_BUS_VOLTAGE_ID = 7;
	private static final int DRIVE_R_BUS_CURRENT_ID = 8;
	private static final int DRIVE_R_MOTOR_VELOCITY_ID = 9;
	private static final int DRIVE_R_VEHICLE_VELOCITY_ID = 10;
	private static final int DRIVE_R_ODOMETER_ID = 22;
	private static final int DRIVE_R_DCBUS_AMPHOUR_ID = 23;
	private static final int BMS_PACK_CURRENT_ID = 2;
	private static final int BMS_PACK_VOLTAGE_ID = 3;
	private static final int BMS_AVERAGE_CELL_VOLTAGE_ID = 4;
	private static final int BMS_PACK_TEMP_HIGH_ID = 9;
	private static final int BMS_PACK_TEMP_LOW_ID = 10;
	private static final int BMS_REMAINING_ENERGY_ID = 19;
	private static final int VOLANT_VEHICLE_VELOCITY_ID = 4;
	private static final int MUPPET_UIN_MPPT1_ID = 3;
	private static final int MUPPET_IIN_MPPT1_ID = 4;
	private static final int MUPPET_UIN_MPPT2_ID = 9;
	private static final int MUPPET_IIN_MPPT2_ID = 10;
	private static final int MUPPET_UIN_MPPT3_ID = 15;
	private static final int MUPPET_IIN_MPPT3_ID = 16;

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
			        Date date = new Date();

			    	out = new BufferedWriter(fstream);
						 out.write(
							date.toString()+sep+//HEURE
			    			dd.getRawValue(DRIVE_R_ID, DRIVE_R_BUS_VOLTAGE_ID)+sep+
			 				dd.getRawValue(DRIVE_R_ID, DRIVE_R_BUS_CURRENT_ID)+sep+
			 				dd.getRawValue(DRIVE_R_ID, DRIVE_R_MOTOR_VELOCITY_ID)+sep+
			 				dd.getRawValue(DRIVE_R_ID, DRIVE_R_VEHICLE_VELOCITY_ID)+sep+
			 				dd.getRawValue(DRIVE_R_ID, DRIVE_R_ODOMETER_ID)+sep+
			 				dd.getRawValue(DRIVE_R_ID, DRIVE_R_DCBUS_AMPHOUR_ID)+sep+
			 				dd.getRawValue(BMS_ID, BMS_PACK_CURRENT_ID)+sep+
			 				dd.getRawValue(BMS_ID, BMS_PACK_VOLTAGE_ID)+sep+
			 				dd.getRawValue(BMS_ID, BMS_AVERAGE_CELL_VOLTAGE_ID)+sep+
			 				dd.getRawValue(BMS_ID, BMS_PACK_TEMP_HIGH_ID)+sep+
			 				dd.getRawValue(BMS_ID, BMS_PACK_TEMP_LOW_ID)+sep+
			 				dd.getRawValue(BMS_ID, BMS_REMAINING_ENERGY_ID)+sep+
			 				dd.getRawValue(VOLANT_ID, VOLANT_VEHICLE_VELOCITY_ID)+sep+
			 				dd.getRawValue(MUPPET_ID, MUPPET_UIN_MPPT1_ID)+sep+
			 				dd.getRawValue(MUPPET_ID, MUPPET_IIN_MPPT1_ID)+sep+
			 				dd.getRawValue(MUPPET_ID, MUPPET_UIN_MPPT2_ID)+sep+
			 				dd.getRawValue(MUPPET_ID, MUPPET_IIN_MPPT2_ID)+sep+
			 				dd.getRawValue(MUPPET_ID, MUPPET_UIN_MPPT3_ID)+sep+
			 				dd.getRawValue(MUPPET_ID, MUPPET_IIN_MPPT3_ID)+
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
