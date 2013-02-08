package eclipse.view.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;

import eclipse.model.data.DataManager;

/**
 * This class is used to create the table that displays the car data
 * @author fabricehoule
 *
 */

public class DeviceTable extends JPanel implements Observer {
	
	/**
	 * 
	 */
	private JTable dataTable;
	private DataManager dataManager = DataManager.getInstance();

	/**
	 * Creates the panel
	 */
	public DeviceTable() {
		
		dataTable = new JTable(new DeviceTableModel());
		
		// Creates a scroll pane as a container for the table
		JScrollPane scrollPane = new JScrollPane(dataTable);
		dataTable.setFillsViewportHeight(true);

	}

	/**
	 * This method is called whenever an object is changed
	 */
	public void update(Observable o, Object arg) {
		
		
	}

	/**
	 * Class for creating a new table model
	 * @author fabricehoule
	 *
	 */
	class DeviceTableModel extends AbstractTableModel {
	
		private String[] columnNames = {"Device", "ID", "Item", "Value", "Last seen"};

		public int getRowCount() {
			//TODO Methods for the table model
			
			int numberOfItems = 0;
			//for (dataManager.getDevices(){
			//	numberOfItems += dataManager.getDevices().size();
			//}
				
			return 0;
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	
}
