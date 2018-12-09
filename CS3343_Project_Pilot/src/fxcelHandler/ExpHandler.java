/**
 * City University of Hong Kong, Group 22
 * ExpHandler.java
 * For formatting detection and dummy base class for nearly all the classes.
 */

package fxcelHandler;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ExpHandler {
	Stack<String> buffer;

	/**
	 * To detect if a token is a numerical value (integer or float)
	 * @param str The token
	 * @return If the token is numerical
	 */
	public static boolean isNumeric(String str) {
		//Credit to Yutao from CSDN
		Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
		String bigStr;
		try {
			bigStr = new BigDecimal(str).toString();
		} catch (Exception e) {
			return false;
		}
		Matcher isNum = pattern.matcher(bigStr);
		return isNum.matches();
	}

	/**
	 * To detect if a token is Cell-name-like
	 * @param str The token
	 * @return If the token is Cell name
	 */
	public static boolean isCell(String str) {
		Pattern pattern = Pattern.compile("-?[A-Z]+[0-9]+");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	/**
	 * To detect if a token is a function name
	 * @param str The token
	 * @return If the token is a function name
	 */
	public static boolean isFunc(String str) {
		return (GeneralHandler.isFunc(str));
	}

}
