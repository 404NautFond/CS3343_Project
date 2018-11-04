package fxcelHandler;

import fxcel.Cell;
import fxcelException.InfiniteReferenceException;
import fxcelException.InvalidExpressionException;

public abstract class FuncHandler extends ExpHandler {

	protected Cell resultCell;

	@Override
	public Object handle(String expression) throws InvalidExpressionException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String[] columnHandler(String expression) {
		if(!expression.contains(":")) return null;
		
		String[] temp = expression.split(":");
		if(temp.length == 2) return temp;
		else return null;
	}

//	@Override
//	double handleForDoubleReturn(String expression, Cell resultCell) {
//		return 0;
//	}
//
//	@Override
//	int handleForIntegerReturn(String expression, Cell resultCell) {
//		return 0;
//	}
//
//	protected void checkDependentForSingleCell(Cell thisCell) throws InfiniteReferenceException {
//		if (thisCell.checkDep(resultCell)) {
//			thisCell.addDependent(resultCell);
//			resultCell.addDependent(thisCell);
//		} else {
//			throw new InfiniteReferenceException(thisCell, resultCell);
//		}
//	}
}
