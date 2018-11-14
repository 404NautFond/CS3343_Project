package fxcelHandler;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;

public class DeleteHandler extends FuncHandler {

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		Cell tempCell = Fxcel.getInstance().getCell(expression);
		tempCell.clear();
		return 0;
	}

}
