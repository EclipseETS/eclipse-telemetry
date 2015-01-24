package eclipse.view.gui;


import java.awt.Color;

/**
 * Error table is a specilize view for all data in error, it's a normal JChart and it wee be shown under the normal chart
 * @author Marco
 *
 */
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
		for(int i=items.size()-1;i>=0;i--){
			//Remove information on that list
			items.remove(i);
			model.removeRow(i);
		}
			
			//REsize important table
			DesktopManager.getIstance().resizedMe();
	}
	
}
