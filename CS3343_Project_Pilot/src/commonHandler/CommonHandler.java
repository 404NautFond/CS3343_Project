package commonHandler;

import fxcel.Cell;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;
import fxcelHandler.FuncHandler;

public abstract class CommonHandler extends FuncHandler {
    protected double calculateValueForSingleCell(Cell thisCell) throws InvalidExpressionException {
        try {
            return thisCell.getValue();
        } catch (InvalidCellException e) {
        		if(thisCell.getExpression() == null)
        			return 0;
        		else
        			throw new InvalidExpressionException();
        }
    }
}
