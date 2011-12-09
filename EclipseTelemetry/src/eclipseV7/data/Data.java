package eclipseV7.data;


public class Data {

	private Long date;
	private byte[] data;
	
	public Data(byte[] data){
		date=System.currentTimeMillis();
		byte[] tmp = new byte[data.length];
		for(int i = 0; i < data.length; i++){
			tmp[i] = data[i];
		}
		this.data=tmp;
	}

	public Data(byte[] data,long time){
		date=time;
		byte[] tmp = new byte[data.length];
		for(int i = 0; i < data.length; i++){
			tmp[i] = data[i];
		}
		this.data=tmp;
	}

	
	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
}
