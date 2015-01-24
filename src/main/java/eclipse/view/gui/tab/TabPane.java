package eclipse.view.gui.tab;

/**
 * Default interface for all tabbedpannel, usefull for a one point of update opnly
 * @author Eclipse
 *
 */
public interface TabPane {
	
	/**
	 * Update value is automaticly call every second. Use it to put your interface up-to-date
	 */
	void updateValues();

}
