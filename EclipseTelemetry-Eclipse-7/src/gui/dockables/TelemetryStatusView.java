package gui.dockables;

import java.awt.Component;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;

import core.DataManager;
import eclipseV7.data.Device;
import eclipseV7.data.DeviceItem;
import exception.DeviceNotFoundException;

@SuppressWarnings("serial")
public class TelemetryStatusView extends JPanel implements Dockable, Observer{

	DockKey key = new DockKey("status_view");
	Vector<Device> devicesWithStatus = new Vector<Device>();
	
	public TelemetryStatusView() {
		key.setName("Status List View");
		
		MigLayout contentLayout = new MigLayout("insets 0");
		this.setLayout(contentLayout);
		
		// Hold Devices that have definitions in a vector
		for(Device key:DataManager.getInstance().getDeviceMap().values()) {
			boolean deviceHasStatus = false;
			try {
				for(DeviceItem itemKey:DataManager.getInstance().getDevice(key.getId()).getDeviceItemMap().values()) {
					if (itemKey.hasDefinitions()) deviceHasStatus = true;
				}
			} catch (DeviceNotFoundException e) {
				e.printStackTrace();
			}
			if (deviceHasStatus) devicesWithStatus.add(key);
		}
		
		
		
	}
	
	@SuppressWarnings("unused")
	private void reloadDeviceItemStatus(int itemId) {
		
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
		
	}

}
