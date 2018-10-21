package fxcelHandler;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ExpHandler {
	Stack<String> buffer;
	abstract double handle(String expression);

	//Credit to Yutao from CSDN
	public static boolean isNumeric(String str) {
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

	public static boolean isCell(String str) {
		Pattern pattern = Pattern.compile("-?[A-Z]+[0-9]+");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	public static boolean isFunc(String str) {
		switch(str) {
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
