package eclipse.controller.acqui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class give all we need to open different port
 * @author Marco
 *
 */
public class AcquisitionManager {
	
	private static AcquisitionManager acqui = new AcquisitionManager();
	
	private AcquisitionManager(){
		
	}
	
	//List of thread to be run
	private List<DataAcquisition> dataAcquisitionList =Collections.synchronizedList(new ArrayList<DataAcquisition>());
		
	
	public static AcquisitionManager getInstance(){
		return acqui;
		
	}
	
	public void addAcqui (DataAcquisition dt){
		dataAcquisitionList.add(dt);
	}
	
	public DataAcquisition getAcqui(int id){
		return dataAcquisitionList.get(id);
	}

}
