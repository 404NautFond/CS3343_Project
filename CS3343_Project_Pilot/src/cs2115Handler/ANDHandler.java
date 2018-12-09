/**
 * City University of Hong Kong, Group 22
 * ANDHandler.java
 * Base handler class with overridden return <em>value</em> method
 */

package cs2115Handler;

public class ANDHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) {
		setInput(expression);
		feed();
		String[] input = getInput().split(",");
		for(String celltext: input) {
			if(isFalseLike(celltext)) return 0;
		}
		return 1;
	}

}
