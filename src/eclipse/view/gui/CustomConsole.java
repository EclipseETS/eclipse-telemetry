package eclipse.view.gui;


import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;


import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import eclipse.controller.util.JTextPaneAppender;

/**
 * This console is the bottom left console of this application, it binds on log4j
 * @author Eclipse
 *
 */
public class CustomConsole extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6280882211819001268L;
	
	private JTextPane statusConsole;
	private JScrollPane consoleScrollPane;
	private JTextPaneAppender tpa;
	
	
	// Root Logger
	static Logger logger = Logger.getLogger("main");
	
	public CustomConsole() {
		statusConsole = new JTextPane();
		consoleScrollPane = new JScrollPane(statusConsole);

		tpa = new JTextPaneAppender(
				new PatternLayout("%d{HH:mm:ss.SSS} [%-5p] %m (%c, %C)%n"),
				"jtextarea", null, statusConsole, 100);
		tpa.setFontSize(12);
		
		this.setLayout(new GridLayout(1,1));
		this.add(consoleScrollPane);
		
		logger.addAppender(tpa);
		Logger.getLogger("main").addAppender(tpa);
		logger.error("TEST");
	}
	
	public void clear() {
		statusConsole.setText("");
	}


}
