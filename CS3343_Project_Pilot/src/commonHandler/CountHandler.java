package commonHandler;

import fxcelException.InvalidExpressionException;
import fxcelHandler.CellNamingHandler;

public class CountHandler extends CommonHandler{

	private String input;
	
	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		this.input = expression;
		if(input.contains(":")) return calculateForColumnInput();
		else if(input.contains(",")) return calculateForCommaInput();
		throw new InvalidExpressionException();
	}

	@Override
	protected double calculateForColumnInput() {
		String[] temp = columnHandler(input);
		int strCol = CellNamingHandler.getColumnEnhanced(temp[0]);
		int endCol = CellNamingHandler.getColumnEnhanced(temp[1]);
		int strRow = CellNamingHandler.getRowEnhanced(temp[0]);
		int endRow = CellNamingHandler.getRowEnhanced(temp[1]);
		return (endCol-strCol+1)*(endRow-strRow+1);
	}

	@Override
	protected double calculateForCommaInput() {
		String[] temp = input.split(",");
		return temp.length;
	}
}
