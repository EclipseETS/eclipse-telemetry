package acquisition;

import util.HexString;

// Dans le cas ou le protocole aurait eu des bytes determines, cette
// classe se serait servit du frame model pour avoir les positions des champs
// Mais pour le moment, c'est le frame model qui fait cette job la et qui
// retourne directement les champs..

public class FrameDesencapsulator {

	FrameModel frameModel = null;
	
	public FrameDesencapsulator() {
		
	}
	
	public FrameDesencapsulator(FrameModel fm) {
		this.frameModel = fm;
	}
	
	public void setFrameModel(FrameModel fm) {
		this.frameModel = fm;
	}
	
	public int getDeviceId(String rawData) {
		try{
		String hex = rawData.substring(frameModel.getDeviceIdStartCharPos(),
									   frameModel.getDeviceIdEndCharPos());
		//System.out.println(frameModel.getDeviceIdStartCharPos() + "-" + frameModel.getDeviceIdEndCharPos());
		//System.out.println(hex);
		return Integer.parseInt(hex, 16); // Hex to string
		}
		catch(Exception e){
		return 0;
		}
	}
	
	public int getDeviceItemId(String rawData) {
		try{
		String hex = rawData.substring(frameModel.getItemIdStartCharPos(),
				   				 	   frameModel.getItemIdEndCharPos());
		//System.out.println(frameModel.getItemIdStartCharPos() + "-" + frameModel.getItemIdEndCharPos());
		//System.out.println(hex);
		return Integer.parseInt(hex, 16); // Hex to string
		}
		catch(Exception e){
			return 0;
		}
		
	}
	
	public byte[] getData(String rawData) {
		try{
		String hex = rawData.substring(frameModel.getDataStartCharPos(),
			 	   					   rawData.length() - frameModel.getCRCcharLen());
		return HexString.hexToBuffer(hex);
		}
		catch(Exception e){
			return new byte[4];
		}
	}
	
	public boolean isCRCok(byte[] rawData) {
		return frameModel.isCRCok(rawData);
	}
}
