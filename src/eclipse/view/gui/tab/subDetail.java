package eclipse.view.gui.tab;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import eclipse.model.data.DataManager;

public class subDetail extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 846991104427450638L;
	
	JLabel Drive_Label = new JLabel("[Item]");
	
	JLabel Drive_ErrorFlags = new JLabel("Error Flags : ");
	JLabel Drive_ErrorFlags_Value = new JLabel("");
	
	String deviceID;
	String deviceItemId;
	
	DataManager dm = DataManager.getInstance();
	
	public subDetail(String devID, String devIId) 
	{	
		setBackground(Color.lightGray);		
		setForeground(Color.BLACK);
		
		deviceID = devID;
		deviceItemId = devIId;
		
		
		Drive_ErrorFlags = new JLabel(deviceID);
		Drive_ErrorFlags_Value = new JLabel(deviceItemId);
		
		
		add(Drive_Label);
		add(Drive_ErrorFlags);
		add(Drive_ErrorFlags_Value);


		
	}
	
	public void updateValues()
	{
		Drive_ErrorFlags_Value.setText(dm.getRoundedValue(Integer.parseInt(deviceID), Integer.parseInt(deviceItemId)));
	}
	

}
