package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class ANDHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		String[] input = expression.split(",");
		for(String celltext: input) {
			if(isFalseLike(celltext)) return 0;
		}
		return 1;
	}

}
