/**
 * City University of Hong Kong, Group 22
 * XNORHandler.java
 * Base handler class with overridden return <em>value</em> and <em>string</em> method
 */

package cs2115Handler;

public class XNORHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression){
		setInput(expression);
		feed();
		String[] input = getInput().split(",");
		int count = 0;
		for(String celltext: input) {
			if(isTrueLike(celltext)) count++;
		}
		return (1-count % 2); 
	}

}