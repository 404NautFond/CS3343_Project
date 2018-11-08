package commonHandler;

import fxcelException.InvalidExpressionException;

public class AverageHandler extends CommonHandler {

	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		double sum = (new SumHandler()).handleForDoubleReturn(expression);
		double num = (new CountHandler()).handleForDoubleReturn(expression);
		return sum/num;
	}
}
