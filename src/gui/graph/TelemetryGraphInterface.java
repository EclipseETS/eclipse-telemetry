package gui.graph;

import java.awt.Component;
import java.util.Observable;

import com.vlsolutions.swing.docking.DockKey;

public interface TelemetryGraphInterface  {
	public void update(Observable o, Object arg);
	public Component getComponent();
	public DockKey getDockKey();
}
