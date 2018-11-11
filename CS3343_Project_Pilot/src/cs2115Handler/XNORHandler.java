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
			} catch (InvalidCellException e) {
				e.printStackTrace();
			}
		}
		if(count % 2 == 0) {
			return 1;
		}else {
			return 0;
		}
	}

}