package eclipse.model.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eclipse.controller.util.ByteManipulator;
import eclipse.controller.util.Rounding;

/**
 * Main point of entry for all the model of the application
 * 
 * This class is a singleton 
 * 
 * Give access to DEvice and Item, Save, Load etc
 * 
 * @author Eclipse
 *
 */
public class DataManager implements Serializable{
	
	private static final long serialVersionUID = 3504102344466093796L;
	private static DataManager dataMgr = new DataManager();
	private List<Device> devices;
	private Map<Integer, Trame> trames;
	private int cpt=0;
	private FileOutputStream fstream;
	private Date dNow = new Date( );
	private SimpleDateFormat ft = new SimpleDateFormat ("yyyy_MM_dd_hh_mm_ss");

	private String filename = "log/Telemetry_"+ft.format(dNow)+".e8";
	
	
	//Private instance of data manager
	private DataManager(){
		devices = Collections.synchronizedList(new ArrayList<Device>());
		trames = Collections.synchronizedMap(new HashMap<Integer, Trame>());
	}	
	
	/**
	 * Singleton that provide datamrg for the hole application
	 * @return the only and single instance of the DataMgr
	 */
	public static DataManager getInstance(){
		return dataMgr;
	}

	 /** Add device in the mgr
	 * @param devices device to be added
	 */
	public void addDevice(Device device) {
		devices.add(device);
	}
	
	/**
	 * Return first device with name
	 * @param device name that we search
	 * @return
	 */
	public Device getDeviceByName(String deviceName) {
		for (Device iDev : devices)
			if (iDev.getDeviceName().toUpperCase().compareTo(deviceName.toUpperCase())==0)
				return iDev;
		return null;
	}
	
	/**
	 * Return device by ID
	 * @param deviceId
	 * @return
	 */
	public Device getDeviceByID (Integer itemId) {
		for (Device iDev : devices)
			if (iDev.getDeviceId().equals(itemId))
				return iDev;
		return null;
	}
	
	/**
	 * Return the tram corresponding to this particular Identifier
	 * @param Identifier link to the trame (from the can frame)
	 * @return
	 */
	public Trame getTrame(int Identifier){
		return trames.get(Identifier);
	}

	/**
	 * Load value from old files 
	 * 
	 * Copy all information, device, item and merge data
	 * 
	 */
	public void load(String location){
		
		File file = new File(location);
		byte[] arrydebites = new byte[20];
		try {
			@SuppressWarnings("resource")
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			for(int plc=0;plc<raf.length()/20;plc++){
			raf.read(arrydebites, 0, 20);
			//GET ID
			byte[] idB = Arrays.copyOfRange(arrydebites,8,12);
			int id = 0;
			try {
				//Convert ID in INT
				id = (int) ByteManipulator.byteArrayToInt(idB, 0, 4, false, false);
			} catch (Exception e) {
				StringWriter stack = new StringWriter();
				e.printStackTrace(new PrintWriter(stack));
				Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
			}
			
				//Get trame
				Trame t = DataManager.getInstance().getTrame(id);
				
				if(t!=null)
				{
					//trame exist
					
					long datte =eclipse.controller.util.ByteManipulator.bytesToLong(Arrays.copyOfRange(arrydebites,0,8));
					
					
					List<DeviceItem> items = t.getItems();
					int i =12;
					for(DeviceItem itm : items)
					{
						double value;
						try {
							//Get data from the right part of the array
							value = ByteManipulator.byteArrayToInt(Arrays.copyOfRange(arrydebites,i,i+(itm.getBitSize()/8)),
									0, (itm.getBitSize()/8), itm.isSigned(), itm.isFloat());
							i+=itm.getBitSize()/8;
							//Set the new value
							itm.addOldDATA(value,datte);
							
							// copy tram in save file
						} catch (Exception e) {
							StringWriter stack = new StringWriter();
							e.printStackTrace(new PrintWriter(stack));
							Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
						}
					}					
				}		
			}
						
		} catch (Exception e) {
			StringWriter stack = new StringWriter();
			e.printStackTrace(new PrintWriter(stack));
			Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
		}
	}
	
	
	/**
	 * Load value from old files from the SD card
	 * 
	 * Copy all information, device, item and merge data
	 * 
	 */
	public void loadSD(String location){
		Date now = new Date();
		File file = new File(location);
		byte[] arrydebites = new byte[16];
		try {
			@SuppressWarnings("resource")
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			for(int plc=0;plc<raf.length()/16;plc++){
			raf.read(arrydebites, 0, 16);
			//GET ID
			byte[] idB = Arrays.copyOfRange(arrydebites,4,8);
			int id = 0;
			try {
				//Convert ID in INT
				id = (int) ByteManipulator.byteArrayToInt(idB, 0, 4, false, false);
			} catch (Exception e) {
				StringWriter stack = new StringWriter();
				e.printStackTrace(new PrintWriter(stack));
				Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
			}
			
				//Get trame
				Trame t = DataManager.getInstance().getTrame(id);
				
				if(t!=null)
				{
					//trame exist
					int date = (int) ByteManipulator.byteArrayToInt(arrydebites, 0, 4, false, false);
					
					
					
					List<DeviceItem> items = t.getItems();
					int i =8;
					for(DeviceItem itm : items)
					{
						double value;
						try {
							//Get data from the right part of the array
							value = ByteManipulator.byteArrayToInt(Arrays.copyOfRange(arrydebites,i,i+(itm.getBitSize()/8)),
									0, (itm.getBitSize()/8), itm.isSigned(), itm.isFloat());
							i+=itm.getBitSize()/8;
							//Set the new value
							itm.addOldDATA(value,date+now.getTime());
							
							// copy tram in save file
						} catch (Exception e) {
							StringWriter stack = new StringWriter();
							e.printStackTrace(new PrintWriter(stack));
							Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
						}
					}					
				}		
			}
						
		} catch (Exception e) {
			StringWriter stack = new StringWriter();
			e.printStackTrace(new PrintWriter(stack));
			Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
		}
	}
	
	public void save(byte[] idB, byte[] byteArray){

		try {
			fstream = new FileOutputStream(filename,true);
			fstream.write(ByteBuffer.allocate(8).putLong(System.currentTimeMillis()).array());
			fstream.write(idB);
			fstream.write(byteArray,6,8);
			fstream.close();
			} catch (Exception e) {
				StringWriter stack = new StringWriter();
				e.printStackTrace(new PrintWriter(stack));
				Logger.getLogger("main").error("Caught exception; decorating with appropriate status template : " + stack.toString());
			}
	
	 }
	
		
	//GETTER AND SETTER
	public List<Device> getDevices() {
		return devices;
	}
	
	public Map<Integer, Trame> getTrames(){
		return trames;
	}

	//cpt is used to know how many item there is
	public int getCpt() {
		return cpt;
	}

	public void setCpt(int cpt) {
		this.cpt = cpt;
	}
	
	public void addCpt(){
		cpt++;
	}
	
	//Helper functions for displaying values
	public String getValue(int deviceID, int itemID) {
		
		return getDeviceByID(deviceID).getItemByID(itemID).getLastData() + " " + getDeviceByID(deviceID).getItemByID(itemID).getUnit();
	}
	
	public String getRoundedValue(int deviceID, int itemID) {
		
		return String.valueOf(Rounding.roundDouble(getDeviceByID(deviceID).getItemByID(itemID).getLastData(), 3)) + " " + getDeviceByID(deviceID).getItemByID(itemID).getUnit();
	}
	public double getRawValue(int deviceID, int itemID) {
		
		return getDeviceByID(deviceID).getItemByID(itemID).getLastData();
	}
	


	

}
