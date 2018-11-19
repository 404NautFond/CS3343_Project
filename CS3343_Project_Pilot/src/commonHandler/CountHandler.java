package commonHandler;

import fxcelHandler.CellNamingHandler;

public class CountHandler extends CommonHandler{

	@Override
	protected double calculateForColumnInput() {
		String[] temp = columnHandler(getInput());
		int strCol = CellNamingHandler.getColumnEnhanced(temp[0]);
		int endCol = CellNamingHandler.getColumnEnhanced(temp[1]);
		int strRow = CellNamingHandler.getRowEnhanced(temp[0]);
		int endRow = CellNamingHandler.getRowEnhanced(temp[1]);
		return (endCol-strCol+1)*(endRow-strRow+1);
	}

	@Override
	protected double calculateForCommaInput() {
		String[] temp = getInput().split(",");
		return temp.length;
	}
}
