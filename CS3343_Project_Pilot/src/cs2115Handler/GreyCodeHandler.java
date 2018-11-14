package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class GreyCodeHandler extends ConvertHandler{

	/*
	public static void main(String[] args) {
		String temp = new GreyCodeHandler().handleForStringReturn("1213");
		String temp2 = new BinaryHandler().handleForStringReturn(temp);
		String temp3 = new BinaryHandler().handleForStringReturn("1213");
		System.out.println(temp2+" "+temp3);
	}
	*/
	
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
		int temp = Integer.parseInt(expression);
		int res = (temp ^ (temp >> 1));
		return "" + new BinaryHandler().handleForStringReturn(""+res);
	}
}
