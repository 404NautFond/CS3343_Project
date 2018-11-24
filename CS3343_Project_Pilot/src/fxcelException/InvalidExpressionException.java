package fxcelException;

import fxcel.Cell;

public class InvalidExpressionException extends FxcelException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9165372884229446012L;
//	private static final String TAG = "InvalidExpressionException";
	private static final String message = "Syntax error on your input!";

	private Cell cell;
	
	public InvalidExpressionException() {
		this.cell = null;
	}
	
//	public InvalidExpressionException(Cell thisCell) {
//		this.thisCell = thisCell;
//	}
	
	public void linkCell(Cell cell) {
		this.cell = cell;
	}
	
	@Override
	public String getMessage() {
		if(cell == null) return message;
		else return "Cell " + cell.getPosition()+"(Expression:\""+ cell.getExpression()+"\") is invalid, please check.";
	}
}
