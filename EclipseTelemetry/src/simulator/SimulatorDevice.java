package simulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import eclipseV7.data.Device;
import eclipseV7.data.DeviceItem;

public class SimulatorDevice extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	// Visual components
	private JButton allOn;
	private JButton allOff;
	
	// This Device
	private Device device;
	
	// This Device's deviceItems
	private Hashtable<Integer, SimulatorDeviceItem> simulatorDeviceItems = new Hashtable<Integer, SimulatorDeviceItem>();
	
	public SimulatorDevice(Device device) {
		this.device = device;
		createPanel();
	}
	
	public String getName() {
		return device.getName();
	}
	
	public int getId() {
		return device.getId();
	}
	
	private void createPanel() {
		
		allOn = new JButton();
		allOn.setText("All On");
		allOn.setActionCommand("allOn");
		allOn.addActionListener(this);
		
		allOff = new JButton();
		allOff.setText("All Off");
		allOff.setActionCommand("allOff");
		allOff.addActionListener(this);
		
		MigLayout mainContentLayout = new MigLayout(
				"", // Layout constraints
				"", // Column constraints
				""); // Row constaints
		
		this.setBorder(new TitledBorder(device.getName()));
		this.setLayout(mainContentLayout);
		
		this.add(allOn);
		this.add(allOff,"wrap");
		
		//Hashtable for deviceItems
		Hashtable<Integer, DeviceItem> deviceItems = device.getDeviceItemMap();
		
		for (DeviceItem itemKey:deviceItems.values()) { // Foreach DeviceItem
			SimulatorDeviceItem currentDeviceItemPanel = new SimulatorDeviceItem(device, itemKey);
			simulatorDeviceItems.put(itemKey.getItemId(), currentDeviceItemPanel);
			this.add(currentDeviceItemPanel.getJPanel(), "wrap, span 2");
		}
	}

    public void setCheckedAll(boolean state) {
    	for (SimulatorDeviceItem key:simulatorDeviceItems.values()) { // Foreach DeviceItem
			key.setChecked(state);
		}
    	
    }
    
    public void setRandomInputValues(){    	
    	for (SimulatorDeviceItem key:simulatorDeviceItems.values()) { // Foreach DeviceItem
			key.setRandomInput();
		}
    }
    
    public void clearInputs() {
    	for (SimulatorDeviceItem key:simulatorDeviceItems.values()) { // Foreach DeviceItem
			key.setInputsToMin();
		}	
	}
    
    public Hashtable<Integer, SimulatorDeviceItem> getSimulatorDeviceItems() {
    	return simulatorDeviceItems;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("allOn".equals(e.getActionCommand())) this.setCheckedAll(true);
		if ("allOff".equals(e.getActionCommand())) this.setCheckedAll(false);
	}

}