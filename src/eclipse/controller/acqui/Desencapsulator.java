package eclipse.controller.acqui;

public interface Desencapsulator {
	
	/**
	 * Send char to the telemetry
	 * @param bt
	 */
	public void receiveChar(Byte bt);
	
	/**
	 * Clear received data up to now (the last incomplet frames
	 */
	public void clearData();

}
