package cs2115Handler;

import fxcelException.FxcelException;
import fxcelException.InvalidExpressionException;

public class NOTHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		setInput(expression);
		feed();
		System.out.println(getInput());
		try {
			if(isTrueLike(getInput())) return 0;
			else if(isFalseLike(getInput())) return 1;
		} catch (FxcelException | NumberFormatException e) {
			throw new InvalidExpressionException();
		}
		return 1;
	}

}
