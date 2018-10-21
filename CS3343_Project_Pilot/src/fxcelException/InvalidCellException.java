package fxcelException;

import fxcel.Cell;

public class InvalidCellException extends FxcelException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6473126906512358115L;
	private static final String TAG = "InvalidCellException";
    private static final String messageNullValue  = "Try to access a null value cell";
    private static final String messageTextValue = "Try to access a text value cell in the formula expression";

    private Cell cell;

    public InvalidCellException(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String toString() {
        return super.toString();
        //TODO: get the cell name and print message
    }
}
