package fxcel;

//import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fxcelException.FxcelException;
import fxcelException.InvalidCellException;
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

//	private Observer obs;

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
		int row = CellNamingHandler.getRowEnhanced(name) - 1;
		int col = CellNamingHandler.getColumnEnhanced(name) - 1;
		return getCell(row,col);
	}
	
	public void writeCell(int row, int col, String expression) {
		//
		getCell(row, col).setPos((char)('A'+row)+""+(col+1));
		try {
			getCell(row, col).assign(expression);		
		} catch (FxcelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Cell getCell(int row, int col) {
		return (table.get(row)).get(col);
	}
	
	public String getCellExpression(int row, int col) {
		return getCell(row,col).getExpression();
	}
	
	public static void main(String[] args) {
		Fxcel istance = Fxcel.getInstance();
		istance.writeCell(0, 0, "=A2+A2");
		//TODO try A1*A1
		try {
			System.out.println(instance.getCell(0, 0).getValue());
//			System.out.println(instance.getCell(0, 1).getValue());
		} catch (InvalidCellException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		istance.writeCell(1, 0, "=A1");
//		istance.writeCell(0, 2, "=A2");
//		System.out.println(instance.getCellExpression(0, 0));
		try {
//			System.out.println(instance.getCell(0, 0).getValue());
			System.out.println(instance.getCell(0, 1).getValue());
		} catch (InvalidCellException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

}
