package eclipseV7.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Vector;


import util.ByteManipulator;
import util.HexString;
import util.TelemetrySettings;

public class DeviceItem extends Observable {

	// Vectors holding the bit definitions, if any, in their on/off position.
	Vector<String> definitionsBitsOff;
	Vector<String> definitionsBitsOn;
	
	// Bit representation of 'rawValue'
	//TODO _MARCO transformer en tableau de profondeur
	char[] binary;
	private int cpt=0;
	protected byte[] tmp;
	protected ArrayList<Data> rawValue;
	private boolean time =false;
	// Item attributes (for numeric data item)
	protected Integer itemId;
	protected String name;
	//protected double value;
	protected String unit;
	protected int minValue;
	protected int maxValue;
	protected double resolution;
	protected double factor;
	protected int offset;
	protected int range;
	protected int numBits;
	protected boolean signed;
	protected boolean status;
	protected int critic;
	protected boolean isFloat;
	
	protected int parentDeviceId;
	protected long lastSeen; // Last seen time
	
	// Create item with no value
	public DeviceItem(Integer itemId,
					  String name,
					  String unit,
					  int minValue,
					  int maxValue,
					  int status,
					  double resolution,
					  double factor,
					  int offset,
					  int range,
					  int numBits,
					  boolean signed,
					  boolean isFloat,
					  Vector<String> definitionsBitsOn,
					  Vector<String> definitionsBitsOff) {

		critic=status;
		this.itemId = itemId;
		this.name = name;
		this.unit = unit;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.resolution = resolution;
		this.factor = factor;
		this.offset = offset;
		this.range = range;
		this.numBits = numBits;
		this.signed = signed;
		this.isFloat=isFloat;
		this.definitionsBitsOn = definitionsBitsOn;
		this.definitionsBitsOff = definitionsBitsOff;
		
		// Set last seen time to now
		this.setLastSeenNow();
		rawValue = new ArrayList<Data>();
	}

	public double getFactor() {
		return this.factor;
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public int getMaxValue() {
		return this.maxValue;
	}

	public int getMinValue() {
		return this.minValue;
	}

	public String getName() {
		return this.name;
	}

	public int getNumBits() {
		return this.numBits;
	}

	public int getOffset() {
		return this.offset;
	}

	public int getRange() {
		return this.range;
	}

	public double getResolution() {
		return this.resolution;
	}

	public String getUnit() {
		return this.unit;
	}
	
	public boolean isSigned() {
		return this.signed;
	}
	
	public boolean isFloat() {
		return this.isFloat;
	}
	
	private void setLastSeenNow() {
		// Sets last seen to the current time (unix time)
		this.lastSeen = System.currentTimeMillis();
	}
	
	public long getLastSeenAsUnixTime() {
		return lastSeen;
	}
	
	public String getLastSeenTimeString() {
		SimpleDateFormat format =  new SimpleDateFormat("HH:mm:ss");
		Date lastSeenDate = new java.sql.Date(this.lastSeen);
		return format.format(lastSeenDate);
	}
	
	public boolean isInt() {
		// It is an integer if the resolution = 1 (no decimal) else double
		return (this.resolution == 1) ? true :  false;
	}
	
	public boolean isDouble() {
		// It is an integer if the resolution = 1 (no decimal) else double
		return (this.resolution != 1) ? true :  false;
	}
	
	public boolean isBinaryData() {
		// If the 'unit' field starts with 'bin', it's binary data
		//return this.unit.startsWith("bin");
		return false;
	}
	
	public boolean hasDefinitions() {
		return (definitionsBitsOn == null ? false : true);
	}

	public Vector<String> getDefinitionsBitsOn() {
		return this.definitionsBitsOn;
	}
	
	public Vector<String> getDefinitionsBitsOff() {
		return this.definitionsBitsOff;
	}
	
	public void setParentDeviceId(int parentDeviceId) {
		this.parentDeviceId = parentDeviceId;
	}
	
	public int getParentDeviceId() {
		return this.parentDeviceId;
	}

	// Returns a vector containing only the definition
	// strings of the status bits set to '1'
	public synchronized Vector<String> getOnBitsDefinitions() {
		if (definitionsBitsOn == null) return null;
		else if (definitionsBitsOff == null) return null;
		else {
			Vector<String> status = new Vector<String>();
			for (int i = 0; i < this.numBits; i++) {
				// TODO : null pointer exception si pas de value dans binary...
				if (binary[i] == '1') status.add(definitionsBitsOn.get(i)); 
			}
			return status;
		}
	}
	
	// Returns a vector containing the definition
	// strings of all the status bits (set to '1' or '0')
	// including the state of the bit as String
	public synchronized Vector<String> getAllBitsDefinitions() {
		if (definitionsBitsOn  == null) return null;
		else if (definitionsBitsOff == null) return null;
		else {
			Vector<String> status = new Vector<String>();
			for (int i = 0; i < this.numBits; i++) {
				// TODO : null pointer exeption si pas de value...
				if (binary[i] == '1') status.add("[1] " + definitionsBitsOn.get(i)); 
				else status.add("[0] " + definitionsBitsOff.get(i));
			}
			return status;
		}
	}

	// OLD WAY
//	public synchronized void setValue(int rawValue) { 
//		// 1. If it is a binary value
//		if (this.isBinaryData()) {
//			// 1a. If the binary is made out of status (definition) bits
//			// Create a char array from the binary of rawValue
//			if (definitionsBitsOn != null && definitionsBitsOff != null) {
//				binary = Integer.toBinaryString(rawValue).toCharArray();
//				this.value = rawValue; // Set anyway
//			}
//			// 1b. If the binary is a ratio % of the max value
//			else
//				this.value = rawValue / (java.lang.Math.pow(2,this.numBits) - 1) * 100;
//		}
//		// 2. If it is a numerical value
//		else
//			// Store the real value from the rawValue and multiplication factor
//			this.value = rawValue / this.factor;
//	}
	
	public synchronized void setValue(byte[] rawValue) { 
		//TODO Limiter l'input MinValue > Value > MaxValue
		//TODO Limiter numBits == definitionBitsOn/off.length
		tmp = new byte[rawValue.length];
		for(int i = 0; i < rawValue.length; i++){
			tmp[i] = rawValue[i];
		}
		this.rawValue.add(new Data(tmp));
		cpt++;
		// If it is a binary value, data is made out of status (definition) bits
		// Create a char array from the binary of rawValue
		
//		if (this.isBinaryData() && definitionsBitsOn != null && definitionsBitsOff != null) {
//				binary = Integer.toBinaryString(this.getIntValue()).toCharArray();
//		}
		
		setLastSeenNow();
		setChanged();
		notifyObservers();

	}
	
	public synchronized void setValue(byte[] rawValue, long time) { 
		//TODO Limiter l'input MinValue > Value > MaxValue
		//TODO Limiter numBits == definitionBitsOn/off.length
		tmp = new byte[rawValue.length];
		for(int i = 0; i < rawValue.length; i++){
			tmp[i] = rawValue[i];
		}
		this.rawValue.add(new Data(tmp,time));
		cpt++;
		// If it is a binary value, data is made out of status (definition) bits
		// Create a char array from the binary of rawValue
		
//		if (this.isBinaryData() && definitionsBitsOn != null && definitionsBitsOff != null) {
//				binary = Integer.toBinaryString(this.getIntValue()).toCharArray();
//		}
		
		setLastSeenNow();
		setChanged();
		notifyObservers();

	}
	
	
	public synchronized double getIntValue() { // if resolution = 1 (no decimal)
		double retVal;
		
		try {
			retVal = ByteManipulator.byteArrayToInt(rawValue.get(cpt-1).getData(), 0, this.numBits/8, this.isSigned(), this.isFloat);
		} catch (Exception e) {
			retVal = 0;
		}
		
		return (retVal*resolution)/factor+offset;
	}
	
	public synchronized double getDoubleValue() {
		double retVal = this.getIntValue();
		
		return retVal;
	}
	
	public int status(){
		if (getDoubleValue()<=minValue||getDoubleValue()>=maxValue){
			return critic;
		}
		else if (lastSeen<=(System.currentTimeMillis()-Double.parseDouble(TelemetrySettings.getInstance().getSetting("DELAIS")))){
			checkTime();
			return critic;
			}
		else{
			time=false;
			return 0;}
		
	}
	
	private void checkTime(){
		if (!time){
			time=true;
			setChanged();
			notifyObservers();
		}
			
			
			
	}
	
	public void setStatus(int status){
		critic=status;
	}
	
	public synchronized String getHexStringValue() {
		return HexString.bufferToHex(rawValue.get(cpt-1).getData());
	}
	
	public double[][] getAllData(){
		double[][] infos = new double[rawValue.size()][2];
		
		for(int i=0;i<infos.length;i++)
		{
			infos[i][0]=Double.parseDouble(rawValue.get(i).getDate().toString());
			
			
			
			double retVal;
			
			try {
				retVal = ByteManipulator.byteArrayToInt(rawValue.get(i).getData(), 0, this.numBits/8, this.isSigned(), this.isFloat);
			} catch (Exception e) {
				retVal = 0;
			}
			double tmp = retVal*resolution/factor+offset;
						
			infos[i][1]=tmp;
		}
		
		return infos;
		
	}
	
	public String[] serialize(){
		String[] out = new String[rawValue.size()];
		int i=0;
		for (Data cur : rawValue) {
//			try {
//				dbd = ByteManipulator.byteArrayToInt(cur.getData(), 0, this.numBits/8, this.isSigned(), this.isFloat);
//			} catch (ArrayIndexOutOfBoundsException e) {
//				// 
//				e.printStackTrace();
//			} catch (Exception e) {
//				// 
//				e.printStackTrace();
//			}
			out[i]=parentDeviceId+";"+itemId+";"+cur.getDate()+";"+HexString.bufferToHex(cur.getData());
			i++;
		}
		return out;
		
	}
	
//	public synchronized Vector<String> getDefinitionsValue() {
//		//TODO exeption si valeur binaire et si resolution est un entier??
//		
//		if (definitionsBitsOn  == null) return null;
//		else if (definitionsBitsOff == null) return null;
//		else {
//			Vector<String> status = new VeByteManipulator.byteArrByteManipulator.byteArrayToInt(rByteManipulator.byteArrayToInByteManipulator.byteArrayToIntByteManipulator.byteArrayToIntByteManipulator.byteArrayToIntByteManipulator.byteArrayToInt(rawValue.get(cpt-1).getData(), 0, this.numBits/8, this.isSigned(), this.isFloat);(rawValue.get(cpt-1).getData(), 0, this.numBits/8, this.isSigned(), this.isFloat);(rawValue.get(cpt-1).getData(), 0, this.numBits/8, this.isSigned(), this.isFloat);(rawValue.get(cpt-1).getData(), 0, this.numBits/8, this.isSigned(), this.isFloat);t(rawValue.get(cpt-1).getData(), 0, this.numBits/8, this.isSigned(), this.isFloat);awValue.get(cpt-1).getData(), 0, this.numBits/8, this.isSigned(), this.isFloat);ayToInt(rawValue.get(cpt-1).getData(), 0, this.numBits/8, this.isSigned(), this.isFloat);ctor<String>();
//			double value = this.getIntValue();
//			for (int i = 0; i < this.numBits; i++) {
//				// TODO : null pointer exeption si pas de value...
//				if ((value & (1<<i)) != 0) {
//					status.add("[1] " + definitionsBitsOn.get(i)); 
//				}
//				else {
//					status.add("[0] " + definitionsBitsOff.get(i));
//				}
//			}
//			return status;
//		}
//		
//	}

}