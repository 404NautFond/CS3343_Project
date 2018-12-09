/**
 * City University of Hong Kong, Group 22
 * DecimalHandler.java
 * Base handler class with overridden return <em>value</em> and <em>string</em> method
 */

package cs2115Handler;

public class DecimalHandler extends ConvertHandler {
	// Default is decimal
	
	@Override
	public double handleForDoubleReturn(String expression) {
		return super.handleForDoubleReturn(expression);
	}

	@Override
	public String handleForStringReturn(String expression){
		double val = super.handleForDoubleReturn(expression);
		if(val < 0) return ("-0d"+convertTo(10,-val));
		else return ("0d"+convertTo(10,val));
	}

}
