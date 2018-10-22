package fxcelHandler;

import fxcel.Cell;
import fxcelException.InfiniteReferenceException;
import fxcelException.InvalidCellException;

public abstract class FuncHandler extends ExpHandler {

	protected Cell resultCell;

	@Override
	public double handle(String expression) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	double handle(String expression, Cell resultCell) {
		return 0;
	}

	protected double calculateValueForSingleCell(Cell thisCell) throws InfiniteReferenceException {
		if (thisCell.checkDep(resultCell)) {
			thisCell.addDependent(resultCell);
			resultCell.addDependent(thisCell);
		} else {
			throw new InfiniteReferenceException(thisCell, resultCell);
		}
		try {
			return thisCell.getValue();
		} catch (InvalidCellException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
