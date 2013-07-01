package eclipse.controller.app;

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
		int[][] values = {
				{	3,	2},
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
					e.printStackTrace();
				}
				DataManager dd = DataManager.getInstance();
				
				for(Device dev : dd.getDevices())
					for(DeviceItem itm : dev.getItems()){
						if(itm.getError()){
								DesktopManager.getIstance().getErrorPanel().addItem(itm, dev);
						}
						else{
							DesktopManager.getIstance().getErrorPanel().remItem(itm, dev);
						}
					}
				int cpt =0;
				for(int[] tmp : values){
					i=dd.getDeviceByID(tmp[0]).getItemByID(tmp[1]).getLastData();
					if(i<oldValues[cpt]||dd.getDeviceByID(tmp[0]).getItemByID(tmp[1]).getLastSeen()+5000<System.currentTimeMillis())
						DesktopManager.getIstance().getErrorPanel().addItem(dd.getDeviceByID(tmp[0]).getItemByID(tmp[1]), dd.getDeviceByID(tmp[0]));
					oldValues[cpt]=(int) i;
					cpt++;
				}
			
				
			}	
			
		}
		
	}

}
