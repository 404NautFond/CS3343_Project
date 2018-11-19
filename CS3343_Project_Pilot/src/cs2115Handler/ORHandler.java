package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class ORHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		setInput(expression);
		feed();
		String[] input = getInput().split(",");
		for(String celltext: input) {
			if(isTrueLike(celltext)) return 1;
		}
		return 0;
	}

}