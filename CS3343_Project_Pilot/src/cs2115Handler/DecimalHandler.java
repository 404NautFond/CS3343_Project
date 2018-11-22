package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class DecimalHandler extends ConvertHandler {
	// Default is decimal
	
	@Override
	public double handleForDoubleReturn(String expression) {
		return super.handleForDoubleReturn(expression);
	}

	@Override
	public String handleForStringReturn(String expression) throws InvalidExpressionException {
		return "0d"+convertTo(10, super.handleForDoubleReturn(expression));
	}

}
