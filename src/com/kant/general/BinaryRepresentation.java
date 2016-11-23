package com.kant.general;

/**
 * Generating the Binary representation of any Number
 * 
 * @author shashi
 * 
 */
public class BinaryRepresentation {
	/**
	 * Actual logic is here.
	 * 
	 * @param number
	 * @return binary_rep_in_String_format
	 */
	public static String printBinaryRepresentation(String number) {
		if (number.indexOf('.') == -1) {
			number = number + ".0";
		}

		int intpart = Integer
				.parseInt(number.substring(0, number.indexOf('.')));
		double decpart = Double.parseDouble(number.substring(
				number.indexOf('.'), number.length()));
		StringBuffer int_string = new StringBuffer("");
		while (intpart > 0) {
			int r = intpart % 2;
			intpart /= 2;
			int_string = int_string.append(r);
		}
		int_string.reverse();

		StringBuffer decstring = new StringBuffer();
		while (decpart > 0) {
			// set precision level
			if (decstring.length() > 10)
				break;
			if (decpart == 1) {
				decstring.append((int) decpart);
				break;
			}
			double r = decpart * 2;
			if (r >= 1) {
				decstring.append(1);
				decpart = r - 1;
			} else {
				decstring.append(0);
				decpart = r;
			}
		}
		System.out.println("[INT Part]# " + int_string);
		System.out.println("[Dec Part]# " + decstring);
		System.out.println("[Output]# " + int_string
				+ ((decstring.length() != 0) ? "." : "") + decstring);
		return int_string.append(((decstring.length() != 0) ? "." : ""))
				.append(decstring).toString();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(printBinaryRepresentation("8.50"));
	}

}
