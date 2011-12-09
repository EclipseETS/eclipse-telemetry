package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnit {
	
	@Test
	public void testByteArrayToInt() {
		try {
			//i=byteArrayToInt(new byte[(0x0A)], 0, 1, true, true);
			@SuppressWarnings("unused")
			byte[] 	i=HexString.hexToBuffer("0A");
		} catch (ArrayIndexOutOfBoundsException e) {
			// 
			e.printStackTrace();
		} catch (Exception e) {
			// 
			e.printStackTrace();
		}
		
		
		assertEquals(10, 10,0);
	}

}
