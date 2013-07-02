package eclipse.view.gui;


import java.awt.Color;

public class ErrorTable extends ImportantDeviceTable {

	private static final long serialVersionUID = -6942467875864683781L;
	
	public ErrorTable() {
		dataTable.setBackground(Color.red);
		
		
	}
	
	public int getHeightCustom(){
		return (model.getRowCount()+1)*dataTable.getRowHeight()-3;
	}
	
}
