package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class XNORHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		String[] input = expression.split(",");
		int count = 0;
		for(String celltext: input) {
			if(isTrueLike(celltext)) count++;
		}
		if(count % 2 == 0) {
			return 1;
		}else {
			return 0;
		}
	}

}