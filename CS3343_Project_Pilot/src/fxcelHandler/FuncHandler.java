package fxcelHandler;

import fxcel.Cell;
import fxcelException.InfiniteReferenceException;

public abstract class FuncHandler extends ExpHandler {

	protected Cell resultCell;

	@Override
	public double handleForDoubleReturn(String expression) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	double handleForDoubleReturn(String expression, Cell resultCell) {
		return 0;
	}

	@Override
	int handleForIntegerReturn(String expression, Cell resultCell) {
		return 0;
	}

	protected void checkDependentForSingleCell(Cell thisCell) throws InfiniteReferenceException {
		if (thisCell.checkDep(resultCell)) {
			thisCell.addDependent(resultCell);
			resultCell.addDependent(thisCell);
		} else {
			throw new InfiniteReferenceException(thisCell, resultCell);
		}
	}
}
