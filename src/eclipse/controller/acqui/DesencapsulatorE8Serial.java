package eclipse.controller.acqui;


import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import eclipse.controller.util.ByteManipulator;
import eclipse.controller.util.CRC8;
import eclipse.controller.util.HexString;
import eclipse.model.data.DataManager;
import eclipse.model.data.DeviceItem;
import eclipse.model.data.Trame;

/**
 * Provide full desencapsulation, by receiving byte from acquisition
 * @author Marco
 *
 */
public class DesencapsulatorE8Serial implements Desencapsulator {
	
	private byte[] byteArray = new byte[15]; //15 is the max a communication will be
	private int cpt=0;
	static Logger logger = Logger.getLogger("main");
	private byte value = (byte) 0xAA;
	private byte value2 = 0x55;
	
	public void receiveChar(byte bt) {
		if(bt==value){
			if(byteArray[0]==value)
				parseData(cpt);		
			cpt=0;
			
		}
		byteArray[cpt]=bt;
		cpt++;
		if(cpt==15){
			cpt=0;
		}
	}

	
	public void clearData() {
		byteArray = new byte[15];
	}
	
	/**
	 * Hard core method that convert byte array to data and directly add it in the device ITEM
	 * @param lengt
	 */
	private void parseData(int lengt){
		//if array is full lengt is back to 0, 
		if(lengt==0)
			lengt=15;
		//Verify 2 first byte to have AA 55
		if(byteArray[0]==value && byteArray[1]==value2)
		{
			//GET ID
			byte[] idB = Arrays.copyOfRange(byteArray,2,6);
			int id = 0;
			try {
				//Convert ID in INT
				id = (int) ByteManipulator.byteArrayToInt(idB, 0, 4, false, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			byte c = 0;
			//Calculate CRC
			byte crcTmp = CRC8.updateBlock(byteArray, lengt-1, c);
			if(crcTmp==byteArray[lengt-1]){
				//CRC VALID 

				//Get trame
				Trame t = DataManager.getInstance().getTrame(id);
				
				if(t!=null)
				{
					//trame exist
					
					logger.debug("Recu Trame "+ HexString.bufferToHex(idB));
					List<DeviceItem> items = t.getItems();
					int i =6;
					for(DeviceItem itm : items)
					{
						double value;
						try {
							//Get data from the right part of the array
							value = ByteManipulator.byteArrayToInt(Arrays.copyOfRange(byteArray,i,i+(itm.getBitSize()/8)),
									0, (itm.getBitSize()/8), itm.isSigned(), itm.isFloat());
							i+=itm.getBitSize()/8;
							//Set the new value
							itm.setValue(value);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}
				else{
					//TRAME EXISTE PAS
					logger.error("Recu Trame "+ HexString.bufferToHex(idB) +" existe pas dans XML");
				}
				
				}
			else{
				//DLC error
				logger.debug("ERROR DLC");
			}
		}
		
		//TODO: Coder le desencapsulateur
		
//		//manipuler les données (Frame devrais etre entier)
//		for(int i=0;i<cpt;i++)				
//			System.out.println(byteArray[i]);
		
		
		
	}

}
