package eclipse.view.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


import eclipse.controller.util.TelemetrySettings;
import eclipse.model.data.DataManager;
import eclipse.model.data.Device;
import eclipse.model.data.DeviceItem;

/**
 * This class is used to create the table that displays the car data
 * @author MArco
 *
 */

public class DeviceTable extends JPanel  {
	
	private static final long serialVersionUID = -2652127495341433024L;
	private JScrollPane scrollPane;
	private JTable dataTable;
	private JButton btnGraph;
	private JButton btnIndex;
	private DataManager dataManager = DataManager.getInstance();

	/**
	 * Creates the panel
	 */
	public DeviceTable() {
		
		
		int nmItem = dataManager.getCpt();
		
		// Builds a layout without borders between elements
		this.setLayout(new BorderLayout());		
		DefaultTableModel model = new DefaultTableModel(nmItem, 5)
		  {
			private static final long serialVersionUID = 5555926687022393905L;
			public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		dataTable = new JTable(model);
		btnGraph = new JButton("Graph this data");
		btnIndex = new JButton("Keep this value");
		
		btnIndex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String deviceId = (String) dataTable.getModel().getValueAt(dataTable.getSelectedRow(), 0);
				deviceId=deviceId.substring(0,deviceId.indexOf("-"));
				
				
				String deviceItemId = (String) dataTable.getModel().getValueAt(dataTable.getSelectedRow(), 1);
				deviceItemId=deviceItemId.substring(0,deviceItemId.indexOf("-"));
				
				Device dev = dataManager.getDeviceByID(Integer.valueOf(deviceId));
				DeviceItem item = dev.getItemByID(Integer.valueOf(deviceItemId));
				
				DesktopManager.getIstance().getImportantPanel().addItem(item, dev);
				
			}

			});
		
		// Set default width for the table columns
		dataTable.setFillsViewportHeight(true);
		dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		dataTable.getColumnModel().getColumn(0).setPreferredWidth(50);  // Device
		dataTable.getColumnModel().getColumn(1).setPreferredWidth(60);  // Item
		dataTable.getColumnModel().getColumn(2).setPreferredWidth(10); // Value
		dataTable.getColumnModel().getColumn(3).setPreferredWidth(2); // Last seen
		dataTable.getColumnModel().getColumn(4).setPreferredWidth(60); // Unit
		dataTable.getColumnModel().getColumn(0).setHeaderValue(TelemetrySettings.getInstance().getSetting("GUI_TABLE_COLUMN_DEVICE"));
		dataTable.getColumnModel().getColumn(1).setHeaderValue(TelemetrySettings.getInstance().getSetting("GUI_TABLE_COLUMN_DEVICE_ITEM"));
		dataTable.getColumnModel().getColumn(2).setHeaderValue(TelemetrySettings.getInstance().getSetting("GUI_TABLE_COLUMN_VALUE"));
		dataTable.getColumnModel().getColumn(3).setHeaderValue(TelemetrySettings.getInstance().getSetting("GUI_TABLE_COLUMN_UNIT"));
		dataTable.getColumnModel().getColumn(4).setHeaderValue(TelemetrySettings.getInstance().getSetting("GUI_TABLE_COLUMN_LAST_SEEN"));
		
		// Creates a scroll pane as a container for the table
		scrollPane = new JScrollPane(dataTable);
		this.add(scrollPane);
	//	this.add(btnGraph, BorderLayout.NORTH);
		this.add(btnIndex, BorderLayout.SOUTH);
		
		//Fill table
		int i=0;
		String lbl1;
		String lbl2;
		
		for(Device dev : DataManager.getInstance().getDevices()){
			lbl1=dev.getDeviceId()+"-"+dev.getDeviceName();
			for(DeviceItem item : dev.getItems()){
				lbl2=item.getItemId()+"-"+item.getName();				
				dataTable.getModel().setValueAt(lbl1, i, 0);
				dataTable.getModel().setValueAt(lbl2, i, 1);
				dataTable.getModel().setValueAt(item.getUnit(), i, 3);
				i++;
			}
		}
	}
	
	/**
	 * This method is called every second by the DesktopManager to add one line to the table
	 */
	public void updateTable() {
		int i=0;
		for(Device dev : DataManager.getInstance().getDevices())
			for(DeviceItem item : dev.getItems()){
				String S = new SimpleDateFormat("hh:mm:ss:SS").format(item.getLastSeen());
				dataTable.getModel().setValueAt(item.getLastData(), i, 2);
				dataTable.getModel().setValueAt(S, i, 4);
				i++;
			}
		
		
	}
	
	
}
