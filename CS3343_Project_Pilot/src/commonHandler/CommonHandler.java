/**
 * City University of Hong Kong, Group 22
 * CommonHandler.java
 * Abstract handler class with overridden return <em>value</em> method
 * and inner reference resolve
 */

package commonHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import fxcelException.InvalidExpressionException;
import fxcelHandler.FuncHandler;
import fxcelHandler.GeneralHandler;

public abstract class CommonHandler extends FuncHandler {
	protected String input;
	/**
	 * For expression with ";"
	 * @return The value of such expression
	 */
	protected abstract double calculateForColumnInput();
	
	/**
	 * For expression with ","
	 * @return The value of such expression
	 */
	protected abstract double calculateForCommaInput();
	
	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		this.input = expression;
		if(expression.contains(",")) {
			feed();
			return calculateForCommaInput();
		}
		else if(expression.contains(":")) return calculateForColumnInput();
		//TODO: assume at least 2 parameters
		throw new InvalidExpressionException();
	}
	
	/**
	 * Avoid passing function into a function
	 */
	protected void feed() {
		int lock = 0;
		List<String> token = getTokens(getInput());
		String tempToken = "";
		List<String> tempInput = new ArrayList<String>();
		for(int i = 0; i < token.size(); i++) {
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
