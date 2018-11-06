package cs2115Handler;

import fxcelException.InvalidExpressionException;
import fxcelHandler.FuncHandler;

public abstract class LogicHandler extends FuncHandler{
	//TODO: Assumption, TRUE: textual value = "TRUE", numerical value = 1.00;
	//FALSE: textual value = "FALSE", numerical value = 0.00;
	public abstract double handleForDoubleReturn(String expression) throws InvalidExpressionException;
	public abstract String handleForStringReturn(String expression) throws InvalidExpressionException;
}
