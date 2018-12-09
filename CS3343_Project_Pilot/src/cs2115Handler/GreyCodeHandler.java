/**
 * City University of Hong Kong, Group 22
 * GreyCodeHandler.java
 * Base handler class with overridden return <em>value</em> and <em>string</em> method
 */

package cs2115Handler;

public class GreyCodeHandler extends ConvertHandler{
	
	@Override
	public double handleForDoubleReturn(String expression) {
		if(expression.length() >6 && expression.subSequence(0, 6).equals("=GREY("))
			expression = expression.substring(6,expression.length()-1);
		double tempRes = super.handleForDoubleReturn(expression);
		return tempRes;
	}
	
	@Override
	public String handleForStringReturn(String expression) {
		//hard code
		if(expression.length() >6 && expression.subSequence(0, 6).equals("=GREY("))
			expression = expression.substring(6,expression.length()-1);
		double tempRes = super.handleForDoubleReturn(expression);
		int temp = (int)tempRes;
		int res = (temp ^ (temp >> 1));
		return "" + new BinaryHandler().handleForStringReturn(""+res);
	}
}
