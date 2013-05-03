package eclipse.view.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;
import java.util.List;

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

import eclipse.model.data.Data;
import eclipse.model.data.DataManager;
import eclipse.model.data.Device;
import eclipse.model.data.DeviceItem;

public class TelemetryGraph extends JPanel implements TabPane {

	private static final long serialVersionUID = 7068305431504601524L;
	TimeSeries graphedValues;
	ChartPanel chartPanel;
	
	int deviceId;
	int itemDeviceId;
	DeviceItem item;
	Device device;
	TimeSeriesCollection dataset;
	double x;
	double y;
	
	
	public TelemetryGraph(int deviceId, int itemDeviceId) {
		this.deviceId = deviceId;
		this.itemDeviceId = itemDeviceId;
		try {

			device = DataManager.getInstance().getDeviceByID(deviceId);
			item = device.getItemByID(itemDeviceId);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		addHistory();
		createGraph();
		this.setLayout(new GridLayout(1,1));
		this.add(chartPanel);
		
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
		NumberAxis range = new NumberAxis(item.getUnit());
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
		
		JFreeChart chart = new JFreeChart("(Id: " + item.getItemId() + ")",
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

			public void chartMouseMoved(ChartMouseEvent arg0) {
			
			}
		});

		
	}

	private void addValuetoGraph(double value) {
		graphedValues.addOrUpdate(new Second(), value);
	}
	
	private void addHistory() {
		List<Data> tmp = item.getAllData();
		graphedValues = new TimeSeries("value");
		for (int i=0;i<tmp.size();i++)
			graphedValues.addOrUpdate(new Second(new Date((long) tmp.get(i).getDate())), tmp.get(i).getData());
		
	}

	public void updateValues() {
		double value = item.getLastData();
		addValuetoGraph(value);
		graphedValues.fireSeriesChanged();
	}


}
