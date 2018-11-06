package fxcelHandler;

import fxcel.Cell;
import fxcelException.InvalidExpressionException;

import java.math.BigDecimal;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commonHandler.AverageHandler;
import commonHandler.CountHandler;
import commonHandler.MaxHandler;
import commonHandler.MinHandler;
import commonHandler.SumHandler;

public abstract class ExpHandler {
	Stack<String> buffer;

	/**
	 * Compute the value for the expression
	 * @param expression The String type expression
	 * @return The double value
	 * @throws InvalidExpressionException 
	 */
	abstract double handleForDoubleReturn(String expression) throws InvalidExpressionException;
	


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
