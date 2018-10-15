package fxcel;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fxcel implements Serializable{
	
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
	}
	
	public void resize(int col, int row) {
		//TODO: Check overflow
		
		//TODO: Warning
	}
	
	public void addCol(int col) {
		
	}
	
	public void addRow(int row) {
		
	}
	
	public void clear() {
		
	}
	
	@Override
	public String toString() {
		return null;
	}
	
	/**
	 * Singleton of Fxcel
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
//	
//	/**
//	 * Write the cell and store in the Fxcel Object
//	 * @param row the intended row number
//	 * @param col the intended column number
//	 * @param content the intended String input
//	 */
//	
//	public void writeCell(int row, int col, String content) throws Exception{
//		if(row <= this.row && col <= this.col) {
//			if(mycell[row][col] != null) {
//				mycell[row][col].writeCell(content);
//			} else {
//				mycell[row][col] = new Cell(row, col, content);
//				obs.updateAll();
//			}
//		} else {
//			throw new Exception();
//		}
//	}
//	/**
//	 * Return the content in a cell with the format of String
//	 * @param row the intended row number of the cell
//	 * @param col the intended column number of the cell
//	 * @return the content in the cell
//	 */
//	public String readCellContent(int row, int col) {
//		return mycell[row][col].readContent();
//	}
//	
//	public double readCellVal(int row, int col) {
//		return mycell[row][col].readVal();
//	}
//	
//	public Cell getCell(int row, int col) {
//		return mycell[row][col];
//	}
	

}
