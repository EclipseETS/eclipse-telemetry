package eclipse.controller.app;


import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

import eclipse.controller.acqui.DataAcquisition;
import eclipse.model.data.DataManager;
import eclipse.model.data.Device;
import eclipse.model.data.DeviceItem;
import eclipse.view.gui.DesktopManager;
/**
 * This class try to detect value over or under theyr min max and other error
 * @author Marco
 *
 */
public class ErrorFinder implements Runnable{

	
	public void run() {
			double i;
			
			//ID off all OS tick
		int[][] values = {
				{	4,	1},
				{	5,	1},
				{	6,	1},
				{	7,	1}
				};
		int[] oldValues = new int[values.length];
		
		while(true){
			if(DataAcquisition.getInstance().getAcquiStatus()){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					StringWriter stack = new StringWriter();
					e.printStackTrace(new PrintWriter(stack));
					Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
				}
				DataManager dd = DataManager.getInstance();
				
				
				//Verify if Item is in error, and if so add it to the error list
				for(Device dev : dd.getDevices())
					for(DeviceItem itm : dev.getItems()){
						if(itm.getError()){
								DesktopManager.getIstance().getErrorPanel().addItem(itm, dev);
								String outOfBounds = itm.getLastData() > itm.getMaxValue() ? 
										Double.toString(itm.getLastData()) + " > " + Double.toString(itm.getMaxValue()) : 
											Double.toString(itm.getLastData()) + " < " + Double.toString(itm.getMinValue());
								Logger.getLogger("devices").error(dev.getDeviceName() + " - " + itm.getName() + " : " + outOfBounds); 
						}
//						else{
//							DesktopManager.getIstance().getErrorPanel().remItem(itm, dev);
//						}
					}
				
				/*
				 * This part is used to verified all the boards. The OsTick is a value that sould always go up.. if it start back to 
				 * 0 the board as probably rebooted (or run for around 64 years) so an alarm will jump, 
				 * 
				 * Also if there is no news for 10 sec.... board probably down
				 */
				int cpt =0;
				for(int[] tmp : values){
					i=dd.getDeviceByID(tmp[0]).getItemByID(tmp[1]).getLastData();
					if(i<oldValues[cpt]||dd.getDeviceByID(tmp[0]).getItemByID(tmp[1]).getLastSeen()+10000<System.currentTimeMillis()) {
						DesktopManager.getIstance().getErrorPanel().addItem(dd.getDeviceByID(tmp[0]).getItemByID(tmp[1]), dd.getDeviceByID(tmp[0]));
						Logger.getLogger("devices").error(dd.getDeviceByID(tmp[0]).getDeviceName() + " - " + dd.getDeviceByID(tmp[0]).getItemByID(tmp[1]).getName() + " Error");
					}
					oldValues[cpt]=(int) i;
					cpt++;
				}
			
				
			}	
			else
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					StringWriter stack = new StringWriter();
					e.printStackTrace(new PrintWriter(stack));
					Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
				}
			
		}
		
	}

}
