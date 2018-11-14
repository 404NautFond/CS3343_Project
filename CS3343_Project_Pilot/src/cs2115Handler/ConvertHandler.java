package cs2115Handler;

import fxcelException.InvalidExpressionException;
import fxcelHandler.FuncHandler;
import fxcelHandler.GeneralHandler;

public class ConvertHandler extends FuncHandler {
	
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException{
		return new GeneralHandler().handleForDoubleReturn(expression);
	};
	
	public String handleForStringReturn(String expression) throws InvalidExpressionException{
		return "0d"+convertTo(10, Double.parseDouble(expression));
	};
	
	public String convertTo(int radix, double target) {
		String temp = "";
		if(target < 0) {
			temp = temp + '-';
			target = -target;
		}
		double decimal = target - (int)target;
		int integer = (int)target;
		char[] charset = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E'};
		while(integer != 0) {
			temp = charset[integer%radix]+temp;
			integer /= radix;
		}
		if(decimal < 0.0001 && decimal > -0.0001) return temp;
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
