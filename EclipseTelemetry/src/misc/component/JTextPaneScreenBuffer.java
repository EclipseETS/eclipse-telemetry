package misc.component;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;

public class JTextPaneScreenBuffer extends JTextPane implements DocumentListener {

	private static final long serialVersionUID = 1L;
	
	private int maxLines;

	public JTextPaneScreenBuffer(int maxLines) {
		this.maxLines = maxLines;
		getDocument().addDocumentListener(this);
		
	}
	
	public JTextPaneScreenBuffer() {
		maxLines = 100;
		getDocument().addDocumentListener(this);
		
	}
	
	public void setLineBufferSize(int maxLines) {
		this.maxLines = maxLines;
	}


	@Override
	public void insertUpdate(DocumentEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				removeLines();
			}
		});
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	public void removeLines() {
//		Element root = getDocument().getDefaultRootElement();
//		System.out.println(root.getElementCount());
//		while (root.getElementCount() > maxLines) {
//			Element firstLine = root.getElement(0);
//			try {
//				getDocument().remove(0, firstLine.getEndOffset());
//			} catch (BadLocationException ble) {
//				System.out.println("Requested illegal: " + ble.offsetRequested());
//			}
//		}
		
		Element root = getDocument().getDefaultRootElement();
		System.out.println(root.getElementCount());
		while (root.getElementCount() > maxLines) {
			Element firstLine = root.getElement(0);
			try {
				getDocument().remove(0, firstLine.getEndOffset());
			} catch (BadLocationException ble) {
				System.out.println("Requested illegal: " + ble.offsetRequested());
			}
		}
	}
}
