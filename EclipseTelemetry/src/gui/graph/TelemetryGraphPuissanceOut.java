package gui.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;
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
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;

import core.DataManager;

import eclipseV7.data.Device;
import eclipseV7.data.DeviceItem;
import exception.DeviceItemNotFoundException;
import exception.DeviceNotFoundException;

@SuppressWarnings("serial")
public class TelemetryGraphPuissanceOut extends JPanel implements Dockable, Observer, TelemetryGraphInterface {

	DockKey key;
	
	TimeSeries graphedValues;
	ChartPanel chartPanel;
	
	int[] deviceId = new int[6];
	int[] itemDeviceId = new int[6];
	DeviceItem[] item = new DeviceItem[6];
	Device[] device = new Device[6];
	TimeSeriesCollection dataset;
	double x;
	double y;
	
	public TelemetryGraphPuissanceOut() {
		deviceId[0]=10;
		deviceId[1]=10;
		deviceId[2]=11;
		deviceId[3]=11;
		deviceId[4]=12;
		deviceId[5]=12;
		itemDeviceId[0]=3;
		itemDeviceId[1]=4;
		itemDeviceId[2]=3;
		itemDeviceId[3]=4;
		itemDeviceId[4]=3;
		itemDeviceId[5]=4;
		try {
			for(int i=0;i<device.length;i++)
			{
			item[i] = DataManager.getInstance().getDeviceItem(deviceId[i], itemDeviceId[i]);
			device[i] = DataManager.getInstance().getDevice(deviceId[i]);
			}
			DataManager.getInstance().getDeviceItem(deviceId[0], itemDeviceId[0]).addObserver(this);
			
		} catch (DeviceItemNotFoundException e) {
			e.printStackTrace();
		} catch (DeviceNotFoundException e) {
			e.printStackTrace();
		}
		
		key = new DockKey("puissance de sortie");
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

		dataset= new TimeSeriesCollection();
		
		//dataset.addSeries(this.graphedValues);
		dataset.addSeries(graphedValues);
		DateAxis domain = new DateAxis("Time");
		NumberAxis range = new NumberAxis("Puissance de Sortie");
		//range.setRange(item.getMinValue(), item.getMaxValue());
		range.setAutoRange(true);
		
		domain.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		range.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		domain.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
		range.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
		
		XYItemRenderer renderer = new XYLineAndShapeRenderer(true,true);
		renderer.setSeriesPaint(0, Color.RED);
		//renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setBaseStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL));
	
		  
		XYPlot plot = new XYPlot(dataset, domain, range, renderer);
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		
		domain.setAutoRange(true);
		domain.setLowerMargin(0.0);
		domain.setUpperMargin(0.0);
		domain.setTickLabelsVisible(true);
		range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		JFreeChart chart = new JFreeChart("Puissance de sortie",
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
		        JOptionPane.showMessageDialog(new JFrame(), ""+y+"  ::  "+new Second(new Date((long)x)));
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

	private void addValuetoGraph(double value) {
	graphedValues.addOrUpdate(new Second(), value);
	}
	
	private void addHistory() {
		graphedValues = new TimeSeries("value");
		double[][][] valuesTmp=new double[item.length][][];
		for(int i=0;i<item.length;i++){
			valuesTmp[i]=item[i].getAllData();			
		}
		for(int i=0;i<valuesTmp[0].length;i++){
//			if(-5<=(valuesTmp[0][i][0]-valuesTmp[1][i][0])&&5>=(valuesTmp[0][i][0]-valuesTmp[1][i][0])&&
//				-5<=(valuesTmp[1][i][0]-valuesTmp[2][i][0])&&5>=(valuesTmp[1][i][0]-valuesTmp[2][i][0])&&
//				-5<=(valuesTmp[2][i][0]-valuesTmp[3][i][0])&&5>=(valuesTmp[2][i][0]-valuesTmp[3][i][0])&&
//				-5<=(valuesTmp[3][i][0]-valuesTmp[4][i][0])&&5>=(valuesTmp[3][i][0]-valuesTmp[4][i][0])&&
//				-5<=(valuesTmp[4][i][0]-valuesTmp[5][i][0])&&5>=(valuesTmp[4][i][0]-valuesTmp[5][i][0]))
			try{
				double power=valuesTmp[0][i][1]*
				valuesTmp[1][i][1]+
				valuesTmp[2][i][1]*
				valuesTmp[3][i][1]+
					valuesTmp[4][i][1]*
				valuesTmp[5][i][1];
				graphedValues.addOrUpdate(new Second(new Date((long) valuesTmp[0][i][0])),power);
			
		}
			catch(Exception e)
			{
				
			}
		}	
			
//		double[][] tmp = item[0].getAllData();
//		graphedValues = new TimeSeries("value");
//		for (int i=0;i<tmp.length;i++)
//			graphedValues.addOrUpdate(new Second(new Date((long) tmp[i][0])), tmp[i][1]*3.6);
//		}
//		
	}

	@Override
	public void update(Observable o, Object arg) {
		double value = item[0].getDoubleValue()*item[1].getDoubleValue()+
		item[2].getDoubleValue()*item[3].getDoubleValue()+
		item[4].getDoubleValue()*item[5].getDoubleValue();
		addValuetoGraph(value);
		graphedValues.fireSeriesChanged();
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