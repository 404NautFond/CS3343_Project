/**
 * City University of Hong Kong, Group 22
 * NORHandler.java
 * Base handler class with overridden return <em>value</em> and <em>string</em> method
 */

package cs2115Handler;

public class NORHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) {
		return new NOTHandler().handleForDoubleReturn(""+(int)new ORHandler().handleForDoubleReturn(expression));
	}

}