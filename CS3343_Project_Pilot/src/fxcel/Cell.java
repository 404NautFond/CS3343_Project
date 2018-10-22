package fxcel;

import java.util.ArrayList;
import java.util.List;

import fxcelException.*;
import fxcelHandler.*;

public class Cell extends Subject implements Observer {
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 1088607801192340601L;
	
	private String expression;
	private double value;	//default is 0 when computing, but not display
	private List<Cell> dependent = new ArrayList<Cell>();
	private boolean isValueNotDefine;
	
	private String mypos = null;
	public void setPos(String str){this.mypos = str;}
	
	/**
	 * Default constructor for cell, will be called by Fxcel
	 */
	public Cell() {
		super();
		this.expression = null;
		this.value = 0;
		this.isValueNotDefine = true;
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
	 * @return value
	 * @throws InvalidCellException
	 */
	public double getValue() throws InvalidCellException{
		if(isValueNotDefine) {
			throw new InvalidCellException(this);
		}
		return this.value;
	}
	
	/**
	 * Assign an expression to the Cell. Classifications and computations will be done.
	 * @param expression The String fed into the cell
	 * @throws InfiniteReferenceException 
	 */
	protected void assign(String expression) throws InfiniteReferenceException {
		// keep the original content anyway
		this.expression = expression;

		// define expression by the first char
		char identifier = expression.charAt(0);
		
		if (identifier == ':') {					// pure text
			return;
		} else if (identifier == '=') {			// expression
			// use regular expression to split the expression
			String[] numberlike = expression.split("=|:|\\+|-|\\*|/|^|\\(|\\)");
			// add all referenced cell into dep list
			for(String num:numberlike) {
				if(ExpHandler.isCell(num)) {
					Cell tempCell = Fxcel.getInstance().getCell(num);
					if(!dependent.contains(tempCell)) {
						this.addDependent(tempCell);
						tempCell.attach(this);
					}
				}
			}
			
			// recursively check dependent list
			for(Cell dep: dependent) {
				if(!dep.equals(this) && dep.checkDep(this)) {
					//infinite reference
					throw new InfiniteReferenceException(dep, this);
				}
			}
			
			// change the value otherwise
			this.value = new GeneralHandler().handleForDoubleReturn(expression);
			this.isValueNotDefine = false;
			this.notifyObservers();
		}
	}

	/**
	 * Auxiliary function for adding dependent list
	 * @param cell The target cell to be added into the list of the current cell 
	 */
	public void addDependent(Cell cell) {
		if(!dependent.contains(cell))
			dependent.add(cell);
	}
	
	/**
	 * Recursively check if the cell exists in the dependent list
	 * @param cell The target cell
	 * @return If the cell exists in the dependent list
	 */
	public boolean checkDep(Cell cell) {
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
	protected void clear() {
		this.expression = null;
		this.value = 0;
		this.isValueNotDefine = true;
		for (Cell cell: dependent) {
			cell.update();
			detach(cell);
		}
		this.dependent.clear();
	}

	/* From class Subject */
	@Override
	public void attach(Cell c) {
		for (Cell cell:list) {
			if (cell == c) {
				return;
			}
		}
		list.add(c);
	}

	@Override
	public void detach(Cell c) {
		list.remove(c);
	}

	@Override
	public void notifyObservers() {
		for (Cell c :list) {
			c.update();
		}
	}
	
	@Override
	public void update() {
		this.value = new GeneralHandler().handleForDoubleReturn(this.expression);
	}
	/* End of override function from Subject */

	/* From class Object */
	@Override
	public String toString() {
		String temp = mypos+": The expression is \""+ this.expression + "\"";
		if(!this.isValueNotDefine) temp += ", The value is \""+ this.value + "\"";
		return temp;
	}
	/* End of override function from Object*/
}
