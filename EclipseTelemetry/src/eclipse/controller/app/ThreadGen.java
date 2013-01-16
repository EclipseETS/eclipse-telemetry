package eclipse.controller.app;

import java.util.ArrayList;


/**
 * Thread Generator, from this point we will start all thread for this app. 
 * 
 * @author Marco
 *
 */
public class ThreadGen {
	
	//static version used for the singleton
	private static ThreadGen threadGenInstance = new ThreadGen();
	
	
	//List of thread to be run
	private ArrayList<Runnable> threadList = new ArrayList<Runnable>();
	
	/**
	 * Singleton
	 */
	private ThreadGen(){
		
	}
	
	public static ThreadGen getInstance(){
		return threadGenInstance;
	}
	
	/**
	 * Add new thread to be run to the thread list
	 * @param currentThread
	 */
	public void addThread(Runnable currentThread){
		threadList.add(currentThread);
	}
	
	
	/**
	 * This is where all the thread need to be for the application, Thing in here need to implements Runnable.
	 * 
	 * ====BEFORE RUNNING THIS, BE SURE TO ADDTHREAD=======
	 */
	public void startThread(){
		for(Runnable current : threadList){
			current.run();
		}
	}
	
	
	/**
	 * If thread Gen already started and you want to add a new thread
	 * @param threadToAdd
	 */
	public void addRunningThread(Runnable threadToAdd){
		
	}
	

}
