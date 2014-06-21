package eclipse.view.gui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;










import eclipse.controller.util.TelemetrySettings;
import eclipse.model.data.DataManager;
import eclipse.model.data.Device;
import eclipse.model.data.DeviceItem;
import eclipse.view.gui.tab.TelemetryGraph;

/**
 * This class is used to create the table that displays the car data
 * 
 *Similar to Important table
 * 
 * @author MArco
 *
 */
public class DeviceTable extends JPanel implements ItemListener  {
	
	private static final long serialVersionUID = -2652127495341433024L;
	private static final String SETTINGS_FILE = "telemetrySettings.properties";
	private JScrollPane scrollPane;
	private JTable dataTable;
	private JButton btnGraph;
	private JButton btnIndex;
	private DataManager dataManager = DataManager.getInstance();
	JPanel[] checkBoxPanel = new JPanel[2];		
	JCheckBox[] deviceCheckBox = new JCheckBox[10];
	
	Device dev;

	/**
	 * Creates the panel
	 */
	public DeviceTable() {		
		
		int nmItem = dataManager.getCpt();
		
		// Builds a layout without borders between elements
		this.setLayout(new BorderLayout());		
		DefaultTableModel model = new DefaultTableModel(0, 5)
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
		
		JPanel upControlPanel = new JPanel();
		upControlPanel.setLayout(new BoxLayout(upControlPanel, BoxLayout.Y_AXIS));
		
		checkBoxPanel[0] = new JPanel();
		checkBoxPanel[1] = new JPanel();
		
		deviceCheckBox[0] = new JCheckBox("Driver Ctrl");
		deviceCheckBox[1] = new JCheckBox("Drive");
		deviceCheckBox[2] = new JCheckBox("BMS");
		deviceCheckBox[3] = new JCheckBox("Flashers");
		deviceCheckBox[4] = new JCheckBox("Volant");
		deviceCheckBox[5] = new JCheckBox("Instru");
		deviceCheckBox[6] = new JCheckBox("PSU");
		deviceCheckBox[7] = new JCheckBox("MPPT1");
		deviceCheckBox[8] = new JCheckBox("MPPT2");
		deviceCheckBox[9] = new JCheckBox("MPPT3");
		
		int i = 0;
		
		for (; i<10 ; i++) {			
			deviceCheckBox[i].setSelected(true);
			deviceCheckBox[i].addItemListener(this);
		}
		for (i = 0 ; i<5 ; i++) {
			checkBoxPanel[0].add(deviceCheckBox[i]);
		}
		for (; i<10 ; i++) {
			checkBoxPanel[1].add(deviceCheckBox[i]);
		}
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(btnGraph, BorderLayout.WEST);
		buttonPanel.add(btnIndex, BorderLayout.EAST);
		
		//Action Listner on the button, to use in case we want to add items to important list 
		btnIndex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i : dataTable.getSelectedRows()){
				String deviceId = (String) dataTable.getModel().getValueAt(i, 0);
				deviceId=deviceId.substring(0,deviceId.indexOf("-"));
				
				
				String deviceItemId = (String) dataTable.getModel().getValueAt(i, 1);
				deviceItemId=deviceItemId.substring(0,deviceItemId.indexOf("-"));
				
				Device dev = dataManager.getDeviceByID(Integer.valueOf(deviceId));
				DeviceItem item = dev.getItemByID(Integer.valueOf(deviceItemId));

				DesktopManager.getIstance().getImportantPanel().addItem(item, dev);
				
				String importantValuesRaw = TelemetrySettings.getInstance().getSetting("GUI_IMPORTANT_VALUES");
				if (importantValuesRaw.contains(".")) {
					importantValuesRaw = importantValuesRaw + ",";
				}
				importantValuesRaw = importantValuesRaw + deviceId + "." + deviceItemId;
			
				TelemetrySettings.getInstance().setSetting("GUI_IMPORTANT_VALUES", importantValuesRaw);
				//DesktopManager.getIstance().getErrorPanel().addItem(item, dev);
				}
			}

			});
		
		
		btnGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i : dataTable.getSelectedRows()){
					
				String deviceId = (String) dataTable.getModel().getValueAt(i, 0);
				deviceId=deviceId.substring(0,deviceId.indexOf("-"));
				
				
				String deviceItemId = (String) dataTable.getModel().getValueAt(i, 1);
				deviceItemId=deviceItemId.substring(0,deviceItemId.indexOf("-"));
				
				Device dev = dataManager.getDeviceByID(Integer.valueOf(deviceId));
				DeviceItem item = dev.getItemByID(Integer.valueOf(deviceItemId));
				
				DesktopManager.getIstance().getTabbedPannel().addTab(
						new TelemetryGraph(Integer.valueOf(deviceId), Integer.valueOf(deviceItemId)),dev.getDeviceName()+"-"+item.getName());
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
		
		// Creates a scroll pane as a container for the table
		scrollPane = new JScrollPane(dataTable);
		this.add(scrollPane);
		
		upControlPanel.add(buttonPanel);
		upControlPanel.add(checkBoxPanel[0]);
		upControlPanel.add(checkBoxPanel[1]);		
		
		this.add(upControlPanel, BorderLayout.NORTH);
		
		fillTable();
		
	}
	
	public void itemStateChanged(ItemEvent ev) {
		emptyTable();
		fillTable();
		updateTable();
	}
	
	/**
	 * This method is called every second by the DesktopManager to add one line to the table
	 */
	public void updateTable() {
		
		int i=0;
		int j=0;
		for(Device dev : DataManager.getInstance().getDevices()) {
			if (deviceCheckBox[j].isSelected()) {
				for(DeviceItem item : dev.getItems()){
					String S = new SimpleDateFormat("hh:mm:ss:SS").format(item.getLastSeen());
					dataTable.getModel().setValueAt(item.getLastData(), i, 2);
					dataTable.getModel().setValueAt(S, i, 4);
					i++;
				}
			}
			j++;
		}
	}
	
	public void fillTable() {
		
		//Fill table
		int i=0;
		int j=0;
		int k=0;
		String lbl1;
		String lbl2;
		
		
		//Create static information (name, unit, etc)s
		for(Device dev : DataManager.getInstance().getDevices()) {
			if (deviceCheckBox[j].isSelected()) {				
				lbl1=dev.getDeviceId()+"-"+dev.getDeviceName();
				for(DeviceItem item : dev.getItems()){
					((DefaultTableModel)dataTable.getModel()).addRow(new Object[]{"", "", "", "", ""});
					lbl2=item.getItemId()+"-"+item.getName();				
					dataTable.getModel().setValueAt(lbl1, i, 0);
					dataTable.getModel().setValueAt(lbl2, i, 1);
					dataTable.getModel().setValueAt(item.getUnit(), i, 3);
					i++;					
				}
			}
			j++;
		}
		
	}
	
	public void emptyTable() {
	
		int rowCount = ((DefaultTableModel)dataTable.getModel()).getRowCount();
		for(int i=0; i<rowCount; i++) {
			((DefaultTableModel)dataTable.getModel()).removeRow(0);
		}
	}
	
	
}
