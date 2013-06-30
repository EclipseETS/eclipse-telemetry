package eclipse.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;


/**
 * Class that represent a physical Item and offer current and past values.
 * @author Marco
 *
 */
public class DeviceItem extends Observable implements Serializable {
	
	private static final long serialVersionUID = 8400429229079413845L;

	//Data them self
	protected List<Data> values;
	
	//Human Info
	protected Integer itemId;
	protected String name;
	
	//Data Properties
	protected String unit;
	protected int bitSize;
	protected double minValue;
	protected double maxValue;
	protected double resolution;
	protected double factor;
	protected int offset;
	protected boolean signed;
	protected boolean isFloat;
	protected boolean erreur;
	
	//Complete constructor
	public DeviceItem(Integer itemId, String name,
			String unit,int bitSize, double minValue2, double maxValue2, double resolution,
			double factor, int offset, boolean signed, boolean isFloat) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.unit = unit;
		this.bitSize = bitSize;
		this.minValue = minValue2;
		this.maxValue = maxValue2;
		this.resolution = resolution;
		this.factor = factor;
		this.offset = offset;
		this.signed = signed;
		this.isFloat = isFloat;

		values = Collections.synchronizedList(new ArrayList<Data>());
		DataManager.getInstance().addCpt();
		setValue(0.0);
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
	public double getMinValue() {
		return minValue;
	}
	public double getMaxValue() {
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
	public boolean isSigned() {
		return signed;
	}
	public boolean isFloat() {
		return isFloat;
	}
	
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
		return values.get(values.size()-1).getData();		
	}
	
	/**
	 * Return Last Seen value. in other words last update date... 
	 * @return Long: Timestamp
	 */
	public long getLastSeen(){
		return values.get(values.size()-1).getDate();		
	}
	
	/**
	 * Add new value to the system  whit curent timestamp
	 * @param value value as double to be added to the table
	 */
	public void setValue(Double value){
		value=value*resolution/factor+offset;
		values.add(new Data(value));
		if(value<minValue||value>maxValue)
			erreur=true;
		else
			erreur=false;
		setChanged();
		notifyObservers();
	}
	
	public Boolean getError(){
		return erreur;
	}
}
