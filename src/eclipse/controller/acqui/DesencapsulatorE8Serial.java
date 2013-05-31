package eclipse.controller.acqui;


import java.util.Arrays;

import org.apache.log4j.Logger;

import eclipse.controller.util.ByteManipulator;
import eclipse.controller.util.CRC8;

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
		if(cpt==14){
			cpt=0;
		}
	}

	
	public void clearData() {
		byteArray = new byte[15];
	}
	
	private void parseData(int lengt){
		if(byteArray[0]==value && byteArray[1]==value2 && lengt>2)
		{
			byte[] idB = Arrays.copyOfRange(byteArray,2,6);
			int id = 0;
			try {
				id = (int) ByteManipulator.byteArrayToInt(idB, 0, 4, false, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			byte c = 0;
			byte crcTmp = CRC8.updateBlock(byteArray, lengt-1, c);
			if(crcTmp==byteArray[lengt-1]){
				//FRAME VALID 
				
				
				System.out.println(id);
			}
		}
		
		//TODO: Coder le desencapsulateur
		
//		//manipuler les données (Frame devrais etre entier)
//		for(int i=0;i<cpt;i++)				
//			System.out.println(byteArray[i]);
		
		
		
	}

}
