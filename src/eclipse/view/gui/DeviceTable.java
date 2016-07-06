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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;













import eclipse.controller.util.TelemetrySettings;
import eclipse.model.data.DataManager;
import eclipse.model.data.Device;
import eclipse.model.data.DeviceItem;
import eclipse.view.gui.tab.TabPane;
import eclipse.view.gui.tab.TelemetryGraph;
import eclipse.view.gui.tab.tabDetails;

/**
 * This class is used to create the table that displays the car data
 * 
 *Similar to Important table
 * 
 * @author MArco
 *
 */
public class DeviceTable extends JPanel implements ItemListener  
{
	
	private static final long serialVersionUID = -2652127495341433024L;
	private static final int deviceCount = 13;
	private JScrollPane scrollPane;
	private JTable dataTable;
	private JButton btnGraph;
	private JButton btnIndex;
	private JButton btn_Monitor;
	private JButton btnAll;
	private JButton btnNone;
	private DataManager dataManager = DataManager.getInstance();
	JPanel[] checkBoxPanel = new JPanel[3];		
	JCheckBox[] deviceCheckBox = new JCheckBox[deviceCount];
	
	Device dev;

	/**
	 * Creates the panel
	 */
	public DeviceTable() {		
		
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
		btnGraph = new JButton("Graph");
		btnIndex = new JButton("Keep");
		btn_Monitor = new JButton("Monitor");
		btnAll = new JButton("All");
		btnNone = new JButton("None");
		
		JPanel upControlPanel = new JPanel();
		upControlPanel.setLayout(new BoxLayout(upControlPanel, BoxLayout.Y_AXIS));
		
		checkBoxPanel[0] = new JPanel();
		checkBoxPanel[1] = new JPanel();
		checkBoxPanel[2] = new JPanel();
		
		deviceCheckBox[0] = new JCheckBox("Driver");
		deviceCheckBox[1] = new JCheckBox("Drive (L)");
		deviceCheckBox[2] = new JCheckBox("Drive (R)");
		deviceCheckBox[3] = new JCheckBox("BMS");
		deviceCheckBox[4] = new JCheckBox("Dash");
		deviceCheckBox[5] = new JCheckBox("Volant");
		deviceCheckBox[6] = new JCheckBox("Instru");
		deviceCheckBox[7] = new JCheckBox("MPPT (M)");
		deviceCheckBox[8] = new JCheckBox("MPPT (S)");
		deviceCheckBox[9] = new JCheckBox("PSU");
		deviceCheckBox[10] = new JCheckBox("Ignition");
		deviceCheckBox[11] = new JCheckBox("Flashers");
		deviceCheckBox[12] = new JCheckBox("Muppet");
		
		int i;
		
		for (i = 0; i<deviceCount ; i++) {
			deviceCheckBox[i].setSelected(true);
			deviceCheckBox[i].addItemListener(this);
		}
		for (i = 0 ; i<4 ; i++) {
			checkBoxPanel[0].add(deviceCheckBox[i]);
		}
		for (; i<9 ; i++) {
			checkBoxPanel[1].add(deviceCheckBox[i]);
		}
		for (; i<deviceCount ; i++) {
			checkBoxPanel[2].add(deviceCheckBox[i]);
		}
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(btnAll);
		buttonPanel.add(btnGraph);
		buttonPanel.add(btnIndex);
		buttonPanel.add(btnNone);
		buttonPanel.add(btn_Monitor);
		
		
		
		
		
		//Action Listner on the button, to use in case we want to add items to important list 
		btnIndex.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				
				for (int i : dataTable.getSelectedRows())
				{
					String deviceId = (String) dataTable.getModel().getValueAt(i, 0);
					deviceId=deviceId.substring(0,deviceId.indexOf("-"));
					
					
					String deviceItemId = (String) dataTable.getModel().getValueAt(i, 1);
					deviceItemId=deviceItemId.substring(0,deviceItemId.indexOf("-"));
					
					Device dev = dataManager.getDeviceByID(Integer.valueOf(deviceId));
					DeviceItem item = dev.getItemByID(Integer.valueOf(deviceItemId));
	
					DesktopManager.getIstance().getImportantPanel().addItem(item, dev);
					
					String importantValuesRaw = TelemetrySettings.getInstance().getSetting("GUI_IMPORTANT_VALUES");
					
					if (importantValuesRaw.contains(".")) 
					{
						importantValuesRaw = importantValuesRaw + ",";
					}
					
					importantValuesRaw = importantValuesRaw + deviceId + "." + deviceItemId;
				
					TelemetrySettings.getInstance().setSetting("GUI_IMPORTANT_VALUES", importantValuesRaw);
				}
			}

			});
		
		
		btnGraph.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{				
				for (int i : dataTable.getSelectedRows())
				{					
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
		
		btn_Monitor.addActionListener(new ActionListener() 
		{			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				for (int i : dataTable.getSelectedRows())
				{	
					String deviceId = (String) dataTable.getModel().getValueAt(i, 0);
					deviceId=deviceId.substring(0,deviceId.indexOf("-"));
										
					String deviceItemId = (String) dataTable.getModel().getValueAt(i, 1);
					deviceItemId=deviceItemId.substring(0,deviceItemId.indexOf("-"));
					
					((tabDetails)((JTabbedPane)DesktopManager.getIstance().getTabbedPannel().getComponent(0)).getComponent(1)).addItem(deviceId, deviceItemId);
					
				}				
			}
		});

		btnAll.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				int i;
				for (i=0; i<deviceCount ; i++)
				{			
					deviceCheckBox[i].setSelected(true);
				}
			}
		});
		
		btnNone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i;
				for (i=0; i<deviceCount ; i++) {
					deviceCheckBox[i].setSelected(false);
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
		upControlPanel.add(checkBoxPanel[2]);		
		
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
