package cs2115Handler;

import fxcelException.InvalidExpressionException;
import fxcelHandler.ExpHandler;
import fxcelHandler.FuncHandler;
import fxcelHandler.GeneralHandler;

public abstract class LogicHandler extends FuncHandler{
	//TODO: Assumption, TRUE: textual value = "TRUE", numerical value = 1.00;
	//FALSE: textual value = "FALSE", numerical value = 0.00;
	public abstract double handleForDoubleReturn(String expression) throws InvalidExpressionException;
	
	public static boolean isCell(String str) {
		return ExpHandler.isCell(str);
	}
	
	public static boolean isTrueLike(String str) {
		if(ExpHandler.isNumeric(str) && !str.equals("0")) return true;
		else if(str.equalsIgnoreCase("true")) return true;
		else if(str.equalsIgnoreCase("false")) return false;
		else if(new GeneralHandler().handleForDoubleReturn(str) != 0) return true;
		else return false;
	}
	
	public static boolean isFalseLike(String str) {
		if(str.equals("0")) return true;
		else if(str.equalsIgnoreCase("true")) return false;
		else if(str.equalsIgnoreCase("false")) return true;
		else if(new GeneralHandler().handleForDoubleReturn(str) == 0) return true;
		else return false;
	}
	
	//maybe delete
//	public String handleForStringReturn(String expression) throws InvalidExpressionException{
//		return (handleForDoubleReturn(expression)==1)?"TRUE":"FALSE";
//	}
}
