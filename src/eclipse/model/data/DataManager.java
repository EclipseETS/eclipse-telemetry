package eclipse.model.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

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
public class DataManager {
	
	private static DataManager dataMgr = new DataManager();
	private List<Device> devices;
	private Map<Integer, Trame> trames;
	private int cpt=0;
	
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
		
		//Load temporary Datamanager
		DataManager datatmp = new DataManager();
		XStream xs = new XStream(new DomDriver());

        try {
            FileInputStream fis = new FileInputStream(location);
            xs.fromXML(fis, datatmp);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        for(Device devNouveau : datatmp.getDevices()){
        	Device devOriginal = dataMgr.getDeviceByID(devNouveau.getDeviceId());
        	if(devOriginal==null)
        		dataMgr.addDevice(devNouveau);
        	else
	        	for (DeviceItem itemNouveau : devNouveau.getItems()){
	        		DeviceItem itemOriginal = devOriginal.getItemByID(itemNouveau.getItemId());
	        		if(itemOriginal==null)
	        			devOriginal.addItem(itemNouveau);
	        		else
	        			itemOriginal.getAllData().addAll(itemNouveau.getAllData());     		
	        			
	        	}
        }
			
         	
        
		
	}
	
	/**
	 * Save curent value to XML format
	 */
	public void save(String location){

		XStream xs = new XStream(new DomDriver());

	        try {
	            FileOutputStream fs = new FileOutputStream(location);
	            xs.omitField(Observable.class, "obs"); 
	            xs.toXML(dataMgr,fs);
	        } catch (FileNotFoundException e1) {
	            e1.printStackTrace();
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

	

}
