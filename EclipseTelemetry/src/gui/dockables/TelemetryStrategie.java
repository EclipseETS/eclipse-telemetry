package gui.dockables;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.regex.Pattern;


import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;


import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class TelemetryStrategie extends JPanel implements Dockable, ActionListener {

	DockKey key = new DockKey("RACING Strategy");
	private JButton btnCalculate;
	private JRadioButton rdbtnBattery;
	private JRadioButton rdbtnBatteryOn;
	private JRadioButton rdbtnSpped;
	private JRadioButton rdbtnDistance;
	private JRadioButton rdbtnFinalKm;
	private JRadioButton rdbtnTime;
	private JFormattedTextField batt;
	private JFormattedTextField battMatin;
	private JFormattedTextField speed;
	private JFormattedTextField distance;
	private JFormattedTextField finalKM;
	private JFormattedTextField time;
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel lblHhmm;
	private ButtonGroup bgroup;
	private JRadioButton data;
	private JLabel lblKm;
	private JLabel lblKm_1;
	private JLabel lblKmh;
	private JLabel label_1;
	private JLabel label_2;
	
	public TelemetryStrategie() {
		setLayout(new MigLayout("", "[][][42.00,grow][grow][][grow][]", "[][][][][][][][][]"));
		bgroup = new ButtonGroup();
		label = new JLabel("");
		add(label, "cell 6 0");
		
		lblNewLabel = new JLabel("");
		add(lblNewLabel, "cell 0 1");
		
		rdbtnSpped = new JRadioButton("speed");
		add(rdbtnSpped, "cell 1 2");
		
		speed = new JFormattedTextField();
		add(speed, "cell 2 2,growx");
		
		lblKmh = new JLabel("km/h");
		add(lblKmh, "cell 3 2");
		
		
		rdbtnBattery = new JRadioButton("% Battery");
		add(rdbtnBattery, "cell 4 2");
		
		batt = new JFormattedTextField();
		add(batt, "cell 5 2,growx");
		
		label_2 = new JLabel("0-100");
		add(label_2, "cell 6 2");
		
		rdbtnDistance = new JRadioButton("Distance");
		add(rdbtnDistance, "cell 1 3");
		
		distance = new JFormattedTextField();
		add(distance, "cell 2 3,growx");
		
		lblKm_1 = new JLabel("km");
		add(lblKm_1, "cell 3 3");
		
		rdbtnBatteryOn = new JRadioButton("% Battery on morning");
		add(rdbtnBatteryOn, "cell 4 3");
		
		battMatin = new JFormattedTextField();
		add(battMatin, "cell 5 3,growx");
		
		label_1 = new JLabel("0-100");
		add(label_1, "cell 6 3");
		
		rdbtnFinalKm = new JRadioButton("Final KM");
		add(rdbtnFinalKm, "cell 1 4");
		
		finalKM = new JFormattedTextField();
		add(finalKM, "cell 2 4,growx");
		
		lblKm = new JLabel("km");
		add(lblKm, "cell 3 4");
		
		rdbtnTime = new JRadioButton("Time");
		add(rdbtnTime, "cell 1 5");
		
		time = new JFormattedTextField();
		add(time, "cell 2 5,growx");
		
		lblHhmm = new JLabel("hh:mm");
		add(lblHhmm, "cell 3 5");
		
		btnCalculate = new JButton("Calculate");
		add(btnCalculate, "cell 4 8,alignx right");
		
		
		btnCalculate.setActionCommand("calculate");
		btnCalculate.addActionListener(this);
		
		key.setName("Racing View");
		
		
		
		bgroup.add(rdbtnDistance);
		bgroup.add(rdbtnFinalKm);
		bgroup.add(rdbtnSpped);
		bgroup.add(rdbtnTime);
		bgroup.add(rdbtnBattery);
		bgroup.add(rdbtnBatteryOn);
		
		
		rdbtnSpped.setActionCommand("1");
		rdbtnSpped.addActionListener(this);
		
		rdbtnDistance.setActionCommand("2");
		rdbtnDistance.addActionListener(this);
		
		rdbtnFinalKm.setActionCommand("3");
		rdbtnFinalKm.addActionListener(this);
		
		rdbtnTime.setActionCommand("4");
		rdbtnTime.addActionListener(this);
		
		rdbtnBattery.setActionCommand("5");
		rdbtnBattery.addActionListener(this);
		
		rdbtnBatteryOn.setActionCommand("6");
		rdbtnBatteryOn.addActionListener(this);
		
		
		
	}
	
	
	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public DockKey getDockKey() {
		return key;
	}

	private boolean verifyField(){
		if (Pattern.matches("[0-9]*", speed.getText()))
			if (Pattern.matches("[0-9]*", batt.getText()))
				if (Pattern.matches("[0-9]*", battMatin.getText()))
					if (Pattern.matches("[0-9]*", distance.getText()))
						if (Pattern.matches("[0-9]*", finalKM.getText()))
							if (Pattern.matches("[0-9]{1,2}:[0-9]{1,2}", time.getText())||Pattern.matches("", time.getText()))
								return true;
							else
								JOptionPane.showMessageDialog(new JFrame(), "Invalid time");
						else
							JOptionPane.showMessageDialog(new JFrame(), "Invalid final KM");
					else	
						JOptionPane.showMessageDialog(new JFrame(), "Invalid distance");
				else
					JOptionPane.showMessageDialog(new JFrame(), "Invalid Morning%");
			else
				JOptionPane.showMessageDialog(new JFrame(), "Invalid Batt%");
		else
			JOptionPane.showMessageDialog(new JFrame(), "Invalid speed");
					
			
			return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("calculate".equals(e.getActionCommand())) {
			if(verifyField()){
				JOptionPane.showMessageDialog(new JFrame(), data.getLabel());
			}
		}
		else if ("1".equals(e.getActionCommand())) {
			data=rdbtnSpped;
		}
		else if ("2".equals(e.getActionCommand())) {
			data=rdbtnDistance;
		}
		else if ("3".equals(e.getActionCommand())) {
			data=rdbtnFinalKm;
		}
		else if ("4".equals(e.getActionCommand())) {
			data=rdbtnTime;
		}
		else if ("5".equals(e.getActionCommand())) {
			data=rdbtnBattery;
		}
		else if ("6".equals(e.getActionCommand())) {
			data=rdbtnBatteryOn;
		}
	}	
}