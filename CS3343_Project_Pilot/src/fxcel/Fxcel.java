package fxcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import fxcelException.FxcelException;
import fxcelException.InfiniteReferenceException;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;
import fxcelHandler.CellNamingHandler;
import fxcelHandler.ExpHandler;

public class Fxcel implements Serializable {
	// Singleton
	private static Fxcel instance = new Fxcel();

	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 7670923517384831952L;
	private List<List<Cell>> table = null; 	// Array structure for cells
	private int row_max;
	private int col_max;

	/**
	 * Default Constructor, will be called by the singleton
	 */
	private Fxcel() {
		//initialize max row and col to be 30
		this.row_max = 30;
		this.col_max = 30;
		this.table = new ArrayList<List<Cell>>();

		//initialize each cell and the table
		for (int i = 0; i < 30; i++) {
			List<Cell> li = new ArrayList<Cell>();
			for (int j = 0; j < 30; j++) {
				li.add(new Cell());
			}
			table.add(li);
		}

		reassignPosition();
	}

	/**
	 * Assign position to each cell
	 */
	private void reassignPosition() {
		for(int i = 0; i < row_max; i++) {
			for(int j = 0; j < col_max; j++) {
				setCellPosition(i,j);
			}
		}
	}

	/**
	 * Change the table size based on original content
	 * @param col The new column number
	 * @param row The new row number
	 */
	public void resize(int col, int row) {
		//Check overflow. If overflow, resize the col or row.
		if (col > col_max) {
			for (int i = col_max; i < col_max * 2; i++) {
				List<Cell> li = new ArrayList<Cell>();
				for (int j = 0; j < row_max; j++) {
					li.add(new Cell());
				}
				table.add(li);
			}
			col_max *= 2;
		}

		if (row > row_max) {
			for (int i = 0; i < col_max; i++) {
				List<Cell> li = table.get(i);
				for (int j = row_max; j < row_max * 2; j++) {
					li.add(new Cell());
				}
			}
			row_max *= 2;
		}
	}

	/**
	 * Add new column before a given col number
	 * @param col The column number
	 */
	public void addCol(int col) {
		for(int i = 0; i < row_max; i++) {
			List<Cell> li = table.get(i);
			li.add(col, new Cell());
		}
		reassignPosition();
		col_max++;
	}

	/**
	 * Add new row before a given row number
	 * @param row The row position
	 */
	public void addRow(int row) {
		List<Cell> li = new ArrayList<Cell>();
		for (int i = 0; i < col_max; i++) {
			li.add(new Cell());
		}
		table.add(row, li);
		reassignPosition();
		row_max++;
	}

	/**
	 * Clear the table
	 */
	public void clear() {
		for (List<Cell> l: table) {
			for (Cell c: l) {
				c.clear();
				c.detachAll();
			}
		}
		this.row_max = 30;
		this.col_max = 30;
	}

	/**
	 * Convert col from int to String with letter representation and assign the position String to the cell
	 * @param row The row number
	 * @param col The col number
	 */
	private void setCellPosition(int row, int col) {
		int cellCol = col;
		String tempCol = "";
		col++;
		//convert col from int to String with letter representation
		while(col != 0) {
			tempCol = (char)('A'+(col)%26-1) + tempCol;
			col /= 26;
		}
		getCell(row,cellCol).setPosition(tempCol+(row+1));
	}

	/**
	 * Assign an expression to a cell
	 * @param row The row position
	 * @param col The column position
	 * @param expression The input expression
	 */
	public void writeCell(int row, int col, String expression) {
		Cell target = getCell(row, col);
		try {
			target.assign(expression);	
			setCellPosition(row, col);
		} catch (InfiniteReferenceException e) {
			System.out.println(e.getMessage());
			target.setTextual("#INF#");
		} catch(InvalidExpressionException e) {
			target.setTextual("#INVALID#");
			System.out.println(target.getPosition()+"(Expression:\""+target.getExpression()+"\") is invalid, please check.");
		}
	}

	/**
	 * Write a Cell by the cell name
	 * @param name The Cell name
	 * @param expression The writing expression
	 */
	public void writeCell(String name, String expression) {
		int row = CellNamingHandler.getRowEnhanced(name) - 1;
		int col = CellNamingHandler.getColumnEnhanced(name) - 1;
		writeCell(row, col, expression);
	}

	@Override
	public String toString() {
		String res = "Fxcel Instance with "+ row_max + " rows & " + col_max + " cols; ";
		for(int i = 0; i < row_max; i++) {
			for(int j = 0; j < col_max; j++) {
				if(getTextualValue(i,j) != null)
					res += "-"+getCell(i,j).toString() + "\n";
			}
		}
		return res;
	}

	/**
	 * Singleton of Fxcel
	 * @return the instance of Fxcel
	 */
	public static Fxcel getInstance() {
		return instance;
	}

	/**
	 * Get the Cell reference by the Cell name
	 * @param name The String input
	 * @return The Cell reference
	 */
	public Cell getCell(String name) {
		//throw exception if the expression of the cell is invalid
		if(!ExpHandler.isCell(name)) {
			throw new InvalidExpressionException();
		}
		//convert cell name to row and col numbers
		int row = CellNamingHandler.getRowEnhanced(name) - 1;
		int col = CellNamingHandler.getColumnEnhanced(name) - 1;
		return getCell(row,col);
	}

	/**
	 * Get the Cell reference by the location
	 * @param row The row location
	 * @param col The column location
	 * @return The Cell reference
	 */
	public Cell getCell(int row, int col) {
		return (table.get(row)).get(col);
	}

	/**
	 * Get the expression of the Cell by Cell name
	 * @param name The Cell name
	 * @return The expression
	 */
	public String getCellExpression(String name) {
		return getCell(name).getExpression();
	}

	/**
	 * Get the expression of the Cell by location
	 * @param row The row location
	 * @param col The column location
	 * @return The expression
	 */
	public String getCellExpression(int row, int col) {
		return getCell(row,col).getExpression();
	}

	/**
	 * Get the value of the Cell by Cell name
	 * @param name The Cell name
	 * @return The value
	 * @throws InvalidCellException
	 */
	public double getCellValue(String name) throws InvalidCellException {
		return getCell(name.trim()).getValue();
	}

	/**
	 * Get the value of the Cell by location
	 * @param row The row location
	 * @param col The column location
	 * @return The expression
	 * @throws InvalidCellException
	 */
	public double getCellValue(int row, int col) throws InvalidCellException {
		return getCell(row,col).getValue();
	}

	/**
	 * Get the String to be displayed
	 * @param row The row location
	 * @param col The column location
	 * @return The text
	 */
	public String getTextualValue(int row, int col) {
		return getCell(row,col).getTextual();
	}

	/**
	 * Get the String to be displayed
	 * @param name The Cell name
	 * @return The text
	 */
	public String getTextualValue(String name){
		return getCell(name).getTextual();
	}

}
