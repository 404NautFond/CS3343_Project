package fxcel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fxcelException.*;
import fxcelHandler.*;

public class Cell extends Subject implements Observer {

	public enum Type{
		NULL,
		TEXT,
		NUMERIC,
		FUNC_NUMERIC,
		//		FUNC_TEXT,
		FUNC_RADIX,
		FUNC_LOGIC,
		ERROR
	}

	private String expression;
	private double value;	//default is 0 when computing, but not display
	private Type type;
	private String textual;     //contain the expression error information
	private String position;

	private List<Cell> dependent = new ArrayList<Cell>();

	/**
	 * Default constructor for cell, will be called by Fxcel
	 */
	public Cell() {
		super();
		setCell(0, null, Type.NULL);
		this.expression = null;
	}
	
	/**
	 * Auxiliary function for setting Cells
	 */
	private void setCell(double value, String text, Type type) {
		this.value = value;
		this.textual = text;
		this.type = type;
	}

	/**
	 * Get the String to display
	 * @return String textual showing error
	 */
	public String getTextual() {
		return textual;
	}

	/**
	 * Set the String to display
	 * @param text The text to display
	 */
//	public void setTextual(String text) {
//		this.textual = text;
//	}

	/**
	 * Set the position String in the format "A8"
	 * @param position The formatted String
	 */
	protected void setPosition(String position){
		this.position = position;
	}

	/**
	 * Get the position String for troubleshooting
	 * @return The position String
	 */
	public String getPosition(){
		return this.position;
	}

	/**
	 * Getter of String field expression
	 * @return expression
	 */
	public String getExpression() {
		return this.expression;
	}

	/**
	 * Getter of double field value
	 * @return value The value in the double form
	 * @throws InvalidCellException Throws when the value is not defined for computation
	 */
	public double getValue() throws InvalidCellException{
//		System.out.println(type);
		switch(type) {
		case NULL:
			return 0;
		case TEXT:
		case ERROR:
			throw new InvalidCellException(this);
		default:
			return this.value;
		}
	}

	/**
	 * Assign an expression to the Cell. Classifications and computations will be done.
	 * @param expression The String fed into the cell
	 */
	protected void assign(String expression){

		// resolve infinite reference problem
		if(this.type == Type.ERROR && this.textual.equals("#INF#")) {
			list.remove(this);
			for(Cell tempCell: list) {
				tempCell.detach(this);
			}
		}

		//TODO hard code
		if(expression.equalsIgnoreCase("true")) {
			setCell(1,"TRUE",Type.FUNC_LOGIC);
			return;
		}else if(expression.equalsIgnoreCase("false")) {
			setCell(0,"FALSE",Type.FUNC_LOGIC);
			return;
		}

		// keep the original content anyway
		this.clear();
		this.expression = expression;

		// handle null input
		if(expression.equals("")) {
			setCell(0,"",Type.TEXT);
			return;
		}
		// define expression by the first char
		char identifier = expression.charAt(0);

		if (identifier == ':') {					// pure text
			setCell(0,expression.substring(1),Type.TEXT);
			return;
		} else if (identifier == '=') {			// expression
			// use regular expression to split the expression
			String[] numberlike = expression.split("=|\\+|-|\\*|/|^|\\(|\\)|,|\\s");
			ArrayList<String> numberlikelist = new ArrayList<String>(Arrays.asList(numberlike));
			// add all referenced cell into dependent list
			for(String num: numberlikelist) {
				if(ExpHandler.isCell(num)) {
					Cell tempCell = Fxcel.getInstance().getCell(num);
					this.addDependent(tempCell);
					tempCell.attach(this);
				}
			}
			addDependent(numberlikelist);
			// recursively check dependent list
			boolean tempFlag = false;
			FxcelException close = null;
			for(Cell dep: dependent) {
				if(dep.equals(this) || dep.checkDep(this)) {
					tempFlag = true;
					dep.detach(this);
					close = new InfiniteReferenceException(dep, this);
					setCell(0,"#INF#",Type.ERROR);
					System.out.println(close.getMessage());
					this.notifyObservers();
					dep.attach(this);
				}
			}
			if(tempFlag) {
				return;
			}

			// change the value otherwise
			try {
				GeneralHandler gen = new GeneralHandler();
				double val = gen.handleForDoubleReturn(expression);
				String str = gen.handlerForStringReturn(expression);
				setCell(val,str,Type.NULL);
//				System.out.println(val + str);
			}catch(InvalidCellException e1) {
				setCell(0, "#CELL#", Type.ERROR);
				System.out.println(this.getPosition()+": using "+e1.getMessage());
				this.notifyObservers();
				return;
			}catch(InvalidExpressionException e2) {
				setCell(0, "#INVALID#", Type.ERROR);
				System.out.println(this.getPosition()+": "+e2.getMessage());
				this.notifyObservers();
				return;
			}

			if(ExpHandler.isNumeric(this.textual)) {
				if(this.textual.equals(""+this.value)) {
					this.type = Type.FUNC_NUMERIC;
					this.textual = new DecimalFormat("#.00").format(this.value);
				} else {
					this.type = Type.FUNC_LOGIC;
				}
			}else {
				this.type = Type.FUNC_RADIX;
			}
		} else {
			try {
				// without "="
				double val = Double.parseDouble(expression);
				setCell(val, new DecimalFormat("#.00").format(val), Type.NUMERIC);
			}catch(NumberFormatException e) {
				// not able to parse into number
				setCell(0, expression, Type.TEXT);
			}
		}
		this.notifyObservers();
	}

	/**
	 * Auxiliary function for analyzing the related cells
	 * @param left The list of suspicious String
	 */
	private void addDependent(ArrayList<String> left) {
		for(String range:left) {
			String[] cells = range.split(" |:");
			//Pre-conditions: The cells are sorted
			if(cells.length == 2 && ExpHandler.isCell(cells[0]) && ExpHandler.isCell(cells[1])) {
				for(int j = CellNamingHandler.getColumnEnhanced(cells[0]);
						j <= CellNamingHandler.getColumnEnhanced(cells[1]);
						j++) {
					for(int i = CellNamingHandler.getRowEnhanced(cells[0]);
							i <= CellNamingHandler.getRowEnhanced(cells[1]);
							i++) {
						addDependent(Fxcel.getInstance().getCell(i-1, j-1));
						Fxcel.getInstance().getCell(i-1,j-1).attach(this);
					}
				}
			}
		}
	}

	/**
	 * Auxiliary function for adding dependent list
	 * @param cell The target cell to be added into the list of the current cell 
	 */
	private void addDependent(Cell cell) {
		if(!dependent.contains(cell))
			dependent.add(cell);
	}

	/**
	 * Recursively check if the cell exists in the dependent list
	 * @param cell The target cell
	 * @return If the cell exists in the dependent list
	 */
	private boolean checkDep(Cell cell) {
		if(dependent.contains(cell)) 
			return true;
		for(Cell dep: dependent) {
			if (dep.checkDep(cell)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Clear the current cell
	 */
	public void clear() {
		this.expression = null;
		setCell(0, null, Type.NULL);
		this.dependent.clear();
	}

	/* From class Subject */
	@Override
	public void attach(Cell c) {
		if(!list.contains(c)) 
			list.add(c);
	}

	@Override
	public void detach(Cell c) {
		list.remove(c);
	}

	public void detachAll() {
		list.clear();
	}

	@Override
	public void notifyObservers() {
		for (Cell c: list) {
			c.update();
		}
	}

	@Override
	public void update() {
		this.assign(this.expression);
	}
	/* End of override function from Subject */

	/* From class Object */
	@Override
	public String toString() {
		String temp = position+": The expression is \""+ this.expression + "\"";
		if(this.type != Type.NULL) 
			temp += ", The value is \""+ this.value + "\"";
		return temp;
	}
	/* End of override function from Object*/
}
