package eclipse.view.gui.tab;


import javax.swing.ImageIcon;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.apache.log4j.Logger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import eclipse.controller.acqui.DataAcquisition;
import eclipse.controller.util.TelemetrySettings;
import eclipse.model.data.DataManager;
/**
 * This view show all important information on 1 single view
 * @author Marco
 * @author Olivier
 *
 */




public class Tabchar extends JPanel implements TabPane, MouseListener 
{
	
	private static final long serialVersionUID = 7275321077445435378L;
	List<Double> average_power_bat_l = new ArrayList<Double>();
	
	private static final int LABEL_WIDTH = 100;
	private static final int LABEL_HEIGHT = 14;
	private static final int LINE_OFFSET = 20;
	
	/*PSU*/
	private static final int PSU_ID = 7;
	private static final int PSU_X = 40;
	private static final int PSU_X_VALUE = 120;
	private static final int PSU_Y = 250;
	
	private static final int PSU_ACAN_ID = 2;
	private static final int PSU_VCAN_ID = 3;
	
	
	/*Drive*/
	private static final int DRIVE_ID = 2;
	private static final int DRIVE_X = 40;
	private static final int DRIVE_X_VALUE = 140;
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
	private static final int INSTRU_X_VALUE = 1090;
	private static final int INSTRU_Y = 50;
	private static final int INSTRU_LAT_ID = 2;
	private static final int INSTRU_LON_ID = 3;
	private static final int INSTRU_TIME_ID = 4;
	private static final int INSTRU_DATE_ID = 5;
	
	/*BMS*/
	private static final int BMS_ID = 4;
	private static final int BMS_X = 325;
	private static final int BMS_X_2 = 500;
	private static final int BMS_X_VALUE = 405;
	private static final int BMS_X_2_VALUE = 570;
	private static final int BMS_Y = 50;
	private static final int BMS_PACK_CURRENT_ID = 2;
	private static final int BMS_PACK_VOLTAGE_ID = 3;
	private static final int BMS_MAXCELLV_ID = 75;
	private static final int BMS_MINCELLV_ID = 76;
	private static final int BMS_MAXCELLT_ID = 81;
	private static final int BMS_SOCPC_ID = 57;
	private static final int BMS_SOCAH_ID = 58;
	private static final int BMS_PACKA_ID = 83;
	private static final int BMS_PACKV_ID = 84;
	private static final int BMS_EXTSTATUS_ID = 97;
	private static final int CMU1_PCBTEMP_ID = 3;
	private static final int CMU2_PCBTEMP_ID = 14;
	private static final int CMU3_PCBTEMP_ID = 25;
	private static final int CMU4_PCBTEMP_ID = 36;
	private static final int CMU5_PCBTEMP_ID = 47;
//	
	/*Error Messages*/
	private static final int MSG_LABEL_WIDTH = 900;
	private static final int ERRORMSG_X = 40;
	private static final int ERRORMSG_X_VALUE = 120;
	private static final int ERRORMSG_Y = 500;
	
	/*Info 1*/
	private static final int INFO1_X = 150;
	private static final int INFO1_X_VALUE = 25;
	private static final int INFO1_Y = 10;
	private static final int DRIVE_SPEED_ID = 9;
	private static final int DRIVECTRL_ID = 1;
	private static final int DRIVECTRL_RPM_ID = 1;
	
	/*MPPT*/
	private static final int MUPPET_ID = 13;
	private static final int MPPT_X = 1050;
	private static final int MPPT_X_VALUE = 1145;
	private static final int MPPT_Y = 400;
	private static final int MUPPET_UIN_MPPT1_ID = 3;
	private static final int MUPPET_IIN_MPPT1_ID = 4;
	private static final int MUPPET_UIN_MPPT2_ID = 9;
	private static final int MUPPET_IIN_MPPT2_ID = 10;
	private static final int MUPPET_UIN_MPPT3_ID = 15;
	private static final int MUPPET_IIN_MPPT3_ID = 16;
	
	private Image img;
	
	DataManager dd = DataManager.getInstance();
	
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
	
	JLabel Info1_PowerPan = new JLabel("Power Pan. : ");
	JLabel Info1_PowerPan_Value = new JLabel("");
	
	JLabel Info1_PowerBat = new JLabel("Power Bat. : ");
	JLabel Info1_PowerBat_Value = new JLabel("");
	
	JLabel Info1_AveragePowerBat = new JLabel("Average Power Bat. : ");
	JLabel Info1_AveragePowerBat_Value = new JLabel("");
	
	JLabel Info1_PowerMotor = new JLabel("Power Motor : ");
	JLabel Info1_PowerMotorValue = new JLabel("");
	
	/*MPPT*/
	JLabel MPPT_Label = new JLabel("[MPPT]");
	
	JLabel MPPT_MainRelay = new JLabel("Main Relay : ");
	JLabel MPPT_MainRelay_Value = new JLabel("");
	
	JLabel MPPT_OneStatus = new JLabel("MPPT1 Status : ");
	JLabel MPPT_OneStatus_Value = new JLabel("");
	
	JLabel MPPT_TwoStatus = new JLabel("MPPT2 Status : ");
	JLabel MPPT_TwoStatus_Value = new JLabel("");
	
	JLabel MPPT_ThreeStatus = new JLabel("MPPT3 Status : ");
	JLabel MPPT_ThreeStatus_Value = new JLabel("");
	
	JTable table;
	
	public Tabchar() 
	{
		addMouseListener(this);		
		setBackground(Color.WHITE);		
		setForeground(Color.BLACK);
		setLayout(null);
		
	
	
		img = new ImageIcon("images/image.png").getImage();
		
//		/*PSU*/
//		PSU_Label.setBounds(PSU_X, PSU_Y, LABEL_WIDTH, LABEL_HEIGHT);
//		add(PSU_Label);
//	
//		PSU_ICAN.setBounds(PSU_X, PSU_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(PSU_ICAN);
//		PSU_ICAN_Value.setBounds(PSU_X_VALUE, PSU_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(PSU_ICAN_Value);
//		
//		PSU_VCAN.setBounds(PSU_X, PSU_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(PSU_VCAN);
//		PSU_VCAN_Value.setBounds(PSU_X_VALUE, PSU_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(PSU_VCAN_Value);
//		
//		/*Drive*/
//		Drive_Label.setBounds(DRIVE_X, DRIVE_Y, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Drive_Label);
//		
//		Drive_RPM.setBounds(DRIVE_X, DRIVE_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Drive_RPM);
//		Drive_RPM_Value.setBounds(DRIVE_X_VALUE, DRIVE_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Drive_RPM_Value);
//		
//		Drive_HSTemp.setBounds(DRIVE_X, DRIVE_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Drive_HSTemp);
//		Drive_HSTemp_Value.setBounds(DRIVE_X_VALUE, DRIVE_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Drive_HSTemp_Value);
//		
//		Drive_MotorTemp.setBounds(DRIVE_X, DRIVE_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Drive_MotorTemp);
//		Drive_MotorTemp_Value.setBounds(DRIVE_X_VALUE, DRIVE_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Drive_MotorTemp_Value);
//		
//		Drive_DSPTemp.setBounds(DRIVE_X, DRIVE_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Drive_DSPTemp);
//		Drive_DSPTemp_Value.setBounds(DRIVE_X_VALUE, DRIVE_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Drive_DSPTemp_Value);
//		
//		/*Instru*/
//		Instru_Label.setBounds(INSTRU_X, INSTRU_Y, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Instru_Label);
//		
//		Instru_Lat.setBounds(INSTRU_X, INSTRU_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Instru_Lat);
//		Instru_Lat_Value.setBounds(INSTRU_X_VALUE, INSTRU_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Instru_Lat_Value);
//		
//		Instru_Lon.setBounds(INSTRU_X, INSTRU_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Instru_Lon);
//		Instru_Lon_Value.setBounds(INSTRU_X_VALUE, INSTRU_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Instru_Lon_Value);
//		
//		Instru_Time.setBounds(INSTRU_X, INSTRU_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Instru_Time);
//		Instru_Time_Value.setBounds(INSTRU_X_VALUE, INSTRU_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Instru_Time_Value);
//		
//		Instru_Date.setBounds(INSTRU_X, INSTRU_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Instru_Date);
//		Instru_Date_Value.setBounds(INSTRU_X_VALUE, INSTRU_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Instru_Date_Value);
//		
//		/*BMS*/
//		BMS_Label.setBounds(BMS_X, BMS_Y, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_Label);
//		
//		BMS_MaxCellV.setBounds(BMS_X, BMS_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_MaxCellV);
//		BMS_MaxCellV_Value.setBounds(BMS_X_VALUE, BMS_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_MaxCellV_Value);
//		
//		BMS_MinCellV.setBounds(BMS_X, BMS_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_MinCellV);
//		BMS_MinCellV_Value.setBounds(BMS_X_VALUE, BMS_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_MinCellV_Value);
//		
//		BMS_Vpack.setBounds(BMS_X, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_Vpack);
//		BMS_Vpack_Value.setBounds(BMS_X_VALUE, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_Vpack_Value);
//		
//		BMS_Ipack.setBounds(BMS_X, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_Ipack);
//		BMS_Ipack_Value.setBounds(BMS_X_VALUE, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_Ipack_Value);
//		
//		BMS_MaxCellT.setBounds(BMS_X_2, BMS_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_MaxCellT);
//		BMS_MaxCellT_Value.setBounds(BMS_X_2_VALUE, BMS_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_MaxCellT_Value);
//		
//		BMS_MaxPCBT.setBounds(BMS_X_2, BMS_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_MaxPCBT);
//		BMS_MaxPCBT_Value.setBounds(BMS_X_2_VALUE, BMS_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_MaxPCBT_Value);
//		
//		BMS_SOCAh.setBounds(BMS_X_2, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_SOCAh);
//		BMS_SOCAh_Value.setBounds(BMS_X_2_VALUE, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_SOCAh_Value);
//		
//		BMS_SOCPc.setBounds(BMS_X_2, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_SOCPc);
//		BMS_SOCPc_Value.setBounds(BMS_X_2_VALUE, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(BMS_SOCPc_Value);
//		
//		/*Error Messages*/
//		ErrorMsg_Label.setBounds(ERRORMSG_X, ERRORMSG_Y, LABEL_WIDTH, LABEL_HEIGHT);
//		add(ErrorMsg_Label);
//		
//		ErrorMsg_BMUExtStatus.setBounds(ERRORMSG_X, ERRORMSG_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(ErrorMsg_BMUExtStatus);
//		ErrorMsg_BMUExtStatus_Value.setBounds(ERRORMSG_X_VALUE, ERRORMSG_Y + LINE_OFFSET, MSG_LABEL_WIDTH, LABEL_HEIGHT);
//		add(ErrorMsg_BMUExtStatus_Value);
//		
//		ErrorMsg_DriveErrorFlags.setBounds(ERRORMSG_X, ERRORMSG_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(ErrorMsg_DriveErrorFlags);
//		ErrorMsg_DriveErrorFlags_Value.setBounds(ERRORMSG_X_VALUE, ERRORMSG_Y + 2*LINE_OFFSET, MSG_LABEL_WIDTH, LABEL_HEIGHT);
//		add(ErrorMsg_DriveErrorFlags_Value);
//		
//		ErrorMsg_DriveLimitFlags.setBounds(ERRORMSG_X, ERRORMSG_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(ErrorMsg_DriveLimitFlags);
//		ErrorMsg_DriveLimitFlags_Value.setBounds(ERRORMSG_X_VALUE, ERRORMSG_Y + 3*LINE_OFFSET, MSG_LABEL_WIDTH, LABEL_HEIGHT);
//		add(ErrorMsg_DriveLimitFlags_Value);
//		
//		/*Info 1*/
//		Info1_Label.setBounds(INFO1_X, INFO1_Y, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Info1_Label);
//		
//		Info1_SpeedKMH.setBounds(INFO1_X, INFO1_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Info1_SpeedKMH);
//		Info1_SpeedKMH_Value.setBounds(INFO1_X_VALUE, INFO1_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Info1_SpeedKMH_Value);
//		Info1_SpeedMPH_Value.setBounds(INFO1_X_VALUE, INFO1_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Info1_SpeedMPH_Value);
//		
//		Info1_Setpoint.setBounds(INFO1_X, INFO1_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Info1_Setpoint);
//		Info1_Setpoint_Value.setBounds(INFO1_X_VALUE, INFO1_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(Info1_Setpoint_Value);
//		
		Info1_PowerPan.setBounds(INFO1_X, INFO1_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_PowerPan);
		Info1_PowerPan_Value.setBounds(INFO1_X_VALUE, INFO1_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_PowerPan_Value);
		
		Info1_PowerBat.setBounds(INFO1_X, INFO1_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_PowerBat);
		Info1_PowerBat_Value.setBounds(INFO1_X_VALUE, INFO1_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_PowerBat_Value);
		
		Info1_PowerMotor.setBounds(INFO1_X, INFO1_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_PowerMotor);
		Info1_PowerMotorValue.setBounds(INFO1_X_VALUE, INFO1_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_PowerMotorValue);
		
		Info1_AveragePowerBat.setBounds(INFO1_X, INFO1_Y + 7*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_AveragePowerBat);
		Info1_AveragePowerBat_Value.setBounds(INFO1_X_VALUE, INFO1_Y + 7*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(Info1_AveragePowerBat_Value);
//		
//		/*MPPT*/		
//		MPPT_Label.setBounds(MPPT_X, MPPT_Y, LABEL_WIDTH, LABEL_HEIGHT);
//		add(MPPT_Label);
//		
//		MPPT_MainRelay.setBounds(MPPT_X, MPPT_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(MPPT_MainRelay);
//		MPPT_MainRelay_Value.setBounds(MPPT_X_VALUE, MPPT_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(MPPT_MainRelay_Value);
//		
//		MPPT_OneStatus.setBounds(MPPT_X, MPPT_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(MPPT_OneStatus);
//		MPPT_OneStatus_Value.setBounds(MPPT_X_VALUE, MPPT_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(MPPT_OneStatus_Value);
//		
//		MPPT_TwoStatus.setBounds(MPPT_X, MPPT_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(MPPT_TwoStatus);
//		MPPT_TwoStatus_Value.setBounds(MPPT_X_VALUE, MPPT_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(MPPT_TwoStatus_Value);
//		
//		MPPT_ThreeStatus.setBounds(MPPT_X, MPPT_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(MPPT_ThreeStatus);
//		MPPT_ThreeStatus_Value.setBounds(MPPT_X_VALUE, MPPT_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
//		add(MPPT_ThreeStatus_Value);
		
	}

	public void updateValues() {
		
//		/*PSU*/
//		PSU_ICAN_Value.setText(dd.getRoundedValue(PSU_ID, PSU_ACAN_ID));
//		PSU_VCAN_Value.setText(dd.getRoundedValue(PSU_ID, PSU_VCAN_ID));
//		
//		/*Drive*/
//		Drive_RPM_Value.setText(dd.getRoundedValue(DRIVE_ID, DRIVE_RPM_ID));
//		Drive_HSTemp_Value.setText(dd.getRoundedValue(DRIVE_ID, DRIVE_HSTEMP_ID));
//		Drive_MotorTemp_Value.setText(dd.getRoundedValue(DRIVE_ID, DRIVE_MOTORTEMP_ID));
//		Drive_DSPTemp_Value.setText(dd.getRoundedValue(DRIVE_ID, DRIVE_DSPTEMP_ID));
//		
//		/*Instru*/
//		Instru_Lat_Value.setText(dd.getRoundedValue(INSTRU_ID, INSTRU_LAT_ID));
//		Instru_Lon_Value.setText(dd.getRoundedValue(INSTRU_ID, INSTRU_LON_ID));
//		String heure = Double.toString(dd.getRawValue(INSTRU_ID, INSTRU_TIME_ID));
//		String date = Double.toString(dd.getRawValue(INSTRU_ID, INSTRU_DATE_ID));
//		int delay = Integer.parseInt(TelemetrySettings.getInstance().getSetting("DELAY_TIME"));
//		if(Double.parseDouble(date)>9999){
//			Instru_Time_Value.setText((Integer.parseInt(heure.substring(0, 2))+delay)+":"+heure.substring(2,4)+":"+heure.substring(4,6));
//			Instru_Date_Value.setText(date.substring(0, 2)+"/"+date.substring(2,4)+"/"+date.substring(4,6));			
//		}
//		else {
//			Instru_Time_Value.setText("0");
//			Instru_Date_Value.setText("0");
//		}
//		
//		/*BMS*/
//		BMS_MaxCellV_Value.setText(dd.getRoundedValue(BMS_ID, BMS_MAXCELLV_ID));
//		BMS_MinCellV_Value.setText(dd.getRoundedValue(BMS_ID, BMS_MINCELLV_ID));
//		BMS_SOCPc_Value.setText(dd.getRoundedValue(BMS_ID, BMS_SOCPC_ID));
//		BMS_SOCAh_Value.setText(dd.getRoundedValue(BMS_ID, BMS_SOCAH_ID));
//		
//		BMS_MaxCellT_Value.setText(dd.getRoundedValue(BMS_ID, BMS_MAXCELLT_ID));
//		BMS_MaxPCBT_Value.setText(getMaxPCBTemp());
//		BMS_Vpack_Value.setText(dd.getRoundedValue(BMS_ID, BMS_PACKV_ID));
//		BMS_Ipack_Value.setText(dd.getRoundedValue(BMS_ID, BMS_PACKA_ID));
//		
//		/*Error Message*/
//		ErrorMsg_BMUExtStatus_Value.setText(getBMUExtStatusMsg());
//		ErrorMsg_DriveErrorFlags_Value.setText(getDriveErrorFlagsMsg());
//		ErrorMsg_DriveErrorFlags_Value.setText(getDriveErrorFlagsMsg());
//		
		/*Info 1*/
		double speedKmh = dd.getRawValue(DRIVE_ID, DRIVE_SPEED_ID);
		Info1_SpeedKMH_Value.setText(String.format("%.2f", speedKmh) + " km/h");
		double speedMph = speedKmh * 0.621371;
		Info1_SpeedMPH_Value.setText(String.format("%.2f", speedMph) + " mph");
		double setpoint = dd.getRawValue(DRIVECTRL_ID, DRIVECTRL_RPM_ID) / 11;
		Info1_Setpoint_Value.setText(String.format("%.2f", setpoint) + " km/h");
		double powerBat = dd.getRawValue(BMS_ID, BMS_PACK_CURRENT_ID) * dd.getRawValue(BMS_ID, BMS_PACK_VOLTAGE_ID);
		average_power_bat_l.add(powerBat);
		if (average_power_bat_l.size() > 503){
			average_power_bat_l.remove(0);
		}
		double sum = 0;
		double average_power_bat = 0;
		for(double average_power_ba : average_power_bat_l){
			sum += average_power_ba;
		}
		average_power_bat = sum/average_power_bat_l.size();
		
		Info1_AveragePowerBat_Value.setText(String.format("%.2f", average_power_bat) + " W");
		
		Info1_PowerBat_Value.setText(String.format("%.2f", powerBat) + " W");
		double powerPan = 0;

		powerPan = dd.getRawValue(MUPPET_ID, MUPPET_UIN_MPPT1_ID) * dd.getRawValue(MUPPET_ID, MUPPET_IIN_MPPT1_ID) +
				   dd.getRawValue(MUPPET_ID, MUPPET_UIN_MPPT2_ID) * dd.getRawValue(MUPPET_ID, MUPPET_IIN_MPPT2_ID) +
				   dd.getRawValue(MUPPET_ID, MUPPET_UIN_MPPT3_ID) * dd.getRawValue(MUPPET_ID, MUPPET_IIN_MPPT3_ID);
		Info1_PowerBat_Value.setForeground(Color.black);
		

		Info1_PowerPan_Value.setText(String.format("%.2f", powerPan) + " W");
		
		double powerMotor = powerBat - powerPan;		
		Info1_PowerMotorValue.setText(String.format("%.2f", powerMotor) + " W");
		Info1_PowerMotorValue.setForeground(Color.black);
		
		if (DataAcquisition.getInstance().getAcquiStatus()) {
			/*Log info 1 values*/
			Logger.getLogger("calculated_values").info("Speed : " + String.format("%.2f", speedKmh) + " km/h" + ", " + String.format("%.2f", speedMph) + " mph");
			Logger.getLogger("calculated_values").info("Setpoint : " + String.format("%.2f", setpoint) + " km/h");
			Logger.getLogger("calculated_values").info("Power Bat. : " + String.format("%.2f", powerBat) + " watts");
			Logger.getLogger("calculated_values").info("Power Motor. : " + String.format("%.2f", powerMotor) + " watts");
			Logger.getLogger("calculated_values").info("Power Pan. : " + String.format("%.2f", powerPan) + " watts");
		}
//		
//		/*MPPT*/
//		if (dd.getRawValue(MPPT_ID, MPPT_MAINRELAY_ID) == 0) {
//			MPPT_MainRelay_Value.setText("OFF");
//			MPPT_MainRelay_Value.setForeground(Color.red);
//		}
//		else {
//			MPPT_MainRelay_Value.setText("ON");
//			MPPT_MainRelay_Value.setForeground(Color.green);
//		}
//		
//		if (dd.getRawValue(MPPT_ID, MPPT_ONESTATUS_ID) == 0) {
//			MPPT_OneStatus_Value.setText("OFF");
//			MPPT_OneStatus_Value.setForeground(Color.red);
//		}
//		else {
//			MPPT_OneStatus_Value.setText("ON");
//			MPPT_OneStatus_Value.setForeground(Color.green);
//		}
//		
//		if (dd.getRawValue(MPPT_ID, MPPT_TWOSTATUS_ID) == 0) {
//			MPPT_TwoStatus_Value.setText("OFF");
//			MPPT_TwoStatus_Value.setForeground(Color.red);
//		}
//		else {
//			MPPT_TwoStatus_Value.setText("ON");
//			MPPT_TwoStatus_Value.setForeground(Color.green);
//		}
//		
//		if (dd.getRawValue(MPPT_ID, MPPT_THREESTATUS_ID) == 0) {
//			MPPT_ThreeStatus_Value.setText("OFF");
//			MPPT_ThreeStatus_Value.setForeground(Color.red);
//		}
//		else {
//			MPPT_ThreeStatus_Value.setText("ON");
//			MPPT_ThreeStatus_Value.setForeground(Color.green);
//		}	
	}
	
	public void paintComponent(Graphics g) {
		
		int width = getWidth();
		int height = getHeight();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.drawImage(img, 180, 150, null);
	}
	
	public String getMaxPCBTemp() {
		
		float CMU1PCBTemp = (float)(dd.getDeviceByID(BMS_ID).getItemByID(CMU1_PCBTEMP_ID).getLastData());
		float CMU2PCBTemp = (float)(dd.getDeviceByID(BMS_ID).getItemByID(CMU2_PCBTEMP_ID).getLastData());
		float CMU3PCBTemp = (float)(dd.getDeviceByID(BMS_ID).getItemByID(CMU3_PCBTEMP_ID).getLastData());
		float CMU4PCBTemp = (float)(dd.getDeviceByID(BMS_ID).getItemByID(CMU4_PCBTEMP_ID).getLastData());
		float CMU5PCBTemp = (float)(dd.getDeviceByID(BMS_ID).getItemByID(CMU5_PCBTEMP_ID).getLastData());
		
		return Math.max(Math.max(Math.max(CMU1PCBTemp, CMU2PCBTemp), Math.max(CMU3PCBTemp, CMU4PCBTemp)), CMU5PCBTemp) + " " + dd.getDeviceByID(BMS_ID).getItemByID(CMU1_PCBTEMP_ID).getUnit();		
	}
	
	public String getDriveErrorFlags() {
		
		int errorFlags = (int)(dd.getDeviceByID(DRIVE_ID).getItemByID(DRIVE_ERRORFLAGS_ID).getLastData());
		
		return Integer.toString(errorFlags & 0xFF);		
	}
	
	public String getDriveErrorFlagsMsg() {
		
		int errorFlags = (int)(dd.getDeviceByID(DRIVE_ID).getItemByID(DRIVE_ERRORFLAGS_ID).getLastData());
		
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
			//errorMsg = errorMsg + "15V rail under voltage lock out (UVLO), ";
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
		
		int limitFlags = (int)(dd.getDeviceByID(DRIVE_ID).getItemByID(DRIVE_LIMITFLAGS_ID).getLastData());
		
		return Integer.toString(limitFlags & 0x7F);		
	}
	
	public String getDriveLimitFlagsMsg() {
		
		int limitFlags = (int)(dd.getDeviceByID(DRIVE_ID).getItemByID(DRIVE_LIMITFLAGS_ID).getLastData());
		
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
		
		int extStatusFlags = (int)(dd.getDeviceByID(BMS_ID).getItemByID(BMS_EXTSTATUS_ID).getLastData());
		
		return Integer.toString(extStatusFlags & 0x1FFF);		
	}
	
	public String getBMUExtStatusMsg() {
		
		int extStatusFlags = (int)(dd.getDeviceByID(BMS_ID).getItemByID(BMS_EXTSTATUS_ID).getLastData());
		
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
			//errorMsg = errorMsg + "CMU CAN bus power status, ";
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
	
	public void mousePressed(MouseEvent e) 
	{
		//javax.swing.JOptionPane.showMessageDialog(null,"bonjour");  
	}
	
	public void mouseReleased(MouseEvent e) 
	{
		//javax.swing.JOptionPane.showMessageDialog(null,"aurevoir");
	}
	
	public void mouseEntered(MouseEvent e) 
	{
		//javax.swing.JOptionPane.showMessageDialog(null,"bienvenue");  
	}
	
	public void mouseExited(MouseEvent e) 
	{
		//javax.swing.JOptionPane.showMessageDialog(null,"bye");  
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		//javax.swing.JOptionPane.showMessageDialog(null,"click");  
	} 
}




