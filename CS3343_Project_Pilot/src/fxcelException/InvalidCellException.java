package fxcelException;

import fxcel.Cell;

public class InvalidCellException extends FxcelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6473126906512358115L;
	private static final String TAG = "InvalidCellException";
	private static final String message = "Try to access a text value cell in the formula expression";

	private Cell cell;

	public InvalidCellException(Cell cell) {
		this.cell = cell;
	}

	@Override
	public String getMessage() {
		return cell.getPosition() + "(Expression:\"" + cell.getExpression() + "\")" + " is not able for this computation.";
	}
	
	@Override
	public String toString() {
		return TAG+": "+message;
	}
}
