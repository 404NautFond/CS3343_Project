package fxcel;

import java.io.File;
import java.io.Serializable;

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

	private static Fxcel instance = new Fxcel();
	
	/** 
	 * Default Constructor
	 */
	private Fxcel() {}
	/**
	 * Singleton of Fxcel
	 * @return the instance of Fxcel
	 */
	public static Fxcel getInstance() {
		return instance;
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
	public void writeCell(int row, int col, String content) {}
	/**
	 * Return the content in a cell with the format of String
	 * @param row the intended row number of the cell
	 * @param col the intended column number of the cell
	 * @return the content int the cell
	 */
	public String readCell(int row, int col) {return null;/*automation*/}
	
	public Cell getCell(int row, int col) {return mycell[row][col];}
}
