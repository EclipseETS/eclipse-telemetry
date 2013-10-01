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
	 //TODO ajouter temperature du char
	 
	 //FL
	 	JLabel FLStatus = new JLabel("Status:");		
		JLabel FLDrTmp = new JLabel("Heat Sink Tmp :");
		JLabel FLDrvCurrent = new JLabel("Drive current :");
		JLabel FLMotorTmp = new JLabel("Motor Tmp:");
		JLabel FLRmp = new JLabel("RPM:");		
		
		JLabel FLStatusoAnswer = new JLabel("status");
		JLabel FLDrTmpAnwser = new JLabel();
		JLabel FLDrvCurrentAnswer = new JLabel("Drive current :");
		JLabel FLMotorTmpAnswer = new JLabel();		
		JLabel FLRmpAnswer = new JLabel();
		

		//FR
		
		JLabel FRStatus = new JLabel("Status:");
		JLabel FRDrvCurrent = new JLabel("Drive current :");
		JLabel FRDrTmp = new JLabel("Heat Sink Tmp :");
		JLabel FRMotorTmp = new JLabel("Motor Tmp:");
		JLabel FRRmp = new JLabel("RPM:");
		
		JLabel FRStatusoAnswer = new JLabel("Status:");
		JLabel FRDrTmpAnwser = new JLabel("Drive Tmp :");
		JLabel FRDrvCurrentAnswer = new JLabel("Drive current :");		
		JLabel FRMotorTmpAnswer = new JLabel("Motor Tmp:");
		JLabel FRRmpAnswer = new JLabel("RPM:");
		
		//RR
		
		JLabel RRStatus = new JLabel("Status:");
		JLabel RRDrTmp = new JLabel("Heat Sink Tmp :");
		JLabel RRMotorTmp = new JLabel("Motor Tmp:");
		JLabel RRDrvCurrent = new JLabel("Drive current :");
		JLabel RRRmp = new JLabel("RPM:");
		
		JLabel RRStatusoAnswer = new JLabel("Status:");
		JLabel RRDrTmpAnwser = new JLabel("Drive Tmp :");
		JLabel RRDrvCurrentAnswer = new JLabel("Drive current :");
		JLabel RRMotorTmpAnswer = new JLabel("Motor Tmp:");
		JLabel RRRmpAnswer = new JLabel("RPM:");
		
		//RL
		
		JLabel RLStatus = new JLabel("Status:");
		JLabel RLDrTmp = new JLabel("Heat Sink Tmp :");
		JLabel RLMotorTmp = new JLabel("Motor Tmp:");
		JLabel RLDrvCurrent = new JLabel("Drive current :");
		JLabel RLRmp = new JLabel("RPM:");
		
		JLabel RLStatusoAnswer = new JLabel("Status:");
		JLabel RLDrTmpAnwser = new JLabel("Drive Tmp :");
		JLabel RLDrvCurrentAnswer = new JLabel("Drive current :");
		JLabel RLMotorTmpAnswer = new JLabel("Motor Tmp:");
		JLabel RLRmpAnswer = new JLabel("RPM:");
		
		//Battery
		
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
		
		//MPPT
		JLabel MPPT1 = new JLabel("MPPT1");
		JLabel MPPT2 = new JLabel("MPPT2");
		JLabel MPPT3 = new JLabel("MPPT3");
		
		JLabel Ptotal = new JLabel("Power MPPT:");
		
		JLabel Mppt1Vout = new JLabel("Vout:");
		JLabel Mppt2Vout = new JLabel("Vout:");
		JLabel Mppt3Vout = new JLabel("Vout:");
		
		JLabel Mppt1Cout = new JLabel("Courant:");
		JLabel Mppt2Cout = new JLabel("Courant:");
		JLabel Mppt3Cout = new JLabel("Courant:");
		
		JLabel Mppt1Power = new JLabel("Power:");
		JLabel Mppt2Power = new JLabel("Power:");
		JLabel Mppt3Power = new JLabel("Power:");
		
		JLabel PtotalAnswer = new JLabel("Power MPPT:");
		
		JLabel Mppt1VoutAnswer = new JLabel("Vout:");
		JLabel Mppt2VoutAnswer = new JLabel("Vout:");
		JLabel Mppt3VoutAnswer = new JLabel("Vout:");
		
		JLabel Mppt1CoutAnswer = new JLabel("Courant:");
		JLabel Mppt2CoutAnswer = new JLabel("Courant:");
		JLabel Mppt3CoutAnswer = new JLabel("Courant:");
		
		JLabel Mppt1PowerAnswer = new JLabel("Power:");
		JLabel Mppt2PowerAnswer = new JLabel("Power:");
		JLabel Mppt3PowerAnswer = new JLabel("Power:");
		
		
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
		
		// Affichage
		
		// MPPT
				
				MPPT1.setBounds(70, 380, 100, 14);
				add(MPPT1);
				
				MPPT2.setBounds(190, 380, 100, 14);
				add(MPPT2);
				
				MPPT3.setBounds(310, 380, 100, 14);
				add(MPPT3);
		
				Mppt1Power.setBounds(30, 440, 100, 14);
				add(Mppt1Power);
				
				Mppt2Power.setBounds(160, 440, 100, 14);
				add(Mppt2Power);
				
				Mppt3Power.setBounds(280, 440, 100, 14);
				add(Mppt3Power);
				
				Mppt1Vout.setBounds(30, 420, 100, 14);
				add(Mppt1Vout);
				
				Mppt2Vout.setBounds(160, 420, 100, 14);
				add(Mppt2Vout);
				
				Mppt3Vout.setBounds(280, 420, 100, 14);
				add(Mppt3Vout);
				
				Mppt1Cout.setBounds(30, 400, 100, 14);
				add(Mppt1Cout);
				
				Mppt2Cout.setBounds(160, 400, 100, 14);
				add(Mppt2Cout);
				
				Mppt3Cout.setBounds(280, 400, 100, 14);
				add(Mppt3Cout);
				
				Ptotal.setBounds(120, 480, 100, 14);
				add(Ptotal);
				
				Mppt1PowerAnswer.setBounds(80, 440, 100, 14);
				add(Mppt1PowerAnswer);
				
				Mppt2PowerAnswer.setBounds(210, 440, 100, 14);
				add(Mppt2PowerAnswer);
				
				Mppt3PowerAnswer.setBounds(330, 440, 100, 14);
				add(Mppt3PowerAnswer);
				
				Mppt1VoutAnswer.setBounds(80, 420, 100, 14);
				add(Mppt1VoutAnswer);
				
				Mppt2VoutAnswer.setBounds(210, 420, 100, 14);
				add(Mppt2VoutAnswer);
				
				Mppt3VoutAnswer.setBounds(330, 420, 100, 14);
				add(Mppt3VoutAnswer);
				
				Mppt1CoutAnswer.setBounds(80, 400, 100, 14);
				add(Mppt1CoutAnswer);
				
				Mppt2CoutAnswer.setBounds(210, 400, 100, 14);
				add(Mppt2CoutAnswer);
				
				Mppt3CoutAnswer.setBounds(330, 400, 100, 14);
				add(Mppt3CoutAnswer);
				
				PtotalAnswer.setBounds(210, 480, 100, 14);
				add(PtotalAnswer);
				
		//FL
		
				FLStatus.setBounds(800, 100, 100, 14);
				add(FLStatus);
				
				FLDrTmp.setBounds(800, 80, 100, 14);
				add(FLDrTmp);
				
				FLDrvCurrent.setBounds(800, 40, 100, 14);
				add(FLDrvCurrent);
				
				FLMotorTmp.setBounds(800, 60, 100, 14);
				add(FLMotorTmp);
				
				FLRmp.setBounds(800, 20, 46, 14);
				add(FLRmp);
				
				FLStatusoAnswer.setBounds(900, 100, 100, 14);
				add(FLStatusoAnswer);
				
				FLDrTmpAnwser.setBounds(900, 80, 100, 14);
				add(FLDrTmpAnwser);
				
				FLMotorTmpAnswer.setBounds(900, 60, 100, 14);
				add(FLMotorTmpAnswer);
				
				FLDrvCurrentAnswer.setBounds(900, 40, 100, 14);
				add(FLDrvCurrentAnswer);
				
				FLRmpAnswer.setBounds(900, 20, 46, 14);
				add(FLRmpAnswer);
				

				//FR
				
				FRStatus.setBounds(800, 510, 100, 14);
				add(FRStatus);
				
				FRDrTmp.setBounds(800, 490, 100, 14);
				add(FRDrTmp);
				
				FRDrvCurrent.setBounds(800, 450, 100, 14);
				add(FRDrvCurrent);
				
				FRMotorTmp.setBounds(800, 470, 100, 14);
				add(FRMotorTmp);
				
				FRRmp.setBounds(800, 430, 46, 14);
				add(FRRmp);
				
				FRStatusoAnswer.setBounds(900, 510, 100, 14);
				add(FRStatusoAnswer);
				
				FRDrTmpAnwser.setBounds(900, 490, 100, 14);
				add(FRDrTmpAnwser);
				
				FRMotorTmpAnswer.setBounds(900, 470, 100, 14);
				add(FRMotorTmpAnswer);
				
				FRDrvCurrentAnswer.setBounds(900, 450, 100, 14);
				add(FRDrvCurrentAnswer);
				
				FRRmpAnswer.setBounds(900, 430, 46, 14);
				add(FRRmpAnswer);
				
//RR
				
				RRStatus.setBounds(450, 470, 100, 14);
				add(RRStatus);
				
				RRDrTmp.setBounds(450, 450, 100, 14);
				add(RRDrTmp);
				
				RRMotorTmp.setBounds(450, 430, 100, 14);
				add(RRMotorTmp);
				
				RRDrvCurrent.setBounds(450, 410, 100, 14);
				add(RRDrvCurrent);
				
				RRRmp.setBounds(450, 390, 46, 14);
				add(RRRmp);
				
				RRStatusoAnswer.setBounds(550, 470, 100, 14);
				add(RRStatusoAnswer);
				
				RRDrTmpAnwser.setBounds(550, 450, 100, 14);
				add(RRDrTmpAnwser);
				
				RRMotorTmpAnswer.setBounds(550, 430, 100, 14);
				add(RRMotorTmpAnswer);
				
				RRDrvCurrentAnswer.setBounds(550, 410, 100, 14);
				add(RRDrvCurrentAnswer);
				
				RRRmpAnswer.setBounds(550, 390, 46, 14);
				add(RRRmpAnswer);
				
//RL
				
				RLStatus.setBounds(450, 140, 100, 14);
				add(RLStatus);
				
				RLDrTmp.setBounds(450, 120, 100, 14);
				add(RLDrTmp);
				
				RLMotorTmp.setBounds(450, 100, 100, 14);
				add(RLMotorTmp);
				
				RLDrvCurrent.setBounds(450, 80, 100, 14);
				add(RLDrvCurrent);
				
				RLRmp.setBounds(450, 60, 46, 14);
				add(RLRmp);
				
				RLStatusoAnswer.setBounds(550, 140, 100, 14);
				add(RLStatusoAnswer);
				
				RLDrTmpAnwser.setBounds(550, 120, 100, 14);
				add(RLDrTmpAnwser);
				
				RLMotorTmpAnswer.setBounds(550, 100, 100, 14);
				add(RLMotorTmpAnswer);
				
				RLDrvCurrentAnswer.setBounds(550, 80, 100, 14);
				add(RLDrvCurrentAnswer);

				RLRmpAnswer.setBounds(550, 60, 100, 14);
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
	/*
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
		*/
		
		FRDrTmpAnwser.setText(dd.getDeviceByID(12).getItemByID(19).getLastData() +" "+dd.getDeviceByID(12).getItemByID(19).getUnit());	
		FRMotorTmpAnswer.setText(dd.getDeviceByID(12).getItemByID(18).getLastData() +" "+dd.getDeviceByID(12).getItemByID(18).getUnit());	
		FRRmpAnswer.setText(dd.getDeviceByID(12).getItemByID(9).getLastData() +" "+dd.getDeviceByID(12).getItemByID(9).getUnit());
		FRDrvCurrentAnswer.setText(dd.getDeviceByID(12).getItemByID(8).getLastData() +" "+dd.getDeviceByID(12).getItemByID(8).getUnit());
		/*
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
		*/
		FLDrTmpAnwser.setText(dd.getDeviceByID(13).getItemByID(19).getLastData() +" "+dd.getDeviceByID(13).getItemByID(19).getUnit());	
		FLMotorTmpAnswer.setText(dd.getDeviceByID(13).getItemByID(18).getLastData() +" "+dd.getDeviceByID(13).getItemByID(18).getUnit());	
		FLRmpAnswer.setText(dd.getDeviceByID(13).getItemByID(9).getLastData() +" "+dd.getDeviceByID(13).getItemByID(9).getUnit());
		FLDrvCurrentAnswer.setText(dd.getDeviceByID(13).getItemByID(8).getLastData() +" "+dd.getDeviceByID(13).getItemByID(8).getUnit());
		
	
		
		
		/*if(dd.getDeviceByID(3).getItemByID(4).getLastData()==0||dd.getDeviceByID(3).getItemByID(4).getLastData()==1)
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
		*/
		RRDrTmpAnwser.setText(dd.getDeviceByID(14).getItemByID(19).getLastData() +" "+dd.getDeviceByID(14).getItemByID(19).getUnit());	
		RRMotorTmpAnswer.setText(dd.getDeviceByID(14).getItemByID(18).getLastData() +" "+dd.getDeviceByID(14).getItemByID(18).getUnit());	
		RRRmpAnswer.setText(dd.getDeviceByID(14).getItemByID(9).getLastData() +" "+dd.getDeviceByID(14).getItemByID(9).getUnit());
		RRDrvCurrentAnswer.setText(dd.getDeviceByID(14).getItemByID(8).getLastData() +" "+dd.getDeviceByID(14).getItemByID(8).getUnit());
		
		/*
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
		*/
		RLDrTmpAnwser.setText(dd.getDeviceByID(15).getItemByID(19).getLastData() +" "+dd.getDeviceByID(15).getItemByID(19).getUnit());	
		RLMotorTmpAnswer.setText(dd.getDeviceByID(15).getItemByID(18).getLastData() +" "+dd.getDeviceByID(15).getItemByID(18).getUnit());	
		RLRmpAnswer.setText(dd.getDeviceByID(15).getItemByID(9).getLastData() +" "+dd.getDeviceByID(15).getItemByID(9).getUnit());
		RLDrvCurrentAnswer.setText(dd.getDeviceByID(15).getItemByID(8).getLastData() +" "+dd.getDeviceByID(15).getItemByID(8).getUnit());

		
		pileMinAnwser.setText(dd.getDeviceByID(7).getItemByID(42).getLastData() +" "+dd.getDeviceByID(7).getItemByID(42).getUnit());
		pileMaxAnwser.setText(dd.getDeviceByID(7).getItemByID(43).getLastData() +" "+dd.getDeviceByID(7).getItemByID(43).getUnit());
		pileTotalAnwser.setText(dd.getDeviceByID(7).getItemByID(44).getLastData() +" "+dd.getDeviceByID(7).getItemByID(44).getUnit());
		courantOutAnwser.setText(dd.getDeviceByID(7).getItemByID(60).getLastData() +" "+dd.getDeviceByID(7).getItemByID(60).getUnit());
		
		Mppt1VoutAnswer.setText(dd.getDeviceByID(8).getItemByID(2).getLastData() +" "+dd.getDeviceByID(8).getItemByID(2).getUnit());
		Mppt2VoutAnswer.setText(dd.getDeviceByID(8).getItemByID(2).getLastData() +" "+dd.getDeviceByID(8).getItemByID(2).getUnit());
		Mppt3VoutAnswer.setText(dd.getDeviceByID(8).getItemByID(2).getLastData() +" "+dd.getDeviceByID(8).getItemByID(2).getUnit());
		
		Mppt1CoutAnswer.setText(dd.getDeviceByID(9).getItemByID(3).getLastData() +" "+dd.getDeviceByID(9).getItemByID(3).getUnit());
		Mppt2CoutAnswer.setText(dd.getDeviceByID(9).getItemByID(3).getLastData() +" "+dd.getDeviceByID(9).getItemByID(3).getUnit());
		Mppt3CoutAnswer.setText(dd.getDeviceByID(9).getItemByID(3).getLastData() +" "+dd.getDeviceByID(9).getItemByID(3).getUnit());
		
		//calcul de puisance
		
		double  Mppt1P = (dd.getDeviceByID(8).getItemByID(2).getLastData()*dd.getDeviceByID(8).getItemByID(3).getLastData());
		
		double  Mppt2P = (dd.getDeviceByID(9).getItemByID(2).getLastData()*dd.getDeviceByID(9).getItemByID(3).getLastData());
		
		double  Mppt3P = (dd.getDeviceByID(10).getItemByID(2).getLastData()*dd.getDeviceByID(10).getItemByID(3).getLastData());
		
		double  PtotalMppt = (dd.getDeviceByID(8).getItemByID(3).getLastData()+dd.getDeviceByID(9).getItemByID(3).getLastData()+
				dd.getDeviceByID(10).getItemByID(3).getLastData())*dd.getDeviceByID(8).getItemByID(2).getLastData();
		
		
		Mppt1PowerAnswer.setText(String.format("%.2f", Mppt1P)+" W");
		Mppt2PowerAnswer.setText(String.format("%.2f", Mppt2P)+" W");
		Mppt3PowerAnswer.setText(String.format("%.2f", Mppt3P)+" W");
		
		PtotalAnswer.setText(String.format("%.2f", PtotalMppt)+" W");
		
		courantOutAnwser.setText(dd.getDeviceByID(7).getItemByID(60).getLastData() +" "+dd.getDeviceByID(7).getItemByID(60).getUnit());
		
		LAT.setText(Double.toString(dd.getDeviceByID(6).getItemByID(2).getLastData()));
		LON.setText(Double.toString(dd.getDeviceByID(6).getItemByID(3).getLastData()));
		String date =Double.toString(dd.getDeviceByID(6).getItemByID(5).getLastData());
		String heure =Double.toString(dd.getDeviceByID(6).getItemByID(4).getLastData());
		int delay =Integer.parseInt(TelemetrySettings.getInstance().getSetting("DELAY_TIME"));
		if(Double.parseDouble(date)>9999){
			DATE.setText(date.substring(0, 2)+":"+date.substring(2,4)+":"+date.substring(4,6));
			HEURE.setText((Integer.parseInt(heure.substring(0, 2))+delay)+":"+heure.substring(2,4)+":"+heure.substring(4,6));
		}
		
		//calcul de la vitesse
		
		double speed1 = (dd.getDeviceByID(12).getItemByID(10).getLastData())/1000*60*60;
		
		double speed2 = (dd.getDeviceByID(13).getItemByID(10).getLastData())/1000*60*60;
		
		double speed3 = (dd.getDeviceByID(14).getItemByID(10).getLastData())/1000*60*60;
		
		double speed4 = (dd.getDeviceByID(15).getItemByID(10).getLastData())/1000*60*60;
		
		double speedmoy = (speed1 + speed2 + speed3 + speed4)/4; 
		
		VitesseAnwser.setText(String.format("%.2f", speedmoy)+" Km/h");
		CommandeAnwser.setText(Integer.toString((int) dd.getDeviceByID(1).getItemByID(1).getLastData()));
		
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
