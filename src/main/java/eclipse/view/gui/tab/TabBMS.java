package eclipse.view.gui.tab;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import eclipse.model.data.DataManager;
import eclipse.controller.util.Rounding;
/**
 * This view show all important information related to the BMS
 * @author Marco
 * @author Olivier
 *
 */
public class TabBMS extends JPanel implements TabPane {
	
	
	private static final long serialVersionUID = 7310556852006215008L;
	
	private static final int LABEL_WIDTH = 100;
	private static final int LABEL_HEIGHT = 14;
	private static final int LINE_OFFSET = 20;
	
	private static final int BMS_ID = 3;
	
	/*CMU1*/
	private static final int CMU1_X = 150;
	private static final int CMU1_X_VALUE = 200;
	private static final int CMU1_Y = 25;
	private static final int CMU1_PCBTEMP_ID = 3;
	private static final int CMU1_CELL0_ID = 8;
	private static final int CMU1_CELL1_ID = 7;
	private static final int CMU1_CELL2_ID = 6;
	private static final int CMU1_CELL3_ID = 5;
	private static final int CMU1_CELL4_ID = 12;
	private static final int CMU1_CELL5_ID = 11;
	private static final int CMU1_CELL6_ID = 10;
	
	/*CMU2*/
	private static final int CMU2_X = 975;
	private static final int CMU2_X_VALUE = 1025;
	private static final int CMU2_Y = 25;
	private static final int CMU2_PCBTEMP_ID = 14;
	private static final int CMU2_CELL0_ID = 19;
	private static final int CMU2_CELL1_ID = 18;
	private static final int CMU2_CELL2_ID = 17;
	private static final int CMU2_CELL3_ID = 16;
	private static final int CMU2_CELL4_ID = 23;
	private static final int CMU2_CELL5_ID = 22;
	private static final int CMU2_CELL6_ID = 21;
	
	/*CMU3*/
	private static final int CMU3_X = 150;
	private static final int CMU3_X_VALUE = 200;
	private static final int CMU3_Y = 380;
	private static final int CMU3_PCBTEMP_ID = 25;
	private static final int CMU3_CELL0_ID = 30;
	private static final int CMU3_CELL1_ID = 29;
	private static final int CMU3_CELL2_ID = 28;
	private static final int CMU3_CELL3_ID = 27;
	private static final int CMU3_CELL4_ID = 34;
	private static final int CMU3_CELL5_ID = 33;
	private static final int CMU3_CELL6_ID = 32;
	
	/*CMU4*/
	private static final int CMU4_X = 975;
	private static final int CMU4_X_VALUE = 1025;
	private static final int CMU4_Y = 380;
	private static final int CMU4_PCBTEMP_ID = 36;
	private static final int CMU4_CELL0_ID = 41;
	private static final int CMU4_CELL1_ID = 40;
	private static final int CMU4_CELL2_ID = 39;
	private static final int CMU4_CELL3_ID = 38;
	private static final int CMU4_CELL4_ID = 45;
	private static final int CMU4_CELL5_ID = 44;
	private static final int CMU4_CELL6_ID = 43;
	
	/*CMU5*/
	private static final int CMU5_X = 560;
	private static final int CMU5_X_VALUE = 610;
	private static final int CMU5_Y = 25;
	private static final int CMU5_PCBTEMP_ID = 47;
	private static final int CMU5_CELL0_ID = 52;
	private static final int CMU5_CELL1_ID = 51;
	private static final int CMU5_CELL2_ID = 50;
	private static final int CMU5_CELL3_ID = 49;
	private static final int CMU5_CELL4_ID = 56;
	private static final int CMU5_CELL5_ID = 55;
	private static final int CMU5_CELL6_ID = 54;
	
	/*BMU*/
	private static final int BMU_X = 460;
	private static final int BMU_X_2 = 635;
	private static final int BMU_X_VALUE = 540;
	private static final int BMU_X_2_VALUE = 705;
	private static final int BMU_Y = 500;
	private static final int BMU_MAXCELLV_ID = 75;
	private static final int BMU_MINCELLV_ID = 76;
	private static final int BMU_MAXCELLT_ID = 81;
	private static final int BMU_SOCPC_ID = 57;
	private static final int BMU_SOCAH_ID = 58;
	private static final int BMU_PACKA_ID = 83;
	private static final int BMU_PACKV_ID = 84;
	
	private Image img;
	
	DataManager dd = DataManager.getInstance();
	
	/*CMU1*/
	JLabel CMU1_Label = new JLabel("[CMU1]");
	
	JLabel CMU1_Cell0 = new JLabel("Cell0 :");
	JLabel CMU1_Cell0_Value = new JLabel("");
	
	JLabel CMU1_Cell1 = new JLabel("Cell1 :");
	JLabel CMU1_Cell1_Value = new JLabel("");
	
	JLabel CMU1_Cell2 = new JLabel("Cell2 :");
	JLabel CMU1_Cell2_Value = new JLabel("");
	
	JLabel CMU1_Cell3 = new JLabel("Cell3 :");
	JLabel CMU1_Cell3_Value = new JLabel("");
	
	JLabel CMU1_Cell4 = new JLabel("Cell4 :");
	JLabel CMU1_Cell4_Value = new JLabel("");
	
	JLabel CMU1_Cell5 = new JLabel("Cell5 :");
	JLabel CMU1_Cell5_Value = new JLabel("");
	
	JLabel CMU1_Cell6 = new JLabel("Cell6 :");
	JLabel CMU1_Cell6_Value = new JLabel("");
	
	/*CMU2*/
	JLabel CMU2_Label = new JLabel("[CMU2]");
	
	JLabel CMU2_Cell0 = new JLabel("Cell0 :");
	JLabel CMU2_Cell0_Value = new JLabel("");
	
	JLabel CMU2_Cell1 = new JLabel("Cell1 :");
	JLabel CMU2_Cell1_Value = new JLabel("");
	
	JLabel CMU2_Cell2 = new JLabel("Cell2 :");
	JLabel CMU2_Cell2_Value = new JLabel("");
	
	JLabel CMU2_Cell3 = new JLabel("Cell3 :");
	JLabel CMU2_Cell3_Value = new JLabel("");
	
	JLabel CMU2_Cell4 = new JLabel("Cell4 :");
	JLabel CMU2_Cell4_Value = new JLabel("");
	
	JLabel CMU2_Cell5 = new JLabel("Cell5 :");
	JLabel CMU2_Cell5_Value = new JLabel("");
	
	JLabel CMU2_Cell6 = new JLabel("Cell6 :");
	JLabel CMU2_Cell6_Value = new JLabel("");
	
	/*CMU3*/
	JLabel CMU3_Label = new JLabel("[CMU3]");
	
	JLabel CMU3_Cell0 = new JLabel("Cell0 :");
	JLabel CMU3_Cell0_Value = new JLabel("");
	
	JLabel CMU3_Cell1 = new JLabel("Cell1 :");
	JLabel CMU3_Cell1_Value = new JLabel("");
	
	JLabel CMU3_Cell2 = new JLabel("Cell2 :");
	JLabel CMU3_Cell2_Value = new JLabel("");
	
	JLabel CMU3_Cell3 = new JLabel("Cell3 :");
	JLabel CMU3_Cell3_Value = new JLabel("");
	
	JLabel CMU3_Cell4 = new JLabel("Cell4 :");
	JLabel CMU3_Cell4_Value = new JLabel("");
	
	JLabel CMU3_Cell5 = new JLabel("Cell5 :");
	JLabel CMU3_Cell5_Value = new JLabel("");
	
	JLabel CMU3_Cell6 = new JLabel("Cell6 :");
	JLabel CMU3_Cell6_Value = new JLabel("");
	
	/*CMU4*/
	JLabel CMU4_Label = new JLabel("[CMU4]");
	
	JLabel CMU4_Cell0 = new JLabel("Cell0 :");
	JLabel CMU4_Cell0_Value = new JLabel("");
	
	JLabel CMU4_Cell1 = new JLabel("Cell1 :");
	JLabel CMU4_Cell1_Value = new JLabel("");
	
	JLabel CMU4_Cell2 = new JLabel("Cell2 :");
	JLabel CMU4_Cell2_Value = new JLabel("");
	
	JLabel CMU4_Cell3 = new JLabel("Cell3 :");
	JLabel CMU4_Cell3_Value = new JLabel("");
	
	JLabel CMU4_Cell4 = new JLabel("Cell4 :");
	JLabel CMU4_Cell4_Value = new JLabel("");
	
	JLabel CMU4_Cell5 = new JLabel("Cell5 :");
	JLabel CMU4_Cell5_Value = new JLabel("");
	
	JLabel CMU4_Cell6 = new JLabel("Cell6 :");
	JLabel CMU4_Cell6_Value = new JLabel("");
	
	/*CMU5*/
	JLabel CMU5_Label = new JLabel("[CMU5]");
	
	JLabel CMU5_Cell0 = new JLabel("Cell0 :");
	JLabel CMU5_Cell0_Value = new JLabel("");
	
	JLabel CMU5_Cell1 = new JLabel("Cell1 :");
	JLabel CMU5_Cell1_Value = new JLabel("");
	
	JLabel CMU5_Cell2 = new JLabel("Cell2 :");
	JLabel CMU5_Cell2_Value = new JLabel("");
	
	JLabel CMU5_Cell3 = new JLabel("Cell3 :");
	JLabel CMU5_Cell3_Value = new JLabel("");
	
	JLabel CMU5_Cell4 = new JLabel("Cell4 :");
	JLabel CMU5_Cell4_Value = new JLabel("");
	
	JLabel CMU5_Cell5 = new JLabel("Cell5 :");
	JLabel CMU5_Cell5_Value = new JLabel("");
	
	JLabel CMU5_Cell6 = new JLabel("Cell6 :");
	JLabel CMU5_Cell6_Value = new JLabel("");
	
	/*BMU*/
	JLabel BMU_Label = new JLabel("[BMU]");
	
	JLabel BMU_MaxCellV = new JLabel("Cell Vmax : ");
	JLabel BMU_MaxCellV_Value = new JLabel("");
	
	JLabel BMU_MinCellV = new JLabel("Cell Vmin : ");
	JLabel BMU_MinCellV_Value = new JLabel("");
	
	JLabel BMU_MaxCellT = new JLabel("Cell Tmax : ");
	JLabel BMU_MaxCellT_Value = new JLabel("");
	
	JLabel BMU_MaxPCBT = new JLabel("PCB Tmax : ");
	JLabel BMU_MaxPCBT_Value = new JLabel("");
	
	JLabel BMU_SOCPc = new JLabel("SoC % : ");
	JLabel BMU_SOCPc_Value = new JLabel("");
	
	JLabel BMU_SOCAh = new JLabel("SoC Ah: ");
	JLabel BMU_SOCAh_Value = new JLabel("");
	
	JLabel BMU_Vpack = new JLabel("Total Vpack : ");
	JLabel BMU_Vpack_Value = new JLabel("");
	
	JLabel BMU_Ipack = new JLabel("Total Ipack : ");
	JLabel BMU_Ipack_Value = new JLabel("");
	
	JLabel BMU_Status = new JLabel("Status : ");
	JLabel BMU_Status_Value = new JLabel("");
	
	JLabel BMU_ExtStatus = new JLabel("Ext. Status : ");
	JLabel BMU_ExtStatus_Value = new JLabel("");
	 
	public TabBMS() {
		
		setBackground(Color.WHITE);	
		setForeground(Color.BLACK);
		setLayout(null);
			
		img = new ImageIcon("images/batteries.png").getImage();
		
		/*CMU1*/
		CMU1_Label.setBounds(CMU1_X, CMU1_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Label);
		
		CMU1_Cell0.setBounds(CMU1_X, CMU1_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell0);
		CMU1_Cell0_Value.setBounds(CMU1_X_VALUE, CMU1_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell0_Value);
		
		CMU1_Cell1.setBounds(CMU1_X, CMU1_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell1);
		CMU1_Cell1_Value.setBounds(CMU1_X_VALUE, CMU1_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell1_Value);
		
		CMU1_Cell2.setBounds(CMU1_X, CMU1_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell2);
		CMU1_Cell2_Value.setBounds(CMU1_X_VALUE, CMU1_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell2_Value);
		
		CMU1_Cell3.setBounds(CMU1_X, CMU1_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell3);
		CMU1_Cell3_Value.setBounds(CMU1_X_VALUE, CMU1_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell3_Value);
		
		CMU1_Cell4.setBounds(CMU1_X, CMU1_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell4);
		CMU1_Cell4_Value.setBounds(CMU1_X_VALUE, CMU1_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell4_Value);
		
		CMU1_Cell5.setBounds(CMU1_X, CMU1_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell5);
		CMU1_Cell5_Value.setBounds(CMU1_X_VALUE, CMU1_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell5_Value);
		
		CMU1_Cell6.setBounds(CMU1_X, CMU1_Y + 7*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell6);
		CMU1_Cell6_Value.setBounds(CMU1_X_VALUE, CMU1_Y + 7*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU1_Cell6_Value);
		
		/*CMU2*/
		CMU2_Label.setBounds(CMU2_X, CMU2_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Label);
		
		CMU2_Cell0.setBounds(CMU2_X, CMU2_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell0);
		CMU2_Cell0_Value.setBounds(CMU2_X_VALUE, CMU2_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell0_Value);
		
		CMU2_Cell1.setBounds(CMU2_X, CMU2_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell1);
		CMU2_Cell1_Value.setBounds(CMU2_X_VALUE, CMU2_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell1_Value);
		
		CMU2_Cell2.setBounds(CMU2_X, CMU2_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell2);
		CMU2_Cell2_Value.setBounds(CMU2_X_VALUE, CMU2_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell2_Value);
		
		CMU2_Cell3.setBounds(CMU2_X, CMU2_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell3);
		CMU2_Cell3_Value.setBounds(CMU2_X_VALUE, CMU2_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell3_Value);
		
		CMU2_Cell4.setBounds(CMU2_X, CMU2_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell4);
		CMU2_Cell4_Value.setBounds(CMU2_X_VALUE, CMU2_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell4_Value);
		
		CMU2_Cell5.setBounds(CMU2_X, CMU2_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell5);
		CMU2_Cell5_Value.setBounds(CMU2_X_VALUE, CMU2_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell5_Value);
		
		CMU2_Cell6.setBounds(CMU2_X, CMU2_Y + 7*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell6);
		CMU2_Cell6_Value.setBounds(CMU2_X_VALUE, CMU2_Y + 7*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU2_Cell6_Value);
		
		/*CMU3*/
		CMU3_Label.setBounds(CMU3_X, CMU3_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Label);
		
		CMU3_Cell0.setBounds(CMU3_X, CMU3_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell0);
		CMU3_Cell0_Value.setBounds(CMU3_X_VALUE, CMU3_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell0_Value);
		
		CMU3_Cell1.setBounds(CMU3_X, CMU3_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell1);
		CMU3_Cell1_Value.setBounds(CMU3_X_VALUE, CMU3_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell1_Value);
		
		CMU3_Cell2.setBounds(CMU3_X, CMU3_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell2);
		CMU3_Cell2_Value.setBounds(CMU3_X_VALUE, CMU3_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell2_Value);
		
		CMU3_Cell3.setBounds(CMU3_X, CMU3_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell3);
		CMU3_Cell3_Value.setBounds(CMU3_X_VALUE, CMU3_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell3_Value);
		
		CMU3_Cell4.setBounds(CMU3_X, CMU3_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell4);
		CMU3_Cell4_Value.setBounds(CMU3_X_VALUE, CMU3_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell4_Value);
		
		CMU3_Cell5.setBounds(CMU3_X, CMU3_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell5);
		CMU3_Cell5_Value.setBounds(CMU3_X_VALUE, CMU3_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell5_Value);
		
		CMU3_Cell6.setBounds(CMU3_X, CMU3_Y + 7*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell6);
		CMU3_Cell6_Value.setBounds(CMU3_X_VALUE, CMU3_Y + 7*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU3_Cell6_Value);
		
		/*CMU4*/
		CMU4_Label.setBounds(CMU4_X, CMU4_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Label);
		
		CMU4_Cell0.setBounds(CMU4_X, CMU4_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell0);
		CMU4_Cell0_Value.setBounds(CMU4_X_VALUE, CMU4_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell0_Value);
		
		CMU4_Cell1.setBounds(CMU4_X, CMU4_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell1);
		CMU4_Cell1_Value.setBounds(CMU4_X_VALUE, CMU4_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell1_Value);
		
		CMU4_Cell2.setBounds(CMU4_X, CMU4_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell2);
		CMU4_Cell2_Value.setBounds(CMU4_X_VALUE, CMU4_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell2_Value);
		
		CMU4_Cell3.setBounds(CMU4_X, CMU4_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell3);
		CMU4_Cell3_Value.setBounds(CMU4_X_VALUE, CMU4_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell3_Value);
		
		CMU4_Cell4.setBounds(CMU4_X, CMU4_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell4);
		CMU4_Cell4_Value.setBounds(CMU4_X_VALUE, CMU4_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell4_Value);
		
		CMU4_Cell5.setBounds(CMU4_X, CMU4_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell5);
		CMU4_Cell5_Value.setBounds(CMU4_X_VALUE, CMU4_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell5_Value);
		
		CMU4_Cell6.setBounds(CMU4_X, CMU4_Y + 7*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell6);
		CMU4_Cell6_Value.setBounds(CMU4_X_VALUE, CMU4_Y + 7*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU4_Cell6_Value);
		
		/*CMU5*/
		CMU5_Label.setBounds(CMU5_X, CMU5_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Label);
		
		CMU5_Cell0.setBounds(CMU5_X, CMU5_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell0);
		CMU5_Cell0_Value.setBounds(CMU5_X_VALUE, CMU5_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell0_Value);
		
		CMU5_Cell1.setBounds(CMU5_X, CMU5_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell1);
		CMU5_Cell1_Value.setBounds(CMU5_X_VALUE, CMU5_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell1_Value);
		
		CMU5_Cell2.setBounds(CMU5_X, CMU5_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell2);
		CMU5_Cell2_Value.setBounds(CMU5_X_VALUE, CMU5_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell2_Value);
		
		CMU5_Cell3.setBounds(CMU5_X, CMU5_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell3);
		CMU5_Cell3_Value.setBounds(CMU5_X_VALUE, CMU5_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell3_Value);
		
		CMU5_Cell4.setBounds(CMU5_X, CMU5_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell4);
		CMU5_Cell4_Value.setBounds(CMU5_X_VALUE, CMU5_Y + 5*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell4_Value);
		
		CMU5_Cell5.setBounds(CMU5_X, CMU5_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell5);
		CMU5_Cell5_Value.setBounds(CMU5_X_VALUE, CMU5_Y + 6*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell5_Value);
		
		CMU5_Cell6.setBounds(CMU5_X, CMU5_Y + 7*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell6);
		CMU5_Cell6_Value.setBounds(CMU5_X_VALUE, CMU5_Y + 7*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(CMU5_Cell6_Value);
		
		/*BMU*/
		BMU_Label.setBounds(BMU_X, BMU_Y, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_Label);
		
		BMU_MaxCellV.setBounds(BMU_X, BMU_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_MaxCellV);
		BMU_MaxCellV_Value.setBounds(BMU_X_VALUE, BMU_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_MaxCellV_Value);
		
		BMU_MinCellV.setBounds(BMU_X, BMU_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_MinCellV);
		BMU_MinCellV_Value.setBounds(BMU_X_VALUE, BMU_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_MinCellV_Value);
		
		BMU_Vpack.setBounds(BMU_X, BMU_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_Vpack);
		BMU_Vpack_Value.setBounds(BMU_X_VALUE, BMU_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_Vpack_Value);
		
		BMU_Ipack.setBounds(BMU_X, BMU_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_Ipack);
		BMU_Ipack_Value.setBounds(BMU_X_VALUE, BMU_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_Ipack_Value);
		
		BMU_MaxCellT.setBounds(BMU_X_2, BMU_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_MaxCellT);
		BMU_MaxCellT_Value.setBounds(BMU_X_2_VALUE, BMU_Y + LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_MaxCellT_Value);
		
		BMU_MaxPCBT.setBounds(BMU_X_2, BMU_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_MaxPCBT);
		BMU_MaxPCBT_Value.setBounds(BMU_X_2_VALUE, BMU_Y + 2*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_MaxPCBT_Value);
		
		BMU_SOCAh.setBounds(BMU_X_2, BMU_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_SOCAh);
		BMU_SOCAh_Value.setBounds(BMU_X_2_VALUE, BMU_Y + 3*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_SOCAh_Value);
		
		BMU_SOCPc.setBounds(BMU_X_2, BMU_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_SOCPc);
		BMU_SOCPc_Value.setBounds(BMU_X_2_VALUE, BMU_Y + 4*LINE_OFFSET, LABEL_WIDTH, LABEL_HEIGHT);
		add(BMU_SOCPc_Value);
		
	}
	
	public void updateValues() {		
		
		/*CMU1*/
		CMU1_Cell0_Value.setText(dd.getRoundedValue(BMS_ID, CMU1_CELL0_ID));
		CMU1_Cell1_Value.setText(dd.getRoundedValue(BMS_ID, CMU1_CELL1_ID));
		CMU1_Cell2_Value.setText(dd.getRoundedValue(BMS_ID, CMU1_CELL2_ID));
		CMU1_Cell3_Value.setText(dd.getRoundedValue(BMS_ID, CMU1_CELL3_ID));
		CMU1_Cell4_Value.setText(dd.getRoundedValue(BMS_ID, CMU1_CELL4_ID));
		CMU1_Cell5_Value.setText(dd.getRoundedValue(BMS_ID, CMU1_CELL5_ID));
		CMU1_Cell6_Value.setText(dd.getRoundedValue(BMS_ID, CMU1_CELL6_ID));
		
		/*CMU2*/
		CMU2_Cell0_Value.setText(dd.getRoundedValue(BMS_ID, CMU2_CELL0_ID));
		CMU2_Cell1_Value.setText(dd.getRoundedValue(BMS_ID, CMU2_CELL1_ID));
		CMU2_Cell2_Value.setText(dd.getRoundedValue(BMS_ID, CMU2_CELL2_ID));
		CMU2_Cell3_Value.setText(dd.getRoundedValue(BMS_ID, CMU2_CELL3_ID));
		CMU2_Cell4_Value.setText(dd.getRoundedValue(BMS_ID, CMU2_CELL4_ID));
		CMU2_Cell5_Value.setText(dd.getRoundedValue(BMS_ID, CMU2_CELL5_ID));
		CMU2_Cell6_Value.setText(dd.getRoundedValue(BMS_ID, CMU2_CELL6_ID));
		
		/*CMU3*/
		CMU3_Cell0_Value.setText(dd.getRoundedValue(BMS_ID, CMU3_CELL0_ID));
		CMU3_Cell1_Value.setText(dd.getRoundedValue(BMS_ID, CMU3_CELL1_ID));
		CMU3_Cell2_Value.setText(dd.getRoundedValue(BMS_ID, CMU3_CELL2_ID));
		CMU3_Cell3_Value.setText(dd.getRoundedValue(BMS_ID, CMU3_CELL3_ID));
		CMU3_Cell4_Value.setText(dd.getRoundedValue(BMS_ID, CMU3_CELL4_ID));
		CMU3_Cell5_Value.setText(dd.getRoundedValue(BMS_ID, CMU3_CELL5_ID));
		CMU3_Cell6_Value.setText(dd.getRoundedValue(BMS_ID, CMU3_CELL6_ID));
		
		/*CMU4*/
		CMU4_Cell0_Value.setText(dd.getRoundedValue(BMS_ID, CMU4_CELL0_ID));
		CMU4_Cell1_Value.setText(dd.getRoundedValue(BMS_ID, CMU4_CELL1_ID));
		CMU4_Cell2_Value.setText(dd.getRoundedValue(BMS_ID, CMU4_CELL2_ID));
		CMU4_Cell3_Value.setText(dd.getRoundedValue(BMS_ID, CMU4_CELL3_ID));
		CMU4_Cell4_Value.setText(dd.getRoundedValue(BMS_ID, CMU4_CELL4_ID));
		CMU4_Cell5_Value.setText(dd.getRoundedValue(BMS_ID, CMU4_CELL5_ID));
		CMU4_Cell6_Value.setText(dd.getRoundedValue(BMS_ID, CMU4_CELL6_ID));
		
		/*CMU5*/
		CMU5_Cell0_Value.setText(dd.getRoundedValue(BMS_ID, CMU5_CELL0_ID));
		CMU5_Cell1_Value.setText(dd.getRoundedValue(BMS_ID, CMU5_CELL1_ID));
		CMU5_Cell2_Value.setText(dd.getRoundedValue(BMS_ID, CMU5_CELL2_ID));
		CMU5_Cell3_Value.setText(dd.getRoundedValue(BMS_ID, CMU5_CELL3_ID));
		CMU5_Cell4_Value.setText(dd.getRoundedValue(BMS_ID, CMU5_CELL4_ID));
		CMU5_Cell5_Value.setText(dd.getRoundedValue(BMS_ID, CMU5_CELL5_ID));
		CMU5_Cell6_Value.setText(dd.getRoundedValue(BMS_ID, CMU5_CELL6_ID));
		
		/*BMU*/
		BMU_MaxCellV_Value.setText(dd.getRoundedValue(BMS_ID, BMU_MAXCELLV_ID));
		BMU_MinCellV_Value.setText(dd.getRoundedValue(BMS_ID, BMU_MINCELLV_ID));
		BMU_SOCPc_Value.setText(dd.getRoundedValue(BMS_ID, BMU_SOCPC_ID));
		BMU_SOCAh_Value.setText(dd.getRoundedValue(BMS_ID, BMU_SOCAH_ID));
		
		BMU_MaxCellT_Value.setText(dd.getRoundedValue(BMS_ID, BMU_MAXCELLT_ID));
		BMU_MaxPCBT_Value.setText(getMaxPCBTemp());
		BMU_Vpack_Value.setText(dd.getRoundedValue(BMS_ID, BMU_PACKV_ID));
		BMU_Ipack_Value.setText(dd.getRoundedValue(BMS_ID, BMU_PACKA_ID));
	
	}
	
	public void paintComponent(Graphics g) {
		
		int width = getWidth();
		int height = getHeight();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.drawImage(img, 180, 100, null);
	}
	
	private String getMaxPCBTemp() {
		
		double CMU1PCBTemp = (double)(dd.getDeviceByID(BMS_ID).getItemByID(CMU1_PCBTEMP_ID).getLastData());
		double CMU2PCBTemp = (double)(dd.getDeviceByID(BMS_ID).getItemByID(CMU2_PCBTEMP_ID).getLastData());
		double CMU3PCBTemp = (double)(dd.getDeviceByID(BMS_ID).getItemByID(CMU3_PCBTEMP_ID).getLastData());
		double CMU4PCBTemp = (double)(dd.getDeviceByID(BMS_ID).getItemByID(CMU4_PCBTEMP_ID).getLastData());
		double CMU5PCBTemp = (double)(dd.getDeviceByID(BMS_ID).getItemByID(CMU5_PCBTEMP_ID).getLastData());
		
		return Rounding.roundDouble(Math.max(Math.max(Math.max(CMU1PCBTemp, CMU2PCBTemp), Math.max(CMU3PCBTemp, CMU4PCBTemp)), CMU5PCBTemp), 3) + " " + dd.getDeviceByID(BMS_ID).getItemByID(CMU1_PCBTEMP_ID).getUnit();		
	}
}
