package CS3343_Project_Pilot.src.fxcel;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	//TODO: for non-numerical I/O
	/* Start */
	private String textual;
	public String getTextual() {return textual;}
	public void setTextual(String text) {this.textual = text;};
	/* End */
	
	private String mypos = null;
	public void setPos(String str){
		this.mypos = str;
	}
	public String getPos(){
		return this.mypos;
	}
	
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
		this.clear();
		this.expression = expression;
		// define expression by the first char
		char identifier = expression.charAt(0);
		
		if (identifier == ':') {					// pure text
			//TODO: new feature
			this.textual = expression.substring(1);
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
			for(Cell dep: dependent) {
				if(dep.equals(this) || dep.checkDep(this)) {
					//infinite reference
					throw new InfiniteReferenceException(dep, this);
				}
			}
			
			// change the value otherwise
			this.value = new GeneralHandler().handleForDoubleReturn(expression);
			// TODO: new method
			this.textual = new GeneralHandler().handlerForStringReturn(expression);
			
			this.isValueNotDefine = false;
			this.notifyObservers();
		} else {
			try {
				this.value = Double.parseDouble(expression);
				//TODO: new feature
				this.textual = expression;
				this.isValueNotDefine = false;
			}catch(Exception e) {
				this.isValueNotDefine = true;
			}
		}
	}

	public void addDependent(ArrayList<String> left) {
		for(String range:left) {
			String[] cells = range.split(" |:");
			//Pre-conditions: The cells are sorted
			if(cells.length == 2 && ExpHandler.isCell(cells[0]) && ExpHandler.isCell(cells[1])) {
				for(int j = CellNamingHandler.getColumnEnhanced(cells[0]); j <= CellNamingHandler.getColumnEnhanced(cells[1]); j++) {
					for(int i = CellNamingHandler.getRowEnhanced(cells[0]); i <= CellNamingHandler.getRowEnhanced(cells[1]); i++) {
						addDependent(Fxcel.getInstance().getCell(i-1, j-1));
						Fxcel.getInstance().getCell(i,j).addDependent(this);
					}
				}
			}
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
		for (Cell c :list) {
			c.update();
		}
	}
	
	@Override
	public void update() {
		try {
			this.assign(this.expression);
		} catch (InfiniteReferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
