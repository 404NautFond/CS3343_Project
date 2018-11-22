package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class BinaryHandler extends ConvertHandler {
    @Override
    public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		return super.handleForDoubleReturn(expression);
    }
    
    @Override
    public String handleForStringReturn(String expression) {
    		return "0b"+convertTo(2, super.handleForDoubleReturn(expression));
    }

    //TODO: All the radix values, textual value = value after conversion, e.g. 0b0100, expression value = decimal
}
