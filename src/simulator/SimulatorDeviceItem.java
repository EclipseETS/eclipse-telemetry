package simulator;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Random;
import util.ByteManipulator;
import util.HexString;

import javax.swing.BorderFactory;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;

import org.apache.log4j.Logger;

import net.miginfocom.swing.MigLayout;

// Needs the current deviceItem
import eclipseV7.data.Device;
import eclipseV7.data.DeviceItem;

public class SimulatorDeviceItem extends JPanel implements ActionListener, ItemListener, ChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	// Root Logger
	static Logger logger = Logger.getLogger("simulator");
	
	// Visual components
    private JCheckBox jCheckBoxDeviceItemName;
    private JLabel jLabelMaxVal;
    private JLabel jLabelMinVal;
    private JLabel jLabelUnit;
    private JSlider jSliderVal;
    private JLabel jLabelValue;
	
	private DeviceItem item;
	private Device device; 
	private int factoredIntValue=1;
	
	// Others
	private boolean enabled = false;
	private Double delta = 0.0;
    private int randomValue;
    
    public SimulatorDeviceItem(Device device, DeviceItem item) {
    	this.item = item;
    	this.device = device;
    	createPanel();
    }
    
    public int getId() {
    	return item.getItemId();
    }
    
    public String getName() {
    	return item.getName();
    }
    
    public int getFactoredIntValue() {
    	if(item.isFloat())
    		return Float.floatToRawIntBits(this.factoredIntValue);
    	return this.factoredIntValue;
    }
    
    @SuppressWarnings("serial")
	private void createPanel() {    
    	// Components
        jCheckBoxDeviceItemName = new JCheckBox();
        
        //Creates the slider with the ability of clicking on it to set value instead of
        //only the dragging the cursor on slider to set it
        jSliderVal = new JSlider(){
            {
                MouseListener[] listeners = getMouseListeners();
                for (MouseListener l : listeners)
                    removeMouseListener(l); // remove UI-installed TrackListener
                final BasicSliderUI ui = (BasicSliderUI) getUI();
                BasicSliderUI.TrackListener tl = ui.new TrackListener() {
                    // this is where we jump to absolute value of click
                    @Override public void mouseClicked(MouseEvent e) {
                        Point p = e.getPoint();
                        int value = ui.valueForXPosition(p.x);

                        setValue(value);
                    }
                    // disable check that will invoke scrollDueToClickInTrack
                    @Override public boolean shouldScroll(int dir) {
                        return false;
                    }
                };
                addMouseListener(tl);
            }
        };
        
        jLabelMinVal = new JLabel();
        jLabelMaxVal = new JLabel();
        jLabelValue = new JLabel();
        jLabelUnit = new JLabel();

        this.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBoxDeviceItemName.setText(item.getName()+"["+item.getItemId()+"]"); 
        jCheckBoxDeviceItemName.setName("jCheckBoxDeviceItemName"); 
        jCheckBoxDeviceItemName.setToolTipText(item.getName());
        jCheckBoxDeviceItemName.addItemListener(this);

        jSliderVal.setName("jSliderVal"); 
        jSliderVal.setMinimum(item.getMinValue());
        jSliderVal.setMaximum(item.getMaxValue()*2);
        jSliderVal.setPaintTicks(true);
        jSliderVal.setPaintLabels(true);
        
        int sliderIncrement;
        
        if (item.getMinValue() < 0) {
        	if (item.getMaxValue() + Math.abs(item.getMinValue()) < 40) 
        		sliderIncrement = 1;
        	else if ((item.getMaxValue() + Math.abs(item.getMinValue()))/100 < 10) 
        		sliderIncrement = 50;
        	else if ((item.getMaxValue() + Math.abs(item.getMinValue()))/500 < 10) 
        		sliderIncrement = 250 ;
        	else 
        		sliderIncrement = 10000;
        }
        else {
         	if ((item.getMaxValue() - item.getMinValue()) < 40) 
         		sliderIncrement = 1;
        	else if ((item.getMaxValue()*2 - item.getMinValue())/100 < 10) 
        		sliderIncrement = 50;
        	else if ((item.getMaxValue()*2 - item.getMinValue())/500 < 10) 
        		sliderIncrement = 125;
        	else 
        		sliderIncrement = 10000;
        }
        jSliderVal.setMajorTickSpacing(sliderIncrement);
        jSliderVal.setMinorTickSpacing(sliderIncrement/5);
        
        if (item.getMinValue() < 0) 
        	jSliderVal.setValue(1);
        else 
        	jSliderVal.setValue(1);
        
        jSliderVal.setPaintTicks(true);
        jSliderVal.setPaintLabels(true);
        jSliderVal.addChangeListener(this);

        jLabelMinVal.setText(new Integer(item.getMinValue()).toString()); 
        jLabelMinVal.setName("jLabelMinVal"); 

        jLabelMaxVal.setText(new Integer(item.getMaxValue()).toString()); 
        jLabelMaxVal.setName("jLabelMaxVal"); 

        jLabelValue.setText("1"); 
        jLabelValue.setName("jLabelValue"); 
        jLabelValue.setOpaque(true);
        jLabelValue.setBackground(Color.white);
        

        jLabelUnit.setText(item.getUnit()); 
        jLabelUnit.setToolTipText(item.getUnit());
        jLabelUnit.setName("jLabelUnit"); 

        
        // Layout
        MigLayout layout = new MigLayout(
        		"insets 5", // Layout constraints
        		"", // Column constraints
        		""); // Row constaints
        this.setLayout(layout);
        factoredIntValue=1;
        this.add(jCheckBoxDeviceItemName, "width 120!");
        this.add(jLabelMinVal, "width 60!");
        this.add(jSliderVal, "width 400!");
        this.add(jLabelMaxVal, "width 50!");
        this.add(jLabelValue, "width 100!");
        this.add(jLabelUnit, "width 60!");
        // If the item is binary, we need to show the bit definitions
        if (item.isBinaryData()) {
        	JPanel binaryOptionsPanel = new JPanel();
        	binaryOptionsPanel.add(new JLabel("Binary!"));
        	this.add(binaryOptionsPanel, "newline");
        }
    }
    
    public String getSimulatedFrame(){

    	//TODO Temp.. retourner la trame au complet avec les encapsule etc.
    	//MARCO ICI Les Changes a faire.
    	double textFieldValue = new Double(jLabelValue.getText());
		
    	// Gerer le +/-
    	Double randomSign = Math.random() > 0.5 ? -1.0 : 1.0;
    	Double random = Math.random() * delta;
    	Double randomResult = textFieldValue + (random * randomSign);
    	
    	factoredIntValue = (int) (randomResult * item.getFactor()/item.getResolution());
    	
    	try {
    		//String start = "0A";
        	String deviceId = Integer.toHexString(device.getId()).toUpperCase();
        	if (deviceId.length() == 1) deviceId = "0" + deviceId;
        	String itemId = Integer.toHexString(item.getItemId()).toUpperCase();
        	if (itemId.length() == 1) itemId = "00" + itemId;
        	if (itemId.length() == 2) itemId = "0" + itemId;
			String data = HexString.bufferToHex(ByteManipulator.intToByteArray(
					this.getFactoredIntValue(), item.getNumBits()/8, item.isSigned()))
					.toUpperCase();
			String CRC = "AA";
	    	
	    	//return start + deviceId + itemId + data + CRC;
	    	return deviceId + itemId + data + CRC;
	    	
    	} catch (Exception e) {return "0A0000000AA0D";}
    }
    
    public JPanel getJPanel() {
    	return this;
    }
    
    public void setChecked(boolean state) {
    	jCheckBoxDeviceItemName.setSelected(state);
    }
    
    public void setRandomInput(){
    	Random rand = new Random();
    	randomValue = rand.nextInt(item.getMaxValue()/2);
    	
    	jLabelValue.setText(Integer.toString(randomValue));
    	jSliderVal.setValue(randomValue);
    }
    
    public void setInputsToMin() {
    	jLabelValue.setText(Integer.toString(item.getMinValue()));
    	jSliderVal.setValue(item.getMinValue());
	}
    
    public boolean isActive() {
    	return enabled;
    }
    
	@Override
	public void actionPerformed(ActionEvent ae) {
		logger.debug("Double in the field: " + jLabelValue.getText() + " the Integer: " + factoredIntValue);
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		// Checkbox
		if (ie.getSource() instanceof JCheckBox) {
			if (ie.getStateChange() == ItemEvent.SELECTED) this.enabled = true;
			if (ie.getStateChange() == ItemEvent.DESELECTED) this.enabled = false;
		}
	}

	@Override
	public void stateChanged(ChangeEvent ce) {
		if (ce.getSource() instanceof JSlider) {
			JSlider source = (JSlider)ce.getSource();
			jLabelValue.setText(new Integer(source.getValue()).toString());
		}
	}
    
}
