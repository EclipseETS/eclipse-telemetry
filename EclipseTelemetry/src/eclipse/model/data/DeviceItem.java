package eclipse.model.data;

import java.util.ArrayList;


public class DeviceItem {


	
	//Data them self
	protected ArrayList<Data> values;
	
	//Human Info
	protected Integer itemId;
	protected String name;
	
	//Data Properties
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
	
	
	public ArrayList<Data> getValues() {
		return values;
	}
	public void setValues(ArrayList<Data> values) {
		this.values = values;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	public double getResolution() {
		return resolution;
	}
	public void setResolution(double resolution) {
		this.resolution = resolution;
	}
	public double getFactor() {
		return factor;
	}
	public void setFactor(double factor) {
		this.factor = factor;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getNumBits() {
		return numBits;
	}
	public void setNumBits(int numBits) {
		this.numBits = numBits;
	}
	public boolean isSigned() {
		return signed;
	}
	public void setSigned(boolean signed) {
		this.signed = signed;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getCritic() {
		return critic;
	}
	public void setCritic(int critic) {
		this.critic = critic;
	}
	public boolean isFloat() {
		return isFloat;
	}
	public void setFloat(boolean isFloat) {
		this.isFloat = isFloat;
	}

	
	
	
}
