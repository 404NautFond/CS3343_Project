package cs2115Handler;

import fxcelException.InvalidExpressionException;
import fxcelHandler.ExpHandler;
import fxcelHandler.FuncHandler;
import fxcelHandler.GeneralHandler;

public abstract class LogicHandler extends FuncHandler{
	//Assumption, TRUE: textual value = "TRUE", numerical value = 1.00;
	//FALSE: textual value = "FALSE", numerical value = 0.00;
        @Override
	public abstract double handleForDoubleReturn(String expression) throws InvalidExpressionException;
	
        /**
         * To detect if a token is Cell-name-like
	 * @param str The token
	 * @return boolean If the token is Cell name
         */
	public static boolean isCell(String str) {
		return ExpHandler.isCell(str);
	}
        
        /**
         * To detect if the expression is TRUE
         * @param str input expression
         * @return boolean the result of the expression
         */
	public static boolean isTrueLike(String str) {
		if(ExpHandler.isNumeric(str) && !str.equals("0")) return true;
		else if(str.equalsIgnoreCase("true")) return true;
		else if(str.equalsIgnoreCase("false")) return false;
		else if(new GeneralHandler().handleForDoubleReturn(str) != 0) return true;
		else return false;
	}
        
	/**
         * To detect if the expression is False
         * @param str input expression
         * @return boolean the result of the expression
         */
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
