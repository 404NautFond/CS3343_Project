package CS3343_Project_Pilot.src.ma2172Handler;

import fxcelException.InvalidExpressionException;

public class PermutationHandler extends MathHandler {
	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		String[] temp = expression.split(",");
		try {
			int x1 = (int)(getValueFromStringLike(temp[0]));
			int x2 = (int)(getValueFromStringLike(temp[1]));
			return factorial[x1]/(factorial[x1-x2]);
		}catch(Exception e) {
			throw new InvalidExpressionException();
		}
	}
}
