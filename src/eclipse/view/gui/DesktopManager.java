package eclipse.view.gui;


import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import javax.swing.UIManager;


import eclipse.controller.util.TelemetrySettings;
/**
 * This Desktop Manager is the Main Gui point of entry for this application
 * The manager will open any frame, console, or whatever you see
 * 
 * 
 * @author Marco
 *
 */
public class DesktopManager implements Runnable {

	private JFrame frmclipseViii;
	private JSplitPane leftPart;
	private JSplitPane rightPart;
	private JSplitPane all;
	private DeviceTable panTable = new DeviceTable();
	private ImportantDeviceTable panTable2 = new ImportantDeviceTable();
	private CustomConsole con = new CustomConsole();
	private TabbedPannel tab = new TabbedPannel();
	static private DesktopManager desinstance = new DesktopManager();

	static public DesktopManager getIstance(){
		return desinstance;
	}
	

	/**
	 * Create the application.
	 * 
	 */
	private DesktopManager() {
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

	/**
	 * Used to resized the content,all windows and size the important table
	 */
	public void resizedMe() {
		leftPart.setDividerLocation(0.75);
		rightPart.setDividerLocation(panTable2.getHeightCustom());
		all.setDividerLocation(0.75);
		
	}
    


	private void defineLayout() {

		//Set the resizeable panel (set size)
		leftPart = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, tab, con);
		rightPart = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, panTable2, panTable);
		all = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPart, rightPart);
				
		frmclipseViii.add(all);
		
		
	}

	/**
	 * Set the Gui as a Thread
	 */
	public void run() {
		
		//Init all panel, menu and used stuff
		initialize();
		
		
		//Show frame -- Replace frame.show()
		frmclipseViii.setVisible(true);
		
		//This is the timer for the gui part of the application
		while(true){
			//refresh value every second
			panTable.updateTable();
			panTable2.updateTable();
			tab.update();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ImportantDeviceTable getImportantPanel(){
		return panTable2;
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
		//TODO: Add shortcut menu (to give command to the people
	}
	
	public TabbedPannel getTabbedPannel(){
		return tab;
	}

}
