package eclipse.controller.app;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JFrame;
import javax.swing.JSlider;

import org.apache.log4j.Logger;

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
	boolean ok=true;
	

	public void run() {
		
		JFrame frame = new JFrame();
		JSlider slider = new JSlider();
		frame.setTitle("Fake Values");
		frame.setBounds(100, 100, 300, 100);
		frame.add(slider);
		frame.setVisible(true);
		
		frame.addWindowListener( 
			    new WindowAdapter() { 
			        public void windowClosing(WindowEvent e) { 
			        	ok=false;
			        } 
			    } 
			);
		
		while(ok){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					StringWriter stack = new StringWriter();
					e.printStackTrace(new PrintWriter(stack));
					Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
				}

				for(Device dev : data.getDevices())
					for(DeviceItem ite : dev.getItems())
						ite.setValue((double) slider.getValue());
				
			
		}

	}
	

}
