package acquisition.handlers;

import java.io.*;

import acquisition.AcquisitionHandler;

public class TestHandler extends AcquisitionHandler{
	
	final protected static String NAME = "Test";
	
	PipedInputStream in = null;
	PipedOutputStream out = null;
	
	PrintWriter pw = null;
	
	Thread sendThread = null;
	
	String testData[] = {
			"0100100007E",
			"01002000030",
			"010030000FD",
			"010040000AC",
			"01005000061",
			"0100600002F",
			"010070099",
			"0100800C6",
			"01009006D",
			"0100A000013",
			"0100B00005D",
			"0100C000090",
			"0100D0000C1",
			"020010000BB",
			"020020000F5",
			"02003000038",
			"02004000069",
			"020050000A4",
			"020060000EA",
			"02007000027",
			"02008000048",
			"02009000085",
			"0200A0000D6",
			"0200B000098",
			"0200C000055",
			"0200D000004",
			"0200E0000C9",
			"0200F000087",
			"02010000041"
	};
	
	public TestHandler() {
		init();
	}
	
	public void init() {
		try {
			in = new PipedInputStream();
			out = new PipedOutputStream(in);
			pw = new PrintWriter(out);
		} catch (IOException e) {e.printStackTrace();}
	}
	
	@Override
	public void start() {
		// if (sendThread == null) sendThread = new Thread(new
		// sendStuffThread(out));
		// sendThread.start();

		sendThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					for (int i = 0; i < testData.length; i++) {
						try {
							pw.write(testData[i]);
							System.out.println("Sends: " + testData[i]);
							Thread.sleep(500);
							if (i == testData.length-1) i = 0; // boucle
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}

		});
		
		sendThread.start();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		try {
			String stuff;
			while ((stuff = br.readLine()) != null) {
				System.out.println("Test print: " + stuff);
			}
		} catch (IOException e) {
			// 
			e.printStackTrace();
		}

	}

	@Override
	public void stop() {
		sendThread.interrupt();
	}

	@Override
	public Reader getReader() {
		return new InputStreamReader(in);
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return NAME;
	}

}

//class sendStuffThread implements Runnable {
//
//	String testData[] = {
//			"0100100007E",
//			"01002000030",
//			"010030000FD",
//			"010040000AC",
//			"01005000061",
//			"0100600002F",
//			"010070099",
//			"0100800C6",
//			"01009006D",
//			"0100A000013",
//			"0100B00005D",
//			"0100C000090",
//			"0100D0000C1",
//			"020010000BB",
//			"020020000F5",
//			"02003000038",
//			"02004000069",
//			"020050000A4",
//			"020060000EA",
//			"02007000027",
//			"02008000048",
//			"02009000085",
//			"0200A0000D6",
//			"0200B000098",
//			"0200C000055",
//			"0200D000004",
//			"0200E0000C9",
//			"0200F000087",
//			"02010000041"
//	};
//
//	PrintWriter pw = null;
//	
//	public sendStuffThread (OutputStream out) {
//		pw = new PrintWriter(out);
//	}
//	
//	@Override
//	public void run() {
//		while (true) {
//			for (int i = 0; i < testData.length; i++) {
//				try {
//					pw.print(testData[i]);
//					System.out.println("Sends: " + testData[i]);
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	
//}
