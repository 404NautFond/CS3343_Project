package cs2115Handler;

import fxcel.Fxcel;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;

public class ORHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		String[] input = expression.split(",");
		for(String celltext: input) {
			try {
				if(celltext.equals("TRUE") || !celltext.equals("0") || Fxcel.getInstance().getCellValue(celltext) != 0)
					return 1;
			} catch (InvalidCellException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}