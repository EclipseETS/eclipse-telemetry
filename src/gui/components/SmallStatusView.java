package gui.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class SmallStatusView extends JPanel {

	String name;

	JLabel label;
	JLabel value;
	JPanel status;

	public SmallStatusView(String name) {
		this.name = name;
		buildUI();
		//setOk();
	}

	private void buildUI() {

		label = new JLabel(name + ": ");
		label.setSize(10, 6);
		label.setFont(new Font("SansSerif", Font.BOLD, 12));

		value = new JLabel();
		value.setSize(10, 6);
		//value.setText("0");

		status = new JPanel();
		status.setSize(3, 3);

		MigLayout contentLayout = new MigLayout(
				"", // Layout constraints
				"", // Column constraints
				""); // Row constaints

		this.setLayout(contentLayout);
		this.add(label);
		this.add(value);
		this.add(status);
	}

	public void updateValue(double newValue, String unit) {
		value.setText(Double.toString(newValue) + " " + unit);
	}

	public void setOk() {
		status.setBackground(Color.GREEN);
	}

	public void setWarning() {
		status.setBackground(Color.YELLOW);
	}

	public void setError() {
		status.setBackground(Color.RED);
	}
}
