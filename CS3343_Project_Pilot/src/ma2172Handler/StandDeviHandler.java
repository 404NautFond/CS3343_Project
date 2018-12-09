/**
 * City University of Hong Kong, Group 22
 * StandDeviHandler.java
 * Base handler class with overridden return <em>value</em> method
 */

package ma2172Handler;

import fxcelException.InvalidExpressionException;

public class StandDeviHandler extends MathHandler{
	@Override
    public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
    		return Math.sqrt(new VarianceHandler().handleForDoubleReturn(expression));
    }
}
