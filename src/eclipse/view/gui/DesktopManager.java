package eclipse.view.gui;


import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;


import eclipse.controller.util.TelemetrySettings;

public class DesktopManager implements Runnable {

	private JFrame frmclipseViii;
	private JSplitPane leftPart;
	private JSplitPane all;
	private DeviceTable panTable = new DeviceTable();
	private CustomConsole con = new CustomConsole();

	

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
		
		//Change application Look n Feel
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//Frame Creation
		frmclipseViii = new JFrame();
		frmclipseViii.setTitle(TelemetrySettings.getInstance().getSetting("GUI_MENU_TITLE"));
		frmclipseViii.setBounds(100, 100, 683, 575);
		frmclipseViii.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmclipseViii.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		defineMenus();
		
		defineLayout();
		
		//Used to automatically resize the windows
		frmclipseViii.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                resizedMe();
            }
		});

		
	}

	private void resizedMe() {
		leftPart.setDividerLocation(0.75);
		all.setDividerLocation(0.75);
		
	}
    


	private void defineLayout() {

		JPanel panMain = new JPanel();
		
		panMain.setBackground(Color.black);
		
		
		leftPart = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, panMain, con);
		all = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPart, panTable);
				
		frmclipseViii.add(all);
		
		
	}

	/**
	 * Use the Gui as a Thread
	 */
	public void run() {
		
		//Init all panel, menu and used stuff
		initialize();
		
		
		//Show frame -- Replace frame.show()
		frmclipseViii.setVisible(true);
		
		while(true){
			//refresh value every X second
			panTable.updateTable();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
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
