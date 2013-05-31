package eclipse.controller.acqui;

/**
 * Default interface for the desencapsulator. On a futur Eclipse version simply add different Desencapsulator and app will continu to work
 * @author Eclipse
 *
 */
public interface Desencapsulator {
	
	/**
	 * Send char to the telemetry
	 * @param bt
	 */
	public void receiveChar(byte bt);
	
	/**
	 * Clear received data up to now (the last incomplet frames
	 */
	public void clearData();

}
