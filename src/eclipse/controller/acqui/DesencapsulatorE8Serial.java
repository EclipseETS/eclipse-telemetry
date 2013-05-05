package eclipse.controller.acqui;


import org.apache.log4j.Logger;

/**
 * Provide full desencapsulation, by receiving byte from acquisition
 * @author Marco
 *
 */
public class DesencapsulatorE8Serial implements Desencapsulator {
	
	private Byte[] byteArray = new Byte[15]; //15 is the max a communication will be
	private int cpt=0;
	static Logger logger = Logger.getLogger("main");
	private int value = 0x30;
	
	
	public void receiveChar(Byte bt) {
		if(bt==value){
			if(byteArray[0]==value)
				parseData();		
			cpt=0;
		}
		byteArray[cpt]=bt;
		cpt++;
		if(cpt==14){
			cpt=0;
		}
	}

	
	public void clearData() {
		byteArray = new Byte[15];
	}
	
	private void parseData(){
		
		//TODO: Coder le desencapsulateur
		
//		//manipuler les donn�es (Frame devrais etre entier)
//		for(int i=0;i<cpt;i++)				
//			System.out.println(byteArray[i]);
		
		
		
	}

}
