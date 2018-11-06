package cs2115Handler;

import fxcelException.InvalidExpressionException;
import fxcelHandler.FuncHandler;

public class ConvertHandler extends FuncHandler {
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException{
		return 0;
	};
	public String handleForStringReturn(String expression) throws InvalidExpressionException{
		return null;
	};
	
	public double convertTo(int radix, int target) {
		return 0;
	}
}
