package ma2172Handler;

import commonHandler.AverageHandler;
import fxcelException.InvalidExpressionException;

public class MeanHandler extends MathHandler {
	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		return new AverageHandler().handleForDoubleReturn(expression);
	}
}
