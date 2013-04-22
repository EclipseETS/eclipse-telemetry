package eclipse.model.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;


/**
 * Class that represent a physical Item and offer current and past values.
 * @author Marco
 *
 */
public class DeviceItem extends Observable {
	
	//Data them self
	protected List<Data> values;
	
	//Human Info
	protected Integer itemId;
	protected String name;
	
	//Data Properties
	protected String unit;
	protected int bitSize;
	protected int minValue;
	protected int maxValue;
	protected double resolution;
	protected double factor;
	protected int offset;
	protected int numBits;
	protected boolean signed;
	protected boolean isFloat;
	
	//Complete constructor
	public DeviceItem(Integer itemId, String name,
			String unit,int bitSize, int minValue, int maxValue, double resolution,
			double factor, int offset, int numBits, boolean signed, boolean isFloat) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.unit = unit;
		this.bitSize = bitSize;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.resolution = resolution;
		this.factor = factor;
		this.offset = offset;
		this.numBits = numBits;
		this.signed = signed;
		this.isFloat = isFloat;

		values = Collections.synchronizedList(new ArrayList<Data>());
		DataManager.getInstance().addCpt();
	}
	
	
	//Setter and getter
	public Integer getItemId() {
		return itemId;
	}
	public String getName() {
		return name;
	}
	public String getUnit() {
		return unit;
	}
	public int getBitSize() {
		return bitSize;
	}
	public int getMinValue() {
		return minValue;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public double getResolution() {
		return resolution;
	}
	public double getFactor() {
		return factor;
	}
	public int getOffset() {
		return offset;
	}
	public int getNumBits() {
		return numBits;
	}
	public boolean isSigned() {
		return signed;
	}
	public boolean isFloat() {
		return isFloat;
	}
	
	//TODO: For the 2 other cat. be sure to verify for Null option
	/**
	 * Return all data from application startup
	 * @return table of data [long timestamp, Double value]
	 */
	public List<Data> getAllData() {
		return values;
	}
	
	/**
	 * Return the last value 
	 * @return Last value in double.
	 */
	public double getLastData(){
		return values.get(values.size()).getData();		
	}
	
	/**
	 * Return Last Seen value. in other words last update date... 
	 * @return Long: Timestamp
	 */
	public long getLastSeen(){
		return values.get(values.size()).getDate();		
	}
	
	/**
	 * Load value from old files 
	 */
	public void load(){
		
	}
	
	/**
	 * Save curent value to XYZ format
	 */
	public void save(){
		
	}
	
	/**
	 * Add new value to the system  whit curent timestamp
	 * @param value value as double to be added to the table
	 */
	public void setValue(Double value){
		values.add(new Data(value));
	}
	
}
