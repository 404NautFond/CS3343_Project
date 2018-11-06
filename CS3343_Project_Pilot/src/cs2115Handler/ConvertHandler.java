package cs2115Handler;

import fxcelException.InvalidExpressionException;
import fxcelHandler.FuncHandler;

public abstract class ConvertHandler extends FuncHandler {
	public abstract double handleForDoubleReturn(String expression) throws InvalidExpressionException;
	public abstract double handleForStringReturn(String expression) throws InvalidExpressionException;
}
