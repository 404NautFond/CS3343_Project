package cs2115Handler;

import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;

public class ANDHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		String[] input = expression.split(",");
		for(String cell: input) {
			if(Fxcel.getInstance().getCellValue(cell) == 0 ||
					cell.equals("FALSE") ||
					!cell.equals("1"))
				return 0;
		}
		return 1;
	}

}
