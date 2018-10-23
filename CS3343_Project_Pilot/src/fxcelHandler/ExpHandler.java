package fxcelHandler;

import fxcel.Cell;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ExpHandler {
	Stack<String> buffer;
	
	/**
	 * Compute the value for the expression
	 * @param expression The String type expression
	 * @return The double value
	 */
	abstract double handleForDoubleReturn(String expression);
	
	abstract double handleForDoubleReturn(String expression, Cell resultCell);
	
	abstract int handleForIntegerReturn(String expression, Cell resultCell);

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
		switch(str) {
		//TODO: Add all the function name or add a static ArrayList
		case "SUM":
		case "MAX":
		case "AVG":
		case "MIN":
			return true;
		default:
			return false;
		}
	}

}
