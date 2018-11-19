package commonHandler;

import fxcelHandler.CellNamingHandler;

public class MinHandler extends CommonHandler {

	@Override
	protected double calculateForColumnInput() { // 'cell : cell' input type
		double result;
		double tmp;
		int startRow, startColumn, endRow, endColumn;
		String[] temp = columnHandler(getInput());

		startRow = CellNamingHandler.getRowEnhanced(temp[0]);
		startColumn = CellNamingHandler.getColumnEnhanced(temp[0]);
		endRow = CellNamingHandler.getRowEnhanced(temp[1]);
		endColumn = CellNamingHandler.getColumnEnhanced(temp[1]);

		result = getValueByPosition(startRow-1,startColumn-1);

		for (int i = startRow-1; i<=endRow-1; i++) {
			for (int j = startColumn-1; j <= endColumn-1; j++) {
				tmp = getValueByPosition(i, j);
				if(result > tmp) {
					result = tmp;
				}
			}
		}
		return result;
	}

	@Override
	protected double calculateForCommaInput() { // 'cell,cell' input type
		double result;
		double tmp;
		String[] cellName = getInput().split(",");
		result = getValueFromStringLike(cellName[0].trim());
		for (String singleCell: cellName) {
			tmp = getValueFromStringLike(singleCell.trim());
			if(result > tmp) {
				result = tmp;
			}
		}
		return result;
	}

}
