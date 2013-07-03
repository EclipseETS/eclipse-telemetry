package eclipse.view.gui;


import java.awt.Color;

public class ErrorTable extends ImportantDeviceTable {

	private static final long serialVersionUID = -6942467875864683781L;
	
	public ErrorTable() {
		dataTable.setBackground(Color.red);
		
		
	}
	
	public int getHeightCustom(){
		if(model.getRowCount()==0)
			return (model.getRowCount()+1)*dataTable.getRowHeight()-3;
		else
			return(model.getRowCount()+1)*dataTable.getRowHeight()-3+21;
	}
	
	public void deleteAll(){
		for(int i=0;i<items.size();i++){
			//Remove information on that list
			items.remove(i);
			model.removeRow(i);
		}
			
			//REsize important table
			DesktopManager.getIstance().resizedMe();
	}
	
}
