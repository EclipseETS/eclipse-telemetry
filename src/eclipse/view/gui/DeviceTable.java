package eclipse.view.gui;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import eclipse.model.data.DataManager;
import eclipse.model.data.DeviceItem;

/**
 * This class is used to create the table that displays the car data
 * @author fabricehoule
 *
 */

public class DeviceTable extends JPanel {
	
	private static final long serialVersionUID = -2652127495341433024L;
	private JScrollPane scrollPane;
	private JTable dataTable;
	private JButton btnError;
	private JButton btnGraph;
	private JButton btnIndex;
	private DataManager dataManager = DataManager.getInstance();

	/**
	 * Creates the panel
	 */
	public DeviceTable() {
		
		// Builds a layout without borders between elements
		this.setLayout(new BorderLayout());		
		DefaultTableModel model = new DefaultTableModel(30, 5);
		dataTable = new JTable(model);
		btnError = new JButton("Mark as error");
		btnGraph = new JButton("Graph this data");
		btnIndex = new JButton("Keep this value");
		
		// Set default width for the table columns
		dataTable.setFillsViewportHeight(true);
		dataTable.getColumn(0).setPreferredWidth(5);  // Device
		dataTable.getColumn(1).setPreferredWidth(2);  // ItemId
		dataTable.getColumn(2).setPreferredWidth(10); // Item
		dataTable.getColumn(3).setPreferredWidth(10); // Value
		dataTable.getColumn(5).setPreferredWidth(2); // Status
		
		// Creates a scroll pane as a container for the table
		scrollPane = new JScrollPane(dataTable);
		this.add(scrollPane);
		this.add(btnError, BorderLayout.SOUTH);
		this.add(btnGraph, BorderLayout.NORTH);
		this.add(btnIndex, BorderLayout.SOUTH);
	}
	
	/**
	 * This method is called every second by the DesktopManager to add one line to the table
	 */
	public void updateTable() {
		
		
	}
	
	
}
