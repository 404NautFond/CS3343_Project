package fxcel;

import java.io.Serializable;
import java.util.StringTokenizer;

import fxcelException.*;

public class Cell implements Serializable {

    /**
     * For serialization
     */
    private static final long serialVersionUID = 1088607801192340601L;
    private StringTokenizer myToken;
    private String content;
    private double value;	//default is 0 when computing, but not display
    //private Calculator cal;
    /**
     * Default Constructor
     */
    protected Cell() {
    		this.content = null;
    		//this.cal = new Calculator();
    }
    
    protected Cell(int row, int col, String content) {
    		this.content = content;
    }

    /**
     * Constructor with String input
     *
     * @param input the intended content
     */
    protected void writeCell(String input) {
        this.content = input;
    }
    
    protected String readContent() {
    		return this.content;
    }
    protected double readVal() {
    		try {
				computeVal();
			} catch (FxcelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return this.value;
    }

    private Cell findCell(String loc) {
        int row = loc.charAt(0) - 'A';
        int col = Integer.parseInt(loc.substring(1));	//TODO:?
        return Fxcel.getInstance().getCell(row, col);
    }

    /**
     * Compute the value of the content included in the cell
     *
     * @return the value of the cell, if not a number, return null
     */
    protected double computeVal() throws FxcelException {
    		try {
    			value = Double.parseDouble(content);
    		}catch(NumberFormatException e) {
    	        if (content.charAt(0) == '=') {
    	            myToken = new StringTokenizer(content, "=+-*/%^()!", true);
    	            //token 1 can be Cell Location and Function Name
    	            this.value = Calculator.computeToken(myToken);
    	        } else 
    	        	throw new NonParsableException();
    		}
        return value;
    }

//    final private double add(Cell cell1, Cell cell2) throws NonParsableException {
//        return cell1.computeVal() + cell2.computeVal();
//    }
//
//    final private double minus(Cell cell1, Cell cell2) throws NonParsableException {
//        return cell1.computeVal() - cell2.computeVal();
//    }
//
//    final private double multi(Cell cell1, Cell cell2) throws NonParsableException {
//        return cell1.computeVal() * cell2.computeVal();
//    }
//
//    final private double divide(Cell cell1, Cell cell2) throws NonParsableException {
//        return cell1.computeVal() / cell2.computeVal();
//    }
//
//    final private int modd(Cell cell1, Cell cell2) throws NonParsableException {
//        return (int) cell1.computeVal() % (int) cell2.computeVal();
//    }
//
//    final private double pow(Cell cell1, Cell cell2) throws NonParsableException {
//        return Math.pow(cell1.computeVal(), (int) cell2.computeVal());
//    }

    // Add by Richard for testing
    public int getColumn() {
        return 0;
    }
}
