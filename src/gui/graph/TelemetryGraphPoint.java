package gui.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.ui.RectangleInsets;

import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;

import core.DataManager;

import eclipseV7.data.Device;
import eclipseV7.data.DeviceItem;
import exception.DeviceItemNotFoundException;
import exception.DeviceNotFoundException;

@SuppressWarnings("serial")
public class TelemetryGraphPoint extends JPanel implements Dockable, Observer, TelemetryGraphInterface {

	DockKey key;
	
	TimeSeries graphedValues;
	ChartPanel chartPanel;
	
	int deviceId;
	int itemDeviceId;
	DeviceItem item;
	Device device;
	DefaultXYDataset dataset;
	double x;
	double y;
	double[][] seriesmarco;
	
	
	public TelemetryGraphPoint() {
		this.deviceId = 4;
		this.itemDeviceId = 7;
		try {
			item = DataManager.getInstance().getDeviceItem(deviceId, itemDeviceId);
			device = DataManager.getInstance().getDevice(deviceId);
			DataManager.getInstance().getDeviceItem(deviceId, itemDeviceId).addObserver(this);
		} catch (DeviceItemNotFoundException e) {
			e.printStackTrace();
		} catch (DeviceNotFoundException e) {
			e.printStackTrace();
		}
		
		key = new DockKey(item.getName() + " Graph");
		addHistory();
		createGraph();
		this.setLayout(new GridLayout(1,1));
		this.add(chartPanel);
		//this.setSize(1000,1000);
		
	}
	


	private void createGraph() {
		//graphedValues = new TimeSeries(item.getUnit());
		//graphedValues = new TimeSeries(item.getUnit(), Second.class);
		//graphedValues.setMaximumItemAge(3000);
		//graphedValues.setMaximumItemCount(30); 

		dataset= new DefaultXYDataset();
		//dataset.addSeries(this.graphedValues);
		dataset.addSeries(1,seriesmarco);
		NumberAxis domain = new NumberAxis("W Dépensé");
		NumberAxis range = new NumberAxis("km/h");
		//range.setRange(item.getMinValue(), item.getMaxValue());
		range.setAutoRange(true);
		domain.setAutoRange(true);
		range.setAutoRangeIncludesZero(false);
		domain.setAutoRangeIncludesZero(false);
		domain.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		range.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		domain.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
		range.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
		
		StandardXYItemRenderer renderer = new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES);
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setBaseStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL));
	
		  
		XYPlot plot = new XYPlot(dataset, domain, range, renderer);
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		
		domain.setAutoRange(true);
//		domain.setLowerMargin(0.0);
//		domain.setUpperMargin(0.0);
		domain.setTickLabelsVisible(true);
		//range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		JFreeChart chart = new JFreeChart("Puissance dépensé vs vitesse",
										 new Font("SansSerif",Font.BOLD, 24),
										 plot,
										 true);
		
		//List<String> subtitles = new List<String>();
		//subtitles.add("(on " + device.getName() + ")");
		//chart.setSubtitles(subtitles);
		chart.setBackgroundPaint(Color.white);
		chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(BorderFactory.createCompoundBorder(
											BorderFactory.createEmptyBorder(4, 4, 4, 4),
											BorderFactory.createLineBorder(Color.black)));
		chartPanel.setSize(new Dimension(400, 400));
		chartPanel.addChartMouseListener(new ChartMouseListener(){
		    public void chartMouseClicked(ChartMouseEvent e){
		    	try{
		    	XYItemEntity xyitem=(XYItemEntity) e.getEntity();
		        double x = dataset.getXValue(xyitem.getSeriesIndex(), xyitem.getItem());
		        double y = dataset.getYValue(xyitem.getSeriesIndex(), xyitem.getItem());
		        JOptionPane.showMessageDialog(new JFrame(), ""+y+"  ::  "+x);
		    	}
		    	catch(Exception exception )
		    	{
		    	}
		    	
				    }

			@SuppressWarnings("unused")
			public void chartMouseClicked1(ChartMouseEvent arg0) {
		    	XYItemEntity xyitem=(XYItemEntity) arg0.getEntity();
		        double x = dataset.getXValue(xyitem.getSeriesIndex(), xyitem.getItem());
		        double y = dataset.getYValue(xyitem.getSeriesIndex(), xyitem.getItem());
				   }

			@Override
			public void chartMouseMoved(ChartMouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		
	}

	@SuppressWarnings("unused")
	private void addValuetoGraph(double value) {
	}
	
	private void addHistory() {
		double[][] speed = item.getAllData();
		double[][] voltage = null;
		double[][] current = null;
		try {
			voltage =DataManager.getInstance().getDevice(4).getItemByID(6).getAllData();	
		    current =DataManager.getInstance().getDevice(4).getItemByID(5).getAllData();
		} catch (DeviceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		seriesmarco = new double[2][speed.length];
		for(int i=0;i<seriesmarco[0].length;i++){
			seriesmarco[0][i]=voltage[i][1]*current[i][1];
			seriesmarco[1][i]=speed[i][1];
			
		}
		
//		graphedValues = new TimeSeries("value");
//		for (int i=0;i<tmp.length;i++)
//			graphedValues.addOrUpdate(new Second(new Date((long) tmp[i][0])), tmp[i][1]*3.6);
//		
	}

	@Override
	public void update(Observable o, Object arg) {
//		double value = ((DeviceItem) o).getDoubleValue();
//		addValuetoGraph(value);
//		graphedValues.fireSeriesChanged();
	}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public DockKey getDockKey() {
		return key;
	}
}
