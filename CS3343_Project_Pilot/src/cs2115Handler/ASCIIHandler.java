package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class ASCIIHandler extends ConvertHandler{
	//TODO: Char to ASCII String, numerical value = null, textual value = String

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		for(int i = 0; i < expression.length(); i++) {
			char input = expression.charAt(i);
			if ((int)input < 0 || (int)input >127) {
				throw new InvalidExpressionException();
			}
		}
		return 0;
	}

	@Override
	public String handleForStringReturn(String expression) {
		String temp = "";
		for(int i = 0; i < expression.length(); i++) {
			char input = expression.charAt(i);
			temp += (int)input + " ";
		}
		return temp;
	}
}
