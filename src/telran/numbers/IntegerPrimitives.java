package telran.numbers;

public class IntegerPrimitives {

	public static byte getMaxByte() {
		byte res = 1;
		while (res > 0) {
			res *= 2;
		}
		return (byte) (res - 1);
	}

	public static byte getMinByte() {
		return (byte) (getMaxByte() + 1);
	}

	public static int getLengthByte() {
		return getLength(getMaxByte());
	}

	public static char getMaxChar() {
		char res = 1;
		while (res > 0) {
			res = (char) (res * 2);

		}
		return (char) (res - 1);
	}

	public static char getMinChar() {
		return (char) (getMaxChar() + 1);
	}

	public static int getLengthChar() {
		return getLength(getMaxChar());
	}

	public static short getMaxShort() {
		short res = 1;
		while (res > 0) {
			res = (short) (res * 2);
		}
		return (short) (res - 1);
	}

	public static short getMinShort() {
		return (short) (getMaxShort() + 1);
	}

	public static int getLengthShort() {
		return getLength(getMaxShort());
	}

	public static int getMaxInt() {
		int res = 1;
		while (res > 0) {
			res *= 2;
		}
		return res - 1;
	}

	public static int getMinInt() {
		return getMaxInt() + 1;
	}

	public static int getLengthInt() {
		return getLength(getMaxInt());
	}

	public static long getMaxLong() {
		long res = 1;
		while (res > 0) {
			res = res * 2;
		}
		return res - 1;
	}

	public static long getMinLong() {
		return getMaxLong() + 1;
	}

	public static int getLengthLong() {
		return getLength(getMaxLong());
	}

	private static int getLength(long x) {
		int exp = (int) ((Math.log(x) + 1) / Math.log(2));
		return exp / 8;
	}

}
