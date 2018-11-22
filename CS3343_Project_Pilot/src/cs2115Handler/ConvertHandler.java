package cs2115Handler;

import fxcelHandler.FuncHandler;
import fxcelHandler.GeneralHandler;

public abstract class ConvertHandler extends FuncHandler {
	
    @Override
	public double handleForDoubleReturn(String expression){
		return new GeneralHandler().handleForDoubleReturn(expression);
    };
	
        /**
         * Convert expression string to decimal number string
         * @param expression the expression to be converted
         * @return the converted number string
         * @throws InvalidExpressionException 
         */
	public abstract String handleForStringReturn(String expression);
	
        /**
         * Convert the number to the needed radix
         * @param radix the radix that the target is converted to
         * @param target the number which is to be converted
         * @return String of the converted number
         */
	public static String convertTo(int radix, double target) {
		String temp = "";

		double decimal = target - (int)target;
		int integer = (int)target;
		char[] charset = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E'};
		while(integer != 0) {
			temp = charset[integer%radix]+temp;
			integer /= radix;
		}
		if(decimal < 0.0001 && decimal > -0.0001) {
			return temp;
		}
		else {
			temp = temp+'.';
			for(int i = 0; i < 5; i++) {
				if(decimal == 0) break;
				else {
					decimal = decimal*radix;
					temp = temp + charset[(int)decimal];
					decimal = decimal - (int)(decimal);
				}
			}
		}
		return temp;
	}
}
