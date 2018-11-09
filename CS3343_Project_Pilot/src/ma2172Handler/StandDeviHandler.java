package ma2172Handler;

import fxcelException.InvalidExpressionException;

public class StandDeviHandler extends MathHandler{
	@Override
    public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
    		return Math.sqrt(new VarianceHandler().handleForDoubleReturn(expression));
    }
}
