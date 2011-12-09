package acquisition;

// Information about the Frame used to desemcapsulate it
public interface FrameModel {
	
	public int getDeviceIdStartCharPos();
	public int getDeviceIdEndCharPos();
	
	public int getItemIdStartCharPos();
	public int getItemIdEndCharPos();
	
	public int getDataStartCharPos();
	
	public int getCRCcharLen();
	public boolean isCRCok(byte[] rawData);
	
	public byte getStartDelimeter();
	public byte getEndDelimeter();
	
}
