package ma2172Handler;

import fxcel.Fxcel;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;
import fxcelHandler.GeneralHandler;

public class CombinitionHandler extends MathHandler{
	
	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		String[] temp = expression.split(",");
		GeneralHandler gen = new GeneralHandler();
		try {
			//TODO change to use getCell
			int x1 = (int)(Fxcel.getInstance().getCell(temp[0]).getValue());
			int x2 = (int)(Fxcel.getInstance().getCell(temp[1]).getValue());
			return factorial[x1]/(factorial[x2]*factorial[x1-x2]);
		}catch(InvalidCellException e) {
			System.out.println("Expression Error!");
			throw new InvalidExpressionException();
		}
	}
	
	
}
