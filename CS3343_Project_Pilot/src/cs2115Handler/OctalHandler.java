package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class OctalHandler extends ConvertHandler {
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
            if (temp < '0' || temp > '7') {
                throw new InvalidExpressionException();
            }
        }
        result = Integer.parseInt(expression, 8);
        return  result;
    */
    		return super.handleForDoubleReturn(expression);
    }
    
    @Override
    public String handleForStringReturn(String expression) {
    		return "0o"+convertTo(8, super.handleForDoubleReturn(expression));
    }
}
