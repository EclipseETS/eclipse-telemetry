package eclipse.view.gui.tab;


import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;


public class tabDetails extends JPanel implements TabPane
{	
	
	private static final long serialVersionUID = 923162174875065504L;
	private List<subDetail> items;
	
	

	@Override
	public void updateValues() 
	{
		  for (subDetail s : items) 
		  {
		      s.updateValues();
		  }
	}
	
	public tabDetails()
	{
		
		//setBackground(Color.WHITE);		
		//setForeground(Color.BLACK);
		
		this.setLayout(new GridLayout(2, 2, 0, 0));
		items = new ArrayList<subDetail>();
	}
		
	public void addItem(String deviceID, String deviceItemID)
	{
		subDetail test = new subDetail(deviceID, deviceItemID);
		items.add(test);
		add(test);		
		revalidate();
		
	}
	
	
	

}
