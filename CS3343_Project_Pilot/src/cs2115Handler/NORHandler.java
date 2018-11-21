package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class NORHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		return new NOTHandler().handleForDoubleReturn(""+(int)new ORHandler().handleForDoubleReturn(expression));
	}

}