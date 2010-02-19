package simulator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

import misc.log4j.JTextPaneAppender;
import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.swingx.JXStatusBar;

import simulator.comm.SimulatorServer;

import core.DataManager;
import eclipseV7.data.Device;

public class SimulatorView extends SingleFrameApplication {

	// Root Logger
	static Logger logger = Logger.getLogger("simulator");
	
	// Simulated devices
	Hashtable<Integer, SimulatorDevice> simulatorDevices = new Hashtable<Integer, SimulatorDevice>();
	
	// Components
	private JPanel mainPanel;
	private JSplitPane splitPane;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JXStatusBar bar;
	private JTextPane statusConsole;
	
	// Server
	private boolean isConnected = false;
	private SimulatorServer server = new SimulatorServer();
	JButton jButtonStartServer;
	
	// Thread
	private Thread broadcastSimulatedFramesThread;

	public SimulatorView() {
		// TODO Save state of the Gui settings (sliders, values, etc): save
		// file?
	}
	
	// Parse the Devices Map and create the SimulatorDevice Elements dynamically
	// this is used to control each element individually (enable, disable, etc)
	private void CreateSimulatorDeviceTable() {

		Hashtable<Integer, Device> devices = DataManager.getInstance().getDeviceMap();
		for (Device key:devices.values()) { // Foreach Device
			SimulatorDevice sd = new SimulatorDevice(key);
			simulatorDevices.put(key.getId(), sd);
		}
	}

	private void BuildSimGui() {
		
		mainPanel = new JPanel();
		menuBar = new JMenuBar();
		toolBar = new JToolBar();
		bar = new JXStatusBar();
		statusConsole = new JTextPane();
		
		// 1. Menus
		JMenu fileMenu = new JMenu();
		JMenuItem exitMenuItem = new JMenuItem();
		JMenu helpMenu = new JMenu();
		JMenuItem aboutMenuItem = new JMenuItem();

		fileMenu.setName("fileMenu");

		ActionMap actionMap = Application.getInstance(SimulatorView.class).getContext().getActionMap(SimulatorView.class, this);
		exitMenuItem.setAction(actionMap.get("quit"));
		exitMenuItem.setName("exitMenuItem");
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		helpMenu.setName("helpMenu");

		//aboutMenuItem.setAction(actionMap.get("showAboutBox"));
		aboutMenuItem.setName("aboutMenuItem");
		helpMenu.add(aboutMenuItem);

		menuBar.add(helpMenu);
		
		this.getMainView().setMenuBar(menuBar);
		
		// 1.1 ToolBar
		mainPanel.setLayout(new BorderLayout());
		
		JButton jButtonMasterOn = new JButton();
		jButtonMasterOn.setName("masterOn");
		jButtonMasterOn.setAction(getContext().getActionMap(this).get("MasterAllOn"));
		
		JButton jButtonMasterOff = new JButton();
		jButtonMasterOff.setName("masterOff");
		jButtonMasterOff.setAction(getContext().getActionMap(this).get("MasterAllOff"));
		
		JButton jButtonClearConsole = new JButton();
		jButtonClearConsole.setName("clearConsole");
		jButtonClearConsole.setAction(getContext().getActionMap(this).get("clearConsole"));
		
		jButtonStartServer = new JButton();
		jButtonStartServer.setName("startServer");
		jButtonStartServer.setAction(getContext().getActionMap(this).get("startStopServer"));
		
		toolBar.add(jButtonMasterOn);
		toolBar.add(jButtonMasterOff);
		toolBar.addSeparator();
		toolBar.add(jButtonClearConsole);
		toolBar.addSeparator();
		toolBar.add(jButtonStartServer);
		
		toolBar.setFloatable(false);
		mainPanel.add(toolBar, BorderLayout.PAGE_START);
		
		// 2. Main content
		
		JPanel contentPanel = new JPanel();
		JScrollPane mainContentScrollPane = new JScrollPane(contentPanel);
		
		MigLayout mainContentLayout = new MigLayout(
				"", // Layout constraints
				"", // Column constraints
				""); // Row constaints
		contentPanel.setLayout(mainContentLayout);
		
		// 2.1 Main content: Devices
		// Parse the simulatorDevices table and get the JPanels
		for (SimulatorDevice key:simulatorDevices.values()) { // Foreach Device
			contentPanel.add(key, "wrap");
		}
		
		// 3. Console
		JScrollPane consoleScrollPane = new JScrollPane(statusConsole);
		JTextPaneAppender tpa = new JTextPaneAppender(
				new PatternLayout("%d{HH:mm:ss.SSS} [%-5p] %m (%c, %C)%n"),
				"jtextarea", null, statusConsole, 100);
		tpa.setFontSize(12);
		//logger.removeAppender("console");
		//logger.removeAllAppenders();
		//Enumeration enumlog = logger.getAllAppenders();
		//System.out.println(enumlog.toString());
		logger.addAppender(tpa);
		
		// 4. All put together
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainContentScrollPane, consoleScrollPane);
		splitPane.setOneTouchExpandable(false);

		// Provide minimum sizes for the two components in the split pane
		mainContentScrollPane.setMinimumSize(new Dimension(100, 50));
		consoleScrollPane.setMinimumSize(new Dimension(100, 50));
		
		splitPane.setDividerLocation(600);
		mainPanel.add(splitPane); 

	}
	
	private void BroadcastSimulatedFrames() {
		
		if (server.isAlive()) {
			broadcastSimulatedFramesThread = new Thread() {
				
				public void run() {
					while (true) {
						try {
							for (SimulatorDevice device : simulatorDevices.values()) {
								for (SimulatorDeviceItem item : device.getSimulatorDeviceItems().values()) {
									if (item.isActive()) {
//										logger.trace("Item " + item.getName() + "("+ item.getId() + ") of "
//												+ device.getName()
//												+ " sends: " + item.getSimulatedFrame());
										server.send(item.getSimulatedFrame());
									}
								}
							}
	
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			broadcastSimulatedFramesThread.start();
		}
	}
	
	@Action
	public void MasterAllOn() {
		for (SimulatorDevice key:simulatorDevices.values()) { // Foreach Device
			key.setCheckedAll(true);
		}
	}
	
	@Action
	public void MasterAllOff() {
		for (SimulatorDevice key:simulatorDevices.values()) { // Foreach Device
			key.setCheckedAll(false);
		}
	}

	@Action
	public void clearConsole() {
		statusConsole.setText("");
	}
	
	@Action
	public void startStopServer() {
		if(isConnected) {
			isConnected = !isConnected;
			jButtonStartServer.setText("Stop Server");
			if (server.isAlive()) server.interrupt(); // TODO fix avec une var volatile
		}
		else {
			isConnected = !isConnected;
			jButtonStartServer.setText("Start Server");
			if (!server.isAlive()) server.start();
			BroadcastSimulatedFrames();
		}
	}

	@Override
	protected void initialize(String[] args) {

	}

	@Override
	protected void startup() {
		logger.info("Simulator starting..");
		CreateSimulatorDeviceTable();
		BuildSimGui();
		show(mainPanel);
		
		logger.info("Ready to send..");
		//BroadcastSimulatedFrames();
		
		// Test
		//new Thread(new goConsole()).start();

		// Test Data
		//DataManager.getInstance().printDeviceTree();
	}

	@Override
	protected void shutdown() {
		logger.info("Simulator shuting down!");
		super.shutdown();
	}

}
