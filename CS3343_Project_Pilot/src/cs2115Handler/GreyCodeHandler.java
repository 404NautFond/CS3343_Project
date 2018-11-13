package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class GreyCodeHandler extends ConvertHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		if (isNumeric(expression)) {
			int temp = Integer.parseInt(expression);
			return temp ^ (temp >> 1);
		} else {
			throw new InvalidExpressionException();
		}
	}
}
