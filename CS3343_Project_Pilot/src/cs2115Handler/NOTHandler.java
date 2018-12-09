/**
 * City University of Hong Kong, Group 22
 * NOTHandler.java
 * Base handler class with overridden return <em>value</em> and <em>string</em> method
 */

package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class NOTHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		setInput(expression);
		feed();
		if(isTrueLike(getInput())) return 0;
		else if(isFalseLike(getInput())) return 1;
		throw new InvalidExpressionException();
	}

}
