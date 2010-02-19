package gui.dockables;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
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

public class TelemetryGraph extends JPanel implements Dockable, Observer {

	DockKey key;
	
	TimeSeries graphedValues;
	ChartPanel chartPanel;
	
	int deviceId;
	int itemDeviceId;
	DeviceItem item;
	Device device;
	
	public TelemetryGraph(int deviceId, int itemDeviceId) {
		this.deviceId = deviceId;
		this.itemDeviceId = itemDeviceId;
		try {
			item = DataManager.getInstance().getDeviceItem(deviceId, itemDeviceId);
			device = DataManager.getInstance().getDevice(deviceId);
		} catch (DeviceItemNotFoundException e) {
			e.printStackTrace();
		} catch (DeviceNotFoundException e) {
			e.printStackTrace();
		}
		
		key = new DockKey(item.getName() + " Graph");
		createGraph();
		
		this.setLayout(new GridLayout(1,1));
		this.add(chartPanel);
		//this.setSize(1000,1000);
		
	}
	
	private void createGraph() {
		graphedValues = new TimeSeries(item.getUnit());
		//graphedValues = new TimeSeries(item.getUnit(), Second.class);
		graphedValues.setMaximumItemAge(30);
		//graphedValues.setMaximumItemCount(30); 

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		//dataset.addSeries(this.graphedValues);
		dataset.addSeries(this.graphedValues);
		
		DateAxis domain = new DateAxis("Time");
		NumberAxis range = new NumberAxis(item.getUnit());
		//range.setRange(item.getMinValue(), item.getMaxValue());
		range.setAutoRange(true);
		
		domain.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		range.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		domain.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
		range.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
		
		XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
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
		
		JFreeChart chart = new JFreeChart(item.getName() + "(Id: " + item.getItemId() + ")",
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
		
	}

	private void addValuetoGraph(double value) {
		graphedValues.addOrUpdate(new Second(), value);
	}

	@Override
	public void update(Observable o, Object arg) {
		double value = ((DeviceItem) o).getDoubleValue();
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
