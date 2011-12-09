package gui.dockables;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;

@SuppressWarnings("serial")
public class TelemetryMainView extends JPanel implements Dockable {//implements DockableContainer {

	DockKey key = new DockKey("mainView");	

	public TelemetryMainView() {
		this.setLayout(new GridLayout(1,1));
		this.setBackground(Color.LIGHT_GRAY);
		
		key.setName("Main Panel");
		key.setCloseEnabled(false);
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