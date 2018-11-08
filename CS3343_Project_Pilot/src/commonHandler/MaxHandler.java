package commonHandler;

import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;
import fxcelHandler.CellNamingHandler;

public class MaxHandler extends CommonHandler {

	private String input;

	@Override
	public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
		this.input = expression;
		if(expression.contains(":"))
			return calculateForInputType1();
		else if(expression.contains(","))
			return calculateForInputType2();
		throw new InvalidExpressionException();
	}

	private double calculateForInputType1() { // 'cell : cell' input type
		double result;
		double tmp;
		int startRow;
		int startColumn;
		int endRow;
		int endColumn;
		String[] temp = input.split(":");

		startRow = CellNamingHandler.getRowEnhanced(temp[0]);
		startColumn = CellNamingHandler.getColumnEnhanced(temp[0]);
		endRow = CellNamingHandler.getRowEnhanced(temp[1]);
		endColumn = CellNamingHandler.getColumnEnhanced(temp[1]);

		result = calculateValueForSingleCell(Fxcel.getInstance().getCell(startRow-1,startColumn-1));

		for (int i = startRow-1; i<=endRow-1; i++) {
			for (int j = startColumn-1; j <= endColumn-1; j++) {
				tmp = calculateValueForSingleCell(Fxcel.getInstance().getCell(i, j));
				if(result < tmp) {
					result = tmp;
				}
			}
		}
		return result;
	}

	private double calculateForInputType2() { // 'cell,cell' input type
		double result = 0;
		double tmp;
		String[] parameters = input.split(",");
		
		result = calculateValueForSingleCell("="+parameters[0].trim());
		
		for (String name: parameters) {
			tmp = calculateValueForSingleCell("="+name.trim());
			if(result < tmp) {
				result = tmp;
			}
		}
		return result;
	}

}
