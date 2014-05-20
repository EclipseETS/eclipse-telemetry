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
	
	private static final int LABEL_WIDTH = 100;
	private static final int LABEL_HEIGHT = 14;
	private static final int LINE_OFFSET = 20;
	
	private static final int PSU_ID = 7;
	private static final int PSU_X = 850;
	private static final int PSU_X_VALUE = 940;
	private static final int PSU_Y = 480;
	private static final int PSU_ICAN_ID = 2;
	private static final int PSU_VCAN_ID = 3;
	
	private static final int MPPT1_ID = 8;
	private static final int MPPT1_X = 50;
	private static final int MPPT1_X_VALUE = 100;
	private static final int MPPT1_Y = 480;
	
	private static final int MPPT2_ID = 9;
	private static final int MPPT2_X = 250;
	private static final int MPPT2_X_VALUE = 300;
	private static final int MPPT2_Y = 480;
	
	private static final int MPPT3_ID = 10;
	private static final int MPPT3_X = 450;
	private static final int MPPT3_X_VALUE = 500;
	private static final int MPPT3_Y = 480;
	
	 private Image img;
	 
	 //TODO ajouter temperature du char
	 
 	JLabel Status = new JLabel("Status:");
 	JLabel StatusoAnswer = new JLabel("status");
 	
	JLabel DrTmp = new JLabel("Heat Sink Tmp :");
	JLabel DrTmpAnwser = new JLabel();
	
	JLabel Dsptmp = new JLabel("DSP Tmp :");
	JLabel DSPTmpAnswer = new JLabel("DSP Tmp");
	
	JLabel MotorTmp = new JLabel("Motor Tmp:");
	JLabel MotorTmpAnswer = new JLabel();
	
	JLabel Rmp = new JLabel("RPM:");			
	JLabel RmpAnswer = new JLabel();
		

	//------------------//
		
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
	
	
	/*MPPT1*/
	JLabel MPPT1_Label = new JLabel("MPPT1");

	JLabel MPPT1_Vin = new JLabel("Vin :");
	JLabel MPPT1_Vin_Value = new JLabel("");
	
	JLabel MPPT1_Vout = new JLabel("Vout :");
	JLabel MPPT1_Vout_Value = new JLabel("");
	
	JLabel MPPT1_Iout = new JLabel("Iout :");
	JLabel MPPT1_Iout_Value = new JLabel("");
	
	JLabel MPPT1_Temp = new JLabel("Temp :");
	JLabel MPPT1_Temp_Value = new JLabel("");
	
	/*MPPT2*/
	JLabel MPPT2_Label = new JLabel("MPPT2");

	JLabel MPPT2_Vin = new JLabel("Vin :");
	JLabel MPPT2_Vin_Value = new JLabel("");
	
	JLabel MPPT2_Vout = new JLabel("Vout :");
	JLabel MPPT2_Vout_Value = new JLabel("");
	
	JLabel MPPT2_Iout = new JLabel("Iout :");
	JLabel MPPT2_Iout_Value = new JLabel("");
	
	JLabel MPPT2_Temp = new JLabel("Temp :");
	JLabel MPPT2_Temp_Value = new JLabel("");
	
	/*MPPT3*/
	JLabel MPPT3_Label = new JLabel("MPPT3");

	JLabel MPPT3_Vin = new JLabel("Vin :");
	JLabel MPPT3_Vin_Value = new JLabel("");
	
	JLabel MPPT3_Vout = new JLabel("Vout :");
	JLabel MPPT3_Vout_Value = new JLabel("");
	
	JLabel MPPT3_Iout = new JLabel("Iout :");
	JLabel MPPT3_Iout_Value = new JLabel("");
	
	JLabel MPPT3_Temp = new JLabel("Temp :");
	JLabel MPPT3_Temp_Value = new JLabel("");
	
	/*PSU*/
	JLabel PSU_Label = new JLabel("PSU");
	
	JLabel PSU_ICAN = new JLabel("CAN Current :");
	JLabel PSU_ICAN_Value = new JLabel("");
	
	JLabel PSU_VCAN = new JLabel("CAN Voltage :");
	JLabel PSU_VCAN_Value = new JLabel("");
	
	/*Drive*/
	JLabel Drive_Label = new JLabel("Drive");
	
	JLabel Drive_ErrorFlags = new JLabel("Error Flags : ");
	JLabel Drive_ErrorFlags_Value = new JLabel("");
	
	JLabel Drive_LimitFlags = new JLabel("Limit Flags : ");
	JLabel Drive_LimitFlags_Value = new JLabel("");
	 
	public Tabchar() {
		setBackground(Color.WHITE);

		
		setForeground(Color.BLACK);
		setLayout(null);
		
		/*MPPT1*/
		MPPT1_Label.setBounds(MPPT1_X, MPPT1_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT1_Label);
		
		MPPT1_Vin.setBounds(MPPT1_X, MPPT1_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT1_Vin);
		MPPT1_Vin_Value.setBounds(MPPT1_X_VALUE, MPPT1_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT1_Vin_Value);
		
		MPPT1_Vout.setBounds(MPPT1_X, MPPT1_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT1_Vout);
		MPPT1_Vout_Value.setBounds(MPPT1_X_VALUE, MPPT1_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT1_Vout_Value);
		
		MPPT1_Iout.setBounds(MPPT1_X, MPPT1_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT1_Iout);
		MPPT1_Iout_Value.setBounds(MPPT1_X_VALUE,  MPPT1_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT1_Iout_Value);
		
		MPPT1_Temp.setBounds(MPPT1_X, MPPT1_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT1_Temp);
		MPPT1_Temp_Value.setBounds(MPPT1_X_VALUE,  MPPT1_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT1_Temp_Value);
		
		/*MPPT2*/
		MPPT2_Label.setBounds(MPPT2_X, MPPT2_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT2_Label);
		
		MPPT2_Vin.setBounds(MPPT2_X, MPPT2_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT2_Vin);
		MPPT2_Vin_Value.setBounds(MPPT2_X_VALUE, MPPT2_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT2_Vin_Value);
		
		MPPT2_Vout.setBounds(MPPT2_X, MPPT2_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT2_Vout);
		MPPT2_Vout_Value.setBounds(MPPT2_X_VALUE, MPPT2_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT2_Vout_Value);
		
		MPPT2_Iout.setBounds(MPPT2_X, MPPT2_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT2_Iout);
		MPPT2_Iout_Value.setBounds(MPPT2_X_VALUE,  MPPT2_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT2_Iout_Value);
		
		MPPT2_Temp.setBounds(MPPT2_X, MPPT2_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT2_Temp);
		MPPT2_Temp_Value.setBounds(MPPT2_X_VALUE,  MPPT2_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT2_Temp_Value);
		
		/*MPPT3*/
		MPPT3_Label.setBounds(MPPT3_X, MPPT3_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT3_Label);
		
		MPPT3_Vin.setBounds(MPPT3_X, MPPT3_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT3_Vin);
		MPPT3_Vin_Value.setBounds(MPPT3_X_VALUE, MPPT3_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT3_Vin_Value);
		
		MPPT3_Vout.setBounds(MPPT3_X, MPPT3_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT3_Vout);
		MPPT3_Vout_Value.setBounds(MPPT3_X_VALUE, MPPT3_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT3_Vout_Value);
		
		MPPT3_Iout.setBounds(MPPT3_X, MPPT3_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT3_Iout);
		MPPT3_Iout_Value.setBounds(MPPT3_X_VALUE,  MPPT3_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT3_Iout_Value);
		
		MPPT3_Temp.setBounds(MPPT3_X, MPPT3_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT3_Temp);
		MPPT3_Temp_Value.setBounds(MPPT3_X_VALUE,  MPPT3_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(MPPT3_Temp_Value);;
		
		/*PSU*/
		PSU_Label.setBounds(PSU_X, PSU_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(PSU_Label);
		
		PSU_ICAN.setBounds(PSU_X, PSU_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(PSU_ICAN);
		PSU_ICAN_Value.setBounds(PSU_X_VALUE, PSU_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(PSU_ICAN_Value);
		
		PSU_VCAN.setBounds(PSU_X, PSU_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(PSU_VCAN);
		PSU_VCAN_Value.setBounds(PSU_X_VALUE, PSU_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(PSU_VCAN_Value);
		
		/*Drive*/
		Drive_Label.setBounds(1050, 480, 100, 14);
		add(Drive_Label);
		
		Drive_ErrorFlags.setBounds(1050, 500, 100, 14);
		add(Drive_ErrorFlags);
		Drive_LimitFlags.setBounds(1050, 520, 100, 14);
		add(Drive_LimitFlags);
		
		
		Status.setBounds(500, 100, 100, 14);
		add(Status);
		
		DrTmp.setBounds(800, 80, 100, 14);
		add(DrTmp);
		
		Dsptmp.setBounds(800, 40, 100, 14);
		add(Dsptmp);
		
		MotorTmp.setBounds(800, 60, 100, 14);
		add(MotorTmp);
		
		Rmp.setBounds(800, 20, 46, 14);
		add(Rmp);
		
		StatusoAnswer.setBounds(900, 100, 100, 14);
		add(StatusoAnswer);
		
		DrTmpAnwser.setBounds(900, 80, 100, 14);
		add(DrTmpAnwser);
		
		MotorTmpAnswer.setBounds(900, 60, 100, 14);
		add(MotorTmpAnswer);
		
		DSPTmpAnswer.setBounds(900, 40, 100, 14);
		add(DSPTmpAnswer);
		
		RmpAnswer.setBounds(900, 20, 46, 14);
		add(RmpAnswer);

		//------------------//
		
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

		/*MPPT1*/
		MPPT1_Vin_Value.setText(dd.getDeviceByID(MPPT1_ID).getItemByID(1).getLastData() + " " + dd.getDeviceByID(MPPT1_ID).getItemByID(1).getUnit());
		MPPT1_Vout_Value.setText(dd.getDeviceByID(MPPT1_ID).getItemByID(2).getLastData() + " " + dd.getDeviceByID(MPPT1_ID).getItemByID(2).getUnit());
		MPPT1_Iout_Value.setText(dd.getDeviceByID(MPPT1_ID).getItemByID(3).getLastData() + " " + dd.getDeviceByID(MPPT1_ID).getItemByID(3).getUnit());
		MPPT1_Temp_Value.setText(dd.getDeviceByID(MPPT1_ID).getItemByID(4).getLastData() + " " + dd.getDeviceByID(MPPT1_ID).getItemByID(4).getUnit());
		
		/*MPPT2*/
		MPPT2_Vin_Value.setText(dd.getDeviceByID(MPPT2_ID).getItemByID(1).getLastData() + " " + dd.getDeviceByID(MPPT2_ID).getItemByID(1).getUnit());
		MPPT2_Vout_Value.setText(dd.getDeviceByID(MPPT2_ID).getItemByID(2).getLastData() + " " + dd.getDeviceByID(MPPT2_ID).getItemByID(2).getUnit());
		MPPT2_Iout_Value.setText(dd.getDeviceByID(MPPT2_ID).getItemByID(3).getLastData() + " " + dd.getDeviceByID(MPPT2_ID).getItemByID(3).getUnit());
		MPPT2_Temp_Value.setText(dd.getDeviceByID(MPPT2_ID).getItemByID(4).getLastData() + " " + dd.getDeviceByID(MPPT2_ID).getItemByID(4).getUnit());
		
		/*MPPT3*/
		MPPT3_Vin_Value.setText(dd.getDeviceByID(MPPT3_ID).getItemByID(1).getLastData() + " " + dd.getDeviceByID(MPPT3_ID).getItemByID(1).getUnit());
		MPPT3_Vout_Value.setText(dd.getDeviceByID(MPPT3_ID).getItemByID(2).getLastData() + " " + dd.getDeviceByID(MPPT3_ID).getItemByID(2).getUnit());
		MPPT3_Iout_Value.setText(dd.getDeviceByID(MPPT3_ID).getItemByID(3).getLastData() + " " + dd.getDeviceByID(MPPT3_ID).getItemByID(3).getUnit());
		MPPT3_Temp_Value.setText(dd.getDeviceByID(MPPT3_ID).getItemByID(4).getLastData() + " " + dd.getDeviceByID(MPPT3_ID).getItemByID(4).getUnit());

		
		/*PSU*/
		PSU_ICAN_Value.setText(dd.getDeviceByID(PSU_ID).getItemByID(PSU_ICAN_ID).getLastData() + " " + dd.getDeviceByID(PSU_ID).getItemByID(PSU_ICAN_ID).getUnit());
		PSU_VCAN_Value.setText(dd.getDeviceByID(PSU_ID).getItemByID(PSU_VCAN_ID).getLastData() + " " + dd.getDeviceByID(PSU_ID).getItemByID(PSU_VCAN_ID).getUnit());
		
		/*Drive*/
		//Drive_ErrorFlags_Value.setBounds(1050, 500, 100, 14);
		//Drive_LimitFlags_Value.setBounds(1050, 520, 100, 14);

		/*
		
		DrTmpAnwser.setText(dd.getDeviceByID(2).getItemByID(23).getLastData() +" "+dd.getDeviceByID(2).getItemByID(23).getUnit());	
		MotorTmpAnswer.setText(dd.getDeviceByID(2).getItemByID(24).getLastData() +" "+dd.getDeviceByID(2).getItemByID(24).getUnit());	
		RmpAnswer.setText(dd.getDeviceByID(2).getItemByID(10).getLastData() +" "+dd.getDeviceByID(2).getItemByID(10).getUnit());
		DSPTmpAnswer.setText(dd.getDeviceByID(2).getItemByID(26).getLastData() +" "+dd.getDeviceByID(2).getItemByID(26).getUnit());
		
	
		

		
		pileMinAnwser.setText(dd.getDeviceByID(7).getItemByID(42).getLastData() +" "+dd.getDeviceByID(7).getItemByID(42).getUnit());
		pileMaxAnwser.setText(dd.getDeviceByID(7).getItemByID(43).getLastData() +" "+dd.getDeviceByID(7).getItemByID(43).getUnit());
		pileTotalAnwser.setText(dd.getDeviceByID(7).getItemByID(44).getLastData() +" "+dd.getDeviceByID(7).getItemByID(44).getUnit());
		courantOutAnwser.setText(dd.getDeviceByID(7).getItemByID(48).getLastData() +" "+dd.getDeviceByID(7).getItemByID(47).getUnit());
		
		LAT.setText(Double.toString(dd.getDeviceByID(6).getItemByID(2).getLastData()));
		LON.setText(Double.toString(dd.getDeviceByID(6).getItemByID(3).getLastData()));
		String date =Double.toString(dd.getDeviceByID(6).getItemByID(5).getLastData());
		String heure =Double.toString(dd.getDeviceByID(6).getItemByID(4).getLastData());
		int delay =Integer.parseInt(TelemetrySettings.getInstance().getSetting("DELAY_TIME"));
		if(Double.parseDouble(date)>9999){
			DATE.setText(date.substring(0, 2)+":"+date.substring(2,4)+":"+date.substring(4,6));
			HEURE.setText((Integer.parseInt(heure.substring(0, 2))+delay)+":"+heure.substring(2,4)+":"+heure.substring(4,6));
		}
		
		double speed1 = (dd.getDeviceByID(12).getItemByID(10).getLastData())/1000*60*60;
		
		double speed2 = (dd.getDeviceByID(13).getItemByID(10).getLastData())/1000*60*60;
		
		double speed3 = (dd.getDeviceByID(14).getItemByID(10).getLastData())/1000*60*60;
		
		double speed4 = (dd.getDeviceByID(15).getItemByID(10).getLastData())/1000*60*60;
		
		double speedmoy = (speed1 + speed2 + speed3 + speed4)/4; 
		
		VitesseAnwser.setText(String.format("%.2f", speedmoy)+" Km/h");
		CommandeAnwser.setText(Integer.toString((int) dd.getDeviceByID(5).getItemByID(4).getLastData()));
		
		Integer puiss = (int) (dd.getDeviceByID(7).getItemByID(44).getLastData()*dd.getDeviceByID(7).getItemByID(47).getLastData());
		
		if (puiss>0)
			wattAnwser.setForeground(Color.red);
		else
			wattAnwser.setForeground(Color.green);
		wattAnwser.setText(Integer.toString(puiss)+" W");
		
		*/
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
