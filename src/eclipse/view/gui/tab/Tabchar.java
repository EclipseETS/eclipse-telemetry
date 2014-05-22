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
	private static final int MPPT1_VIN_ID = 1;

	/*MPPT2*/
	private static final int MPPT2_ID = 9;
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
	private static final int PSU_ICAN_ID = 2;
	private static final int PSU_VCAN_ID = 3;
	
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
	 
	public Tabchar() {
		
		setBackground(Color.WHITE);		
		setForeground(Color.BLACK);
		setLayout(null);
		
		img = new ImageIcon("images/image.png").getImage();
		
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
		
		BMS_Vpack.setBounds(BMS_X_2, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_Vpack);
		BMS_Vpack_Value.setBounds(BMS_X_2_VALUE, BMS_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_Vpack_Value);
		
		BMS_Ipack.setBounds(BMS_X_2, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_Ipack);
		BMS_Ipack_Value.setBounds(BMS_X_2_VALUE, BMS_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_Ipack_Value);
		
		BMS_ExtStatus.setBounds(BMS_X_2, BMS_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_ExtStatus);
		BMS_ExtStatus_Value.setBounds(BMS_X_2_VALUE, BMS_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMS_ExtStatus_Value);
		
	}

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
		BMS_MaxPCBT_Value.setText(dd.getMaxPCBTemp());
		BMS_Vpack_Value.setText(dd.getValue(BMS_ID, BMS_PACKV_ID));
		BMS_Ipack_Value.setText(dd.getValue(BMS_ID, BMS_PACKA_ID));
		BMS_ExtStatus_Value.setText(dd.getValue(BMS_ID, BMS_EXTSTATUS_ID));
	}
	
	public void paintComponent(Graphics g) {
		
		int width = getWidth();
		int height = getHeight();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.drawImage(img, 180, 100, null);
	}

	public String getMessage(int i) {
		
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
