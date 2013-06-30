package eclipse.view.gui.tab;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import eclipse.controller.util.TelemetrySettings;
import eclipse.model.data.DataManager;
/**
 * This view show all important information on 1 single view
 * @author Marco
 *
 */
public class Tabchar extends JPanel implements TabPane {
	 private Image img;
	 
	 JLabel FLStatus = new JLabel("Status:");		
		JLabel FLDrTmp = new JLabel("Drive Tmp :");
		JLabel FLMotorTmp = new JLabel("Motor Tmp:");
		JLabel FLRmp = new JLabel("RPM:");		
		JLabel FLStatusoAnswer = new JLabel("T");
		
		JLabel FLDrTmpAnwser = new JLabel();
		
		JLabel FLMotorTmpAnswer = new JLabel();		
		JLabel FLRmpAnswer = new JLabel();
		

		//FR
		
		JLabel FRStatus = new JLabel("Status:");
		
		JLabel FRDrTmp = new JLabel("Drive Tmp :");
		JLabel FRMotorTmp = new JLabel("Motor Tmp:");
		
		JLabel FRRmp = new JLabel("RPM:");
		JLabel FRStatusoAnswer = new JLabel("Status:");
		JLabel FRDrTmpAnwser = new JLabel("Drive Tmp :");
		
		JLabel FRMotorTmpAnswer = new JLabel("Motor Tmp:");
		
		JLabel FRRmpAnswer = new JLabel("RPM:");
		
//RR
		
		JLabel RRStatus = new JLabel("Status:");
		
		JLabel RRDrTmp = new JLabel("Drive Tmp :");
		JLabel RRMotorTmp = new JLabel("Motor Tmp:");
		
		JLabel RRRmp = new JLabel("RPM:");
		JLabel RRStatusoAnswer = new JLabel("Status:");
		JLabel RRDrTmpAnwser = new JLabel("Drive Tmp :");
		JLabel RRMotorTmpAnswer = new JLabel("Motor Tmp:");
		
		JLabel RRRmpAnswer = new JLabel("RPM:");
		
//RL
		
		JLabel RLStatus = new JLabel("Status:");
		
		JLabel RLDrTmp = new JLabel("Drive Tmp :");
		JLabel RLMotorTmp = new JLabel("Motor Tmp:");
		
		JLabel RLRmp = new JLabel("RPM:");
		JLabel RLStatusoAnswer = new JLabel("Status:");
		
		JLabel RLDrTmpAnwser = new JLabel("Drive Tmp :");
		
		JLabel RLMotorTmpAnswer = new JLabel("Motor Tmp:");
		
		JLabel RLRmpAnswer = new JLabel("RPM:");
		
		JLabel bat = new JLabel("BATTERY");
		
		JLabel pileMin = new JLabel("Pile Min:");
		JLabel pileMinAnwser = new JLabel("Pile Min:");
		JLabel pileMax = new JLabel("Pile Max:");
		JLabel pileMaxAnwser = new JLabel("Pile Max:");
		JLabel pileTotal = new JLabel("Pile Total:");
		JLabel pileTotalAnwser = new JLabel("Pile Total:");
		JLabel courantOut = new JLabel("Courant:");
		JLabel courantOutAnwser = new JLabel("Courant:");
		JLabel Watt = new JLabel("Puissance:");
		JLabel wattAnwser = new JLabel("W:");
		
		JLabel LON = new JLabel("LON");
		
		JLabel LAT = new JLabel("LAT");
		
		JLabel HEURE = new JLabel("HEURE");
		
		JLabel DATE = new JLabel("DATE");
	 
		JLabel Vitesse = new JLabel("Vitesse:");
		JLabel VitesseAnwser = new JLabel("V:");
		JLabel Commande = new JLabel("Commande:");
		JLabel CommandeAnwser = new JLabel("Commande:");
		
	 
	 
	 
	 
	 
	public Tabchar() {
		setBackground(Color.WHITE);

		
		setForeground(Color.BLACK);
		setLayout(null);
		
		//DRIVE - MOTEUR
		
		//FL
		
				FLStatus.setBounds(800, 100, 100, 14);
				add(FLStatus);
				
				FLDrTmp.setBounds(800, 80, 100, 14);
				add(FLDrTmp);
				
				FLMotorTmp.setBounds(800, 60, 100, 14);
				add(FLMotorTmp);
				
				FLRmp.setBounds(800, 40, 46, 14);
				add(FLRmp);
				
				FLStatusoAnswer.setBounds(900, 100, 100, 14);
				add(FLStatusoAnswer);
				
				FLDrTmpAnwser.setBounds(900, 80, 100, 14);
				add(FLDrTmpAnwser);
				
				FLMotorTmpAnswer.setBounds(900, 60, 100, 14);
				add(FLMotorTmpAnswer);
				
				FLRmpAnswer.setBounds(900, 40, 46, 14);
				add(FLRmpAnswer);
				

				//FR
				
				FRStatus.setBounds(800, 500, 100, 14);
				add(FRStatus);
				
				FRDrTmp.setBounds(800, 480, 100, 14);
				add(FRDrTmp);
				
				FRMotorTmp.setBounds(800, 460, 100, 14);
				add(FRMotorTmp);
				
				FRRmp.setBounds(800, 440, 46, 14);
				add(FRRmp);
				
				FRStatusoAnswer.setBounds(900, 500, 100, 14);
				add(FRStatusoAnswer);
				
				FRDrTmpAnwser.setBounds(900, 480, 100, 14);
				add(FRDrTmpAnwser);
				
				FRMotorTmpAnswer.setBounds(900, 460, 100, 14);
				add(FRMotorTmpAnswer);
				
				FRRmpAnswer.setBounds(900, 440, 46, 14);
				add(FRRmpAnswer);
				
//RR
				
				RRStatus.setBounds(450, 450, 100, 14);
				add(RRStatus);
				
				RRDrTmp.setBounds(450, 430, 100, 14);
				add(RRDrTmp);
				
				RRMotorTmp.setBounds(450, 410, 100, 14);
				add(RRMotorTmp);
				
				RRRmp.setBounds(450, 390, 46, 14);
				add(RRRmp);
				
				RRStatusoAnswer.setBounds(550, 450, 100, 14);
				add(RRStatusoAnswer);
				
				RRDrTmpAnwser.setBounds(550, 430, 100, 14);
				add(RRDrTmpAnwser);
				
				RRMotorTmpAnswer.setBounds(550, 410, 100, 14);
				add(RRMotorTmpAnswer);
				
				RRRmpAnswer.setBounds(550, 390, 46, 14);
				add(RRRmpAnswer);
				
//RL
				
				RLStatus.setBounds(450, 140, 100, 14);
				add(RLStatus);
				
				RLDrTmp.setBounds(450, 120, 100, 14);
				add(RLDrTmp);
				
				RLMotorTmp.setBounds(450, 100, 100, 14);
				add(RLMotorTmp);
				
				RLRmp.setBounds(450, 80, 46, 14);
				add(RLRmp);
				
				RLStatusoAnswer.setBounds(550, 140, 100, 14);
				add(RLStatusoAnswer);
				
				RLDrTmpAnwser.setBounds(550, 120, 100, 14);
				add(RLDrTmpAnwser);
				
				RLMotorTmpAnswer.setBounds(550, 100, 100, 14);
				add(RLMotorTmpAnswer);
				
				RLRmpAnswer.setBounds(550, 80, 100, 14);
				add(RLRmpAnswer);
				
				bat.setBounds(140, 100, 100, 14);
				add(bat);
				
				pileMin.setBounds(110, 120, 100, 14);
				add(pileMin);
				pileMinAnwser.setBounds(180, 120, 100, 14);
				add(pileMinAnwser);
				
				pileMax.setBounds(110, 140, 100, 14);
				add(pileMax);
				pileMaxAnwser.setBounds(180, 140, 100, 14);
				add(pileMaxAnwser);
				
				pileTotal.setBounds(110, 160, 100, 14);
				add(pileTotal);
				pileTotalAnwser.setBounds(180, 160, 100, 14);
				add(pileTotalAnwser);
				
				courantOut.setBounds(110, 180, 100, 14);
				add(courantOut);
				courantOutAnwser.setBounds(180, 180, 100, 14);
				add(courantOutAnwser);
				
				Watt.setBounds(110, 200, 100, 14);
				add(Watt);
				wattAnwser.setBounds(180, 200, 100, 14);
				add(wattAnwser);
				
				LON.setBounds(1050, 60, 100, 14);
				add(LON);
				
				LAT.setBounds(1050, 80, 100, 14);
				add(LAT);
				
				HEURE.setBounds(1050, 40, 100, 14);
				add(HEURE);
				
				DATE.setBounds(1050, 20, 100, 14);
				add(DATE);
				
				
				Vitesse.setBounds(1030, 250, 100, 14);
				add(Vitesse);
				
				VitesseAnwser.setBounds(1100, 250, 100, 14);
				add(VitesseAnwser);
				
				Commande.setBounds(1030, 270, 100, 14);
				add(Commande);
				
				CommandeAnwser.setBounds(1100, 270, 100, 14);
				add(CommandeAnwser);
				
			 
		
		img=new ImageIcon("images/image.png").getImage();
		  

		
	}

	private static final long serialVersionUID = 7275321077445435378L;
	public void updateValues() {
		
		DataManager dd = DataManager.getInstance();
	
			if(dd.getDeviceByID(3).getItemByID(6).getLastData()==0||dd.getDeviceByID(3).getItemByID(6).getLastData()==1)
			if(dd.getDeviceByID(3).getItemByID(19).getLastData()!=0){
				FRStatusoAnswer.setForeground(Color.green);
				FRStatusoAnswer.setText("OK");
			}
			else{
				FRStatusoAnswer.setForeground(Color.red);
				FRStatusoAnswer.setText("ERREUR");
			}
		else{
			FRStatusoAnswer.setForeground(Color.red);
			FRStatusoAnswer.setText(getMessage((int) dd.getDeviceByID(3).getItemByID(6).getLastData()));
		}
		
		FRDrTmpAnwser.setText(dd.getDeviceByID(3).getItemByID(7).getLastData() +" "+dd.getDeviceByID(3).getItemByID(7).getUnit());	
		FRMotorTmpAnswer.setText(dd.getDeviceByID(3).getItemByID(35).getLastData() +" "+dd.getDeviceByID(3).getItemByID(5).getUnit());	
		FRRmpAnswer.setText(dd.getDeviceByID(3).getItemByID(11).getLastData() +" "+dd.getDeviceByID(3).getItemByID(11).getUnit());
		
		if(dd.getDeviceByID(3).getItemByID(3).getLastData()==0||dd.getDeviceByID(3).getItemByID(3).getLastData()==1)
			if(dd.getDeviceByID(3).getItemByID(20).getLastData()!=0){
				FLStatusoAnswer.setForeground(Color.green);
				FLStatusoAnswer.setText("OK");
			}
			else{
				FLStatusoAnswer.setForeground(Color.red);
				FLStatusoAnswer.setText("ERREUR");
			}
		else{
			FLStatusoAnswer.setForeground(Color.red);
			FLStatusoAnswer.setText(getMessage((int) dd.getDeviceByID(3).getItemByID(3).getLastData()));
		}
		
		FLDrTmpAnwser.setText(dd.getDeviceByID(3).getItemByID(8).getLastData() +" "+dd.getDeviceByID(3).getItemByID(8).getUnit());	
		FLMotorTmpAnswer.setText(dd.getDeviceByID(3).getItemByID(36).getLastData() +" "+dd.getDeviceByID(3).getItemByID(36).getUnit());	
		FLRmpAnswer.setText(dd.getDeviceByID(3).getItemByID(12).getLastData() +" "+dd.getDeviceByID(3).getItemByID(12).getUnit());
		
	
		
		
		if(dd.getDeviceByID(3).getItemByID(4).getLastData()==0||dd.getDeviceByID(3).getItemByID(4).getLastData()==1)
			if(dd.getDeviceByID(3).getItemByID(21).getLastData()!=0){
				RRStatusoAnswer.setForeground(Color.green);
				RRStatusoAnswer.setText("OK");
			}
			else{
				RRStatusoAnswer.setForeground(Color.red);
				RRStatusoAnswer.setText("ERREUR");
			}
		else{
			RRStatusoAnswer.setForeground(Color.red);
			RRStatusoAnswer.setText(getMessage((int) dd.getDeviceByID(3).getItemByID(4).getLastData()));
		}
		
		RRDrTmpAnwser.setText(dd.getDeviceByID(3).getItemByID(9).getLastData() +" "+dd.getDeviceByID(3).getItemByID(9).getUnit());	
		RRMotorTmpAnswer.setText(dd.getDeviceByID(3).getItemByID(37).getLastData() +" "+dd.getDeviceByID(3).getItemByID(37).getUnit());	
		RRRmpAnswer.setText(dd.getDeviceByID(3).getItemByID(13).getLastData() +" "+dd.getDeviceByID(3).getItemByID(13).getUnit());
		
		
		if(dd.getDeviceByID(3).getItemByID(5).getLastData()==0||dd.getDeviceByID(3).getItemByID(5).getLastData()==1)
			if(dd.getDeviceByID(3).getItemByID(22).getLastData()!=0){
				RLStatusoAnswer.setForeground(Color.green);
				RLStatusoAnswer.setText("OK");
			}
			else{
				RLStatusoAnswer.setForeground(Color.red);
				RLStatusoAnswer.setText("ERREUR");
			}
		else{
			RLStatusoAnswer.setForeground(Color.red);
			RLStatusoAnswer.setText(getMessage((int) dd.getDeviceByID(3).getItemByID(5).getLastData()));
		}
		
		RLDrTmpAnwser.setText(dd.getDeviceByID(3).getItemByID(10).getLastData() +" "+dd.getDeviceByID(3).getItemByID(10).getUnit());	
		RLMotorTmpAnswer.setText(dd.getDeviceByID(3).getItemByID(38).getLastData() +" "+dd.getDeviceByID(3).getItemByID(38).getUnit());	
		RLRmpAnswer.setText(dd.getDeviceByID(3).getItemByID(14).getLastData() +" "+dd.getDeviceByID(3).getItemByID(14).getUnit());
		
		
		pileMinAnwser.setText(dd.getDeviceByID(7).getItemByID(42).getLastData() +" "+dd.getDeviceByID(7).getItemByID(42).getUnit());
		pileMaxAnwser.setText(dd.getDeviceByID(7).getItemByID(43).getLastData() +" "+dd.getDeviceByID(7).getItemByID(43).getUnit());
		pileTotalAnwser.setText(dd.getDeviceByID(7).getItemByID(44).getLastData() +" "+dd.getDeviceByID(7).getItemByID(44).getUnit());
		courantOutAnwser.setText(dd.getDeviceByID(7).getItemByID(47).getLastData() +" "+dd.getDeviceByID(7).getItemByID(47).getUnit());
		
		LAT.setText(Double.toString(dd.getDeviceByID(6).getItemByID(2).getLastData()));
		LON.setText(Double.toString(dd.getDeviceByID(6).getItemByID(3).getLastData()));
		String date =Double.toString(dd.getDeviceByID(6).getItemByID(5).getLastData());
		String heure =Double.toString(dd.getDeviceByID(6).getItemByID(4).getLastData());
		int delay =Integer.parseInt(TelemetrySettings.getInstance().getSetting("DELAY_TIME"));
		if(Double.parseDouble(date)>9999){
			DATE.setText(date.substring(0, 2)+":"+date.substring(2,4)+":"+date.substring(4,6));
			HEURE.setText((Integer.parseInt(heure.substring(0, 2))+delay)+":"+heure.substring(2,4)+":"+heure.substring(4,6));
		}
		
		double speed = (dd.getDeviceByID(3).getItemByID(11).getLastData()+
				dd.getDeviceByID(3).getItemByID(12).getLastData())/2*0.492*3.1459*60/1000;
		VitesseAnwser.setText(speed+" Km/h");
		CommandeAnwser.setText(Integer.toString((int) dd.getDeviceByID(5).getItemByID(4).getLastData()));
		
		Integer puiss = (int) (dd.getDeviceByID(7).getItemByID(44).getLastData()*dd.getDeviceByID(7).getItemByID(47).getLastData());
		
		if (puiss>0)
			wattAnwser.setForeground(Color.red);
		else
			wattAnwser.setForeground(Color.green);
		wattAnwser.setText(Integer.toString(puiss)+" W");
		
		
	}
	  public void paintComponent(Graphics g) {
		    
		    int width = getWidth();
		    int height = getHeight();        

		    g.setColor(Color.WHITE);
		    g.fillRect(0, 0, width, height);
		    g.drawImage(img, 180, 100, null);
			  }
	  
	  public String getMessage(int i){
		  switch (i) {
		  
		  case 0:			
				return "OK";

		  case 1:			
				return "OK";

		  case 2:			
				return "OK";

		  case 3:			
				return "I2CShutOff";

		  case 4:			
				return "AntiBackwardShort";

		  case 5:			
				return "AlarmRegen";

		  case 6:			
				return "AlarmShort";

		  case 7:			
				return "OverSpeedI";

		  case 8:			
				return "OverSpeedV";

		  case 9:			
				return "V12UVP";

		  case 10:			
				return "V12OVP";

		  case 11:			
				return "VPwrUVP";

		  case 12:			
				return "VPwrOVP";

		  case 13:			
				return "OCProtect";

		  case 14:			
				return "BadStatorPN";

		  case 15:			
				return "HallError";

			default:
			return "UNKOOWN CODE";
		}
	  }
}
