package fxcelException;

import fxcel.Cell;

public class InvalidExpressionException extends FxcelException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9165372884229446012L;
//	private static final String TAG = "InvalidExpressionException";
	private static final String message = "Syntax error on your input!";

	private Cell thisCell;
	
	public InvalidExpressionException() {
		//default
	}
	public InvalidExpressionException(Cell thisCell) {
		this.thisCell = thisCell;
	}
	
	@Override
	public String getMessage() {
		if(thisCell == null) return message;
		else return thisCell.getPosition()+"(Expression:\""+ thisCell.getExpression()+"\") is invalid, please check.";
	}
}
