package eclipse.view.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import eclipse.controller.util.TelemetrySettings;
import eclipse.model.data.DataManager;
import eclipse.model.data.Device;
import eclipse.model.data.DeviceItem;

/**
 * This class is used to create the table that displays the car data
 * @author Marco
 *
 */

public class ImportantDeviceTable extends JPanel  {
	
	private static final long serialVersionUID = -2652127495341433024L;
	private JTable dataTable;
	private DefaultTableModel model;
	private List<DeviceItem> items;

	/**
	 * Creates the panel
	 */
	public ImportantDeviceTable() {
		
		
	
		items = Collections.synchronizedList(new ArrayList<DeviceItem>());
		
		// Builds a layout without borders between elements
		this.setLayout(new BorderLayout());		
		model = new DefaultTableModel(0, 5)
		  {
			private static final long serialVersionUID = -8430358070918350866L;
			public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		dataTable = new JTable(model);
		
		dataTable.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {
			      if (e.getClickCount() == 2) {
			         JTable target = (JTable)e.getSource();
			         int row = target.getSelectedRow();
			         String deviceId = (String) dataTable.getModel().getValueAt(dataTable.getSelectedRow(), 0);
					deviceId=deviceId.substring(0,deviceId.indexOf("-"));
						
						
						String deviceItemId = (String) dataTable.getModel().getValueAt(dataTable.getSelectedRow(), 1);
						deviceItemId=deviceItemId.substring(0,deviceItemId.indexOf("-"));
						
						Device dev = DataManager.getInstance().getDeviceByID(Integer.valueOf(deviceId));
						DeviceItem item = dev.getItemByID(Integer.valueOf(deviceItemId));
						
						
						items.remove(item);
						model.removeRow(row);
						DesktopManager.getIstance().resizedMe();
			         }
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

		
		this.add(dataTable);
		
	}
	
	/**
	 * This method is called every second by the DesktopManager to add one line to the table
	 */
	public void updateTable() {
		
		int i=0;
		for(DeviceItem item : items){
			String S = new SimpleDateFormat("hh:mm:ss:SS").format(item.getLastSeen());
			dataTable.getModel().setValueAt(item.getLastData(), i, 2);
			dataTable.getModel().setValueAt(S, i, 4);
			i++;
		}
		
	}
	
	public void addItem(DeviceItem itemCurrent, Device deviceCurrent){
		
		String lbl1;
		String lbl2;
		items.add(itemCurrent);
		
		
		
		lbl1=deviceCurrent.getDeviceId()+"-"+deviceCurrent.getDeviceName();
		lbl2=itemCurrent.getItemId()+"-"+itemCurrent.getName();	
		model.addRow(new String[5]);
		dataTable.getModel().setValueAt(lbl1, items.size()-1, 0);
		dataTable.getModel().setValueAt(lbl2, items.size()-1, 1);
		dataTable.getModel().setValueAt(itemCurrent.getUnit(), items.size()-1, 3);
		
		DesktopManager.getIstance().resizedMe();
		updateTable();
					
		
	}
	
	public int getHeightCustom(){
		return (model.getRowCount())*dataTable.getRowHeight();
	}
	
	
}
