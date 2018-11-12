package cs2115Handler;

import fxcelException.InvalidExpressionException;
import fxcelHandler.ExpHandler;
import fxcelHandler.FuncHandler;

public abstract class LogicHandler extends FuncHandler{
	//TODO: Assumption, TRUE: textual value = "TRUE", numerical value = 1.00;
	//FALSE: textual value = "FALSE", numerical value = 0.00;
	public abstract double handleForDoubleReturn(String expression) throws InvalidExpressionException;
	
	public static boolean isCell(String str) {
		return ExpHandler.isCell(str);
	}
	
	//maybe delete
//	public String handleForStringReturn(String expression) throws InvalidExpressionException{
//		return (handleForDoubleReturn(expression)==1)?"TRUE":"FALSE";
//	}
}
