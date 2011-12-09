package app;

import java.util.ArrayList;
import java.util.List;

public class DataAlert {
	    public static String Alerts[] = {	"1;Dash;Status: Pas de signal avec le seurveur;2",
	    									"2;Dash;Pedal: Pas de signal avec le seurveur;1",
	    									"3;Dash;Temperature critique;3",
	    									"4;Startup Controller;Status: Pas de signal avec le seurveur;4",
	    									"5;Startup Controller;Status: Pas de signal avec le seurveur;4",
	    									"4;Startup Controller;Status: Pas de signal avec le seurveur;3",
	    									"7;Startup Controller;Status: Pas de signal avec le seurveur;4",
	    									"8;MPPT1;Status: Pas de signal avec le seurveur;7",
	    									"1;MPPT2;Temperature critique;2",
	    									"10;MPPT3;Status: Pas de signal avec le seurveur;4",
	    									"2;Dash;Pedal: Pas de signal avec le seurveur;1",
	    									"3;Dash;Temperature critique;3",
	    									"4;Startup Controller;Status: Pas de signal avec le seurveur;4",
	    									"1;Startup Controller;Status: Pas de signal avec le seurveur;5",
	    									"6;Startup Controller;Status: Pas de signal avec le seurveur;4",
	    									"2;Startup Controller;Status: Pas de signal avec le seurveur;4",
	    									"8;MPPT1;Status: Pas de signal avec le seurveur;1",
	    									"7;MPPT2;Temperature critique;2",
	    									"7;MPPT3;Status: Pas de signal avec le seurveur;3"
	    							
	    };
	    
	    public static List<String> LiveAlerts = new ArrayList<String>();
	    
	    public static List<String> Filters = new ArrayList<String>();
	    
	    public static boolean working = false;
	    
	    public static boolean isFilterExist(String str){
	    	for (int i=0; i<Filters.size();i++){
	    		if (String.valueOf(Filters.get(i)).equals(str)){
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    
	    public static void showMeLiveAlerts(){
	    	for (int i=0; i<LiveAlerts.size();i++){
	    		System.out.println("alert #"+i + " : " + LiveAlerts.get(i));
	    	}
	    }
	    
	    public static void showMeFilters(){
	    	for (int i=0; i<Filters.size();i++){
	    		System.out.println("filter #"+i + " : " + Filters.get(i));
	    	}
	    }
}
