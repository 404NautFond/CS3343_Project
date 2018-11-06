package cs2115Handler;

import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;

public class ANDHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		String[] input = expression.split(",");
		for(String cell: input) {
			if(!cell.equals("TRUE") && !cell.equals("1") && Fxcel.getInstance().getCellValue(cell) == 0)
				return 0;
		}
		return 1;
	}

}
