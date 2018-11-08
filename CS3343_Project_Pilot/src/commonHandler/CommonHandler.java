package commonHandler;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;
import fxcelHandler.FuncHandler;
import fxcelHandler.GeneralHandler;

public abstract class CommonHandler extends FuncHandler {
	protected double calculateValueForSingleCell(Cell thisCell) throws InvalidExpressionException {
		try {
			return thisCell.getValue();
		} catch (InvalidCellException e) {
			if(thisCell.getTextual() == null)
				return 0;
			else throw new InvalidExpressionException();
		}
	}

	protected double calculateValueForSingleCell(String name) {
		GeneralHandler gen = new GeneralHandler();
		try {
			double res = gen.handleForDoubleReturn(name);
			return res;
		} catch (InvalidCellException e) {
			if (Fxcel.getInstance().getCell(name).getTextual() == null)
				return 0;
			else throw new InvalidExpressionException();
		}
	}
}
