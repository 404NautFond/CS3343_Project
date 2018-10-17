package fxcel;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fxcelHandler.CellNamingHandler;

public class Fxcel implements Serializable {

	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 7670923517384831952L;
	/**
	 * Array structure for cells
	 */
	private List<List<Cell>> table = null;
	private int row_max;
	private int col_max;

	private Observer obs;

	private static Fxcel instance = new Fxcel();

	/**
	 * Default Constructor
	 */
	private Fxcel() {
		this.row_max = 30;
		this.col_max = 30;
		this.table = new ArrayList<List<Cell>>();

		for (int i = 0; i < 30; i++) {
			List<Cell> li = new ArrayList<Cell>();
			for (int j = 0; j < 30; j++) {
				li.add(new Cell());
			}
			table.add(li);
		}
	}

	public void resize(int col, int row) {
		//TODO: Check overflow
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

	public void addCol(int col) {
		List<Cell> li = new ArrayList<Cell>();
		for (int i = 0; i < row_max; i++) {
			li.add(new Cell());
		}
		table.add(col - 1, li);
	}

	public void addRow(int row) {
		for(int i = 0; i < col_max; i++) {
			List<Cell> li = table.get(i);
			li.add(row - 1, new Cell());
		}
	}

	public void clear() {
		for (List<Cell> l : table) {
			for (Cell c : l) {
				c.clear();
			}
		}
		this.row_max = 30;
		this.col_max = 30;
	}
	
	public Cell getCell(String name) {
		int row = CellNamingHandler.getRowEnhanced(name);
		int col = CellNamingHandler.getColumnEnhanced(name);
		return getCell(row,col);
	}
	
	public void writeCell(int row, int col, String expression) {
		getCell(row, col).assign(expression);
	}
	
	public Cell getCell(int row, int col) {
		return (table.get(row)).get(col);
	}
	
	public String getCellExpression(int row, int col) {
		return getCell(row,col).getExpression();
	}

	
	@Override
	public String toString() {
		return null;
	}

	/**
	 * Singleton of Fxcel
	 *
	 * @return the instance of Fxcel
	 */
	public static Fxcel getInstance() {
		return instance;
	}

	//	//Testing
	//	public static void main(String[] args) {
	//		try {
	//			Fxcel.getInstance().writeCell(1, 2, "=1+2");
	//			String out = Fxcel.getInstance().readCellContent(1, 2);
	//			double val = Fxcel.getInstance().readCellVal(1, 2);
	//			System.out.println(val);
	//			//out = Fxcel.getInstance().readCellContent(1, 3);
	//		} catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//	}
	//	
	//	/**
	//	 * Read the Fxcel Object from the file in the path, need the object serial
	//	 * @param inFile
	//	 */
	//	public void readFxcel(File inFile) {}
	//	/**
	//	 * To save the Fxcel file in the path
	//	 * @param path the path to be stored in
	//	 */
	//	public void saveFxcel(String path) {}

}
