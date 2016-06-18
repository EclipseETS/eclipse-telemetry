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
	
	
	private static final int VOLANT_ID = 7;
	
	private static final int VOLANT_VVELOCITY_ID = 4;
	private static final int VOLANT_MVELOCITY_ID = 5;
	private static final int VOLANT_BUSVOLTAGE_ID = 6;
	private static final int VOLANT_BUSCURRENT_ID = 7;
	private static final int VOLANT_ODOMETER_ID = 8;
	private static final int VOLANT_AMPHOUR_ID = 9;

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
			    			dd.getRawValue(VOLANT_ID, VOLANT_VVELOCITY_ID)+sep+//LAT
			 				dd.getRawValue(VOLANT_ID, VOLANT_MVELOCITY_ID)+sep+//LON
			 				dd.getRawValue(VOLANT_ID, VOLANT_BUSVOLTAGE_ID)+sep+//DRIVE_RPM
			 				dd.getRawValue(VOLANT_ID, VOLANT_BUSCURRENT_ID)+sep+//DRIVE_C
			 				dd.getRawValue(VOLANT_ID, VOLANT_ODOMETER_ID)+sep+//DRIVE_T
			 				dd.getRawValue(VOLANT_ID, VOLANT_AMPHOUR_ID)+//DRIVE_T

			    			
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
