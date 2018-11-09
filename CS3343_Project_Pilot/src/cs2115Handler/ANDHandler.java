package cs2115Handler;

import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;

public class ANDHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		String[] input = expression.split(",");
		for(String celltext: input) {
			if(!celltext.equals("TRUE") && !celltext.equals("1") && Fxcel.getInstance().getCellValue(celltext) == 0)
				return 0;
		}
		return 1;
	}

}
