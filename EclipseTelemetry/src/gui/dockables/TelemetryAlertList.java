package gui.dockables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.AlertFilterManager;

import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TelemetryAlertList extends JPanel implements Dockable, Observer{

	DockKey key = new DockKey("alert_view");
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel(){
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int column){
			try {
				return getValueAt(0, column).getClass();
			}
			catch (Exception e){
				System.out.println("ERREUR1");
				return null;
			}
		}

	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return false;
	    }
	};
	private Dimension tailleBoutons = new Dimension();
	
	public TelemetryAlertList() {
		tailleBoutons.setSize(250, 30);
		initDatas();
		
		setBorder(null);
		setLayout(new MigLayout("", "[434.00,grow][434.00,grow]", "[210.00,grow][68.00]"));
		
		String[] columnNames = {"ID", "Time", "Item", "Description", "Priority"};

		model.setColumnIdentifiers(columnNames);
		table = new JTable(model){
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
				try {
					Component c = super.prepareRenderer(renderer, row, column);
					if (!isRowSelected(row)){
						c.setBackground(getBackground());
						c.setForeground(getForeground());
						int modelRow = convertRowIndexToModel(row);
						String type = (String)getModel().getValueAt(modelRow, 4);
						if ("1".equals(type)){
							c.setBackground(Color.RED);
							c.setForeground(Color.WHITE);
						}
						else if("2".equals(type)){
							c.setBackground(Color.ORANGE);
						}
						else if("3".equals(type)){
							c.setBackground(Color.YELLOW);
						}
					}
					return c;
					} catch (Exception e){
						System.out.println("ERREUR2");
						return null;
					}
			}
		};
		
		
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getTableHeader().setReorderingAllowed(false);
		
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(0).setMaxWidth(20);
		table.getColumnModel().getColumn(1).setMinWidth(60);
		table.getColumnModel().getColumn(1).setMaxWidth(60);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(60);
		table.getColumnModel().getColumn(4).setMaxWidth(60);
		
		
		JScrollPane sp = new JScrollPane(table);
		add(sp, "cell 0 0 2 1,grow");
		
		JButton btnSelectAll = new JButton("Select All");
		btnSelectAll.setPreferredSize(tailleBoutons);
		btnSelectAll.setToolTipText("Cliquez ici pour sélectionner tous les alertes de la liste.");
		btnSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("ALERT : Select All button");
				table.selectAll();
			}
		});
		add(btnSelectAll, "flowy,cell 0 1,alignx left,aligny top");
		
		JButton btnUnselectAll = new JButton("Unselect All");
		btnUnselectAll.setPreferredSize(tailleBoutons);
		btnUnselectAll.setToolTipText("Cliquez ici pour désélectionner tous les alertes de la liste.");
		btnUnselectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("ALERT : Unselect All button");
				table.clearSelection();
			}
		});
		add(btnUnselectAll, "cell 0 1,alignx left,aligny top");
		
		JButton btnReverseSelected = new JButton("Reverse Selected");
		btnReverseSelected.setPreferredSize(tailleBoutons);
		btnReverseSelected.setToolTipText("Cliquez ici pour invertir les alertes sélectionnées de la liste.");
		btnReverseSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("ALERT : Reverse Selected button");
				
				if (table.getSelectedRowCount() == 0){
					table.selectAll();
				}
				else if (table.getSelectedRowCount() == table.getRowCount()){
					table.clearSelection();
				}
				else{
					int[] selectedIndexs = table.getSelectedRows();
					table.selectAll();

					for (int i = 0; i < table.getRowCount(); i++) {
					    for (int selectedIndex : selectedIndexs) {
					        if (selectedIndex == i) {
					        	table.removeRowSelectionInterval(i, i);
					            break;
					        }
					    }
					}
				}
			}
		});
		add(btnReverseSelected, "cell 0 1,alignx left,aligny top");
		
		JButton btnFilterSelectedAlertsItem = new JButton("Filter Selected Alerts by 'Item'");
		btnFilterSelectedAlertsItem.setPreferredSize(tailleBoutons);
		btnFilterSelectedAlertsItem.setToolTipText("Cliquer ici pour filtrer les alertes sélectionnées selon leur item.");
		btnFilterSelectedAlertsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("ALERT : Filter item selected button");
				app.DataAlert.working = true;
				int rows[] = table.getSelectedRows();
				for (int i=0; i< rows.length;i++) {
					//System.out.println("row selected : " + table.getValueAt(rows[i], 2));
					if(!app.DataAlert.isFilterExist("Item;"+table.getValueAt(rows[i], 2))){
						app.DataAlert.Filters.add("Item;"+table.getValueAt(rows[i], 2));
					}
				}
				
				updateTable();
				app.DataAlert.working = false;
			}
		});
		add(btnFilterSelectedAlertsItem, "flowy,cell 1 1,alignx right,aligny top");
		
		JButton btnFiltermanager = new JButton("Filter Manager...");
		btnFiltermanager.setPreferredSize(tailleBoutons);
		btnFiltermanager.setToolTipText("Cliquer ici pour ouvrir le gestionnaire des alertes.");
		btnFiltermanager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("ALERT : Filter Manager button");
				AlertFilterManager am = new AlertFilterManager();
				am.setVisible(true);
				updateTable();
			}
		});
		
		JButton btnFilterSelectedAlertsPriority = new JButton("Filter Selected Alerts by 'Priority'");
		btnFilterSelectedAlertsPriority.setPreferredSize(tailleBoutons);
		btnFilterSelectedAlertsPriority.setToolTipText("Cliquer ici pour filtrer les alertes sélectionnées selon leur priorité.");
		btnFilterSelectedAlertsPriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("ALERT : Filter priority selected button");
				int rows[] = table.getSelectedRows();
				for (int i=0; i< rows.length;i++) {
					if(!app.DataAlert.isFilterExist("Priority;"+table.getValueAt(rows[i], 4))){
						app.DataAlert.Filters.add("Priority;"+table.getValueAt(rows[i], 4));
					}
				}
				updateTable();
			}
		});
		add(btnFilterSelectedAlertsPriority, "cell 1 1,alignx right");
		add(btnFiltermanager, "cell 1 1,alignx right,aligny bottom");
		
		key.setName("Alert View");	
	}
	
	public void updateTable(){
		int count = model.getRowCount();
		
		for (int i=0; i< count;i++){
			model.removeRow(0);
		}
		
		boolean filtered = false;
		for (int i=0; i< app.DataAlert.LiveAlerts.size();i++){
			for (int j=0; j<app.DataAlert.Filters.size();j++){
				//System.out.println(app.DataAlert.Filters.get(j).split(";")[0]);
				//System.out.println(app.DataAlert.Filters.get(j).split(";")[1]);
				//System.out.println(app.DataAlert.LiveAlerts.get(i).split(";")[1]);
				//System.out.println("-----");
				                                                                                   
				if (app.DataAlert.Filters.get(j).split(";")[0].equals("ID") 			&& app.DataAlert.Filters.get(j).split(";")[1].equals(app.DataAlert.LiveAlerts.get(i).split(";")[0])){
					filtered = true;
				}
				else if (app.DataAlert.Filters.get(j).split(";")[0].equals("Item") 		&& app.DataAlert.Filters.get(j).split(";")[1].equals(app.DataAlert.LiveAlerts.get(i).split(";")[1])){
					filtered = true;
				}
				else if (app.DataAlert.Filters.get(j).split(";")[0].equals("Priority") 	&& app.DataAlert.Filters.get(j).split(";")[1].equals(app.DataAlert.LiveAlerts.get(i).split(";")[3])){
					filtered = true;
				}
			}
			
			if (!filtered){
				String data[] = {app.DataAlert.LiveAlerts.get(i).split(";")[0],app.DataAlert.LiveAlerts.get(i).split(";")[4],app.DataAlert.LiveAlerts.get(i).split(";")[1],app.DataAlert.LiveAlerts.get(i).split(";")[2], app.DataAlert.LiveAlerts.get(i).split(";")[3] };
				model.insertRow(0, data);
			}
			else {
				filtered = false;
			}
		}
	}
	
	public void addAlert(String str){
		//System.out.println(str);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String date = format.format(new Date());
		boolean filtered = false;
		
		if (app.DataAlert.Filters.size() >0) {
			for (int j=0; j<app.DataAlert.Filters.size();j++){                                                                              
				if (app.DataAlert.Filters.get(j).split(";")[0].equals("ID") 			&& app.DataAlert.Filters.get(j).split(";")[1].equals(str.split(";")[0])){
					filtered = true;
					}
				else if (app.DataAlert.Filters.get(j).split(";")[0].equals("Item") 		&& app.DataAlert.Filters.get(j).split(";")[1].equals(str.split(";")[1])){
					filtered = true;
					}
				else if (app.DataAlert.Filters.get(j).split(";")[0].equals("Priority") 	&& app.DataAlert.Filters.get(j).split(";")[1].equals(str.split(";")[3])){
					filtered = true;
					}
				}
			
			if (!filtered){
				String data[] = {str.split(";")[0],date,str.split(";")[1], str.split(";")[2], str.split(";")[3] };
				model.insertRow(0, data);
			}
			else {
				filtered = false;
			}
			
		}
		else {
			String data[] = {str.split(";")[0],date,str.split(";")[1], str.split(";")[2], str.split(";")[3] };
			model.insertRow(0, data);
		}
		
		app.DataAlert.LiveAlerts.add(str + ";" + date);
		
		if (str.split(";")[3]=="1"){
			omgP1Alert(str);
			}
	}
	
	public void omgP1Alert(String str){
		System.out.println("omg! i've a P1!!!!");
	}
	
	public void initDatas(){
	/*try {
			DataManager.getInstance().getDeviceItem(2, 2).addObserver(this);
			
		} catch (DeviceItemNotFoundException e) {
				e.printStackTrace();
		} catch (DeviceNotFoundException e) {
			e.printStackTrace();
		}*/
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
		//DeviceItem item = ((DeviceItem) o);
		System.out.println("ici????????????????");
	}
}
