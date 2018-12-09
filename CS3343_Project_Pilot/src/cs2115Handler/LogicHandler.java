/**
 * City University of Hong Kong, Group 22
 * LogicHandler.java
 * Abstract handler class with overridden return <em>value</em> and <em>string</em> method
 */

package cs2115Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import fxcelHandler.ExpHandler;
import fxcelHandler.FuncHandler;
import fxcelHandler.GeneralHandler;

public abstract class LogicHandler extends FuncHandler{
	private String input;
	
	@Override
	public abstract double handleForDoubleReturn(String expression);

	/**
	 * To detect if a token is Cell-name-like
	 * @param str The token
	 * @return boolean If the token is Cell name
	 */
	public static boolean isCell(String str) {
		return ExpHandler.isCell(str);
	}

	/**
	 * To detect if the expression is TRUE
	 * @param str input expression
	 * @return boolean the result of the expression
	 */
	public static boolean isTrueLike(String str) {
		if(ExpHandler.isNumeric(str) && !str.equals("0")) return true;
		else if(str.equalsIgnoreCase("true")) return true;
		else if(str.equalsIgnoreCase("false")) return false;
		else if(new GeneralHandler().handleForDoubleReturn(str) != 0) return true;
		else return false;
	}

	/**
	 * To detect if the expression is False
	 * @param str input expression
	 * @return boolean the result of the expression
	 */
	public static boolean isFalseLike(String str) {
		if(str.equals("0")) return true;
		else if(str.equalsIgnoreCase("true")) return false;
		else if(str.equalsIgnoreCase("false")) return true;
		else if(new GeneralHandler().handleForDoubleReturn(str) == 0) return true;
		else return false;
	}

	/**
	 * Avoid passing function into a function
	 */
	protected void feed() {
		int lock = 0;
		List<String> token = getTokens(getInput());
		String tempToken = "";
		List<String> tempInput = new ArrayList<String>();
		if(token.size() == 1) {
			tempInput.add(token.get(0));
		}
		else for(int i = 0; i < token.size(); i++) {
			switch(token.get(i)) {
			case "(":
				lock++;
				if(lock == 1) {
					tempToken += token.get(i-1)+"(";
					tempInput.remove(tempInput.size()-1);
				}
				break;
			case ")":
				lock--;
				if(lock == 0) {
					tempToken += ")";
					tempInput.add(new GeneralHandler().handlerForStringReturn(tempToken));
					tempToken = "";
				}
				break;
			default:
				if(lock > 0) {
					tempToken += token.get(i);
				} else {
					tempInput.add(token.get(i));
				}
			}
		}
		String input = "";
		for(String temp:tempInput) {
			input+=temp;
		}
		setInput(input);
	}

	/**
	 * Split the input string in terms of ,()
	 * @param str the input string
	 * @return the list with split strings
	 */
	private List<String> getTokens(String str) {
		List<String> tokens = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(str,",()",true);
		while (tokenizer.hasMoreElements()) {
			tokens.add(tokenizer.nextToken());
		}
		return tokens;
	}

	/**
	 * Pass the field input to extended classes
	 * @return The expression assigned
	 */
	protected void setInput(String newInput) {
		this.input = newInput;
	}

	/**
	 * Pass the field input to extended classes
	 * @return The expression assigned
	 */
	protected String getInput() {
		return this.input;
	}

}
