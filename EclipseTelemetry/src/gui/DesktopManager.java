package gui;

import exception.DeviceItemNotFoundException;
import exception.DeviceNotFoundException;
import gui.dockables.TelemetryGraph;
import gui.dockables.TelemetryMainView;
import gui.graph.TelemetryGraphInterface;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.vlsolutions.swing.docking.Dockable;
import com.vlsolutions.swing.docking.DockingDesktop;
import com.vlsolutions.swing.docking.event.DockableStateWillChangeEvent;
import com.vlsolutions.swing.docking.event.DockableStateWillChangeListener;

import core.DataManager;

public class DesktopManager {
	
	// Singleton
	private static DesktopManager dm = null;
	
	// The Desktop
	DockingDesktop desktop;
    byte [] savedWorkpace; // byte array used to save a workspace
    
    // The Main View Port on which to tab new Dockables
	TelemetryMainView mainView;
	
	private DesktopManager() {
		desktop = new DockingDesktop();
		mainView = new TelemetryMainView();
		
	    // listen to dockable state changes before they are commited
		desktop.addDockableStateWillChangeListener(new DockableStateWillChangeListener(){
	        public void dockableStateWillChange(DockableStateWillChangeEvent  event) {
//	            DockableState current = event.getCurrentState();
//	            if (current.getDockable() == mainView){
//	                if (event.getFutureState().isClosed()){
//	                    // we are facing a closing of the editorPanel
//	                    event.cancel(); // refuse it
//	                }
//	            }
	        }
	    });
	}
	
	public static DesktopManager getInstance() {
		if (dm == null) dm = new DesktopManager();
		return dm;
	}
	
	public DockingDesktop getDesktop() {
		return desktop;
	}
	
	public Dockable getMainView() {
		return mainView;
	}
	
    /** Save the current workspace into an instance byte array */
    public void saveWorkspace() {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            desktop.writeXML(out);
            out.close();
            savedWorkpace = out.toByteArray();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /** Reloads a saved workspace  */
    public void loadWorkspace() {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(savedWorkpace);
            desktop.readXML(in);
            in.close();
        } catch (Exception ex) {
            // catch all exceptions, including those of the SAXParser
            ex.printStackTrace();
        }
    }
	
    public void addGraphTab(int deviceId, int deviceItemId) {
    	try {
    		TelemetryGraph graph = new TelemetryGraph(deviceId, deviceItemId);
			DataManager.getInstance().getDeviceItem(deviceId, deviceItemId).addObserver(graph);
			desktop.createTab(mainView, graph, 999, true);
		} catch (DeviceItemNotFoundException e) {
			e.printStackTrace();
		} catch (DeviceNotFoundException e) {
			e.printStackTrace();
		}		
    }
    
    public void addSpecialGraphTab(TelemetryGraphInterface graph) {
    	desktop.createTab(mainView, (Dockable) graph, 999, true);		
    }
    
    public void addViewTab(Dockable view) {
    	desktop.createTab(mainView, view, 999, true);
    }
	
}
