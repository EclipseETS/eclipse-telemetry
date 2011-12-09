package gui.dockables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;

import core.DataManager;
import exception.DeviceNotFoundException;

import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class TelemetryLogicalView extends JPanel implements Dockable {
	DockKey key = new DockKey("Global_View");
	public JLabel lblDate;
	public JLabel lblHeure;
	public JLabel lblMppt1;
	public JLabel lblMppt2;
	public JLabel lblMppt3;
	public JLabel lblVout1;
	public JLabel lblVout2;
	public JLabel lblVout3;
	public JLabel lblIout1;
	public JLabel lblIout2;
	public JLabel lblIout3;
	public JLabel lblWattTotal;
	public JLabel lblBattvoltage;
	public JLabel lblMin;
	public JLabel lblMax;
	public JLabel lblLatitude;
	public JLabel lblLongitude;
	public JLabel lblTemperature;
	private JLabel lblVitesse;
	private JLabel lblDrive;
	private JLabel lblDrivev;
	private JLabel lblDrivea;
	private JLabel lblDrivew;
	private JLabel lblDw;
	private JLabel lblPuimec;
	private JLabel lblEff;
			
	public TelemetryLogicalView() {
		
		lblDate = new JLabel("date");
		
		lblHeure = new JLabel("heure");
		setLayout(new MigLayout("", "[100.00px][100.00][100px][100px][100px][17px][47px,right]", "[15px][12px][1px][15px][15px][15px][15px][15px][][][]"));
		
		lblDrive = new JLabel("Drive");
		add(lblDrive, "cell 3 0,alignx center");
		add(lblDate, "cell 6 0,alignx right,aligny top");
		
		lblVitesse = new JLabel("vitesse");
		add(lblVitesse, "cell 3 1,alignx center");
		add(lblHeure, "cell 6 1,alignx right,aligny bottom");
		
		lblMppt1 = new JLabel("Mppt1");
		add(lblMppt1, "cell 0 0,alignx center,aligny center");
		
		lblMppt2 = new JLabel("Mppt2");
		add(lblMppt2, "cell 1 0,alignx center,aligny center");
		
		lblVout1 = new JLabel("Vout");
		add(lblVout1, "cell 0 1,alignx center,aligny center");
		
		lblVout2 = new JLabel("Vout");
		add(lblVout2, "cell 1 1,alignx center,aligny center");
		
		lblVout3 = new JLabel("Vout");
		add(lblVout3, "cell 2 1,alignx center,aligny center");
		
		lblIout1 = new JLabel("Iout");
		add(lblIout1, "cell 0 2,alignx center,aligny center");
		
		lblIout2 = new JLabel("Iout");
		add(lblIout2, "cell 1 2,alignx center,aligny center");
		
		lblIout3 = new JLabel("Iout");
		add(lblIout3, "cell 2 2,alignx center,aligny center");
		
		lblDrivev = new JLabel("DriveV");
		add(lblDrivev, "cell 3 2,alignx center");
		
		lblLongitude = new JLabel("Longitude");
		add(lblLongitude, "cell 6 2,alignx center,aligny bottom");
		
		lblDrivea = new JLabel("DriveA");
		add(lblDrivea, "cell 3 3,alignx center");
		
		lblLatitude = new JLabel("Latitude");
		add(lblLatitude, "cell 6 3,alignx center,aligny top");
		
		lblWattTotal = new JLabel("Watt Total");
		add(lblWattTotal, "cell 1 5,alignx center,aligny top");
		
		lblDrivew = new JLabel("DriveW");
		add(lblDrivew, "cell 3 5,alignx center");
		
		lblDw = new JLabel("DW");
		add(lblDw, "cell 2 6,alignx center");
		
		lblBattvoltage = new JLabel("BattVoltage");
		add(lblBattvoltage, "cell 0 7,alignx center,aligny top");
		
		lblPuimec = new JLabel("PuiMec");
		add(lblPuimec, "cell 4 7,alignx center");
		
		lblMin = new JLabel("min");
		add(lblMin, "cell 0 8,alignx center,aligny top");
		
		lblTemperature = new JLabel("Temperature");
		add(lblTemperature, "cell 6 4,alignx center,aligny bottom");
		
		lblMppt3 = new JLabel("Mppt3");
		add(lblMppt3, "cell 2 0,alignx center,aligny center");
		
		lblEff = new JLabel("Eff");
		add(lblEff, "cell 4 8,alignx center");
		
		lblMax = new JLabel("max");
		add(lblMax, "cell 0 9,alignx center,aligny bottom");
		key.setName("Global View");
		
		
		javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					UpdateStatus();
				} catch (DeviceNotFoundException e1) {
					// 
					e1.printStackTrace();
				}
             }

			private void UpdateStatus() throws DeviceNotFoundException {
				lblDate.setText("Date: "+
						(int)(DataManager.getInstance().getDevice(13).getItemByID(11).getDoubleValue())+" "+
						DataManager.getInstance().getDevice(13).getItemByID(11).getUnit());
				
				lblHeure.setText("Heure: "+
						(int)(DataManager.getInstance().getDevice(13).getItemByID(10).getDoubleValue())+" "+
						DataManager.getInstance().getDevice(13).getItemByID(10).getUnit());
				
				lblIout1.setText("I out: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(10).getItemByID(4).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(10).getItemByID(4).getUnit());
				
				lblVout1.setText("V out: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(10).getItemByID(3).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(10).getItemByID(3).getUnit());
				
				lblIout2.setText("I out: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(11).getItemByID(4).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(11).getItemByID(4).getUnit());
				
				lblVout2.setText("V out: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(11).getItemByID(3).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(11).getItemByID(3).getUnit());
				
				lblIout3.setText("I out: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(12).getItemByID(4).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(12).getItemByID(4).getUnit());
				
				lblVout3.setText("V out: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(12).getItemByID(3).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(12).getItemByID(3).getUnit());
				
				double watt = 
					DataManager.getInstance().getDevice(12).getItemByID(3).getDoubleValue()*
					DataManager.getInstance().getDevice(12).getItemByID(4).getDoubleValue()+
					DataManager.getInstance().getDevice(11).getItemByID(3).getDoubleValue()*
					DataManager.getInstance().getDevice(11).getItemByID(4).getDoubleValue()+
					DataManager.getInstance().getDevice(10).getItemByID(3).getDoubleValue()*
					DataManager.getInstance().getDevice(10).getItemByID(4).getDoubleValue();
				
				lblWattTotal.setText("Watts: "+String.format("%.2f",watt)+" W.");
				
				
				lblBattvoltage.setText("Batt Pack: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(9).getItemByID(51).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(9).getItemByID(51).getUnit());
			
				lblMin.setText("Min: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(9).getItemByID(50).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(9).getItemByID(50).getUnit());
			
				lblMax.setText("Max: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(9).getItemByID(49).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(9).getItemByID(49).getUnit());
			
				lblLatitude.setText("State Of Charge: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(8).getItemByID(11).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(8).getItemByID(11).getUnit());
			
				lblLongitude.setText("Dépense Drive: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(4).getItemByID(6).getDoubleValue()*
								DataManager.getInstance().getDevice(4).getItemByID(29).getDoubleValue()))+" W");
				
				lblTemperature.setText("Consomé: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(8).getItemByID(11).getDoubleValue()-
								DataManager.getInstance().getDevice(4).getItemByID(6).getDoubleValue()*
								DataManager.getInstance().getDevice(4).getItemByID(29).getDoubleValue()))+" W");
				
				lblVitesse.setText("Vitesse: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(4).getItemByID(7).getDoubleValue()))
						+" Km/h");
			
				lblDrivea.setText("Drive Tension: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(4).getItemByID(6).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(4).getItemByID(6).getUnit());
				
				lblDrivev.setText("Drive Courant: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(4).getItemByID(5).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(4).getItemByID(5).getUnit());
				
				double puissanceDrive = DataManager.getInstance().getDevice(4).getItemByID(6).getDoubleValue()*
				DataManager.getInstance().getDevice(4).getItemByID(5).getDoubleValue();
				
				lblDrivew.setText("Puissance: "+String.format("%.2f",(puissanceDrive))+" W.");
				
				lblDw.setForeground(Color.GREEN);
				if(watt-puissanceDrive<0)
					lblDw.setForeground(Color.RED);
				
				lblDw.setText("Delta Puissance: "+
						String.format("%.2f",(watt-puissanceDrive))+" W.");
				
				lblPuimec.setText("Pot: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(6).getItemByID(4).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(6).getItemByID(4).getUnit());
				
				lblEff.setText("V out: "+
						String.format("%.2f",(DataManager.getInstance().getDevice(4).getItemByID(1).getDoubleValue()))+" "+
						DataManager.getInstance().getDevice(4).getItemByID(1).getUnit());
				
			//	lblPuimec.setText("Puissance Mec" +String.format("%.2f",DataManager.getInstance().getDevice(4).getItemByID(7).getDoubleValue()/
				//		(.5*Math.PI)*.39*3*DataManager.getInstance().getDevice(4).getItemByID(9).getDoubleValue())+" W.");
				
			//	lblEff.setText("% Eff" +String.format("%.2f",(DataManager.getInstance().getDevice(4).getItemByID(7).getDoubleValue()/
				//		(.5*Math.PI)*.39*3*DataManager.getInstance().getDevice(4).getItemByID(9).getDoubleValue())/
					//	puissanceDrive*100)+" W.");
				
			}
         });
    	t.start();
		
	}
	
	
	
	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public DockKey getDockKey() {
		return key;
	}
}