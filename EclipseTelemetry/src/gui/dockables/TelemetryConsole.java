package gui.dockables;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import misc.log4j.JTextPaneAppender;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;

@SuppressWarnings("serial")
public class TelemetryConsole extends JPanel implements Dockable {
	private JTextPane statusConsole;
	private JScrollPane consoleScrollPane;
	private JTextPaneAppender tpa;
	
	DockKey key = new DockKey("console");
	
	// Root Logger
	static Logger logger = Logger.getLogger("telemetry");
	
	public TelemetryConsole () {
		key.setName("Console");
		key.setCloseEnabled(false);
		
		statusConsole = new JTextPane();
		consoleScrollPane = new JScrollPane(statusConsole);

		tpa = new JTextPaneAppender(
				new PatternLayout("%d{HH:mm:ss.SSS} [%-5p] %m (%c, %C)%n"),
				"jtextarea", null, statusConsole, 100);
		tpa.setFontSize(12);
		
		this.setLayout(new GridLayout(1,1));
		this.add(consoleScrollPane);
		
		logger.addAppender(tpa);
	}
	
	public void clear() {
		statusConsole.setText("");
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
