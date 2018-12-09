/**
 * City University of Hong Kong, Group 22
 * AverageHandler.java
 * Base handler class with overridden return <em>value</em> method
 */

package commonHandler;

public class AverageHandler extends SumHandler {
	@Override
	public double handleForDoubleReturn(String expression){
		double sum = (new SumHandler()).handleForDoubleReturn(expression);
		double num = (new CountHandler()).handleForDoubleReturn(expression);
		return sum/num;
	}
}
