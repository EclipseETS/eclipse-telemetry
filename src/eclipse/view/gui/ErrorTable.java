package eclipse.view.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ErrorTable extends ImportantDeviceTable {

	private static final long serialVersionUID = -6942467875864683781L;
	
	public ErrorTable() {
		dataTable.setBackground(Color.red);
		
		dataTable.addMouseListener(new MouseListener() {
		public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		});
	}
	
	public int getHeightCustom(){
		return (model.getRowCount()+1)*dataTable.getRowHeight()-3;
	}
	
}
