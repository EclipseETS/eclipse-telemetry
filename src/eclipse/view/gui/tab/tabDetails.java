package eclipse.view.gui.tab;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import net.miginfocom.layout.Grid;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.jar.Attributes.Name;

import eclipse.controller.acqui.DataAcquisition;
import eclipse.controller.util.TelemetrySettings;
import eclipse.model.data.DataManager;
import eclipse.model.data.DeviceItem;


public class tabDetails extends JPanel implements TabPane
{	
	
	private static final long serialVersionUID = 923162174875065504L;
	protected List<subDetail> items;
	
	

	@Override
	public void updateValues() 
	{
		// TODO Auto-generated method stub
		
	}
	
	public tabDetails()
	{
		
		setBackground(Color.WHITE);		
		setForeground(Color.BLACK);
		this.setLayout(new GridLayout(2, 2, 0, 0));
	}
		
	public void addItem(String deviceID, String deviceItemID)
	{
	
			subDetail test = new subDetail(deviceID, deviceItemID);
			add(test);
			
		
	}
	
	
	

}
