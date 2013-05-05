package eclipse.view.gui.tab;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/**
 * This class is the main windows of this application all other information will be add on this tabbed panel
 * 
 * **To remove a tab right click on the tab
 * @author Marco
 *
 */
public class TabbedPannel extends JPanel {
	
	private static final long serialVersionUID = -3694535778568444098L;
	private JTabbedPane tabPanel;

	public TabbedPannel(){
		tabPanel = new JTabbedPane();
		tabPanel.addMouseListener(new RightClickListener());
		setLayout(new GridLayout(1, 1));
		add(tabPanel);
	}

	/**
	 * public methode to add a new tab
	 * @param comp
	 * @param name
	 */
	public void addTab(Component comp, String name){
		if(comp instanceof TabPane){
			tabPanel.add(name,comp);
			tabPanel.setSelectedIndex(tabPanel.getTabCount()-1);
		}
	}

	/**
	 * Private listner to close unused tab
	 * @author Marco
	 *
	 */
	public class RightClickListener extends MouseAdapter {
	    public void mouseClicked(MouseEvent e) {
	        if (SwingUtilities.isRightMouseButton(e)) {
	        	tabPanel.remove(tabPanel.indexAtLocation(e.getX(), e.getY()));
	        }
	    }
	}
	
	public void update(){
		for (Component com : tabPanel.getComponents())
			if(com instanceof TabPane)
				((TabPane)com).updateValues();
	}
  
}

