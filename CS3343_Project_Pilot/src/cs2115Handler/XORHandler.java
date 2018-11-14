package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class XORHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		return new NOTHandler().handleForDoubleReturn(""+new XNORHandler().handleForDoubleReturn(expression));
	}

}