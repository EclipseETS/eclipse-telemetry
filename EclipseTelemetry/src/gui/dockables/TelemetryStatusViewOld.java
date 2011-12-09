package gui.dockables;

import java.awt.Component;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import net.miginfocom.swing.MigLayout;

import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;

import core.DataManager;
import eclipseV7.data.Device;
import eclipseV7.data.DeviceItem;
import exception.DeviceNotFoundException;

@SuppressWarnings("serial")
public class TelemetryStatusViewOld extends JPanel implements Dockable, TableModelListener {
	
	DockKey key = new DockKey("status_view");
	Vector<Device> devicesWithStatus = new Vector<Device>();
	
	public TelemetryStatusViewOld()  {
		key.setName("Status List View");
		
		MigLayout contentLayout = new MigLayout("insets 0");
		this.setLayout(contentLayout);
		
		// Hold Devices that have definitions in a vector
		for(Device key:DataManager.getInstance().getDeviceMap().values()) {
			boolean deviceHasStatus = false;
			try {
				for(DeviceItem itemKey:DataManager.getInstance().getDevice(key.getId()).getDeviceItemMap().values()) {
					if (itemKey.hasDefinitions()) deviceHasStatus = true;
				}
			} catch (DeviceNotFoundException e) {
				e.printStackTrace();
			}
			if (deviceHasStatus) devicesWithStatus.add(key);
		}
		
		// Create a table for each of those devices
		for (int i = 0; i < devicesWithStatus.size(); i++) {
			Device currentDevice = devicesWithStatus.get(i);
			JTable table = new JTable(new StatusTableModel(currentDevice.getId()));
			table.getModel().addTableModelListener(this);
			
			JScrollPane scrollPane = new JScrollPane(table);
			
			this.add(new JLabel(currentDevice.getName()), "wrap");
			this.add(scrollPane, "wrap");
		}
		
		JTable table = new JTable(new DeviceTableModel(this));
		table.getModel().addTableModelListener(this);
		
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);  // Device
		table.getColumnModel().getColumn(1).setPreferredWidth(2);  // ItemId
		table.getColumnModel().getColumn(2).setPreferredWidth(10); // Item
		table.getColumnModel().getColumn(3).setPreferredWidth(10); // Value
		
		JScrollPane scrollPane = new JScrollPane(table);
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
	}
}

@SuppressWarnings("serial")
class StatusTableModel extends AbstractTableModel implements Observer {

	String[] columnNames = {"Status", "Definition"};
	int deviceId;
	
	public StatusTableModel(int deviceId) {
		this.deviceId = deviceId;
	}
	
	@Override
    public int getColumnCount() {
        return columnNames.length;
    }

	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
	
    public boolean isCellEditable(int row, int col) {
    	return false;
    }
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
	
}


