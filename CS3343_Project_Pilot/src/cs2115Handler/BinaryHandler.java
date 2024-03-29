/**
 * City University of Hong Kong, Group 22
 * BinaryHandler.java
 * Base handler class with overridden return <em>value</em> and <em>string</em> method
 */

package cs2115Handler;

public class BinaryHandler extends ConvertHandler {
    @Override
    public double handleForDoubleReturn(String expression) {
		return super.handleForDoubleReturn(expression);
    }
    
    @Override
    public String handleForStringReturn(String expression) {
    		double val = super.handleForDoubleReturn(expression);
    		if(val < 0) return ("-0b"+convertTo(2,-val));
    		else return ("0b"+convertTo(2,val));
    }

}
