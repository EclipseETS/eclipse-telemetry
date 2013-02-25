package eclipse.controller.acqui;


import org.apache.log4j.Logger;

public class DesencapsulatorE8Serial implements Desencapsulator {
	
	private Byte[] byteArray = new Byte[15]; //15 is the max a communication will be
	private int cpt=0;
	static Logger logger = Logger.getLogger("telemetry");
	private int value = 0x30;
	
	public void receiveChar(Byte bt) {
		if(bt==value){
			if(byteArray[0]==value)
				
				//manipuler les donn�es (Frame devrais etre entier)
				for(int i=0;i<cpt;i++)				
					System.out.println(byteArray[i]);
			
			cpt=0;
		}
		byteArray[cpt]=bt;
		cpt++;
	}

	
	public void clearData() {
		byteArray = new Byte[15];
	}

}
