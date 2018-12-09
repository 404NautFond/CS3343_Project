/**
 * City University of Hong Kong, Group 22
 * SumHandler.java
 * Base handler class with overridden return <em>value</em> method
 */

package commonHandler;

import fxcelHandler.CellNamingHandler;

public class SumHandler extends CommonHandler {

	@Override
	protected double calculateForColumnInput() { // 'cell : cell' input type
		double result = 0;
		int startRow;
		int startColumn;
		int endRow;
		int endColumn;
		String[] temp = columnHandler(getInput());

		startRow = CellNamingHandler.getRowEnhanced(temp[0]);
		startColumn = CellNamingHandler.getColumnEnhanced(temp[0]);
		endRow = CellNamingHandler.getRowEnhanced(temp[1]);
		endColumn = CellNamingHandler.getColumnEnhanced(temp[1]);

		for (int i = startRow-1; i<=endRow-1; i++) {
			for (int j = startColumn-1; j <= endColumn-1; j++) {
				result += getValueByPosition(i, j);
			}
		}
		return result;
	}

	@Override
	protected double calculateForCommaInput() { // 'cell,cell' input type
		double result = 0;
		String[] cellName = getInput().split(",");
		for (String singleCell: cellName) {
			result += getValueFromStringLike(singleCell.trim());
		}
		return result;
	}

}
