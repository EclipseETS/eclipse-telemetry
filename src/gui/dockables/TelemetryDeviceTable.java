package gui.dockables;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import simulator.SimulatorDevice;
import util.ByteManipulator;

import net.miginfocom.swing.MigLayout;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;

import core.DataManager;
import eclipseV7.data.Device;
import eclipseV7.data.DeviceItem;
import exception.DeviceItemNotFoundException;
import exception.DeviceNotFoundException;
import gui.DesktopManager;

public class TelemetryDeviceTable extends JPanel implements Dockable, TableModelListener, ActionListener{

	DockKey key = new DockKey("deviceTable");
	JScrollPane scrollPane;
	JPanel contentPane;
	JTable table;
	JButton graphThisButton;
	
	public TelemetryDeviceTable() {
		key.setName("Device View");
		key.setCloseEnabled(false);
		
		MigLayout contentLayout = new MigLayout("insets 0");
		//this.setLayout(contentLayout);
		
		this.setLayout(new BorderLayout());
		
		contentPane = new JPanel();
		contentPane.setLayout(contentLayout);
		
		graphThisButton = new JButton("Graph Selected");
		graphThisButton.setActionCommand("graph");
		graphThisButton.addActionListener(this);
		
		table = new JTable(new DeviceTableModel());
		table.getModel().addTableModelListener(this);
		
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);  // Device
		table.getColumnModel().getColumn(1).setPreferredWidth(2);  // ItemId
		table.getColumnModel().getColumn(2).setPreferredWidth(10); // Item
		table.getColumnModel().getColumn(3).setPreferredWidth(10); // Value
		
		scrollPane = new JScrollPane(table);
		
		this.add(graphThisButton,BorderLayout.NORTH);
		this.add(scrollPane);
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
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int column = e.getColumn();
		TableModel model = (TableModel)e.getSource();
		model.getValueAt(row, column);
		
	//	System.out.println("Table changed! " + "Row:" + row + " Column:" + column + " Value:" + model.getValueAt(row, column));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// JButton
		if ("graph".equals(e.getActionCommand())) {
			int deviceId = ((DeviceTableModel) table.getModel()).getDeviceIdAtRow(table.getSelectedRow());
			int deviceItemId = ((DeviceTableModel) table.getModel()).getDeviceItemIdAtRow(table.getSelectedRow());
			DesktopManager.getInstance().addGraphTab(deviceId, deviceItemId);
		}

	}

}

class DeviceTableModel extends AbstractTableModel implements Observer {
	String[] columnNames = {"Device", "Id", "Item", "Value", "Last seen"};
	
	// Devices
	Hashtable<Integer, Device> devices = DataManager.getInstance().getDeviceMap();
	Vector<Integer[]> deviceList = new Vector<Integer[]>();

    public DeviceTableModel() {
    	constructDeviceListAndAddObservers();
    }
    
    private void constructDeviceListAndAddObservers() {
    	// deviceList: itemCount, DeviceId, ItemID
    	int totalItemsCount = 0;
		try {
			for (int i = 1; i <= devices.size(); i++) {
				for (int j = 1; j <= DataManager.getInstance().getDevice(i).getDeviceItemMap().size(); j++) {
					int deviceId = DataManager.getInstance().getDevice(i).getId();
					int deviceItemId = DataManager.getInstance().getDevice(i).getItemByID(j).getItemId();
					Integer[] deviceItem = { totalItemsCount, deviceId, deviceItemId };
					
					deviceList.add(deviceItem);
					totalItemsCount++;
					
					// Add observer
					DataManager.getInstance().getDevice(i).getItemByID(j).addObserver(this);
				}
			}
		} catch (DeviceNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    private int getRow(int deviceId, int itemId) {
    	// deviceList: itemCount, DeviceId, ItemID
    	for (int i = 0; i < deviceList.size(); i++) {
    		if ((deviceList.get(i)[1] == deviceId) && (deviceList.get(i)[2] == itemId))
    			return deviceList.get(i)[0];
    	}
    	return 0;
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        int totalNumberOfItems = 0;
		for (Device key:devices.values()) {
			totalNumberOfItems += key.getDeviceItemMap().size();
		}
		return totalNumberOfItems;
    }
    
    public int getDeviceIdAtRow(int row) {
    	// deviceList: itemCount, DeviceId, ItemID
    	return deviceList.get(row)[1];
    }
    
    public int getDeviceItemIdAtRow(int row) {
    	// deviceList: itemCount, DeviceId, ItemID
    	return deviceList.get(row)[2];
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

	public Object getValueAt(int row, int col) {
		// Columns: "Device", "ItemId", "Item", "Value"
		
		try {
			Integer[] itemLineInList = deviceList.get(row); // itemCount, DeviceId, ItemID
			switch (col) {
			case 0:
//				StringBuilder sb0 = new StringBuilder();
//				sb0.append(DataManager.getInstance().getDevice(itemLineInList[1]).getName());
//				sb0.append(" (");
//				sb0.append(DataManager.getInstance().getDevice(itemLineInList[1]).getId());
//				sb0.append(")");
//				return sb0.toString();
				return DataManager.getInstance().getDevice(itemLineInList[1]).getName();
			case 1: // Device name
				return DataManager.getInstance().getDevice(itemLineInList[1]).getItemByID(itemLineInList[2])
						.getItemId();
			case 2: // Device Item Id
				return DataManager.getInstance().getDevice(itemLineInList[1]).getItemByID(itemLineInList[2])
						.getName();
			case 3: // Value
				StringBuilder sb1 = new StringBuilder();
				sb1.append(DataManager.getInstance().getDevice(itemLineInList[1]).getItemByID(itemLineInList[2])
						.getDoubleValue());
				sb1.append(" ");
				sb1.append(DataManager.getInstance().getDevice(itemLineInList[1]).getItemByID(itemLineInList[2])
						.getUnit());
				return sb1.toString();
			case 4: // Last seen time
				return DataManager.getInstance().getDevice(itemLineInList[1]).getItemByID(itemLineInList[2]).getLastSeenTimeString();
			default:
				return 0;
			}
		} catch (DeviceNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
    	return false;
    }

	@Override
	public void update(Observable o, Object arg) {
		int deviceId = ((DeviceItem) o).getParentDeviceId();
		int deviceItemId = ((DeviceItem) o).getItemId();
		
		//System.out.println("Would update: Device:" +  deviceId + " Item:" + deviceItemId + " at row: " + getRow(deviceId, deviceItemId));
		this.fireTableCellUpdated(this.getRow(deviceId, deviceItemId), 3); // Col 3 = Value
		this.fireTableCellUpdated(this.getRow(deviceId, deviceItemId), 4); // Col 4 = Last seen time
	}
}
