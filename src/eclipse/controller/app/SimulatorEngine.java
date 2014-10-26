package eclipse.controller.app;

import javax.swing.JFrame;

import eclipse.view.gui.SimulatorGUI;

/**
 * The engine that will handle the Simulation
 * Simulation is related to the data simulator
 *  
 * @author marco
 *
 */
public class SimulatorEngine implements Runnable{
	
	private JFrame frame;

	@Override
	public void run() {
		frame = SimulatorGUI.createAndShowGUI();

	}
	
	public void kill(){
		frame.dispose();
	}
	
	

}
