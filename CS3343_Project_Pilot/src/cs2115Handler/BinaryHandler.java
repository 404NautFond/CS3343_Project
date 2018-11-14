package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class BinaryHandler extends ConvertHandler {
    @Override
    public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
    	/*
        double result;
        int length = expression.length();
        if (length == 0) {
            throw new InvalidExpressionException();
        }
        for (int i = 0; i < length; i++) {
            char temp = expression.charAt(i);
            if (temp != '0' || temp != '1') {
                throw new InvalidExpressionException();
            }
        }
        result = Integer.parseInt(expression, 2);
        return  result;
    */
		return super.handleForDoubleReturn(expression);
    }
    
    @Override
    public String handleForStringReturn(String expression) {
    		return "0b"+convertTo(2, super.handleForDoubleReturn(expression));
    }

    //TODO: All the radix values, textual value = value after conversion, e.g. 0b0100, expression value = decimal
}
