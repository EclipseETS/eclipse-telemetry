package eclipse.controller.util;

import java.nio.ByteBuffer;


/**
 * @author Frederic Nadeau
 * 
 */
public class ByteManipulator {
	
	private static final int MASK = 0xff;

	/**
	 * @param b
	 *            The byte array
	 * @param offset
	 *            Occurrence of the first byte in the array
	 * @param size
	 *            Number of byte to be converted. Must be between 1 and 4.
	 * @param isSigned
	 *            Is the value in the byte array a sined value?
	 * @return In case of signed integer, the whole rage is supported. In case
	 *         of unsigned int, the maximum value is 0x7FFFFFFF.
	 * @throws ArrayIndexOutOfBoundsException
	 *             Combination of offset and size is invalid. Yield outside the
	 *             range of the byte array.
	 * @throws Exception
	 *             Either size argument is invalid or the value is too big to be
	 *             converted.
	 */
	public static double byteArrayToInt(byte[] b, int offset, int size,
			boolean isSigned, boolean isFloat) throws ArrayIndexOutOfBoundsException, Exception {
		if(isFloat)
			return byteArrayToFloat(b, offset);
		
		long value = 0;

		if ((size > 4) || (size <= 0)) {
			throw new Exception("Value out of range");
		}

		if ((size + offset) > b.length) {
			throw new ArrayIndexOutOfBoundsException();
		}

		// extract value from array
		for (int i = 0; i < size; i++) {
			int shift = (size - 1 - i) * 8;
			value += (Long.valueOf(b[i + offset]) & 0xFF) << shift;
		}

		if (isSigned == true) {
			if ((b[offset] & 0x80) != 0) { // check if number is big enough to be negative
				// do 2's complement
				value -= 1;
				if (size == 1)
					value = -255L + value;
				else if (size == 2)
					value = -65535L + value;
				else
					value = -4294967295L + value;
			}
		}

		return value;
	}

	public static byte[] intToByteArray(int b, int size, boolean isSigned) throws Exception{
		byte[] value = new byte[size];
		
		if ((size > 4) || (size <= 0)) {
			throw new Exception("Value out of range");
		}
		
		if(isSigned == true){
			if(b < (0-(Math.pow(2.0,size*8-1))) || b > (Math.pow(2.0,size*8-1)-1)){
				throw new Exception("Value out of range");
			}
		}else{
			if(b > (Math.pow(2.0,size*8)-1)){
				throw new Exception("Value out of range");
			}
		}
		
		for(int i = size - 1, j = 0; i>=0; i--, j++)
		{
			value[j] = (byte)(b >>> (i * 8));
		}
		
		return value;
	}
	
	public static float byteArrayToFloat(byte[] b, int offset) throws Exception{
		int bits = 0;
		int i = 0;
		for (int shifter = 3; shifter >= 0; shifter--) {
			bits |= ((int) b[i + offset] & MASK) << (shifter * 8);
			i++;
		}
			 
		return Float.intBitsToFloat(bits);
	}
	
	public static long bytesToLong(byte[] bytes) {
	    ByteBuffer buffer = ByteBuffer.allocate(8);
	    buffer.put(bytes);
	    buffer.flip();//need flip 
	    return buffer.getLong();
	}
	
}
