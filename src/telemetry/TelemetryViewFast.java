package telemetry;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import misc.log4j.JTextPaneAppender;
import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.swingx.JXStatusBar;

import com.vlsolutions.swing.docking.DockingDesktop;


import acquisition.DataAcquisition;
import acquisition.DataAcquisitionController;
import acquisition.FrameDesencapsulator;
import acquisition.handlers.TCPHandler;
import core.DataManager;
import eclipseV7.data.Device;
import eclipseV7.data.DeviceItem;
import eclipseV7.data.FrameModelV7;
import exception.DeviceItemNotFoundException;
import exception.DeviceNotFoundException;

import simulator.SimulatorDevice;
import simulator.SimulatorView;
import util.ByteManipulator;

public class TelemetryViewFast extends SingleFrameApplication {

	// Root Logger
	static Logger logger = Logger.getLogger("telemetry");
	
	// Components
	private JPanel mainPanel;
	private JSplitPane splitPane;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JXStatusBar bar;
	private JTextPane statusConsole;
	
	private DockingDesktop desktop = new DockingDesktop();
	
	// Stuff avec le stuff dessus
	JPanel contentPanel = new JPanel();
	Hashtable<Integer, Device> devices = DataManager.getInstance().getDeviceMap();
	JScrollPane mainContentScrollPane;
	
	public TelemetryViewFast() {
		
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

		ActionMap actionMap = Application.getInstance(TelemetryViewFast.class).getContext().getActionMap(TelemetryViewFast.class, this);
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
		
//		JButton jButtonMasterOn = new JButton();
//		jButtonMasterOn.setName("masterOn");
//		jButtonMasterOn.setAction(getContext().getActionMap(this).get("MasterAllOn"));
//		
//		JButton jButtonMasterOff = new JButton();
//		jButtonMasterOff.setName("masterOff");
//		jButtonMasterOff.setAction(getContext().getActionMap(this).get("MasterAllOff"));
//		
		JButton jButtonClearConsole = new JButton();
		jButtonClearConsole.setName("clearConsole");
		jButtonClearConsole.setAction(getContext().getActionMap(this).get("clearConsole"));
		
//		toolBar.add(jButtonMasterOn);
//		toolBar.add(jButtonMasterOff);
//		toolBar.addSeparator();
		toolBar.add(jButtonClearConsole);

		toolBar.setFloatable(false);
		mainPanel.add(toolBar, BorderLayout.PAGE_START);
		
		// 2. Main content
		mainContentScrollPane = new JScrollPane(contentPanel);
		
		MigLayout mainContentLayout = new MigLayout(
				"", // Layout constraints
				"", // Column constraints
				""); // Row constaints
		
		contentPanel.setLayout(mainContentLayout);
		
		refreshContentPanel();
		
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
	
	@Action
	public void clearConsole() {
		statusConsole.setText("");
	}
	
	
	@Override
	protected void initialize(String[] args) {
		
//		// Test
//		DAMtest = new Thread (new Runnable() {
//
//			@Override
//			public void run() {
//				FrameDesencapsulator fd = new FrameDesencapsulator();		
//				fd.setFrameModel(new FrameModelV7());
//				
//				//DataAcquisitionManager.getInstance().setAcquisitionHandler(new TestHandler());
//				//DataAcquisitionManager.getInstance().setAcquisitionHandler(new TestFileHandler());
//				DataAcquisitionManager.getInstance().setAcquisitionHandler(new TCPHandler());
//				DataAcquisitionManager.getInstance().setDesencapsulator(fd);
//				DataAcquisitionManager.getInstance().start();
//			}
//			
//		});
		
	}
	
	public void refreshContentPanel() {

		for (Device key : devices.values()) { // Foreach Device
			JPanel thisDeviceJPanel = new JPanel();
			thisDeviceJPanel.setBorder(new TitledBorder(key.getName() + "("
					+ key.getId() + ")")); // new jpanel /w device
			thisDeviceJPanel.setLayout(new BoxLayout(thisDeviceJPanel,
					BoxLayout.PAGE_AXIS));

			Hashtable<Integer, DeviceItem> deviceItems;
			try {
				deviceItems = DataManager.getInstance().getDevice(key.getId())
						.getDeviceItemMap();
				for (DeviceItem itemKey : deviceItems.values()) {
					JLabel name = new JLabel();
					JTextField value = new JTextField(5);

					String nameStr = itemKey.getName() + "("
							+ itemKey.getItemId() + ")";
					String valueStr = Double.toString(itemKey.getDoubleValue());

					name.setText(nameStr);
					value.setText(valueStr);

					thisDeviceJPanel.add(name);
					thisDeviceJPanel.add(value);
					
					if (itemKey.getDefinitionsBitsOn() != null) {
						
						StringBuilder sb = new StringBuilder();
						sb.append("<html>");
						Vector<String> bits  = itemKey.getDefinitionsValue();
						for (int i = 0; i<itemKey.getNumBits(); i++) {
							sb.append(bits.get(i) + "<br/>");
						}
						sb.append("</html>");
			
						name.setToolTipText(sb.toString());
						value.setToolTipText(sb.toString());
					}

				}
			} catch (DeviceNotFoundException e) {
			}

			contentPanel.add(thisDeviceJPanel);
		}
	}
	
	@Override
	protected void startup() {
		logger.info("Telemetry starting..");
		BuildSimGui();
		show(mainPanel);
	
		// Refresh GUI
		 int delay = 1000; //milliseconds
		  ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	contentPanel.removeAll();
		    	refreshContentPanel();
		    	contentPanel.validate();
		      }
		  };
		  new Timer(delay, taskPerformer).start();

		//DAMtest.start();
	}
	
	@Override
	protected void shutdown() {
		logger.info("Telemetry shuting down!");
		super.shutdown();
	}

}
