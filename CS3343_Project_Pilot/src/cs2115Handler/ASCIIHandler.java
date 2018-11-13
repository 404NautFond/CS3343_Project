package cs2115Handler;

import fxcelException.InvalidExpressionException;

public class ASCIIHandler extends ConvertHandler{
	//TODO: Char to ASCII String, numerical value = null, textual value = String

    @Override
    public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
        char input = expression.charAt(0);
        if ((int)input < 0 || (int)input >127) {
            throw new InvalidExpressionException();
        }
        return (double)input;
    }
}
