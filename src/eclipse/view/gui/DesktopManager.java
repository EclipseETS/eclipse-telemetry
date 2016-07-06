package eclipse.view.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.apache.log4j.Logger;
















import eclipse.controller.acqui.DataAcquisition;
import eclipse.controller.acqui.DesencapsulatorE8Serial;
import eclipse.controller.acqui.handlers.SimpleSerialHandler;
import eclipse.controller.acqui.handlers.TCPHandler;
import eclipse.controller.util.TelemetrySettings;
import eclipse.model.data.DataManager;
import eclipse.model.data.Device;
import eclipse.model.data.DeviceItem;
import eclipse.view.gui.DeviceTable;
import eclipse.view.gui.ImportantDeviceTable;
import eclipse.view.gui.tab.TabbedPannel;
import eclipse.view.gui.tab.Tabchar;
import eclipse.view.gui.tab.TabBMS;
import eclipse.view.gui.tab.TelemetryStrategie;
import eclipse.view.gui.tab.tabDetails;
import eclipse.view.gui.tab.graph.TelemetryGraphPoint;
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
	private JSplitPane rightFullPart;
	private JSplitPane all;
	private DeviceTable panTable = new DeviceTable();
	private ImportantDeviceTable panTable2 = new ImportantDeviceTable();
	private ErrorTable errTable = new ErrorTable();
	private CustomConsole con = new CustomConsole();
	private TabbedPannel tab = new TabbedPannel();
	static private DesktopManager desinstance = new DesktopManager();
	static Logger logger = Logger.getLogger("main");
	JMenuItem mnStart;
	JMenuItem mnStop;
	private static final String SETTINGS_FILE = "telemetrySettings.properties";
	private DataManager dataManager = DataManager.getInstance();

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
		
//		//Change application Look n Feel
//		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//		} catch (Exception e1) {
//			
//		}
		
		//Frame Creation
		frmclipseViii = new JFrame();
		frmclipseViii.setTitle(TelemetrySettings.getInstance().getSetting("GUI_MENU_TITLE"));
		frmclipseViii.setBounds(100, 100, 683, 575);
		frmclipseViii.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmclipseViii.setExtendedState(JFrame.MAXIMIZED_BOTH);


		frmclipseViii.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frmclipseViii.addWindowListener( new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {

		    	logger.info("X pressed, Quitting");
		    	
		    		System.exit(0);
		    	
		    }
		});
		
		Image im = null;
		try {
			im = ImageIO.read(new File("images/eclipse.png"));
		} catch (IOException e) {
			StringWriter stack = new StringWriter();
			e.printStackTrace(new PrintWriter(stack));
			Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
		}
		frmclipseViii.setIconImage(im);
	  
		defineMenus();
		
		defineLayout();
		
		// File important list		
		TelemetrySettings.getInstance().load(SETTINGS_FILE);
		
		String importantValuesRaw = TelemetrySettings.getInstance().getSetting("GUI_IMPORTANT_VALUES");
		
		if (importantValuesRaw.contains(",")) {
			String[] importantValues = importantValuesRaw.split(",");
	
			for(String pairValueRaw : importantValues) {
				String[] pairValue = pairValueRaw.split("\\.");
				Device dev = dataManager.getDeviceByID(Integer.valueOf(pairValue[0]));
				DeviceItem item = dev.getItemByID(Integer.valueOf(pairValue[1]));
				DesktopManager.getIstance().getImportantPanel().addItem(item, dev);
			}
		}
		else if (importantValuesRaw.contains(".")) {
			String pairValueRaw = importantValuesRaw;
			String[] pairValue = pairValueRaw.split("\\.");
			Device dev = dataManager.getDeviceByID(Integer.valueOf(pairValue[0]));
			DeviceItem item = dev.getItemByID(Integer.valueOf(pairValue[1]));
			DesktopManager.getIstance().getImportantPanel().addItem(item, dev);
		}
		
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
		rightFullPart.setDividerLocation(all.getHeight()+-errTable.getHeightCustom());
		all.setDividerLocation(0.78);
		
	}
    


	private void defineLayout() {

		//Set the resizeable panel (set size)
		leftPart = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, tab, con);
		rightPart = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, panTable2, panTable);
		rightFullPart = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, rightPart, errTable);
		all = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPart, rightFullPart);
				
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
		
		//Show Global View at startup
		tab.addTab(new Tabchar(),TelemetrySettings.getInstance().getSetting("GUI_MENU_PANEL_CHAR"));
		//tab.addTab(new tabDetails(),"TEST COMPARER");
		
		//This is the timer for the gui part of the application
		while(true)
		{
			//refresh value every second
			panTable.updateTable();
			panTable2.updateTable();
			tab.update();
			
			try 
			{
				Thread.sleep(1000);
			} 
			catch (Exception e) 
			{
				StringWriter stack = new StringWriter();
				e.printStackTrace(new PrintWriter(stack));
				Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
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
		
		//FILE MENU 
		JMenu mnFile = new JMenu(TelemetrySettings.getInstance().getSetting("GUI_MENU_FILE"));
		menuBar.add(mnFile);
		
		JMenuItem mntmSAve = new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_FILE_SAVE"));
		mnFile.add(mntmSAve);
		mntmSAve.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent arg0) {
	       			logger.debug("File->Load SD pressed");
	       			
	       			JFileChooser load = new JFileChooser(DesktopManager.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	       		    int returnVal = load.showOpenDialog(new JFrame());
	       		    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       		    	//DataAcquisition.getInstance().stopAcquiring();
	       		    	DataManager.getInstance().loadSD(load.getSelectedFile().getAbsolutePath());
	       		    	//DataAcquisition.getInstance().startAcquiring();
	       		    	JOptionPane.showMessageDialog(new JFrame(),"Load Finish!");
	       		    }
					
				}

	        });
		
		JMenuItem mntmLoad = new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_FILE_LOAD"));
		mnFile.add(mntmLoad);
		mntmLoad.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent arg0) {
	       			logger.debug("File->Load pressed");
	       			
	       			JFileChooser load = new JFileChooser(DesktopManager.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	       		    int returnVal = load.showOpenDialog(new JFrame());
	       		    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       		    	//DataAcquisition.getInstance().stopAcquiring();
	       		    	DataManager.getInstance().load(load.getSelectedFile().getAbsolutePath());
	       		    	//DataAcquisition.getInstance().startAcquiring();
	       		    	JOptionPane.showMessageDialog(new JFrame(),"Load Finish!");
	       		    }
					
				}

	        });
		
		JMenuItem mntmQuit = new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_FILE_EXIT"));
		mnFile.add(mntmQuit);
		mntmQuit.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
       			logger.info("File->Exit, Quitind apps");
				System.exit(0);
				
			}

        });
		

		
		//ACQUISITION
		JMenu mnAcqui = new JMenu(TelemetrySettings.getInstance().getSetting("GUI_MENU_ACQUISITION"));
		menuBar.add(mnAcqui);
		
		mnStart= new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_ACQUISITION_START"));
		mnAcqui.add(mnStart);
		mnStart.addActionListener(new ActionListener() 
		{
           public void actionPerformed(ActionEvent arg0) 
           {
				DataAcquisition.getInstance().startAcquiring();
			}

        });
		
		mnStop= new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_ACQUISITION_STOP"));
		mnAcqui.add(mnStop);
		mnStop.addActionListener(new ActionListener() 
		{
           public void actionPerformed(ActionEvent arg0) 
           {
        	   DataAcquisition.getInstance().stopAcquiring();
           }

        });
		mnStop.setEnabled(false);
		
		JMenuItem mntReleasePort = new JMenuItem("Release port");
		mnAcqui.add(mntReleasePort);
		mntReleasePort.addActionListener(new ActionListener() 
		{
           public void actionPerformed(ActionEvent arg0) 
           {
        	   DataAcquisition.getInstance().stopAcquiring();
        	   TelemetrySettings.getInstance().setSetting("HANDLER_SERIAL_PORT", "XXX");
				
				
			}
           
		});
		
		
		
		JMenuItem mntDeleteError = new JMenuItem("Clear errors");
		mnAcqui.add(mntDeleteError);
		mntDeleteError.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
       			errTable.deleteAll();
				
				
			}

        });
		
		JMenuItem mntChangeHandler = new JMenuItem();
		
		if((TelemetrySettings.getInstance().getSetting("HANDLER_TYPE")).equals("TCP_HANDLER"))
		 {
			mntChangeHandler.setText("Change Handler to SERIAL");
		 }
		 else
		 {
			 mntChangeHandler.setText("Change Handler to TCP");
		 }
		
		mnAcqui.add(mntChangeHandler);
		mntChangeHandler.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {        	   
        	 if((TelemetrySettings.getInstance().getSetting("HANDLER_TYPE")).equals("TCP_HANDLER"))
      		 {
        		 TelemetrySettings.getInstance().setSetting("HANDLER_TYPE", "SERIAL_HANDLER");
      		 }
      		 else
      		 {
      			 TelemetrySettings.getInstance().setSetting("HANDLER_TYPE", "TCP_HANDLER");
      		 }				
			}

        });
		
		
		
		//PANEL
		JMenu mnView = new JMenu(TelemetrySettings.getInstance().getSetting("GUI_MENU_PANEL"));
		menuBar.add(mnView);
		
		JMenuItem mnGlobal= new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_PANEL_CHAR"));
		mnView.add(mnGlobal);
		mnGlobal.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent arg0) {
	       			logger.debug("File->Global Pressed");
	       			JPanel charSolaire = new Tabchar();
	       			tab.addTab(charSolaire,TelemetrySettings.getInstance().getSetting("GUI_MENU_PANEL_CHAR"));
					
				}

	        });
		
		JMenuItem mnBMS= new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_PANEL_BMS"));
		mnView.add(mnBMS);
		mnBMS.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent arg0) {
	       			logger.debug("File->BMS Pressed");
	       			JPanel batteryManagementSystem = new TabBMS();
	       			tab.addTab(batteryManagementSystem,TelemetrySettings.getInstance().getSetting("GUI_MENU_PANEL_BMS"));
					
				}

	        });
		
		JMenuItem mnStart= new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_PANEL_STRATEGIE"));
		mnView.add(mnStart);
		mnStart.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent arg0) {
	       			logger.debug("File->Start Pressed");
	       			JPanel charSolaire = new TelemetryStrategie();
	       			tab.addTab(charSolaire,TelemetrySettings.getInstance().getSetting("GUI_MENU_PANEL_STRATEGIE"));
					
				}

	        });
	
		
		//GRAPH
		JMenu mnGraph = new JMenu(TelemetrySettings.getInstance().getSetting("GUI_MENU_GRAPH"));
		menuBar.add(mnGraph);
		
		JMenuItem mnGraphPoint= new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_GRAPH_POINT"));
		mnGraph.add(mnGraphPoint);
		mnGraphPoint.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent arg0) {
	       			//logger.debug("File->Global Pressed");
	       			JPanel charSolaire = new TelemetryGraphPoint();
	       			tab.addTab(charSolaire,TelemetrySettings.getInstance().getSetting("GUI_MENU_GRAPH_POINT"));
					
				}

	        });
		
		
		//HELP
		JMenu mnHelp = new JMenu(TelemetrySettings.getInstance().getSetting("GUI_MENU_HELP"));
		menuBar.add(mnHelp);
		
		JMenuItem mnCommand = new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_HELP_COMMAND"));
		mnHelp.add(mnCommand);
		mnCommand.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent arg0) {
	        	   JOptionPane.showMessageDialog(new JFrame(TelemetrySettings.getInstance().getSetting("GUI_MENU_HELP_COMMAND")), 
	        			   "Indicatif rapide des commandes: \n \n " +
	    	        			   "TABLEAU DE DONNÉE \n \n " +
	    	        			   "Pour ajouter un graphique, Choisir Item et clicker Graph \n " +
	    	        			   "Pour ajouter un item dans la liste rapide, Choisir Item et clicker keep this value  \n " +
	    	        			   "Pour retirer une valeur de la liste rapide, double clique dessu \n \n \n " +
	    	        			   "PANNEAU PRINCIPAL \n \n " +
	    	        			   "Click droit sur un onglet pour faire disparaitre l'onglet \n " +
	        			   "");
					
				}

	        });
		
		
		JMenuItem mnAbout = new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_HELP_ABOUT"));
		mnHelp.add(mnAbout);
		mnAbout.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent arg0) {
	        	   JOptionPane.showMessageDialog(new JFrame(TelemetrySettings.getInstance().getSetting("GUI_MENU_HELP_ABOUT")), 
	        			   "Eclipse Solar Car Telemetry software. All right reserved. \n" +
	        			   "Surtout ne touchez pas à Denise, C'est un bien public. Elle devrais dailleur être nationalisée!");
					
				}

	        });
	}
	
	public TabbedPannel getTabbedPannel(){
		return tab;
	}
	
	
	
	public void menuStop(){
		mnStart.setEnabled(false);
		mnStop.setEnabled(true);
	}
	
	public void menuStart(){
		mnStart.setEnabled(true);
		mnStop.setEnabled(false);
	}


	public ImportantDeviceTable getErrorPanel() {
		return errTable;
	}

}
