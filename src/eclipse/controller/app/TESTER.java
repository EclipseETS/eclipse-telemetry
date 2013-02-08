package eclipse.controller.app;

import eclipse.model.data.DataManager;
import eclipse.model.data.Device;
import eclipse.model.data.DeviceItem;


/**
 * This class fill data to do some test with dumb data
 * @author Marco
 *
 */
public class TESTER implements Runnable {
	
	private DataManager data = DataManager.getInstance();


	public void run() {
		initData();
		double i =0;
		
		while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				for(Device dev : data.getDevices())
					for(DeviceItem ite : dev.getItems())
						ite.setValue(i);
				i++;
			
		}

	}
	
	private void initData(){
		
	}

}
