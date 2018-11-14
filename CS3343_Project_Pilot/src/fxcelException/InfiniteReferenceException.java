package fxcelException;

import fxcel.Cell;

public class InfiniteReferenceException extends FxcelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -287625456045125009L;
	private static final String TAG = "InvalidExpressionExcept";
    private static final String message = "Refer to an object that has already referred to the caller!";

    private Cell cellA;
    private Cell cellB;

    public InfiniteReferenceException(Cell cellA, Cell cellB) {
        this.cellA = cellA;
        this.cellB = cellB;
    }
    
    @Override
    public String getMessage() {
    		return "Cell "+this.cellA.getPos()+"(expression:\""+this.cellA.getExpression()+
    				"\") uses the value of "+this.cellA.getPos() + ", please check.";
    }

    @Override
    public String toString() {
        return super.toString();
        // TODO: get two cells names and print message
    }
}
