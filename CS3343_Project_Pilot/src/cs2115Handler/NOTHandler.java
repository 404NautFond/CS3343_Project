package cs2115Handler;

import fxcel.Fxcel;
import fxcelException.FxcelException;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;

public class NOTHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		try {
			if(expression.equals("TRUE") || expression.equals("1") || Fxcel.getInstance().getCellValue(expression) == 1)
				return 0;
		} catch (FxcelException | NumberFormatException e) {
			if(isCell(expression) || expression.equals("FALSE") || expression.equals("0")) return 1;
			else throw new InvalidExpressionException();
		}
		return 1;
	}

}
