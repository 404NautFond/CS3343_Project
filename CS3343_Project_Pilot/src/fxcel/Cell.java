package fxcel;

import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;

import fxcelException.*;

public class Cell extends Subject implements Observer {

    /**
     * For serialization
     */
    private static final long serialVersionUID = 1088607801192340601L;
    private StringTokenizer myToken;
    private String expression;
    private double value;	//default is 0 when computing, but not display
    private List<Cell> dependent;
    /**
     * Default Constructor
     */
    protected Cell() {
    		this.expression = null;
    		//this.cal = new Calculator();
    }
    
    protected void assign(String expression) {
    		char identifier = expression.charAt(0);
    		if(identifier == ':')
    			this.expression = expression;
    		else if(identifier == '=');
    			//TODO: check dependency
    		else this.expression = expression;
    }
    
    protected boolean checkDep(Cell c) {
    		for(Cell dep:dependent) {
    			if(dep == c) {
    				if(this != c) return false;
    			}
    			if(!dep.checkDep(c)) return false;
    		}
    		return true;
    }
    
    protected void clear() {
    	
    }
    
    @Override
    public String toString() {
    		return null;
    }
    
    /**
     * Constructor with String input
     *
     * @param input the intended content
     */
    protected void writeCell(String input) {
        this.expression = input;
    }
    
    protected String readContent() {
    		return this.expression;
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
//    protected double computeVal() throws FxcelException {
//    		try {
//    			value = Double.parseDouble(expression);
//    		}catch(NumberFormatException e) {
//    	        if (expression.charAt(0) == '=') {
//    	            myToken = new StringTokenizer(expression, "=+-*/%^()!", true);
//    	            //token 1 can be Cell Location and Function Name
//    	            this.value = Calculator.computeToken(myToken);
//    	        } else 
//    	        	throw new NonParsableException();
//    		}
//        return value;
//    }
    
    public int getColumn() {
        return 0;
    }
}
