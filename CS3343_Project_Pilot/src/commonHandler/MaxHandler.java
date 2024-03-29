/**
 * City University of Hong Kong, Group 22
 * MaxHandler.java
 * Base handler class with overridden return <em>value</em> method
 */

package commonHandler;

import fxcelHandler.CellNamingHandler;

public class MaxHandler extends CommonHandler {

	@Override
	protected double calculateForColumnInput() { // 'cell : cell' input type
		double result;
		double tmp;
		int startRow;
		int startColumn;
		int endRow;
		int endColumn;
		String[] temp = getInput().split(":");

		startRow = CellNamingHandler.getRowEnhanced(temp[0]);
		startColumn = CellNamingHandler.getColumnEnhanced(temp[0]);
		endRow = CellNamingHandler.getRowEnhanced(temp[1]);
		endColumn = CellNamingHandler.getColumnEnhanced(temp[1]);

		result = getValueByPosition(startRow-1,startColumn-1);

		for (int i = startRow-1; i<=endRow-1; i++) {
			for (int j = startColumn-1; j <= endColumn-1; j++) {
				tmp = getValueByPosition(i, j);
				if(result < tmp) {
					result = tmp;
				}
			}
		}
		return result;
	}

	@Override
	protected double calculateForCommaInput() { // 'cell,cell' input type
		double result = 0;
		double tmp;
		String[] parameters = getInput().split(",");
		
		result = getValueFromStringLike(parameters[0].trim());
		
		for (String name: parameters) {
			tmp = getValueFromStringLike(name.trim());
			if(result < tmp) {
				result = tmp;
			}
		}
		return result;
	}

}
