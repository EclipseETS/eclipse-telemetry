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
		data.addDevice(new Device(0, "Motor"));
		data.addDevice(new Device(1, "Volant"));
		data.addDevice(new Device(2, "Batterie"));
		
		data.getDeviceByID(1).addItem(new DeviceItem(1, "Vitesse", "bin", 0, 0, 1, 1, 0, 0, 0, false, false, 0, false));
		data.getDeviceByID(1).addItem(new DeviceItem(2, "Tension", "bin", 0, 0, 1, 1, 0, 0, 0, false, false, 0, false));
		data.getDeviceByID(1).addItem(new DeviceItem(3, "Courant", "bin", 0, 0, 1, 1, 0, 0, 0, false, false, 0, false));
		data.getDeviceByID(1).addItem(new DeviceItem(4, "Temperature", "bin", 0, 0, 1, 1, 0, 0, 0, false, false, 0, false));
		
		data.getDeviceByID(2).addItem(new DeviceItem(1, "pot", "bin", 0, 0, 1, 1, 0, 0, 0, false, false, 0, false));
		data.getDeviceByID(2).addItem(new DeviceItem(2, "boutton", "bin", 0, 0, 1, 1, 0, 0, 0, false, false, 0, false));
		data.getDeviceByID(2).addItem(new DeviceItem(3, "led", "bin", 0, 0, 1, 1, 0, 0, 0, false, false, 0, false));
		data.getDeviceByID(2).addItem(new DeviceItem(4, "paddle", "bin", 0, 0, 1, 1, 0, 0, 0, false, false, 0, false));
		
		
		data.getDeviceByID(0).addItem(new DeviceItem(1, "force", "bin", 0, 0, 1, 1, 0, 0, 0, false, false, 0, false));
		data.getDeviceByID(0).addItem(new DeviceItem(2, "tension", "bin", 0, 0, 1, 1, 0, 0, 0, false, false, 0, false));
		data.getDeviceByID(0).addItem(new DeviceItem(3, "temperature", "bin", 0, 0, 1, 1, 0, 0, 0, false, false, 0, false));
		
	}

}
