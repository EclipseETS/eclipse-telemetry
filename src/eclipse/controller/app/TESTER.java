package eclipse.controller.app;

import javax.swing.JFrame;
import javax.swing.JSlider;

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
		
		JFrame frame = new JFrame();
		JSlider slider = new JSlider();
		frame.setTitle("Fake Values");
		frame.setBounds(100, 100, 300, 100);
		frame.add(slider);
		frame.setVisible(true);
		
		while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				for(Device dev : data.getDevices())
					for(DeviceItem ite : dev.getItems())
						ite.setValue((double) slider.getValue());
				
			
		}

	}
	

}
