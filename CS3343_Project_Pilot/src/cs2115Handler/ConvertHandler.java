package cs2115Handler;

import fxcelException.InvalidExpressionException;
import fxcelHandler.FuncHandler;
import fxcelHandler.GeneralHandler;

public class ConvertHandler extends FuncHandler {
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException{
		return new GeneralHandler().handleForDoubleReturn(expression);
	};
	public String handleForStringReturn(double val) throws InvalidExpressionException{
		return null;
	};
	
	public double convertTo(int radix, int target) {
		return 0;
	}
}
