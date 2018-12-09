/**
 * City University of Hong Kong, Group 22
 * NANDHandler.java
 * Base handler class with overridden return <em>value</em> and <em>string</em> method
 */

package cs2115Handler;

public class NANDHandler extends LogicHandler {

	@Override
	public double handleForDoubleReturn(String expression) {
		return new NOTHandler().handleForDoubleReturn(""+(int)new ANDHandler().handleForDoubleReturn(expression));
	}

}
