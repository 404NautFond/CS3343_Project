package fxcelHandler;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;

public abstract class FuncHandler extends ExpHandler {

	protected Cell resultCell;

	/**
	 * Compute the value for the expression
	 * @param expression The String type expression
	 * @return The double value
	 * @throws InvalidExpressionException 
	 */
	public abstract double handleForDoubleReturn(String expression);
	
        /**
         * Split the cell names by ":" in the expression
         * @param expression the input expression
         * @return String array of the cell names
         */
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
		return Fxcel.getInstance().getCellValue(row, col);
	}

        /**
         * Compute the result of the input String as double
         * @param name the input expression
         * @return the double result of the expression
         * @throws InvalidExpressionException 
         */
	protected double getValueFromStringLike(String name) throws InvalidExpressionException {
		GeneralHandler gen = new GeneralHandler();
		try {
			double res = gen.handleForDoubleReturn(name);
			return res;
		} catch (InvalidCellException e) {
			throw new InvalidExpressionException();
		}
	}
	
	
	
}
