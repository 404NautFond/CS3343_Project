package CS3343_Project_Pilot.src.ma2172Handler;

import commonHandler.AverageHandler;
import fxcelException.InvalidExpressionException;

public class MeanHandler extends MathHandler {
	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		try {
			return new AverageHandler().handleForDoubleReturn(expression);
		}catch(Exception e) {
			throw new InvalidExpressionException();
		}
	}
}
