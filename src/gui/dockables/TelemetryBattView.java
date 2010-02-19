package gui.dockables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;

import core.DataManager;

import eclipseV7.data.Device;
import eclipseV7.data.DeviceItem;
import exception.DeviceItemNotFoundException;
import exception.DeviceNotFoundException;
import gui.components.SmallStatusView;

public class TelemetryBattView extends JPanel implements Dockable, Observer {

	DockKey key = new DockKey("batt_view");
	private static int BATT_DEVICE_ID = 2;
	private static int FIRST_VOLTAGE_IND_ID = 1;
	private static int LAST_VOLTAGE_IND_ID = 35;
	private static int FIRST_STATUS = 45;
	private static int LAST_STATUS = 66;
	
	// Visual Components
	JPanel battPanel;
	JPanel voltageIndPanel;
	JPanel battStatusPanel;
	
	JPanel mainPanel;
	JScrollPane scrollPane;
	
	SmallStatusView totalVoltage;
	SmallStatusView current;
	SmallStatusView energy;
	SmallStatusView temp1;
	SmallStatusView temp2;
	SmallStatusView temp3;
	SmallStatusView temp4;
	SmallStatusView temp5;
	SmallStatusView temp6;
	
	// Layout
	MigLayout contentLayout = new MigLayout(
			"", // Layout constraints
			"", // Column constraints
			""); // Row constaints
	
	// Components
	HashMap<Integer, SmallStatusView> voltageIndHashMap = new HashMap<Integer, SmallStatusView>();
	
	public TelemetryBattView() {
		key.setName("Batt View");
		
		mainPanel = new JPanel();
		battPanel = new JPanel();
		voltageIndPanel = new JPanel();
		battStatusPanel = new JPanel();
		
		initBattPanel();
		initVoltageIndPanel();
		initBattStatusPanel();
		
		this.setLayout(contentLayout);
		this.add(battPanel, "wrap");
		this.add(voltageIndPanel, "wrap");
		//this.add(battStatusPanel, "wrap");
		
	}
	
	public void initBattPanel() {
		try {
			// Layout
			MigLayout battLayout = new MigLayout("insets 3");
			
			battPanel.setLayout(battLayout);
			battPanel.setBorder(new TitledBorder("Main Info"));

			// Total voltage: ItemId 36
			totalVoltage = new SmallStatusView("Total Voltage");
			DataManager.getInstance().getDeviceItem(BATT_DEVICE_ID, 36).addObserver(this);
			battPanel.add(totalVoltage);
			
			// Current: ItemId 37
			current = new SmallStatusView("Current");
			DataManager.getInstance().getDeviceItem(BATT_DEVICE_ID, 37).addObserver(this);
			battPanel.add(current);

			// Energy: ItemId 44
			energy = new SmallStatusView("Energy");
			DataManager.getInstance().getDeviceItem(BATT_DEVICE_ID, 44).addObserver(this);
			battPanel.add(energy);
			
			// Temp1: ItemId 38
			temp1 = new SmallStatusView("Temp1");
			DataManager.getInstance().getDeviceItem(BATT_DEVICE_ID, 38).addObserver(this);
			battPanel.add(temp1, "wrap");
			
			// Temp2: ItemId 39
			temp2 = new SmallStatusView("Temp2");
			DataManager.getInstance().getDeviceItem(BATT_DEVICE_ID, 39).addObserver(this);
			battPanel.add(temp2);
			
			// Temp3: ItemId 40
			temp3 = new SmallStatusView("Temp3");
			DataManager.getInstance().getDeviceItem(BATT_DEVICE_ID, 40).addObserver(this);
			battPanel.add(temp3);
			
			// Temp4: ItemId 41
			temp4 = new SmallStatusView("Temp4");
			DataManager.getInstance().getDeviceItem(BATT_DEVICE_ID, 41).addObserver(this);
			battPanel.add(temp4);
			
			// Temp5: ItemId 42
			temp5 = new SmallStatusView("Temp5");
			DataManager.getInstance().getDeviceItem(BATT_DEVICE_ID, 42).addObserver(this);
			battPanel.add(temp5);
			
			// Temp6: ItemId 43
			temp6 = new SmallStatusView("Temp6");
			DataManager.getInstance().getDeviceItem(BATT_DEVICE_ID, 43).addObserver(this);
			battPanel.add(temp6);
			
		} catch (DeviceItemNotFoundException e) {
				e.printStackTrace();
		} catch (DeviceNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void initVoltageIndPanel() {
		// Layout
		GridLayout voltageIndLayout = new GridLayout(6,8);
		
		voltageIndPanel.setLayout(voltageIndLayout);
		voltageIndPanel.setBorder(new TitledBorder("Individual cells"));
		
		for (int i = FIRST_VOLTAGE_IND_ID; i<= LAST_VOLTAGE_IND_ID; i++ ) {
			SmallStatusView currentCell = new SmallStatusView("Cell " + i);
			voltageIndHashMap.put(i, currentCell); 
			try {
				DataManager.getInstance().getDeviceItem(BATT_DEVICE_ID, i).addObserver(this);
			} catch (DeviceItemNotFoundException e) {
				e.printStackTrace();
			} catch (DeviceNotFoundException e) {
				e.printStackTrace();
			}
			voltageIndPanel.add(currentCell);
		}
	}
	
	public void initBattStatusPanel() {
//		// Layout
//		MigLayout battLayout = new MigLayout("insets 3");
//		
//		Vector<String> OnDefinitions = new Vector<String>();
//		for (int i = FIRST_STATUS; i<= LAST_STATUS; i++) {
//			
//		}
	}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public DockKey getDockKey() {
		return key;
	}

	@Override
	public void update(Observable o, Object arg) {
		// Get the DeviceItem from the Observable
		DeviceItem item = ((DeviceItem) o);
		
		// Act only if DeviceItems are from Batt
		if (item.getParentDeviceId() == BATT_DEVICE_ID) {
			int itemId = item.getItemId();
			
			System.out.println(itemId);
			// Voltage Ind
			if ((itemId >= FIRST_VOLTAGE_IND_ID) && (itemId <= LAST_VOLTAGE_IND_ID)) {
				voltageIndHashMap.get(itemId).updateValue(item.getDoubleValue(), item.getUnit());
			}
			// Status
			else if (itemId >= FIRST_VOLTAGE_IND_ID && itemId <= LAST_VOLTAGE_IND_ID) {
				System.out.println("status");
			}
			// Total Voltage
			else if (itemId == 36) {
				System.out.println("Total voltage!");
				totalVoltage.updateValue(item.getDoubleValue(), item.getUnit());
			}
			// Current
			else if (itemId == 37) {
				current.updateValue(item.getDoubleValue(), item.getUnit());
			}
			// Energy
			else if (itemId == 44) {
				energy.updateValue(item.getDoubleValue(), item.getUnit());
			}
			// Temp1
			else if (itemId == 38) {
				temp1.updateValue(item.getDoubleValue(), item.getUnit());
			}
			// Temp2
			else if (itemId == 39) {
				temp2.updateValue(item.getDoubleValue(), item.getUnit());
			}
			// Temp3
			else if (itemId == 40) {
				temp3.updateValue(item.getDoubleValue(), item.getUnit());
			}
			// Temp4
			else if (itemId == 41) {
				temp4.updateValue(item.getDoubleValue(), item.getUnit());
			}
			// Temp5
			else if (itemId == 42) {
				temp5.updateValue(item.getDoubleValue(), item.getUnit());
			}
			// Temp6
			else if (itemId == 43) {
				temp6.updateValue(item.getDoubleValue(), item.getUnit());
			}
		}
	}	
}