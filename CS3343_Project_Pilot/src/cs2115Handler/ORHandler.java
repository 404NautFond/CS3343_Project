package cs2115Handler;

import fxcel.Fxcel;
import fxcelException.FxcelException;
//import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;

public class ORHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		String[] input = expression.split(",");
		for(String celltext: input) {
			try {
				if(celltext.equals("TRUE") || celltext.equals("1") || Fxcel.getInstance().getCellValue(celltext) != 0)
					return 1;
			} catch (FxcelException | NumberFormatException e) {
				if(!isCell(celltext) && !celltext.equals("1") && !celltext.equals("FALSE")) {
					if(celltext.equals("0")) continue;
					else throw new InvalidExpressionException();
				}
			}
		}
		return 0;
	}

}