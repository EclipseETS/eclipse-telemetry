package eclipseV7.data;

import acquisition.FrameModel;

public class FrameModelCANUSB implements FrameModel {

	final private static int START = 'T';
	final private static int END = 0x0D;
	
	// Fixed field positions in the frame (byte[i]) including Start
//	final private static int POS_DEVICE = 0;
//	final private static int POS_ITEM = 2;
//	final private static int POS_DATA = 5;
//	
//	// Fixed field lengths, in bytes
//	final private static int LEN_DEVICE = 1;
//	final private static int LEN_ITEM = 1;
//	final private static int LEN_CRC = 1;
	
	// Fixed field position (as char of the string) after START
	final private static int POS_CHAR_DEVICE = 1;
	final private static int POS_CHAR_ITEM = 5;
	final private static int POS_CHAR_DATA = 10;
	
	// Fixed field lengths (in char positions)
	final private static int LEN_CHAR_DEVICE = 4;
	final private static int LEN_CHAR_ITEM = 4;
	final private static int LEN_CHAR_CRC = 0;
	
	@Override
	public int getDeviceIdStartCharPos() {
		return POS_CHAR_DEVICE;
	}
	
	@Override
	public int getDeviceIdEndCharPos() {
		return POS_CHAR_DEVICE + LEN_CHAR_DEVICE;
	}
	
	@Override
	public int getItemIdStartCharPos() {
		return POS_CHAR_ITEM;
	}
	
	@Override
	public int getItemIdEndCharPos() {
		return POS_CHAR_ITEM + LEN_CHAR_ITEM;
	}
	
	@Override
	public int getDataStartCharPos() {
		return POS_CHAR_DATA;
	}
	
	@Override
	public int getCRCcharLen() {
		return LEN_CHAR_CRC;
	}
	
	@Override
	public boolean isCRCok(byte[] rawData) {
		return true;//No CRC
	}

	@Override
	public byte getStartDelimeter() {
		return START;
	}
	
	@Override
	public byte getEndDelimeter() {
		return END;
	}

}
