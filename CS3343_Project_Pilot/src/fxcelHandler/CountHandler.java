package fxcelHandler;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;

public class CountHandler extends FuncHandler{

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
		//TODO: to eliminate the invalid ones
		return (endCol-strCol)*(endRow-strRow);
	}
	
	public int countForType2() {
		String[] temp = input.split(",");
		return temp.length;
	}
}
