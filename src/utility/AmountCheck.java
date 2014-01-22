package utility;

public class AmountCheck {
	private static final int MAX = 1000000000;
	private static final int MIN = 0;
	
	public static int checkString(String s) {
		float fn = 0;
		try {
			fn = Float.valueOf(s) * 1000;
		} catch (NumberFormatException e) {
			return -1;
		}
		int in = Math.round(fn);
		if (in < MIN || in > MAX)
			return -2;
		return in;
	}
	
	public static String getErrorByCode(String s, int ec) {
		if (ec == -1)
			return new String("\"" + s + "\" is not a number.");
		if (ec == -2)
			return new String(s + " is out of range.");
		return "Error code out of range.";
	}
}
