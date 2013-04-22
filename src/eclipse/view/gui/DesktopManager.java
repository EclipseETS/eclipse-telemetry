package eclipse.view.gui;


import javax.swing.JFrame;
import javax.swing.JMenuBar;

import eclipse.controller.util.TelemetrySettings;

public class DesktopManager implements Runnable {

	private JFrame frmclipseViii;

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public DesktopManager() {
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmclipseViii = new JFrame();
		frmclipseViii.setTitle(TelemetrySettings.getInstance().getSetting("GUI_MENU_TITLE"));
		frmclipseViii.setBounds(100, 100, 683, 575);
		frmclipseViii.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		defineMenus();
		
		frmclipseViii.add(new MigFrame());

		
	}


	/**
	 * Use the Gui as a Thread
	 */
	public void run() {
		
		//Init all panel, menu and used stuff
		initialize();
		
		
		//Show frame -- Replace frame.show()
		frmclipseViii.setVisible(true);
		
	}
	
	/**
	 * All Menu definition, Information and text should be here in property
	 */
	private void defineMenus(){

		JMenuBar menuBar = new JMenuBar();
		frmclipseViii.setJMenuBar(menuBar);
//		
//		JMenu mnFile = new JMenu("File");
//		menuBar.add(mnFile);
//		
//		JMenuItem mntmQuit = new JMenuItem("Quit");
//		mnFile.add(mntmQuit);
//		
//		JMenu mnView = new JMenu("View");
//		menuBar.add(mnView);
//		
//		JMenu mnGraph = new JMenu("Graph");
//		menuBar.add(mnGraph);
//		
//		JMenu mnHelp = new JMenu("Help");
//		menuBar.add(mnHelp);
//		
//		JMenu mnAbout = new JMenu("About");
//		mnHelp.add(mnAbout);
	}

}
