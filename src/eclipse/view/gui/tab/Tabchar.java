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
	private static final int PSU_X = 1050;
	private static final int PSU_X_VALUE = 1140;
	private static final int PSU_Y = 400;
	private static final int PSU_ICAN_ID = 2;
	private static final int PSU_VCAN_ID = 3;
	
	private static final int MPPT_VIN_ID = 1;
	private static final int MPPT_VOUT_ID = 2;
	private static final int MPPT_IOUT_ID = 3;
	private static final int MPPT_TEMP_ID = 4;
	
	private static final int MPPT1_ID = 8;
	private static final int MPPT1_X = 50;
	private static final int MPPT1_X_VALUE = 100;
	private static final int MPPT1_Y = 400;
	private static final int MPPT1_VIN_ID = 1;

	private static final int MPPT2_ID = 9;
	private static final int MPPT2_X = 250;
	private static final int MPPT2_X_VALUE = 300;
	private static final int MPPT2_Y = 400;
	
	private static final int MPPT3_ID = 10;
	private static final int MPPT3_X = 450;
	private static final int MPPT3_X_VALUE = 500;
	private static final int MPPT3_Y = 400;
	
	private static final int DRIVE_ID = 2;
	private static final int DRIVE_X = 50;
	private static final int DRIVE_X_VALUE = 150;
	private static final int DRIVE_Y = 50;
	private static final int DRIVE_ERRORFLAGS_ID = 5;
	private static final int DRIVE_LIMITFLAGS_ID = 6;
	private static final int DRIVE_RPM_ID = 10;
	private static final int DRIVE_HSTEMP_ID = 23;
	private static final int DRIVE_MOTORTEMP_ID = 24;
	private static final int DRIVE_DSPTEMP_ID = 26;
	
	private static final int INSTRU_ID = 6;
	private static final int INSTRU_X = 1050;
	private static final int INSTRU_X_VALUE = 1100;
	private static final int INSTRU_Y = 50;
	private static final int INSTRU_LAT_ID = 2;
	private static final int INSTRU_LON_ID = 3;
	private static final int INSTRU_TIME_ID = 4;
	private static final int INSTRU_DATE_ID = 5;
	
	private static final int BMS_ID = 3;
	private static final int BMS_X = 325;
	private static final int BMS_X_2 = 500;
	private static final int BMS_X_VALUE = 400;
	private static final int BMS_X_2_VALUE = 580;
	private static final int BMS_Y = 50;
	private static final int BMS_MAXCELLV_ID = 64;
	private static final int BMS_MINCELLV_ID = 65;
	private static final int BMS_MAXCELLT_ID = 70;
	private static final int BMS_SOCPC_ID = 46;
	private static final int BMS_SOCAH_ID = 47;
	private static final int BMS_PACKA_ID = 72;
	private static final int BMS_PACKV_ID = 73;
	private static final int BMS_STATUS_ID = 76;
	private static final int BMS_EXTSTATUS_ID = 86;
	
	
	
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
	JLabel MPPT1_Label = new JLabel("[MPPT1]");

	JLabel MPPT1_Vin = new JLabel("Vin :");
	JLabel MPPT1_Vin_Value = new JLabel("");
	
	JLabel MPPT1_Vout = new JLabel("Vout :");
	JLabel MPPT1_Vout_Value = new JLabel("");
	
	JLabel MPPT1_Iout = new JLabel("Iout :");
	JLabel MPPT1_Iout_Value = new JLabel("");
	
	JLabel MPPT1_Temp = new JLabel("Temp :");
	JLabel MPPT1_Temp_Value = new JLabel("");
	
	/*MPPT2*/
	JLabel MPPT2_Label = new JLabel("[MPPT2]");

	JLabel MPPT2_Vin = new JLabel("Vin :");
	JLabel MPPT2_Vin_Value = new JLabel("");
	
	JLabel MPPT2_Vout = new JLabel("Vout :");
	JLabel MPPT2_Vout_Value = new JLabel("");
	
	JLabel MPPT2_Iout = new JLabel("Iout :");
	JLabel MPPT2_Iout_Value = new JLabel("");
	
	JLabel MPPT2_Temp = new JLabel("Temp :");
	JLabel MPPT2_Temp_Value = new JLabel("");
	
	/*MPPT3*/
	JLabel MPPT3_Label = new JLabel("[MPPT3]");

	JLabel MPPT3_Vin = new JLabel("Vin :");
	JLabel MPPT3_Vin_Value = new JLabel("");
	
	JLabel MPPT3_Vout = new JLabel("Vout :");
	JLabel MPPT3_Vout_Value = new JLabel("");
	
	JLabel MPPT3_Iout = new JLabel("Iout :");
	JLabel MPPT3_Iout_Value = new JLabel("");
	
	JLabel MPPT3_Temp = new JLabel("Temp :");
	JLabel MPPT3_Temp_Value = new JLabel("");
	
	/*PSU*/
	JLabel PSU_Label = new JLabel("[PSU]");
	
	JLabel PSU_ICAN = new JLabel("CAN Current :");
	JLabel PSU_ICAN_Value = new JLabel("");
	
	JLabel PSU_VCAN = new JLabel("CAN Voltage :");
	JLabel PSU_VCAN_Value = new JLabel("");
	
	/*Drive*/
	JLabel Drive_Label = new JLabel("[Drive]");
	
	JLabel Drive_ErrorFlags = new JLabel("Error Flags : ");
	JLabel Drive_ErrorFlags_Value = new JLabel("");
	
	JLabel Drive_LimitFlags = new JLabel("Limit Flags : ");
	JLabel Drive_LimitFlags_Value = new JLabel("");
	
	JLabel Drive_RPM = new JLabel("RPM : ");
	JLabel Drive_RPM_Value = new JLabel("");
	
	JLabel Drive_HSTemp = new JLabel("Heat Sink Temp : ");
	JLabel Drive_HSTemp_Value = new JLabel("");
	
	JLabel Drive_MotorTemp = new JLabel("Motor Temp : ");
	JLabel Drive_MotorTemp_Value = new JLabel("");
	
	JLabel Drive_DSPTemp = new JLabel("DSP Temp : ");
	JLabel Drive_DSPTemp_Value = new JLabel("");
	
	/*Instru*/
	JLabel Instru_Label = new JLabel("[Instru]");
	
	JLabel Instru_Lat = new JLabel("Lat : ");
	JLabel Instru_Lat_Value = new JLabel("");
	
	JLabel Instru_Lon = new JLabel("Lon : ");
	JLabel Instru_Lon_Value = new JLabel("");
	
	JLabel Instru_Time = new JLabel("Time : ");
	JLabel Instru_Time_Value = new JLabel("");
	
	JLabel Instru_Date = new JLabel("Date : ");
	JLabel Instru_Date_Value = new JLabel("");
	
	/*BMS*/
	JLabel BMS_Label = new JLabel("[BMS]");
	
	JLabel BMS_MaxCellV = new JLabel("Cell Vmax : ");
	JLabel BMS_MaxCellV_Value = new JLabel("");
	
	JLabel BMS_MinCellV = new JLabel("Cell Vmin : ");
	JLabel BMS_MinCellV_Value = new JLabel("");
	
	JLabel BMS_MaxCellT = new JLabel("Cell Tmax : ");
	JLabel BMS_MaxCellT_Value = new JLabel("");
	
	JLabel BMS_MaxPCBT = new JLabel("PCB Tmax : ");
	JLabel BMS_MaxPCBT_Value = new JLabel("");
	
	JLabel BMS_SOCPc = new JLabel("SoC % : ");
	JLabel BMS_SOCPc_Value = new JLabel("");
	
	JLabel BMS_SOCAh = new JLabel("SoC Ah: ");
	JLabel BMS_SOCAh_Value = new JLabel("");
	
	JLabel BMS_PackV = new JLabel("Total VPack : ");
	JLabel BMS_PackV_Value = new JLabel("");
	
	JLabel BMS_PackA = new JLabel("Total APack : ");
	JLabel BMS_PackA_Value = new JLabel("");
	
	JLabel BMS_Status = new JLabel("Status : ");
	JLabel BMS_Status_Value = new JLabel("");
	
	JLabel BMS_ExtStatus = new JLabel("Ext. Status : ");
	JLabel BMS_ExtStatus_Value = new JLabel("");
	 
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
		Drive_Label.setBounds(DRIVE_X, DRIVE_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(Drive_Label);
		
		Drive_RPM.setBounds(DRIVE_X, DRIVE_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Drive_RPM);
		Drive_RPM_Value.setBounds(DRIVE_X_VALUE, DRIVE_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Drive_RPM_Value);
		
		Drive_HSTemp.setBounds(DRIVE_X, DRIVE_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Drive_HSTemp);
		Drive_HSTemp_Value.setBounds(DRIVE_X_VALUE, DRIVE_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Drive_HSTemp_Value);
		
		Drive_MotorTemp.setBounds(DRIVE_X, DRIVE_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Drive_MotorTemp);
		Drive_MotorTemp_Value.setBounds(DRIVE_X_VALUE, DRIVE_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Drive_MotorTemp_Value);
		
		Drive_DSPTemp.setBounds(DRIVE_X, DRIVE_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Drive_DSPTemp);
		Drive_DSPTemp_Value.setBounds(DRIVE_X_VALUE, DRIVE_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Drive_DSPTemp_Value);
		
		Drive_ErrorFlags.setBounds(DRIVE_X, DRIVE_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Drive_ErrorFlags);
		Drive_ErrorFlags_Value.setBounds(DRIVE_X_VALUE, DRIVE_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Drive_ErrorFlags_Value);
		
		Drive_LimitFlags.setBounds(DRIVE_X, DRIVE_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Drive_LimitFlags);
		Drive_LimitFlags_Value.setBounds(DRIVE_X_VALUE, DRIVE_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Drive_LimitFlags_Value);
		
		/*Instru*/
		Instru_Label.setBounds(INSTRU_X, INSTRU_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(Instru_Label);
		
		Instru_Lat.setBounds(INSTRU_X, INSTRU_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Instru_Lat);
		Instru_Lat_Value.setBounds(INSTRU_X_VALUE, INSTRU_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Instru_Lat_Value);
		
		Instru_Lon.setBounds(INSTRU_X, INSTRU_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Instru_Lon);
		Instru_Lon_Value.setBounds(INSTRU_X_VALUE, INSTRU_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Instru_Lon_Value);
		
		Instru_Time.setBounds(INSTRU_X, INSTRU_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Instru_Time);
		Instru_Time_Value.setBounds(INSTRU_X_VALUE, INSTRU_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Instru_Time_Value);
		
		Instru_Date.setBounds(INSTRU_X, INSTRU_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Instru_Date);
		Instru_Date_Value.setBounds(INSTRU_X_VALUE, INSTRU_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Instru_Date_Value);
		
		/*BMS*/
		BMS_Label.setBounds(BMS_X, BMS_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_Label);
		
		BMS_MaxCellV.setBounds(BMS_X, BMS_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_MaxCellV);
		BMS_MaxCellV_Value.setBounds(BMS_X_VALUE, BMS_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_MaxCellV_Value);
		
		BMS_MinCellV.setBounds(BMS_X, BMS_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_MinCellV);
		BMS_MinCellV_Value.setBounds(BMS_X_VALUE, BMS_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_MinCellV_Value);
		
		BMS_SOCAh.setBounds(BMS_X, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_SOCAh);
		BMS_SOCAh_Value.setBounds(BMS_X_VALUE, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_SOCAh_Value);
		
		BMS_SOCPc.setBounds(BMS_X, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_SOCPc);
		BMS_SOCPc_Value.setBounds(BMS_X_VALUE, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_SOCPc_Value);
		
		BMS_Status.setBounds(BMS_X, BMS_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_Status);
		BMS_Status_Value.setBounds(BMS_X_VALUE, BMS_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_Status_Value);
		
		BMS_MaxCellT.setBounds(BMS_X_2, BMS_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_MaxCellT);
		BMS_MaxCellT_Value.setBounds(BMS_X_2_VALUE, BMS_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_MaxCellT_Value);
		
		BMS_MaxPCBT.setBounds(BMS_X_2, BMS_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_MaxPCBT);
		BMS_MaxPCBT_Value.setBounds(BMS_X_2_VALUE, BMS_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_MaxPCBT_Value);
		
		BMS_PackV.setBounds(BMS_X_2, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_PackV);
		BMS_PackV_Value.setBounds(BMS_X_2_VALUE, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_PackV_Value);
		
		BMS_PackA.setBounds(BMS_X_2, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_PackA);
		BMS_PackA_Value.setBounds(BMS_X_2_VALUE, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_PackA_Value);
		
		BMS_ExtStatus.setBounds(BMS_X_2, BMS_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_ExtStatus);
		BMS_ExtStatus_Value.setBounds(BMS_X_2_VALUE, BMS_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_ExtStatus_Value);
		
		/*Status.setBounds(500, 100, 100, 14);
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
		add(CommandeAnwser);*/
				
			 
		
		img=new ImageIcon("images/image.png").getImage();
		  

		
	}

	private static final long serialVersionUID = 7275321077445435378L;
	public void updateValues() {
		
		DataManager dd = DataManager.getInstance();

		/*MPPT1*/
		MPPT1_Vin_Value.setText(dd.getValue(MPPT1_ID, MPPT_VIN_ID));
		MPPT1_Vout_Value.setText(dd.getValue(MPPT1_ID, MPPT_VOUT_ID));
		MPPT1_Iout_Value.setText(dd.getValue(MPPT1_ID, MPPT_IOUT_ID));
		MPPT1_Temp_Value.setText(dd.getValue(MPPT1_ID, MPPT_TEMP_ID));
		
		/*MPPT2*/
		MPPT2_Vin_Value.setText(dd.getValue(MPPT2_ID, MPPT_VIN_ID));
		MPPT2_Vout_Value.setText(dd.getValue(MPPT2_ID, MPPT_VOUT_ID));
		MPPT2_Iout_Value.setText(dd.getValue(MPPT2_ID, MPPT_IOUT_ID));
		MPPT2_Temp_Value.setText(dd.getValue(MPPT2_ID, MPPT_TEMP_ID));
		
		/*MPPT3*/
		MPPT3_Vin_Value.setText(dd.getValue(MPPT3_ID, MPPT_VIN_ID));
		MPPT3_Vout_Value.setText(dd.getValue(MPPT3_ID, MPPT_VOUT_ID));
		MPPT3_Iout_Value.setText(dd.getValue(MPPT3_ID, MPPT_IOUT_ID));
		MPPT3_Temp_Value.setText(dd.getValue(MPPT3_ID, MPPT_TEMP_ID));
		
		/*PSU*/
		PSU_ICAN_Value.setText(dd.getValue(PSU_ID, PSU_ICAN_ID));
		PSU_VCAN_Value.setText(dd.getValue(PSU_ID, PSU_VCAN_ID));
		
		/*Drive*/
		Drive_RPM_Value.setText(dd.getValue(DRIVE_ID, DRIVE_RPM_ID));
		Drive_HSTemp_Value.setText(dd.getValue(DRIVE_ID, DRIVE_HSTEMP_ID));
		Drive_MotorTemp_Value.setText(dd.getValue(DRIVE_ID, DRIVE_MOTORTEMP_ID));
		Drive_DSPTemp_Value.setText(dd.getValue(DRIVE_ID, DRIVE_DSPTEMP_ID));
		Drive_ErrorFlags_Value.setText(dd.getValue(DRIVE_ID, DRIVE_ERRORFLAGS_ID));
		Drive_LimitFlags_Value.setText(dd.getValue(DRIVE_ID, DRIVE_LIMITFLAGS_ID));
		
		/*Instru*/
		Instru_Lat_Value.setText(dd.getValue(INSTRU_ID, INSTRU_LAT_ID));
		Instru_Lon_Value.setText(dd.getValue(INSTRU_ID, INSTRU_LON_ID));
		Instru_Time_Value.setText(dd.getValue(INSTRU_ID, INSTRU_TIME_ID));
		Instru_Date_Value.setText(dd.getValue(INSTRU_ID, INSTRU_DATE_ID));
		
		/*BMS*/
		BMS_MaxCellV_Value.setText(dd.getValue(BMS_ID, BMS_MAXCELLV_ID));
		BMS_MinCellV_Value.setText(dd.getValue(BMS_ID, BMS_MINCELLV_ID));
		BMS_SOCPc_Value.setText(dd.getValue(BMS_ID, BMS_SOCPC_ID));
		BMS_SOCAh_Value.setText(dd.getValue(BMS_ID, BMS_SOCAH_ID));
		BMS_Status_Value.setText(dd.getValue(BMS_ID, BMS_STATUS_ID));
		
		BMS_MaxCellT_Value.setText(dd.getValue(BMS_ID, BMS_MAXCELLT_ID));
		//BMS_MaxPCBT_Value.setText(dd.getValue(BMS_ID, BMS_MAXPCBT_ID));
		BMS_PackV_Value.setText(dd.getValue(BMS_ID, BMS_PACKV_ID));
		BMS_PackA_Value.setText(dd.getValue(BMS_ID, BMS_PACKA_ID));
		BMS_ExtStatus_Value.setText(dd.getValue(BMS_ID, BMS_EXTSTATUS_ID));
		
		
		
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
