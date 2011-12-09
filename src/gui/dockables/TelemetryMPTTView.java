package gui.dockables;

import java.awt.Color;
import java.awt.Component;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;

import core.DataManager;
import exception.DeviceNotFoundException;
import gui.components.SmallStatusView;

@SuppressWarnings("serial")
public class TelemetryMPTTView extends JPanel implements Dockable {
	DockKey key = new DockKey("Global_View");
	
	// Visual Components
	JPanel[] devicePanel;
	
	JPanel mainPanel;
	
	
	
		
	public TelemetryMPTTView() {
		key.setName("Global View");
		
		devicePanel=new JPanel[DataManager.getInstance().getDeviceMap().size()];
		
		try {
			initVoltageIndPanel();
		} catch (DeviceNotFoundException e) {
			// 
			e.printStackTrace();
		}
		
		
		this.setLayout(new MigLayout());
			
		
		
	}
	
	
	
	public void initVoltageIndPanel() throws DeviceNotFoundException {
		// Layout
		for (int i=1;i<=DataManager.getInstance().getDeviceMap().size();i++){
			devicePanel[i-1]=new JPanel();
			devicePanel[i-1].setLayout(new MigLayout("wrap 1"));
			
			if (Pattern.matches(".*MPPT.*", DataManager.getInstance().getDevice(i).getName()))
			{
			
				JLabel item = new JLabel(DataManager.getInstance().getDevice(i).getName());
				item.setForeground(Color.BLUE);
				//this.add(item);
				if(DataManager.getInstance().getDevice(i)!=null){
					devicePanel[i-1].setBorder(new TitledBorder(DataManager.getInstance().getDevice(i).getName()));
				for (int j=1;j<=DataManager.getInstance().getDevice(i).getDeviceItemMap().size();j++)
				{
					SmallStatusView currentCell = new SmallStatusView(DataManager.getInstance().getDevice(i).
							getItemByID(j));
					
					devicePanel[i-1].add(currentCell);
				}
				
				this.add(devicePanel[i-1]);
				}
			}
		}	
			
			
		
	}
	
	

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public DockKey getDockKey() {
		return key;
	}

	
}