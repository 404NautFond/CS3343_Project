package ma2172Handler;

import fxcel.Cell;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;
import fxcelHandler.FuncHandler;
import fxcelHandler.GeneralHandler;

public abstract class MathHandler extends FuncHandler{
	protected static int[] factorial;

	public MathHandler() {
		init();
	}

	public void init() {
		factorial = new int[20];
		factorial[0] = 1;
		for(int i = 1; i < 20; i++) {
			factorial[i] = factorial[i-1]*i;
		}
	}

	protected double calculateValueForSingleCell(String name) {
		GeneralHandler gen = new GeneralHandler();
		try {
			return gen.handleForDoubleReturn("="+name);
		} catch (InvalidCellException e) {
			throw new InvalidExpressionException();
		}
	}

	protected double calculateValueForSingleCell(Cell thisCell) throws InvalidExpressionException {
		try {
			return thisCell.getValue();
		} catch (InvalidCellException e) {
			if(thisCell.getExpression() == null)
				return 0;
			else
				throw new InvalidExpressionException();
		}
	}
}
