package eclipse.controller.acqui.handlers;

import eclipse.controller.acqui.AcquisitionHandler;

public class TCPHandler extends AcquisitionHandler {

	@Override
	public Boolean start() {
		return null;
	}

	@Override
	public void stop() {
		
	}

	@Override
	public byte readByte() {
		return 0;
	}

	@Override
	public void writeByte(Byte bt) {
		
	}

	@Override
	public boolean isConnected() {
		return false;
	}

	@Override
	public String getName() {
		return null;
	}

}
