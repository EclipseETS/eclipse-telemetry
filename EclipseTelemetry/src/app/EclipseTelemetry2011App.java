package app;

import java.io.FileNotFoundException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdesktop.application.Application;


import acquisition.DataAcquisitionController;
import acquisition.FrameDesencapsulator;
import acquisition.handlers.CANUSBHandler;
import acquisition.handlers.NullObject;
import acquisition.handlers.SerialHandler;

import simulator.SimulatorView;
import telemetry.TelemetryView;
import util.ArgumentParser;
import util.TelemetrySettings;
import core.DataManager;
import core.ProtocolLoader;
import core.ProtocolValidator;
import eclipseV7.data.FrameModelCANUSB;
import eclipseV7.data.FrameModelV7;
import eclipseV7.xml.ProtocolLoaderV7;
import eclipseV7.xml.ProtocolValidatorV7;

public class EclipseTelemetry2011App {
	
	// RessourceMap (Swing App Framework)
	//ResourceMap resource;
	
	// Settings Properties File
	private static final String SETTINGS_FILE = "telemetrySettings.properties";
	
	// Root Logger
	static Logger logger = Logger.getLogger("main");
	
	// Mode select constants
	private final static int SIMULATOR = 1;
	private final static int TELEMETRY = 2;
	private final static int CANUSB = 3;
	private final static int OFFLINE = 4;
	
	// DataAcquisitionManager
	
	public static void main(String[] args) {
		
		// Parse arguments
		ArgumentParser ap = new ArgumentParser(args);
		int mode = 0;
		if (ap.hasOption("sim") || ap.hasOption("simulator")) mode = SIMULATOR;
		else if (ap.hasOption("USBCAN")) mode = CANUSB;
		else if (ap.hasOption("OFFLINE")) mode = OFFLINE;
		else mode = TELEMETRY;
		// Workaround to call non-static doInit() for clarity.
		EclipseTelemetry2011App app = new EclipseTelemetry2011App();
		app.doInit(mode);

		logger.info("App starting up!");
		
		// Start mode
		switch (mode) {
			case SIMULATOR: Application.launch(SimulatorView.class, args); break;
			case TELEMETRY: Application.launch(TelemetryView.class, args); break;
			case OFFLINE: Application.launch(TelemetryView.class, args); break;
			case CANUSB: Application.launch(TelemetryView.class, args); break;
			//case TELEMETRY: Application.launch(TelemetryViewFast.class, args); break;
			default: logger.error("Bad mode select"); app.abort();
		}
		
		
	}
	
	public void doInit(int mode) {
		// Init loggers
		PropertyConfigurator.configure("log4j.configuration");
		
		logger.info("App initializing..");
		
		// Init RessourceManager and load
        //ApplicationContext ctxt = getContext();
        //ResourceManager mgr = ctxt.getResourceManager();
        //resource = mgr.getResourceMap(DataPoolApp.class);
		
		// Load global settings
		logger.info("Loading global telemetry settings..");
		TelemetrySettings.getInstance().load(SETTINGS_FILE);

		// Load needed settings for init
		String protocolSchema = TelemetrySettings.getInstance().getSetting("XML_PROTOCOL_SCHEMA");
		String protocolXML = TelemetrySettings.getInstance().getSetting("XML_PROTOCOL_FILE");

		// Create ProtocolValidator using ProtocolValidatorV7
		ProtocolValidator protocolValidator;
		
		// Making sure nothing goes wrong in the init.
		try {
			protocolValidator = new ProtocolValidatorV7(protocolXML, protocolSchema);
			
			// Validate and load protocol into data structure using ProtocolLoaderV7
			if (protocolValidator.xmlIsValid()) {
				ProtocolLoader protocolLoader = new ProtocolLoaderV7(protocolXML);
				protocolLoader.load();
			}
			else {
				logger.fatal("Initialization failure. XML validation eror. Exiting.");
				abort();
			}
			
		} catch (NullPointerException e) {
			logger.fatal("Initialization failure. " + e.getMessage() + ". Exiting.");
			 abort();
		} catch (FileNotFoundException e) {
			logger.fatal("Initialization failure. " + e.getMessage() + ". Exiting.");
			 abort();
		}
		
		// When data init is done, set parent DeviceId to all DeviceItems
		DataManager.getInstance().setParentDeviceIDs();
		
		// Initialize Data Acquisition Manager
		FrameDesencapsulator fd = new FrameDesencapsulator();
			
		switch (mode) {
		case SIMULATOR: 		
			fd.setFrameModel(new FrameModelV7());
			DataAcquisitionController.getInstance().setAcquisitionHandler(new NullObject());
			break;
		case TELEMETRY: 		
			fd.setFrameModel(new FrameModelV7());
			DataAcquisitionController.getInstance().setAcquisitionHandler(new SerialHandler()); 
			break;
		case OFFLINE: 		
			fd.setFrameModel(new FrameModelV7());
			DataAcquisitionController.getInstance().setAcquisitionHandler(new NullObject()); 
			break;
		case CANUSB: 		
			fd.setFrameModel(new FrameModelCANUSB());
			DataAcquisitionController.getInstance().setAcquisitionHandler(new CANUSBHandler()); 
			break;
		}
		
		//DataAcquisitionController.getInstance().setAcquisitionHandler(new SerialHandler());
		DataAcquisitionController.getInstance().setDesencapsulator(fd);
		// Aussi event processor
		DataAcquisitionController.getInstance().start();
		
		//DataManager.getInstance().printDeviceTree();
		if(mode!=SIMULATOR){
			String delais =(TelemetrySettings.getInstance().getSetting("MATLABTIME"));
			ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
			exec.scheduleAtFixedRate(new Runnable() {
			  @Override
			  public void run() {
				  try {
					DataManager.getInstance().matLabExport();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			}, 0, Integer.parseInt(delais), TimeUnit.SECONDS);
		}
	}
	
	protected void abort() {
		logger.fatal("App abort.");
		System.exit(1);
	}

	
//	private void insertTestData() {
////		DataManager.getInstance().addDevice(1, "drive");
////		DataManager.getInstance().addDevice(2, "bat");
////		DataManager.getInstance().addDevice(3, "mppt1");
//		
//		Vector<String> def = new Vector<String>();
//		def.add("Pile 1 tension trop basse");
//		def.add("Pile 1 tension trop basse");
//		def.add("Pile 1 tension trop basse");
//		def.add("Pile 2 tension trop basse");
//		def.add("Pile 3 tension trop basse");
//		def.add("Pile 4 tension trop basse");
//		
//		
////		DataManager.getInstance().addDeviceItem(1, new DeviceItem(1, "RPM", "RPM", -100, 100, 0.05, 5, 34, 1000, 8,false, def));
////		DataManager.getInstance().addDeviceItem(1, new DeviceItem(1, "Split", "m", -100, 100, 0.05, 5, 34, 1000, 8,false, def));
////		DataManager.getInstance().addDeviceItem(1, new DeviceItem(2, "Clash", "m", -100, 100, 0.05, 5, 34, 1000, 8,false, null));
////		DataManager.getInstance().addDeviceItem(1, new DeviceItem(3, "Sangre", "m", -100, 100, 0.05, 5, 34, 1000, 8,false, null));
//	
//		
//		XStream stream = new XStream(new DomDriver());
//		
//		stream.registerConverter(new DeviceConverter());
//		stream.registerConverter(new DeviceItemConverter());
//		
//		stream.alias("devices", Device.class);
//		stream.alias("deviceItem", DeviceItem.class);
//		
//		stream.alias("unit", String.class);
//		stream.alias("minvalue", Integer.class);
//		stream.alias("maxvalue", Integer.class);
//		stream.alias("resolution", Integer.class);
//		stream.alias("factor", Integer.class);
//		stream.alias("offset", Integer.class);
//		stream.alias("range", Integer.class);
//		stream.alias("numbits", Integer.class);
//		stream.alias("signed", Boolean.class);
//		
//		stream.alias("definitions", Vector.class);
//		stream.alias("definition", String.class);
//		
//		try {
//			stream.fromXML(new FileInputStream("ProtocolV7.xml"));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	
//		//System.out.println(stream.toXML(DataManager.getInstance().getTelemetryStorage()));
//		
//		DataManager.getInstance().printDeviceTree();
//	}

}
