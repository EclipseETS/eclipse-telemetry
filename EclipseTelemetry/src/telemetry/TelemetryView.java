package telemetry;

import gui.DesktopManager;
import gui.dockables.TelemetryConsole;
import gui.dockables.TelemetryDeviceTable;
import gui.dockables.TelemetryLogicalView;
import gui.graph.TelemetryGraphInterface;
import gui.graph.TelemetryGraphPoint;
import gui.graph.TelemetryGraphPuissanceOut;
import gui.graph.TelemetryGraphSpeed;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ActionMap;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


import org.apache.log4j.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import com.vlsolutions.swing.docking.Dockable;
import com.vlsolutions.swing.docking.DockingConstants;

import core.DataManager;

public class TelemetryView extends SingleFrameApplication {

	// Root Logger
	static Logger logger = Logger.getLogger("telemetry");
	
	// Main components
	private JPanel mainPanel = new JPanel();
	private JMenuBar menuBar;
	
	// Menu
	JMenu fileMenu = new JMenu();
	JMenuItem exitMenuItem = new JMenuItem();
	JMenu helpMenu = new JMenu();
	JMenuItem aboutMenuItem = new JMenuItem();
//	JMenu workspaceMenu = new JMenu();
//	JMenuItem saveWorkspaceMenuItem = new JMenuItem();
//	JMenuItem loadWorkspaceMenuItem = new JMenuItem();
	
	JMenu viewsMenu = new JMenu();
	JMenuItem strategieMenuItem = new JMenuItem();
	JMenuItem battMenuItem = new JMenuItem();
	JMenuItem globalMenuItem = new JMenuItem();
	JMenuItem mpptMenuItem = new JMenuItem();
	
	JMenuItem save = new JMenuItem();
	JMenuItem load = new JMenuItem();
	
	
	JMenu graphMenu = new JMenu();
	JMenuItem grapSpeedMenuItem = new JMenuItem();
	JMenuItem grapPOutMenuItem = new JMenuItem();
	JMenuItem grapWvsKmMenuItem = new JMenuItem();
	
	
	// Dockables
	TelemetryConsole console = new TelemetryConsole();
	TelemetryDeviceTable deviceTable = new TelemetryDeviceTable();
	
	//Hashtable<Integer, Device> devices = DataManager.getInstance().getDeviceMap()
	
	public TelemetryView() {
	}
	
	private void BuildMenus() {
		
		menuBar = new JMenuBar();

		fileMenu = new JMenu();
		exitMenuItem = new JMenuItem();
		helpMenu = new JMenu();
		aboutMenuItem = new JMenuItem();
		//workspaceMenu = new JMenu();
		//saveWorkspaceMenuItem = new JMenuItem();
		//loadWorkspaceMenuItem = new JMenuItem();
		
		viewsMenu = new JMenu();
		strategieMenuItem = new JMenuItem();
		battMenuItem = new JMenuItem();
		mpptMenuItem = new JMenuItem();
		
		fileMenu.setName("fileMenu");
		//workspaceMenu.setName("workspaceMenu");
		viewsMenu.setName("viewsMenu");
		helpMenu.setName("helpMenu");
		aboutMenuItem.setName("aboutMenuItem");

		ActionMap actionMap = Application.getInstance(TelemetryView.class)
										 .getContext().getActionMap(TelemetryView.class, this);
				
		exitMenuItem.setAction(actionMap.get("quit"));
		exitMenuItem.setName("exitMenuItem");
		
		
		graphMenu.setName("graphMenu");
		
		grapSpeedMenuItem.setName("grapSpeedMenuItem");
		graphMenu.add(grapSpeedMenuItem);
		grapSpeedMenuItem.setAction(actionMap.get("grapSpeedMenuItemAction"));
		
		grapPOutMenuItem.setName("grapPOutMenuItem");
		graphMenu.add(grapPOutMenuItem);
		grapPOutMenuItem.setAction(actionMap.get("grapPOutMenuItemAction"));
		
		grapWvsKmMenuItem.setName("grapWvsKmMenuItem");
		graphMenu.add(grapWvsKmMenuItem);
		grapWvsKmMenuItem.setAction(actionMap.get("grapWvsKmMenuItemAction"));
		
		
		
		//workspaceMenu.add(saveWorkspaceMenuItem);
		//workspaceMenu.add(loadWorkspaceMenuItem);
	
//		saveWorkspaceMenuItem.setAction(actionMap.get("saveWorkspaceAction"));
//		saveWorkspaceMenuItem.setName("saveWorkspaceMenuItem");
//		loadWorkspaceMenuItem.setAction(actionMap.get("loadWorkspaceAction"));
//		loadWorkspaceMenuItem.setName("loadWorkspaceMenuItem");
//		
		//viewsMenu.add(strategieMenuItem);
		viewsMenu.add(globalMenuItem);
	//	viewsMenu.add(mpptMenuItem);
	//	viewsMenu.add(battMenuItem);
		
		fileMenu.add(save);
		fileMenu.add(load);
		fileMenu.add(exitMenuItem);
		
		save.setName("save");
		load.setName("load");
		save.setAction(actionMap.get("saveAction"));
		load.setAction(actionMap.get("loadAction"));
		
		battMenuItem.setName("battMenuItem");
		strategieMenuItem.setName("strategieMenuItem");
		
		battMenuItem.setAction(actionMap.get("battMenuAction"));
		strategieMenuItem.setAction(actionMap.get("strategieMenuAction"));
		
		mpptMenuItem.setAction(actionMap.get("mpptMenuAction"));
		mpptMenuItem.setName("mpptMenuItem");
		
		globalMenuItem.setAction(actionMap.get("globalMenuAction"));
		globalMenuItem.setName("globalMenuItem");
		
		helpMenu.add(aboutMenuItem);
		aboutMenuItem.setAction(actionMap.get("helpMenuAction"));
		
		menuBar.add(fileMenu);
		//menuBar.add(workspaceMenu);
		menuBar.add(viewsMenu);
		menuBar.add(graphMenu);
		menuBar.add(helpMenu);
		
		this.getMainView().setMenuBar(menuBar);
	}
	
	private void BuildDockables() {
		
		// The main view port around which to add stuff
		Dockable mainView = DesktopManager.getInstance().getMainView();
		
		DesktopManager.getInstance().getDesktop().addDockable(mainView);
		DesktopManager.getInstance().getDesktop().split(mainView, deviceTable, DockingConstants.SPLIT_RIGHT);
		DesktopManager.getInstance().getDesktop().split(mainView, console, DockingConstants.SPLIT_BOTTOM);
		
		// Main Tabbed Container
		DesktopManager.getInstance().getDesktop().setDockableHeight(mainView, 0.8);
		DesktopManager.getInstance().getDesktop().setDockableWidth(mainView, 0.75);
		
        // cannot reload before a workspace is saved
      //  loadWorkspaceMenuItem.setEnabled(false);
		
	}
	
	@Action
	public void clearConsoleAction() {
		console.clear();
	}
	

//	public void saveWorkspaceAction() {
//		DesktopManager.getInstance().saveWorkspace();
//		loadWorkspaceMenuItem.setEnabled(true);
//	}
//	
//	@Action
//	public void loadWorkspaceAction() {
//		DesktopManager.getInstance().loadWorkspace();
//	}
	
	

	
	@Action
	public void saveAction() {
		JFileChooser Save = new JFileChooser();
		Save.setDialogTitle("Save");
	    int returnVal = Save.showSaveDialog(new JFrame());
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	DataManager.getInstance().serialize(Save.getSelectedFile().getAbsolutePath());
	    }
	}
	
	@Action
	public void strategieMenuItem() {
		DesktopManager.getInstance().addViewTab(new TelemetryGraphSpeed());
		
	}

	@Action
	public void globalMenuAction() {
		DesktopManager.getInstance().addViewTab(new TelemetryLogicalView());
		
	}
	@Action
	public void loadAction() {
	
		JFileChooser load = new JFileChooser();
	    int returnVal = load.showOpenDialog(new JFrame());
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	DataManager.getInstance().load(load.getSelectedFile().getAbsolutePath());
	    }
		
	}
	@Action
	public void helpMenuAction() {
		JOptionPane.showMessageDialog(new JFrame(), "Eclipse Solar Car Telemetric software. All right reserved. Special Thanks To Paulo and Dario for GUI.");

	}
	
	public void graphMenu(TelemetryGraphInterface inter){
		DesktopManager.getInstance().addSpecialGraphTab(inter);
	}

	
	
	@Override
	protected void initialize(String[] args) {	
			
	}
	
	public void refreshContentPanel() {

	}
	
	@Override
	protected void startup() {
		logger.info("Telemetry starting..");	
		
		// Main dockable desktop
		mainPanel.setLayout(new GridLayout(1,1));
		mainPanel.add(DesktopManager.getInstance().getDesktop());
		
		BuildMenus();
		BuildDockables();
		
		this.getMainFrame().setSize(1024,768);
		Image im = null;
		try {
			im = ImageIO.read(new File("eclipse.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getMainFrame().setIconImage(im);
	        SwingUtilities.invokeLater(new Runnable(){
	            public void run(){

	            	show(mainPanel);
	            }
	        });
		
	}
	
	@Override
	protected void shutdown() {
		//DataAcquisitionController.getInstance().stop();
		saveAction();
		logger.info("Telemetry shuting down!");
		super.shutdown();
	}
	
	@Action
	public void grapSpeedMenuItemAction(){
		graphMenu(new TelemetryGraphSpeed());
	}
	@Action
	public void grapPOutMenuItemAction(){
		graphMenu(new TelemetryGraphPuissanceOut());
	}
	@Action
	public void grapWvsKmMenuItemAction(){
		graphMenu(new TelemetryGraphPoint());
	}

}
