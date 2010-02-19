package acquisition.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import acquisition.AcquisitionHandler;


public class TestFileHandler extends AcquisitionHandler {

	final protected static String NAME = "Test File";
	File afile = null;
	
	public TestFileHandler() {
		init();
	}

	@Override
	public Reader getReader() {
		try {
			return new FileReader(afile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void init() {
		afile = new File("testfile.log"); // init test file
	}

	@Override
	public void start() {
		setChanged();
		notifyObservers();
	}

	@Override
	public void stop() {
		// NA

	}

	@Override
	public boolean isConnected() {
		return false;
	}

	@Override
	public String getName() {
		return NAME;
	}

}
