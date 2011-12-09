package gui.components;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import eclipseV7.data.DeviceItem;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class SmallStatusView extends JPanel implements Observer {

	String name;

	JLabel label;
	JLabel value;
	JPanel status;
	DeviceItem item;

	public SmallStatusView(String name) {
		
		this.name = name;
		buildUI();
		//setOk();
	}
	
	public SmallStatusView(DeviceItem item) {
		this.item=item;
		name=item.getName() + ":   "+item.getDoubleValue();
		item.addObserver(this);
		buildUI();
		//setOk();
	}

	private void buildUI() {

		label = new JLabel(name);
		colorization();
		label.setSize(10, 6);
		label.setFont(new Font("SansSerif", Font.BOLD, 10));
		
		
		
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

	public void updateValue() {
		label.setText( item.getName() + ":"+item.getDoubleValue());
	}

	
	public void setOk() {
		status.setBackground(Color.GREEN);
	}

	public void setWarning() {
		status.setBackground(Color.ORANGE);
	}

	public void setError() {
		status.setBackground(Color.RED);
	}
	
	private void colorization(){
		if (item.status()==0)
			label.setForeground(Color.BLACK);
		else if (item.status()>4)
			label.setForeground(Color.RED);
		else
			label.setForeground(Color.ORANGE);
	}

	@Override
	public void update(Observable o, Object arg) {
		colorization();
		updateValue();
		
	}
}
