package eclipse.view.gui.tab;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import eclipse.controller.util.TelemetrySettings;
import eclipse.model.data.DataManager;
/**
 * This view show all important information on 1 single view
 * 
 * -----------------------------------------
 * Value of type anwser are the one modified, the one with the actual value.
 * exemple 
 * Speed: 100km/h
 * String speed = "Speed: "
 * String speedAnwser = 100km/h
 * speedAnwser can be initiated to a random value but that is the value that will be modified
 * as the data came in.
 * 
 * @author Marco
 * @author Olivier
 *
 */
public class Tabchar extends JPanel implements TabPane {
		
	private static final long serialVersionUID = 7275321077445435378L;
	
	private static final int LABEL_WIDTH = 100;
	private static final int LABEL_HEIGHT = 14;
	private static final int LINE_OFFSET = 20;
	
	/*MPPT*/
	private static final int MPPT_VIN_ID = 1;
	private static final int MPPT_VOUT_ID = 2;
	private static final int MPPT_IOUT_ID = 3;
	private static final int MPPT_TEMP_ID = 4;
	
	/*MPPT1*/
	private static final int MPPT1_ID = 8;
	private static final int MPPT1_X = 50;
	private static final int MPPT1_X_VALUE = 100;
	private static final int MPPT1_Y = 400;

	/*MPPT2*/
	private static final int MPPT2_ID = 12;
	private static final int MPPT2_X = 250;
	private static final int MPPT2_X_VALUE = 300;
	private static final int MPPT2_Y = 400;
	
	/*MPPT3*/
	private static final int MPPT3_ID = 10;
	private static final int MPPT3_X = 450;
	private static final int MPPT3_X_VALUE = 500;
	private static final int MPPT3_Y = 400;
	
	/*PSU*/
	private static final int PSU_ID = 7;
	private static final int PSU_X = 1050;
	private static final int PSU_X_VALUE = 1140;
	private static final int PSU_Y = 400;
	private static final int PSU_ACAN_ID = 2;
	private static final int PSU_VCAN_ID = 3;
	private static final int PSU_AHP_ID = 4;
	private static final int PSU_VHP_ID = 5;
	
	/*Drive*/
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
	private static final int DRIVE_ABUS_ID = 7;
	private static final int DRIVE_VBUS_ID = 8;
	
	/*Instru*/
	private static final int INSTRU_ID = 6;
	private static final int INSTRU_X = 1050;
	private static final int INSTRU_X_VALUE = 1100;
	private static final int INSTRU_Y = 50;
	private static final int INSTRU_LAT_ID = 2;
	private static final int INSTRU_LON_ID = 3;
	private static final int INSTRU_TIME_ID = 4;
	private static final int INSTRU_DATE_ID = 5;
	
	/*BMS*/
	private static final int BMS_ID = 3;
	private static final int BMS_X = 325;
	private static final int BMS_X_2 = 500;
	private static final int BMS_X_VALUE = 405;
	private static final int BMS_X_2_VALUE = 570;
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
	
	/*Error Messages*/
	private static final int MSG_LABEL_WIDTH = 900;
	private static final int ERRORMSG_X = 50;
	private static final int ERRORMSG_X_VALUE = 130;
	private static final int ERRORMSG_Y = 400;
	
	/*Info 1*/
	private static final int INFO1_X = 1050;
	private static final int INFO1_X_VALUE = 1140;
	private static final int INFO1_Y = 210;
	private static final int DRIVE_SPEED_ID = 9;
	private static final int DRIVECTRL_ID = 1;
	private static final int DRIVECTRL_RPM_ID = 1;
	
	private Image img;
	
	DataManager dd = DataManager.getInstance();
	 
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
	
	JLabel BMS_Vpack = new JLabel("Total Vpack : ");
	JLabel BMS_Vpack_Value = new JLabel("");
	
	JLabel BMS_Ipack = new JLabel("Total Ipack : ");
	JLabel BMS_Ipack_Value = new JLabel("");
	
	JLabel BMS_Status = new JLabel("Status : ");
	JLabel BMS_Status_Value = new JLabel("");
	
	JLabel BMS_ExtStatus = new JLabel("Ext. Status : ");
	JLabel BMS_ExtStatus_Value = new JLabel("");
	
	/*Error Messages*/
	JLabel ErrorMsg_Label = new JLabel("[Error Messages]");
	
	JLabel ErrorMsg_BMUExtStatus = new JLabel("BMU Status > ");
	JLabel ErrorMsg_BMUExtStatus_Value = new JLabel("");
	
	JLabel ErrorMsg_DriveErrorFlags = new JLabel("Drive Error  > ");
	JLabel ErrorMsg_DriveErrorFlags_Value = new JLabel("");
	
	JLabel ErrorMsg_DriveLimitFlags = new JLabel("Drive Limit  > ");
	JLabel ErrorMsg_DriveLimitFlags_Value = new JLabel("");
	
	/*Info 1*/
	JLabel Info1_Label = new JLabel("[Info]");
	
	JLabel Info1_SpeedKMH = new JLabel("Speed : ");
	JLabel Info1_SpeedKMH_Value = new JLabel("");
	JLabel Info1_SpeedMPH_Value = new JLabel("");
	
	JLabel Info1_Setpoint = new JLabel("Setpoint : ");
	JLabel Info1_Setpoint_Value = new JLabel("");
	
	JLabel Info1_Power = new JLabel("Power Bat. : ");
	JLabel Info1_Power_Value = new JLabel("");
	
	JLabel Info1_PanelPow = new JLabel("Power Pan. : ");
	JLabel Info1_PanelPow_Value = new JLabel("");
	
	JLabel Info1_Consumption = new JLabel("Consumption : ");
	JLabel Info1_Consumption_Value = new JLabel("");
	 
	public Tabchar() {
		
		setBackground(Color.WHITE);		
		setForeground(Color.BLACK);
		setLayout(null);
		
		img = new ImageIcon("images/image.png").getImage();
		
/*		MPPT1
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
		
		MPPT2
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
		
		MPPT3
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
		add(MPPT3_Temp_Value);*/
		
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
		
		BMS_Vpack.setBounds(BMS_X, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_Vpack);
		BMS_Vpack_Value.setBounds(BMS_X_VALUE, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_Vpack_Value);
		
		BMS_Ipack.setBounds(BMS_X, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_Ipack);
		BMS_Ipack_Value.setBounds(BMS_X_VALUE, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_Ipack_Value);
		
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
		
		BMS_SOCAh.setBounds(BMS_X_2, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_SOCAh);
		BMS_SOCAh_Value.setBounds(BMS_X_2_VALUE, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_SOCAh_Value);
		
		BMS_SOCPc.setBounds(BMS_X_2, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_SOCPc);
		BMS_SOCPc_Value.setBounds(BMS_X_2_VALUE, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_SOCPc_Value);
		
		BMS_ExtStatus.setBounds(BMS_X_2, BMS_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_ExtStatus);
		BMS_ExtStatus_Value.setBounds(BMS_X_2_VALUE, BMS_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_ExtStatus_Value);
		
		/*Error Messages*/
		ErrorMsg_Label.setBounds(ERRORMSG_X, ERRORMSG_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(ErrorMsg_Label);
		
		ErrorMsg_BMUExtStatus.setBounds(ERRORMSG_X, ERRORMSG_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(ErrorMsg_BMUExtStatus);
		ErrorMsg_BMUExtStatus_Value.setBounds(ERRORMSG_X_VALUE, ERRORMSG_Y + LINE_OFFSET, MSG_LABEL_WIDTH, LABEL_HEIGHT);
		add(ErrorMsg_BMUExtStatus_Value);
		
		ErrorMsg_DriveErrorFlags.setBounds(ERRORMSG_X, ERRORMSG_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(ErrorMsg_DriveErrorFlags);
		ErrorMsg_DriveErrorFlags_Value.setBounds(ERRORMSG_X_VALUE, ERRORMSG_Y + 2*LINE_OFFSET, MSG_LABEL_WIDTH, LABEL_HEIGHT);
		add(ErrorMsg_DriveErrorFlags_Value);
		
		ErrorMsg_DriveLimitFlags.setBounds(ERRORMSG_X, ERRORMSG_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(ErrorMsg_DriveLimitFlags);
		ErrorMsg_DriveLimitFlags_Value.setBounds(ERRORMSG_X_VALUE, ERRORMSG_Y + 3*LINE_OFFSET, MSG_LABEL_WIDTH, LABEL_HEIGHT);
		add(ErrorMsg_DriveLimitFlags_Value);
		
		/*Info 1*/
		Info1_Label.setBounds(INFO1_X, INFO1_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_Label);
		
		Info1_SpeedKMH.setBounds(INFO1_X, INFO1_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_SpeedKMH);
		Info1_SpeedKMH_Value.setBounds(INFO1_X_VALUE, INFO1_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_SpeedKMH_Value);
		Info1_SpeedMPH_Value.setBounds(INFO1_X_VALUE, INFO1_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_SpeedMPH_Value);
		
		Info1_Setpoint.setBounds(INFO1_X, INFO1_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_Setpoint);
		Info1_Setpoint_Value.setBounds(INFO1_X_VALUE, INFO1_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_Setpoint_Value);
		
		Info1_Power.setBounds(INFO1_X, INFO1_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_Power);
		Info1_Power_Value.setBounds(INFO1_X_VALUE, INFO1_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_Power_Value);
		
		Info1_PanelPow.setBounds(INFO1_X, INFO1_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_PanelPow);
		Info1_PanelPow_Value.setBounds(INFO1_X_VALUE, INFO1_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_PanelPow_Value);
		
		Info1_Consumption.setBounds(INFO1_X, INFO1_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_Consumption);
		Info1_Consumption_Value.setBounds(INFO1_X_VALUE, INFO1_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_Consumption_Value);
		

		
	}

	public void updateValues() {
		

/*		MPPT1
		MPPT1_Vin_Value.setText(dd.getRoundedValue(MPPT1_ID, MPPT_VIN_ID));
		MPPT1_Vout_Value.setText(dd.getRoundedValue(MPPT1_ID, MPPT_VOUT_ID));
		MPPT1_Iout_Value.setText(dd.getRoundedValue(MPPT1_ID, MPPT_IOUT_ID));
		MPPT1_Temp_Value.setText(dd.getRoundedValue(MPPT1_ID, MPPT_TEMP_ID));
		
		MPPT2
		MPPT2_Vin_Value.setText(dd.getRoundedValue(MPPT2_ID, MPPT_VIN_ID));
		MPPT2_Vout_Value.setText(dd.getRoundedValue(MPPT2_ID, MPPT_VOUT_ID));
		MPPT2_Iout_Value.setText(dd.getRoundedValue(MPPT2_ID, MPPT_IOUT_ID));
		MPPT2_Temp_Value.setText(dd.getRoundedValue(MPPT2_ID, MPPT_TEMP_ID));
		
		MPPT3
		MPPT3_Vin_Value.setText(dd.getRoundedValue(MPPT3_ID, MPPT_VIN_ID));
		MPPT3_Vout_Value.setText(dd.getRoundedValue(MPPT3_ID, MPPT_VOUT_ID));
		MPPT3_Iout_Value.setText(dd.getRoundedValue(MPPT3_ID, MPPT_IOUT_ID));
		MPPT3_Temp_Value.setText(dd.getRoundedValue(MPPT3_ID, MPPT_TEMP_ID));*/
		
		/*PSU*/
		PSU_ICAN_Value.setText(dd.getRoundedValue(PSU_ID, PSU_ACAN_ID));
		PSU_VCAN_Value.setText(dd.getRoundedValue(PSU_ID, PSU_VCAN_ID));
		
		/*Drive*/
		Drive_RPM_Value.setText(dd.getRoundedValue(DRIVE_ID, DRIVE_RPM_ID));
		Drive_HSTemp_Value.setText(dd.getRoundedValue(DRIVE_ID, DRIVE_HSTEMP_ID));
		Drive_MotorTemp_Value.setText(dd.getRoundedValue(DRIVE_ID, DRIVE_MOTORTEMP_ID));
		Drive_DSPTemp_Value.setText(dd.getRoundedValue(DRIVE_ID, DRIVE_DSPTEMP_ID));
		//Drive_ErrorFlags_Value.setText(dd.getRoundedValue(DRIVE_ID, DRIVE_ERRORFLAGS_ID));
		Drive_ErrorFlags_Value.setText(getDriveErrorFlags());
		//Drive_LimitFlags_Value.setText(dd.getRoundedValue(DRIVE_ID, DRIVE_LIMITFLAGS_ID));
		Drive_LimitFlags_Value.setText(getDriveLimitFlags());
		
		/*Instru*/
		Instru_Lat_Value.setText(dd.getRoundedValue(INSTRU_ID, INSTRU_LAT_ID));
		Instru_Lon_Value.setText(dd.getRoundedValue(INSTRU_ID, INSTRU_LON_ID));
		String heure = Double.toString(dd.getRawValue(INSTRU_ID, INSTRU_TIME_ID));
		String date = Double.toString(dd.getRawValue(INSTRU_ID, INSTRU_DATE_ID));
		int delay = Integer.parseInt(TelemetrySettings.getInstance().getSetting("DELAY_TIME"));
		if(Double.parseDouble(date)>9999){
			Instru_Time_Value.setText((Integer.parseInt(heure.substring(0, 2))+delay)+":"+heure.substring(2,4)+":"+heure.substring(4,6));
			Instru_Date_Value.setText(date.substring(0, 2)+"/"+date.substring(2,4)+"/"+date.substring(4,6));			
		}
		else {
			Instru_Time_Value.setText("0");
			Instru_Date_Value.setText("0");
		}
//		Instru_Time_Value.setText(dd.getRoundedValue(INSTRU_ID, INSTRU_TIME_ID));
//		Instru_Date_Value.setText(dd.getRoundedValue(INSTRU_ID, INSTRU_DATE_ID));
		
		/*BMS*/
		BMS_MaxCellV_Value.setText(dd.getRoundedValue(BMS_ID, BMS_MAXCELLV_ID));
		BMS_MinCellV_Value.setText(dd.getRoundedValue(BMS_ID, BMS_MINCELLV_ID));
		BMS_SOCPc_Value.setText(dd.getRoundedValue(BMS_ID, BMS_SOCPC_ID));
		BMS_SOCAh_Value.setText(dd.getRoundedValue(BMS_ID, BMS_SOCAH_ID));
		BMS_Status_Value.setText(dd.getRoundedValue(BMS_ID, BMS_STATUS_ID));
		
		BMS_MaxCellT_Value.setText(dd.getRoundedValue(BMS_ID, BMS_MAXCELLT_ID));
		BMS_MaxPCBT_Value.setText(getMaxPCBTemp());
		BMS_Vpack_Value.setText(dd.getRoundedValue(BMS_ID, BMS_PACKV_ID));
		BMS_Ipack_Value.setText(dd.getRoundedValue(BMS_ID, BMS_PACKA_ID));
		//BMS_ExtStatus_Value.setText(dd.getRoundedValue(BMS_ID, BMS_EXTSTATUS_ID));
		BMS_ExtStatus_Value.setText(getBMUExtStatus());
		
		/*Error Message*/
		ErrorMsg_BMUExtStatus_Value.setText(getBMUExtStatusMsg());
		ErrorMsg_DriveErrorFlags_Value.setText(getDriveErrorFlagsMsg());
		ErrorMsg_DriveErrorFlags_Value.setText(getDriveErrorFlagsMsg());
		
		/*Info 1*/
		double speedKmh = dd.getRawValue(DRIVE_ID, DRIVE_SPEED_ID) / 1000*60*60;
		Info1_SpeedKMH_Value.setText(String.format("%.2f", speedKmh) + " Km/h");
		double speedMph = speedKmh * 0.621371;
		Info1_SpeedMPH_Value.setText(String.format("%.2f", speedMph) + " MPH");
		double setpoint = dd.getRawValue(DRIVECTRL_ID, DRIVECTRL_RPM_ID) / 11;
		Info1_Setpoint_Value.setText(String.format("%.2f", setpoint) + " Km/h");
		double power = dd.getRawValue(BMS_ID, BMS_PACKV_ID) * dd.getRawValue(BMS_ID, BMS_PACKA_ID);
		Info1_Power_Value.setText(String.format("%.2f", power) + " Watts");
		double panelPow = 	((dd.getRawValue(BMS_ID, BMS_PACKA_ID) * dd.getRawValue(BMS_ID, BMS_PACKV_ID)) - 
							(dd.getRawValue(DRIVE_ID, DRIVE_ABUS_ID) * dd.getRawValue(DRIVE_ID, DRIVE_VBUS_ID)) - 
							(dd.getRawValue(PSU_ID, PSU_AHP_ID) * dd.getRawValue(PSU_ID, PSU_VHP_ID)));
		Info1_PanelPow_Value.setText(String.format("%.2f", panelPow) + " Watts");
		double consumption = panelPow - power;
		if (consumption >= 0) {
			Info1_Consumption_Value.setForeground(Color.green);
		}
		else {
			Info1_Consumption_Value.setForeground(Color.red);
		}
		Info1_Consumption_Value.setText(String.format("%.2f", consumption) + " Watts");
		
		/*Log info 1 values*/
		Logger.getLogger("calculated_values").info("Speed : " + String.format("%.2f", speedKmh) + " Km/h" + ", " + String.format("%.2f", speedMph) + " MPH");
		Logger.getLogger("calculated_values").info("Setpoint : " + String.format("%.2f", setpoint) + " Km/h");
		Logger.getLogger("calculated_values").info("Power Bat. : " + String.format("%.2f", power) + " Watts");
		Logger.getLogger("calculated_values").info("Power Pan. : " + String.format("%.2f", panelPow) + " Watts");
		Logger.getLogger("calculated_values").info("Consumption : " + String.format("%.2f", consumption) + " Watts");		
	}
	
	public void paintComponent(Graphics g) {
		
		int width = getWidth();
		int height = getHeight();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.drawImage(img, 180, 100, null);
	}
	
	public String getMaxPCBTemp() {
		
		float CMU1PCBTemp = (float)(dd.getDeviceByID(3).getItemByID(3).getLastData());
		float CMU2PCBTemp = (float)(dd.getDeviceByID(3).getItemByID(14).getLastData());
		float CMU3PCBTemp = (float)(dd.getDeviceByID(3).getItemByID(25).getLastData());
		float CMU4PCBTemp = (float)(dd.getDeviceByID(3).getItemByID(36).getLastData());
		
		return Math.max(Math.max(CMU1PCBTemp, CMU2PCBTemp), Math.max(CMU3PCBTemp, CMU4PCBTemp)) + " " + dd.getDeviceByID(3).getItemByID(3).getUnit();		
	}
	
	public String getDriveErrorFlags() {
		
		int errorFlags = (int)(dd.getDeviceByID(2).getItemByID(5).getLastData());
		
		return Integer.toString(errorFlags & 0xFF);		
	}
	
	public String getDriveErrorFlagsMsg() {
		
		int errorFlags = (int)(dd.getDeviceByID(2).getItemByID(5).getLastData());
		
		String errorMsg = "";
		
		if ((errorFlags & 0x01) > 0) {
			errorMsg = "Hardware over current, ";
		}
		if ((errorFlags & 0x02) > 0) {
			errorMsg = errorMsg + "Software over current, ";
		}
		if ((errorFlags & 0x04) > 0) {
			errorMsg = errorMsg + "DC Bus over voltage, ";
		}
		if ((errorFlags & 0x08) > 0) {
			errorMsg = errorMsg + "Bad motor position hall sequence, ";
		}
		if ((errorFlags & 0x10) > 0) {
			errorMsg = errorMsg + "Watchdog caused last reset, ";
		}
		if ((errorFlags & 0x20) > 0) {
			errorMsg = errorMsg + "Config read error (some values may be reset to defaults), ";
		}
		if ((errorFlags & 0x40) > 0) {
			errorMsg = errorMsg + "15V rail under voltage lock out (UVLO), ";
		}
		if ((errorFlags & 0x80) > 0) {
			errorMsg = errorMsg + "Desaturation fault (MOSFET driver UVLO), ";
		}

		if (errorMsg != "") {			
			errorMsg = errorMsg.substring(0, errorMsg.length()-2);
		}
		
		return errorMsg;
		
	}
	
	public String getDriveLimitFlags() {
		
		int limitFlags = (int)(dd.getDeviceByID(2).getItemByID(6).getLastData());
		
		return Integer.toString(limitFlags & 0x7F);		
	}
	
	public String getDriveLimitFlagsMsg() {
		
		int limitFlags = (int)(dd.getDeviceByID(2).getItemByID(6).getLastData());
		
		String errorMsg = "";
		
		if ((limitFlags & 0x01) > 0) {
			errorMsg = "Output Voltage PWM, ";
		}
		if ((limitFlags & 0x02) > 0) {
			errorMsg = errorMsg + "Motor Current, ";
		}
		if ((limitFlags & 0x04) > 0) {
			errorMsg = errorMsg + "Velocity, ";
		}
		if ((limitFlags & 0x08) > 0) {
			errorMsg = errorMsg + "Bus Current, ";
		}
		if ((limitFlags & 0x10) > 0) {
			errorMsg = errorMsg + "Bus Voltage Upper Limit, ";
		}
		if ((limitFlags & 0x20) > 0) {
			errorMsg = errorMsg + "Bus Voltage Lower Limit, ";
		}
		if ((limitFlags & 0x40) > 0) {
			errorMsg = errorMsg + "IPM Temperature or Motor Temperature, ";
		}
		
		if (errorMsg != "") {			
			errorMsg = errorMsg.substring(0, errorMsg.length()-2);
		}
		
		return errorMsg;	
	}
	
	
	public String getBMUExtStatus() {
		
		int extStatusFlags = (int)(dd.getDeviceByID(3).getItemByID(86).getLastData());
		
		return Integer.toString(extStatusFlags & 0x1FFF);		
	}
	
	public String getBMUExtStatusMsg() {
		
		int extStatusFlags = (int)(dd.getDeviceByID(3).getItemByID(86).getLastData());
		
		String errorMsg = "";

		if ((extStatusFlags & 0x01) > 0) {
			errorMsg = "Cell Over Voltage, ";
		}
		if ((extStatusFlags & 0x02) > 0) {
			errorMsg = errorMsg + "Cell Under Voltage, ";
		}
		if ((extStatusFlags & 0x04) > 0) {
			errorMsg = errorMsg + "Cell Over Temperature, ";
		}
		if ((extStatusFlags & 0x08) > 0) {
			errorMsg = errorMsg + "Measurement Untrusted (channel mismatch), ";
		}
		if ((extStatusFlags & 0x10) > 0) {
			errorMsg = errorMsg + "CMU Communications Timeout (lost CMU), ";
		}
		if ((extStatusFlags & 0x20) > 0) {
			errorMsg = errorMsg + "Vehicle Communications Timeout (lost EVDC), ";
		}
		if ((extStatusFlags & 0x40) > 0) {
			errorMsg = errorMsg + "BMU is in Setup mode, ";
		}
		if ((extStatusFlags & 0x80) > 0) {
			errorMsg = errorMsg + "CMU CAN bus power status, ";
		}
		if ((extStatusFlags & 0x100) > 0) {
			errorMsg = errorMsg + "Pack Isolation test failure, ";
		}
		if ((extStatusFlags & 0x200) > 0) {
			errorMsg = errorMsg + "SOC measurement is not valid, ";
		}
		if ((extStatusFlags & 0x400) > 0) {
			errorMsg = errorMsg + "CAN 12V supply is low - about to shut down, ";
		}
		if ((extStatusFlags & 0x800) > 0) {
			errorMsg = errorMsg + "A contactor is stuck / not engaged, ";
		}
		if ((extStatusFlags & 0x1000) > 0) {
			errorMsg = errorMsg + "A CMU has detected an extra cell present, ";
		}
		
		if (errorMsg != "") {			
			errorMsg = errorMsg.substring(0, errorMsg.length()-2);
		}
		
		return errorMsg;		
	}
}
