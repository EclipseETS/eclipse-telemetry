package eclipse.view.gui;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;


import eclipse.controller.util.TelemetrySettings;
import eclipse.model.data.DataManager;
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
	static Logger logger = Logger.getLogger("main");

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
		
		Image im = null;
		try {
			im = ImageIO.read(new File("eclipse.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frmclipseViii.setIconImage(im);
	  
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
		
		//FILE MENU 
		JMenu mnFile = new JMenu(TelemetrySettings.getInstance().getSetting("GUI_MENU_FILE"));
		menuBar.add(mnFile);
		
		JMenuItem mntmSAve = new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_FILE_SAVE"));
		mnFile.add(mntmSAve);
		mntmSAve.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent arg0) {
	       			logger.debug("File->Saved Pressed");
	       			DataManager.getInstance().save();
					
				}

	        });
		
		JMenuItem mntmLoad = new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_FILE_LOAD"));
		mnFile.add(mntmLoad);
		mntmLoad.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent arg0) {
	       			logger.debug("File->Load pressed");
	       			DataManager.getInstance().load();
					
				}

	        });
		
		JMenuItem mntmQuit = new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_FILE_EXIT"));
		mnFile.add(mntmQuit);
		mntmQuit.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
       			logger.debug("File->Exit, Quitind apps");
				System.exit(0);
				
			}

        });
		
		
		
		//PANEL
		JMenu mnView = new JMenu(TelemetrySettings.getInstance().getSetting("GUI_MENU_PANEL"));
		menuBar.add(mnView);
		
		
		//GRAPH
		JMenu mnGraph = new JMenu(TelemetrySettings.getInstance().getSetting("GUI_MENU_GRAPH"));
		menuBar.add(mnGraph);
		
		
		//HELP
		JMenu mnHelp = new JMenu(TelemetrySettings.getInstance().getSetting("GUI_MENU_HELP"));
		menuBar.add(mnHelp);
		
		JMenuItem mnCommand = new JMenuItem(TelemetrySettings.getInstance().getSetting("GUI_MENU_HELP_COMMAND"));
		mnHelp.add(mnCommand);
		mnCommand.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent arg0) {
	        	   JOptionPane.showMessageDialog(new JFrame(TelemetrySettings.getInstance().getSetting("GUI_MENU_HELP_COMMAND")), 
	        			   "Indicatif rapide des commandes: \n \n " +
	    	        			   "TABLEAU DE DONN�E \n \n " +
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
	        			   "Surtout ne touchez pas � Denise, C'est un bien public. Elle devrais dailleur �tre nationalis�e!");
					
				}

	        });
	}
	
	public TabbedPannel getTabbedPannel(){
		return tab;
	}

}
