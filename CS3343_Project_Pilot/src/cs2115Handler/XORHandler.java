package cs2115Handler;

import fxcel.Fxcel;
import fxcelException.FxcelException;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;

public class XORHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		String[] input = expression.split(",");
		int count = 0;
		for(String celltext: input) {
			try {
				if(celltext.equals("TRUE") || celltext.equals("1") || Fxcel.getInstance().getCellValue(celltext) == 1)
					count++;
			} catch (FxcelException e) {
				if(!isCell(celltext))
					throw new InvalidExpressionException();
			}
		}
		if(count % 2 == 0) {
			return 0;
		}else {
			return 1;
		}
	}

}