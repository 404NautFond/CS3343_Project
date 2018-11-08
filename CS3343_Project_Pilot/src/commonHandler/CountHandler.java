package commonHandler;

import fxcelException.InvalidExpressionException;
import fxcelHandler.CellNamingHandler;

public class CountHandler extends CommonHandler{

	private String input;
	
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		this.input = expression;
		if(input.contains(":")) return countForType1();
		else if(input.contains(",")) return countForType2();
		throw new InvalidExpressionException();
	}
	
	public int countForType1() {
		String[] temp = columnHandler(input);
		int strCol = CellNamingHandler.getColumnEnhanced(temp[0]);
		int endCol = CellNamingHandler.getColumnEnhanced(temp[1]);
		int strRow = CellNamingHandler.getRowEnhanced(temp[0]);
		int endRow = CellNamingHandler.getRowEnhanced(temp[1]);
		return (endCol-strCol+1)*(endRow-strRow+1);
	}
	
	public int countForType2() {
		String[] temp = input.split(",");
		return temp.length;
	}
}
