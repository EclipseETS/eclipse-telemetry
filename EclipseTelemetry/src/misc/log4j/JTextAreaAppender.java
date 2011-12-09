package misc.log4j;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import org.apache.log4j.Level;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;

// Ref : http://code.google.com/p/nebulaframework/source/browse/trunk/nebula-core/src/org/nebulaframework/util/log4j/JTextAreaAppender.java?r=69
// Ref : http://textareaappender.zcage.com/ Copyright 2006-2007 Eric Elfner

public class JTextAreaAppender extends WriterAppender {

	public static final Color TRACE = Color.BLUE;
	public static final Color DEBUG = Color.GRAY;
	public static final Color INFO = Color.BLACK;
	public static final Color WARN = Color.ORANGE;
	public static final Color ERROR = Color.RED;
	public static final Color FATAL = Color.RED;
	
	private static JTextPane textPane = null;
	
	public static void setTextArea(JTextPane textPane) {
		JTextAreaAppender.textPane = textPane;
		
		initialize();
	}


	private static void initialize() {
		if (textPane==null) return;
		
		Style def = textPane.addStyle("default", null);
		StyleConstants.setFontFamily(def, "sansserif");
		
		Style trace = textPane.addStyle("trace", def);
		StyleConstants.setForeground(trace, TRACE);
		
		Style debug = textPane.addStyle("debug", def);
		StyleConstants.setForeground(debug, DEBUG);
		
		Style info = textPane.addStyle("info", def);
		StyleConstants.setForeground(info, INFO);
		
		Style warn = textPane.addStyle("warn", def);
		StyleConstants.setForeground(warn, WARN);
		
		Style error = textPane.addStyle("error", def);
		StyleConstants.setForeground(error, ERROR);
		
		Style fatal = textPane.addStyle("fatal", def);
		StyleConstants.setForeground(fatal, FATAL);
		
		
	}


	public void append(LoggingEvent event) {
		
		if (textPane==null) return;
		final String message = this.layout.format(event);
		final String style = getStyleFor(event);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				Document doc = textPane.getDocument();
				try {
					doc.insertString(doc.getLength(), message, textPane.getStyle(style));
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				textPane.setDocument(doc);
				textPane.setCaretPosition(doc.getLength());
			}
		});
	}


	private String getStyleFor(LoggingEvent event)  {
		
		Level l = event.getLevel();
		
		if (l.equals(Level.TRACE)) {
			return "trace";
		}
		else if (l.equals(Level.DEBUG)) {
			return "debug";
		}
		else if (l.equals(Level.INFO)) {
			return "info";
		}
		else if (l.equals(Level.WARN)) {
			return "warn";
		}
		else if (l.equals(Level.ERROR)) {
			return "error";
		}
		else if (l.equals(Level.FATAL)) {
			return "fatal";
		}
		else {
			return "info";
		}
		
	}
	
	
}