/**
 * City University of Hong Kong, Group 22
 * InfiniteReferenceException.java
 * Will be thrown out when referencing a wrong format cell or not-existing cell.
 */

package fxcelException;

import fxcel.Cell;

public class InvalidCellException extends FxcelException {

	private static final long serialVersionUID = -6473126906512358115L;

	private Cell cell;

	public InvalidCellException(Cell cell) {
		this.cell = cell;
	}

	@Override
	public String getMessage() {
		return cell.getPosition() + "(Expression:\"" + cell.getExpression() + "\")" + " is not legal for this computation.";
	}
	
}
