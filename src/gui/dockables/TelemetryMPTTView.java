package gui.dockables;

import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;

import core.DataManager;
import eclipseV7.data.Device;
import exception.DeviceNotFoundException;

public class TelemetryMPTTView extends JPanel implements Dockable, Observer{

	DockKey key = new DockKey("mppt_view");
	
	// Visual Components
	JPanel mppt1;
	JPanel mppt2;
	JPanel mppt3;
	JPanel mppt4;
	JPanel mppt5;
	JPanel mppt6;
	
	double PowerIn = 0;
	
	public TelemetryMPTTView() {
		key.setName("MPPT View");
		
		buildMPPTui(1);
		buildMPPTui(2);
		buildMPPTui(3);
		buildMPPTui(4);
	}
	
	private void buildMPPTui(int mppt) {
		// Layout
		MigLayout mpptLayout = new MigLayout("insets 3");
		
		//battPanel.setLayout(battLayout);
		//battPanel.setBorder(new TitledBorder("Main Info"));
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
		// TODO Auto-generated method stub
		
	}

}
