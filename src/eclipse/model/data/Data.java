package eclipse.model.data;

import java.io.Serializable;

/**
 * Where all the data is stored. 
 * This is a unit data. One per second
 * @author Marco
 *
 */
public class Data implements Serializable{

	private static final long serialVersionUID = 8442914540348823614L;
	private Long date;
	private double data;
	
	/**
	 * Constructor using new data and curent time
	 * @param data
	 */
	public Data(double data){
		date=System.currentTimeMillis();
		this.data=data;
	}

	/**
	 * Construct using specific timestamp
	 * @param data double data
	 * @param time timestamp
	 */
	public Data(double data,long time){
		date=time;
		this.data=data;
	}

	
	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}
	
}
