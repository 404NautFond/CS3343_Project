/**
 * City University of Hong Kong, Group 22
 * MeanHandler.java
 * Base handler class with overridden return <em>value</em> method
 */

package ma2172Handler;

import commonHandler.AverageHandler;
import fxcelException.InvalidExpressionException;

public class MeanHandler extends MathHandler {
	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		return new AverageHandler().handleForDoubleReturn(expression);
	}
}
