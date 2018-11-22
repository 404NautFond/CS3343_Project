package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class HexadecimalHandler extends ConvertHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		/*
		double result;
		int length = expression.length();
		if (length == 0) {
			throw new InvalidExpressionException();
		}
		for (int i = 0; i < length; i++) {
			char temp = expression.charAt(i);
			if (!(temp >= '0' && temp <='9') && !(temp > 'A' && temp < 'F')) {
				throw new InvalidExpressionException();
			}
		}
		result = Integer.parseInt(expression, 16);
		return  result;
		*/
		return super.handleForDoubleReturn(expression);
	}
	
	public String handleForStringReturn(String expression) {
		return "0x"+convertTo(16, super.handleForDoubleReturn(expression));
	}

}
