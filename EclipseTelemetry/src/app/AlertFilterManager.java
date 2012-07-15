package app;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class AlertFilterManager extends JDialog{
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
	private Dimension tailleCombos = new Dimension();
	
	private String[] IDStrings = { "Unfiltered", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10" };
	private String[] ItemStrings = { "Unfiltered", "Dash", "Startup Controller", "MPPT1", "MPPT2", "MPPT3" };
	private String[] PriorityStrings = { "Unfiltered", "01", "02", "03", "04", "05" };
	
	private String IDFilterValue = "Unfiltered";
	private String ItemFilterValue = "Unfiltered";
	private String PriorityFilterValue = "Unfiltered";
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AlertFilterManager() {
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Alert Filter Manager...");
		setSize(750, 300);
		
		tailleBoutons.setSize(250, 30);
		tailleCombos.setSize(100, 30);
		
		getContentPane().setLayout(new MigLayout("", "[434.00,grow][grow][434.00,grow]", "[210.00,grow][68.00]"));
		
		String[] columnNames = {"Type", "Value"};

		model.setColumnIdentifiers(columnNames);
		table = new JTable(model){
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
				try {
					Component c = super.prepareRenderer(renderer, row, column);
					if (!isRowSelected(row)){
						c.setBackground(getBackground());
						c.setForeground(getForeground());
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
		table.getTableHeader().setReorderingAllowed(false);
		
		table.getColumnModel().getColumn(0).setMinWidth(100);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		
		JScrollPane sp = new JScrollPane(table);
		getContentPane().add(sp, "cell 0 0 3 1,grow");
		
		JButton btnSelectAll = new JButton("Select All");
		btnSelectAll.setPreferredSize(tailleBoutons);
		btnSelectAll.setToolTipText("Cliquez ici pour sélectionner tous les filtres de la liste.");
		btnSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("ALERT : Select All button");
				table.selectAll();
			}
		});
		getContentPane().add(btnSelectAll, "flowy,cell 0 1,alignx left,aligny top");
		
		JButton btnUnselectAll = new JButton("Unselect All");
		btnUnselectAll.setPreferredSize(tailleBoutons);
		btnUnselectAll.setToolTipText("Cliquez ici pour désélectionner tous les filtres de la liste.");
		btnUnselectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("ALERT : Unselect All button");
				table.clearSelection();
			}
		});
		getContentPane().add(btnUnselectAll, "cell 0 1,alignx left,aligny top");
		
		JButton btnReverseSelected = new JButton("Reverse Selected");
		btnReverseSelected.setPreferredSize(tailleBoutons);
		btnReverseSelected.setToolTipText("Cliquez ici pour invertir les filtres sélectionnées de la liste.");
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
		getContentPane().add(btnReverseSelected, "cell 0 1,alignx left,aligny top");
		
		JComboBox cmbID = new JComboBox(IDStrings);
		cmbID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IDFilterValue = ((JComboBox)e.getSource()).getSelectedItem().toString();
			}
		});
		cmbID.setPreferredSize(tailleCombos);
		cmbID.setToolTipText("Un filtre de type ID sera ajouté.");
		getContentPane().add(cmbID, "flowx,cell 1 1,alignx center,aligny top");
		
		JComboBox cmbItem = new JComboBox(ItemStrings);
		cmbItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemFilterValue = ((JComboBox)e.getSource()).getSelectedItem().toString();
			}
		});
		cmbItem.setPreferredSize(tailleCombos);
		cmbItem.setToolTipText("Un filtre de type Item sera ajouté.");
		getContentPane().add(cmbItem, "cell 1 1,alignx center,aligny top");
		
		JComboBox cmbPriority = new JComboBox(PriorityStrings);
		cmbPriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PriorityFilterValue = ((JComboBox)e.getSource()).getSelectedItem().toString();
			}
		});
		cmbPriority.setPreferredSize(tailleCombos);
		cmbPriority.setToolTipText("Un filtre de type Priorité sera ajouté.");
		getContentPane().add(cmbPriority, "cell 1 1,alignx center,aligny top");
		
		JButton btnAddPersonalizedFilter = new JButton("Add Personalized Filter");
		btnAddPersonalizedFilter.setPreferredSize(tailleBoutons);
		btnAddPersonalizedFilter.setToolTipText("Cliquer ici pour ajouter un filtre personnalisé.");
		btnAddPersonalizedFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!IDFilterValue.equals("Unfiltered")){
					addFilter("ID;"+Integer.valueOf(IDFilterValue));
				}
				
				if (!ItemFilterValue.equals("Unfiltered")){
					addFilter("Item;"+ItemFilterValue);
				}
				
				if (!PriorityFilterValue.equals("Unfiltered")){
					addFilter("Priority;"+Integer.valueOf(PriorityFilterValue));
				}
				updateTable();
			}
		});
		
		getContentPane().add(btnAddPersonalizedFilter, "flowy,cell 2 1,alignx right,aligny top");
		
		JButton btnClose = new JButton("Fermer");
		btnClose.setPreferredSize(tailleBoutons);
		btnClose.setToolTipText("Cliquer ici pour fermer le gestionnaire des filtres.");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JButton btnRemoveSelectedFilterAlert = new JButton("Remove Selected Filter Alerts");
		btnRemoveSelectedFilterAlert.setPreferredSize(tailleBoutons);
		btnRemoveSelectedFilterAlert.setToolTipText("Cliquer ici supprimer les filtres sélectionnés.");
		btnRemoveSelectedFilterAlert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("ALERT : Filter priority selected button");
				int rows[] = table.getSelectedRows();
				String str = new String("");
				for (int i=0; i< rows.length;i++) {
					str = table.getValueAt(rows[i], 0) + ";" + table.getValueAt(rows[i], 1);
					app.DataAlert.Filters.remove(str);
				}
				updateTable();
			}
		});
		getContentPane().add(btnRemoveSelectedFilterAlert, "cell 2 1,alignx right");
		
		JButton btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.setPreferredSize(tailleBoutons);
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.DataAlert.Filters.clear();
				updateTable();
				
			}
		});
		btnRemoveAll.setToolTipText("Cliquer ici pour supprimer tous les filtres.");
		getContentPane().add(btnRemoveAll, "cell 2 1,alignx right");
		getContentPane().add(btnClose, "cell 2 1,alignx right,aligny bottom");
		
		updateTable();
		
	}
	
	public void updateTable(){
		int count = model.getRowCount();
		
		for (int i=0; i< count;i++){
			model.removeRow(0);
		}
		
		for (int i=0; i<app.DataAlert.Filters.size();i++){
				String data[] = {app.DataAlert.Filters.get(i).split(";")[0], app.DataAlert.Filters.get(i).split(";")[1]};
				model.insertRow(0, data);
			}
	}
	
	public void addFilter(String str){
		boolean exist = false;
		if (app.DataAlert.Filters.size() >0) {
			for (int j=0; j<app.DataAlert.Filters.size();j++){                                                                              
				if (app.DataAlert.Filters.get(j).split(";")[0].equals(str.split(";")[0]) && app.DataAlert.Filters.get(j).split(";")[1].equals(str.split(";")[1])){
					exist = true;
				}
			}
			
			if (!exist){
				String data[] = {str.split(";")[0],str.split(";")[1]};
				model.insertRow(0, data);
				app.DataAlert.Filters.add(str);
			}
		}
		else {
			String data[] = {str.split(";")[0],str.split(";")[1]};
			model.insertRow(0, data);
			app.DataAlert.Filters.add(str);
		}
	}
}
