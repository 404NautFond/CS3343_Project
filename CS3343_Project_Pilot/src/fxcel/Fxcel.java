package fxcel;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Fxcel implements Serializable{
	
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 7670923517384831952L;
	/**
	 * Array structure for cells 
	 */
	private Cell[][] mycell;
	private int row;
	private int col;
	
	private Observer obs;

	private static Fxcel instance = new Fxcel();
	
	/** 
	 * Default Constructor
	 */
	private Fxcel() {
		this.row = 10;
		this.col = 10;
		this.mycell = new Cell[row][col];
		this.obs = new Observer();
	}
	/**
	 * Singleton of Fxcel
	 * @return the instance of Fxcel
	 */
	public static Fxcel getInstance() {
		return instance;
	}
	
	//Testing
	public static void main(String[] args) {
		try {
			Fxcel.getInstance().writeCell(1, 2, "=1+2");
			String out = Fxcel.getInstance().readCellContent(1, 2);
			double val = Fxcel.getInstance().readCellVal(1, 2);
			System.out.println(val);
			//out = Fxcel.getInstance().readCellContent(1, 3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Construct the Fxcel Object with the corresponding rownum and colnum
	 * @param row the number of rows
	 * @param col the number of column
	 */
	public void setFxcel(int row, int col) {
		this.row = row;
		this.col = col;
	}
	/**
	 * Read the Fxcel Object from the file in the path, need the object serial
	 * @param inFile
	 */
	public void readFxcel(File inFile) {}
	/**
	 * To save the Fxcel file in the path
	 * @param path the path to be stored in
	 */
	public void saveFxcel(String path) {}
	
	/**
	 * Write the cell and store in the Fxcel Object
	 * @param row the intended row number
	 * @param col the intended column number
	 * @param content the intended String input
	 */
	public void writeCell(int row, int col, String content) throws Exception{
		if(row <= this.row && col <= this.col) {
			if(mycell[row][col] != null) {
				mycell[row][col].writeCell(content);
			} else {
				mycell[row][col] = new Cell(row, col, content);
				obs.updateAll();
			}
		} else {
			throw new Exception();
		}
	}
	/**
	 * Return the content in a cell with the format of String
	 * @param row the intended row number of the cell
	 * @param col the intended column number of the cell
	 * @return the content in the cell
	 */
	public String readCellContent(int row, int col) {
		return mycell[row][col].readContent();
	}
	
	public double readCellVal(int row, int col) {
		return mycell[row][col].readVal();
	}
	
	public Cell getCell(int row, int col) {
		return mycell[row][col];
	}
	
	class Observer{
		private ArrayList<Cell> subscriber = new ArrayList<Cell>();
		public Observer() {}
		public boolean subscribe(Cell c) {
			try {
				subscriber.add(c);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		public boolean updateAll() {
			try {
				for(Cell c:subscriber) {
					c.computeVal();
				}
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	};
}
