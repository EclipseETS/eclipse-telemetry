package app;


import gui.dockables.TelemetryAlertList;

public class Alerting extends Thread{
	TelemetryAlertList tl;
	public Alerting(gui.dockables.TelemetryAlertList tl) {
        super();
        this.tl = tl;
    }

    public void run() {
        try {
        	while(true){
        		if (!app.DataAlert.working){
            		sleep((int)(Math.random() * 1500));
            		tl.addAlert(app.DataAlert.Alerts[(int)(10.0 * Math.random())]);
        			}
        		}
        	
        	} catch (Exception e) {
        		System.out.println("erreur Alerting.java");
        		System.out.println(e);
        	}
    }
}
