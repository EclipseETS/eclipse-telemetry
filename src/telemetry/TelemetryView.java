package telemetry;

import gui.DesktopManager;
import gui.dockables.TelemetryBattView;
import gui.dockables.TelemetryConsole;
import gui.dockables.TelemetryDeviceTable;
import gui.dockables.TelemetryMainView;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.swing.ActionMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import misc.log4j.JTextPaneAppender;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import util.ByteManipulator;

import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;
import com.vlsolutions.swing.docking.DockablePanel;
import com.vlsolutions.swing.docking.DockableState;
import com.vlsolutions.swing.docking.DockingConstants;
import com.vlsolutions.swing.docking.DockingDesktop;
import com.vlsolutions.swing.docking.event.DockableStateWillChangeEvent;
import com.vlsolutions.swing.docking.event.DockableStateWillChangeListener;

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
	JMenu workspaceMenu = new JMenu();
	JMenuItem saveWorkspaceMenuItem = new JMenuItem();
	JMenuItem loadWorkspaceMenuItem = new JMenuItem();
	
	JMenu viewsMenu = new JMenu();
	JMenuItem raceMenuItem = new JMenuItem();
	JMenuItem driveMenuItem = new JMenuItem();
	JMenuItem battMenuItem = new JMenuItem();
	JMenuItem mpptMenuItem = new JMenuItem();
	JMenuItem melissaMenuItem = new JMenuItem();
	JMenuItem battAuxMenuItem = new JMenuItem();
	JMenuItem statusMenuItem = new JMenuItem();
	
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
		workspaceMenu = new JMenu();
		saveWorkspaceMenuItem = new JMenuItem();
		loadWorkspaceMenuItem = new JMenuItem();
		
		viewsMenu = new JMenu();
		raceMenuItem = new JMenuItem();
		driveMenuItem = new JMenuItem();
		battMenuItem = new JMenuItem();
		mpptMenuItem = new JMenuItem();
		melissaMenuItem = new JMenuItem();
		battAuxMenuItem = new JMenuItem();
		statusMenuItem = new JMenuItem();
		
		fileMenu.setName("fileMenu");
		workspaceMenu.setName("workspaceMenu");
		viewsMenu.setName("viewsMenu");
		helpMenu.setName("helpMenu");
		aboutMenuItem.setName("aboutMenuItem");

		ActionMap actionMap = Application.getInstance(TelemetryView.class)
										 .getContext().getActionMap(TelemetryView.class, this);
				
		exitMenuItem.setAction(actionMap.get("quit"));
		exitMenuItem.setName("exitMenuItem");
		fileMenu.add(exitMenuItem);
		
		workspaceMenu.add(saveWorkspaceMenuItem);
		workspaceMenu.add(loadWorkspaceMenuItem);
	
		saveWorkspaceMenuItem.setAction(actionMap.get("saveWorkspaceAction"));
		saveWorkspaceMenuItem.setName("saveWorkspaceMenuItem");
		loadWorkspaceMenuItem.setAction(actionMap.get("loadWorkspaceAction"));
		loadWorkspaceMenuItem.setName("loadWorkspaceMenuItem");
		
		viewsMenu.add(raceMenuItem);
		viewsMenu.add(driveMenuItem);
		viewsMenu.add(battMenuItem);
		viewsMenu.add(mpptMenuItem);
		viewsMenu.add(melissaMenuItem);
		viewsMenu.add(battAuxMenuItem);
		viewsMenu.add(statusMenuItem);
		
		raceMenuItem.setName("raceMenuItem");
		driveMenuItem.setName("driveMenuItem");
		battMenuItem.setName("battMenuItem");
		mpptMenuItem.setName("mpptMenuItem");
		melissaMenuItem.setName("melissaMenuItem");
		battAuxMenuItem.setName("battAuxMenuItem");
		statusMenuItem.setName("statusMenuItem");
		
		raceMenuItem.setAction(actionMap.get("raceMenuAction"));
		driveMenuItem.setAction(actionMap.get("driveMenuAction"));
		battMenuItem.setAction(actionMap.get("battMenuAction"));
		mpptMenuItem.setAction(actionMap.get("mpptMenuAction"));
		melissaMenuItem.setAction(actionMap.get("melissaMenuAction"));
		battAuxMenuItem.setAction(actionMap.get("battAuxMenuAction"));
		statusMenuItem.setAction(actionMap.get("statusMenuAction"));
		
		helpMenu.add(aboutMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(workspaceMenu);
		menuBar.add(viewsMenu);
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
        loadWorkspaceMenuItem.setEnabled(false);
		
	}
	
	@Action
	public void clearConsoleAction() {
		console.clear();
	}
	
	@Action
	public void saveWorkspaceAction() {
		DesktopManager.getInstance().saveWorkspace();
		loadWorkspaceMenuItem.setEnabled(true);
	}
	
	@Action
	public void loadWorkspaceAction() {
		DesktopManager.getInstance().loadWorkspace();
	}
	
	@Action
	public void raceMenuAction() {
	
	}
	
	@Action
	public void driveMenuAction() {
		
	}
	
	@Action
	public void battMenuAction() {
		DesktopManager.getInstance().addViewTab(new TelemetryBattView());
	}
	
	@Action
	public void mpptMenuAction() {
		
	}
	
	@Action
	public void melissaMenuAction() {
		
	}
	
	@Action
	public void battAuxMenuAction() {
		
	}
	
	@Action
	public void statusMenuAction() {
		
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
		
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
            	show(mainPanel);
            }
        });
		
	}
	
	@Override
	protected void shutdown() {
		logger.info("Telemetry shuting down!");
		super.shutdown();
	}
	


}
