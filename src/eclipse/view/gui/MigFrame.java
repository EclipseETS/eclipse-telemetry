package eclipse.view.gui;

import java.awt.Color;
import java.awt.Label;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;

public class MigFrame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7239132019766918519L;

	/**
	 * Create the panel.
	 */
	public MigFrame() {
		setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, "cell 0 0 2 2,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.BLACK);
		add(scrollPane, "cell 2 0 1 3,grow");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		add(panel, "cell 0 2 2 1,grow");
		
	}

}
