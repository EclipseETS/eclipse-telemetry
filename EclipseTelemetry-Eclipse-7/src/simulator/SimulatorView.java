package simulator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

import misc.log4j.JTextPaneAppender;
import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;

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
	//private JMenuBar menuBar;
	private JToolBar toolBar;
	private JTextPane statusConsole;
	
	// Server
	private boolean isConnected = false;
	private SimulatorServer server = new SimulatorServer();
	JButton jButtonStartServer;
	JButton jButtonStopServer;
	
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
		toolBar = new JToolBar();
		statusConsole = new JTextPane();
		
		// 1. ToolBar
		mainPanel.setLayout(new BorderLayout());
		
		JButton jButtonMasterOn = new JButton();
		jButtonMasterOn.setName("masterOn");
		jButtonMasterOn.setAction(getContext().getActionMap(this).get("masterAllOn"));
		
		JButton jButtonMasterOff = new JButton();
		jButtonMasterOff.setName("masterOff");
		jButtonMasterOff.setAction(getContext().getActionMap(this).get("masterAllOff"));
		
		JButton jButtonInputGenerator = new JButton();
		jButtonInputGenerator.setName("inputGenerator");
		jButtonInputGenerator.setAction(getContext().getActionMap(this).get("inputGenerator"));
		
		JButton jButtonClearInputs = new JButton();
		jButtonClearInputs.setName("clearInputs");
		jButtonClearInputs.setAction(getContext().getActionMap(this).get("clearInputs"));
		
		JButton jButtonClearConsole = new JButton();
		jButtonClearConsole.setName("clearConsole");
		jButtonClearConsole.setAction(getContext().getActionMap(this).get("clearConsole"));
		
		jButtonStartServer = new JButton();
		jButtonStartServer.setName("startServer");
		jButtonStartServer.setAction(getContext().getActionMap(this).get("startServer"));
		
		jButtonStopServer = new JButton();
		jButtonStopServer.setName("stopServer");
		jButtonStopServer.setAction(getContext().getActionMap(this).get("stopServer"));
		jButtonStopServer.setEnabled(false);
		
		toolBar.add(jButtonMasterOn);
		toolBar.add(jButtonMasterOff);
		
		toolBar.addSeparator();
		
		toolBar.add(jButtonInputGenerator);
		toolBar.add(jButtonClearInputs);
		
		toolBar.addSeparator();
		
		toolBar.add(jButtonClearConsole);
		toolBar.addSeparator();
		toolBar.add(jButtonStartServer);
		toolBar.add(jButtonStopServer);
		
		toolBar.setFloatable(false);
		mainPanel.add(toolBar, BorderLayout.PAGE_START);
		
		// 2. Main content
		
        JPanel[] PanelArray = new JPanel[DataManager.getInstance().getDeviceMap().size()];
        
        for(int i=0; i<PanelArray.length;i++){
        	PanelArray[i]= new JPanel();
        }
        
		JPanel contentPanel = new JPanel();
		JTabbedPane jtp = new JTabbedPane();
		
		JScrollPane mainContentScrollPane = new JScrollPane(contentPanel);
		
		MigLayout[] MigLayoutArray = new MigLayout[DataManager.getInstance().getDeviceMap().size()];
		
		//Create an array of MigLayout
		for(int i=0; i<MigLayoutArray.length;i++){
        	MigLayoutArray[i]= new MigLayout(
    				"", // Layout constraints
    				"", // Column constraints
    				""); // Row constaints
        }
		
		//Assign MigLayout to each tab
		for(int i =0; PanelArray.length > i; i++){
			PanelArray[i].setLayout(MigLayoutArray[i]);
		}
		
		contentPanel.setLayout(new BorderLayout());
		
		
		// 2.1 Main content: Devices
		// Parse the simulatorDevices table and get the JPanels
		
		int n = 0;
		for (SimulatorDevice key:simulatorDevices.values()) { // Foreach Device
			
        	PanelArray[n].add(key, "wrap");
        	jtp.addTab(key.getName(),PanelArray[n]);
			n++;
		}
        contentPanel.add(jtp,BorderLayout.CENTER);
		
		// 3. Console
		JScrollPane consoleScrollPane = new JScrollPane(statusConsole);
		JTextPaneAppender tpa = new JTextPaneAppender(
				new PatternLayout("%d{HH:mm:ss.SSS} [%-5p] %m (%c, %C)%n"),
				"jtextarea", null, statusConsole, 100);
		tpa.setFontSize(12);
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
	
	//Broadcast simulated values of each item
	private void BroadcastSimulatedFrames() {
		
		if (server.isAlive()) {
			broadcastSimulatedFramesThread = new Thread() {
				
				public void run() {
					
					while (true) {
						try {
							for (SimulatorDevice device : simulatorDevices.values()) {
								for (SimulatorDeviceItem item : device.getSimulatorDeviceItems().values()) {
									if (item.isActive()) 
										server.send(item.getSimulatedFrame());
							    }
						    }
	
							Thread.sleep(1000);
						} 
						
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			
			broadcastSimulatedFramesThread.start();
		}
	}
	
	//Master All On button function
	@Action
	public void masterAllOn() {
		for (SimulatorDevice key:simulatorDevices.values()) { // Foreach Device
			key.setCheckedAll(true);
		}
	}
	
	//Input All Fields button function
	@Action
	public void inputGenerator(){
		for (SimulatorDevice key:simulatorDevices.values())  // Foreach Device
			key.setRandomInputValues();
	}
	
	// Clear All Inputs button function
	@Action
	public void clearInputs(){
		
		for (SimulatorDevice key:simulatorDevices.values())  // Foreach Device
			key.clearInputs();
	}
	
	// Master All Off button function
	@Action
	public void masterAllOff() {
		for (SimulatorDevice key:simulatorDevices.values()) { // Foreach Device
			key.setCheckedAll(false);
		}
	}
	
	// Clear Console button function
	@Action
	public void clearConsole() {
		statusConsole.setText("");
	}
	
	// Start Server button function
	@Action
	public void startServer() {
		if(isConnected == false){
			isConnected = true;
			
			jButtonStartServer.setEnabled(false);
			jButtonStopServer.setEnabled(true);
			
			if (!server.isAlive()) 
				server.start();
			
			BroadcastSimulatedFrames();
		}
	}
	
	// Stop Server button function
	@Action
	public void stopServer() {
		
		if(isConnected) {
			
			isConnected = false;
			
			jButtonStartServer.setEnabled(true);
			jButtonStopServer.setEnabled(false);
			
			if (server.isAlive()) 
				server.interrupt(); // TODO fix avec une var volatile
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
	}

	@Override
	protected void shutdown() {
		logger.info("Simulator shuting down!");
		super.shutdown();
	}

}