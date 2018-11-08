package fxcelHandler;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.InfiniteReferenceException;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;

public abstract class FuncHandler extends ExpHandler {

	protected Cell resultCell;

	@Override
	public abstract double handleForDoubleReturn(String expression) throws InvalidExpressionException;
	
	public String[] columnHandler(String expression) {
		if(!expression.contains(":")) return null;
		
		String[] temp = expression.split(":");
		if(temp.length == 2) return temp;
		else return null;
	}

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
