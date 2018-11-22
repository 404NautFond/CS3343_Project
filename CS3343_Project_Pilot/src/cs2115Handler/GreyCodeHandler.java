package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class GreyCodeHandler extends ConvertHandler{
	
	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		if (isNumeric(expression)) {
			int temp = Integer.parseInt(expression);
			return temp;
		} else {
			throw new InvalidExpressionException();
		}
	}
	
	@Override
	public String handleForStringReturn(String expression) {
		//hard code
		expression = expression.substring(6,expression.length()-1);
		int temp = Integer.parseInt(expression);
		int res = (temp ^ (temp >> 1));
		return "" + new BinaryHandler().handleForStringReturn(""+res);
	}
}
