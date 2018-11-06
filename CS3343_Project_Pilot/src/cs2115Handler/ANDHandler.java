package cs2115Handler;

import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;

public class ANDHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		String[] input = expression.split(",");
		for(String cell: input) {
			if(Fxcel.getInstance().getCellValue(cell) == 0) return 0;
		}
		return 1;
	}

	@Override
	public String handleForStringReturn(String expression) throws InvalidExpressionException {
		return (handleForDoubleReturn(expression)==1)?"TRUE":"FALSE";
	}

}
