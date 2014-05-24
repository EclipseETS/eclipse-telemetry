package eclipse.model.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;


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
		return round(data, 3);
	}

	public void setData(double data) {
		this.data = data;
	}
	
	public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
}
