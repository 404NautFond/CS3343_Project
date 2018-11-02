package commonHandler;

import fxcel.Cell;
import fxcelException.InvalidExpressionException;
import fxcelHandler.FuncHandler;

public class AverageHandler extends CommonHandler {

//    @Override
//    double handleForDoubleReturn(String expression, Cell resultCell) {
//        double result;
//        result = this.handleForDoubleReturn(expression, resultCell);
//        return result;
//    }
	
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		double sum = (new SumHandler()).handleForDoubleReturn(expression);
		double num = (new CountHandler()).handleForDoubleReturn(expression);
		return sum/num;
	}
}
