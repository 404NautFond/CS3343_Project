package fxcelHandler;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;

public abstract class FuncHandler extends ExpHandler {

	protected Cell resultCell;

	@Override
	public abstract double handleForDoubleReturn(String expression) throws InvalidExpressionException;
	
	public String[] columnHandler(String expression) {
		if(!expression.contains(":")) return null;
		String[] temp = expression.split(":");
		if(temp.length == 2) return temp;
		else return null;
	}

	/**
	 * Get the value of Cell by the coordinate
	 * @param row The row number
	 * @param col The column number
	 * @return The value of the Cell
	 * @throws InvalidExpressionException only when the cell is not able to be computed, default is 0
	 */
	protected double getValueByPosition(int row, int col) throws InvalidExpressionException {
		try {
			return Fxcel.getInstance().getCellValue(row, col);
		} catch (InvalidCellException e) {
			if(Fxcel.getInstance().getTextualValue(row, col) == null)
				return 0;
			else throw new InvalidExpressionException();
		}
	}

	protected double getValueFromStringLike(String name) throws InvalidExpressionException {
		GeneralHandler gen = new GeneralHandler();
		try {
			double res = gen.handleForDoubleReturn(name);
			return res;
		} catch (InvalidCellException e) {
			if (Fxcel.getInstance().getCell(name).getTextual() == null)
				return 0;
			else throw new InvalidExpressionException();
		}
	}
	
	/**
	 * For expression with ";"
	 * @return The value of such expression
	 */
	protected double calculateForColumnInput() {return 0;};
	
	/**
	 * For expression with ","
	 * @return The value of such expression
	 */
	protected double calculateForCommaInput(){return 0;};
}
