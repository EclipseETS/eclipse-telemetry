package eclipse.view.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.apache.log4j.Logger;

import eclipse.controller.util.TelemetrySettings;
import eclipse.model.data.DataManager;
import eclipse.model.data.Device;
import eclipse.model.data.DeviceItem;


/**
 * The GUI view of the simulator.
 * 
 * This represent a GUI for the sim
 * All the devices will be on a separate tab, and all device should have is own slider.
 * @author marco
 *
 */
public class SimulatorGUI extends JPanel {

	private static final long serialVersionUID = 4744905747786777537L;
	
	public SimulatorGUI(){
		//super(new GridLayout(1, 1));        
        JTabbedPane tabbedPane = new JTabbedPane();        
        for(Device d : DataManager.getInstance().getDevices()){
        	JComponent panel1 = makeTextPanel(d);
        	tabbedPane.addTab(d.getDeviceName(), null, panel1,d.getDeviceName());
        }         
        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        //this.setSize(500, 800);
	}
	
	
	 protected JComponent makeTextPanel(Device d) {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        
	        
	        JLabel filler = new JLabel(d.getDeviceName());
 	        filler.setHorizontalAlignment(JLabel.CENTER);
 	        panel.add(filler);
 	        
	        for (DeviceItem i : d.getItems()){
	        	JLabel item = new JLabel(i.getName());
	        	item.setHorizontalAlignment(JLabel.LEFT);
	 	        panel.add(item);
	        }    
	        //panel.setLayout(new GridLayout(1, 1));
	        panel.setPreferredSize(new Dimension(800, 10000));
	        panel.setAutoscrolls(true);
	        
	        return panel;
	    }
	

	public static JFrame createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setTitle(TelemetrySettings.getInstance().getSetting("GUI_SIMULATOR_NAME"));
        frame.setBounds(100, 100, 683, 575);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

		Image im = null;
		try {
			im = ImageIO.read(new File("images/eclipse.png"));
		} catch (IOException e) {
			StringWriter stack = new StringWriter();
			e.printStackTrace(new PrintWriter(stack));
			Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
		}
        
        
        frame.setIconImage(im);
        
        frame.add(new SimulatorGUI());

        //frame.setSize(800, 500);
        
        frame.pack();
        frame.setVisible(true);
		return frame;
    }
}
