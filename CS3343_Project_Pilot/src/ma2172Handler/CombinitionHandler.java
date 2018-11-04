package ma2172Handler;

import fxcelException.InvalidExpressionException;
import fxcelHandler.GeneralHandler;

public class CombinitionHandler extends MathHandler{
	
	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		String[] temp = expression.split(",");
		GeneralHandler gen = new GeneralHandler();
		try {
			//TODO change to use getCell
			temp[0] = ""+gen.handleForDoubleReturn("="+temp[0]);
			temp[1] = ""+gen.handleForDoubleReturn("="+temp[1]);
			int x1 = (int)Double.parseDouble(temp[0]);
			int x2 = (int)Double.parseDouble(temp[1]);
			return factorial[x1]/(factorial[x2]*factorial[x1-x2]);
		}catch(Exception e) {
			e.printStackTrace();
			throw new InvalidExpressionException();
		}
	}
	
	
}
