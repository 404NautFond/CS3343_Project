package cs2115Handler;

import fxcel.Fxcel;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;

public class XNORHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		String[] input = expression.split(",");
		int count = 0;
		for(String celltext: input) {
			try {
				if(celltext.equals("TRUE") || !celltext.equals("0") || Fxcel.getInstance().getCellValue(celltext) != 0)
					count++;
			} catch (InvalidCellException | NumberFormatException e) {
				if(!isCell(celltext) && !celltext.equals("1") && !celltext.equals("FALSE"))
					throw new InvalidExpressionException();
			}
		}
		if(count % 2 == 0) {
			return 1;
		}else {
			return 0;
		}
	}

}