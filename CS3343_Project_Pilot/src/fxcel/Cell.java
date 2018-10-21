package fxcel;

//import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//import java.util.StringTokenizer;

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

	private boolean valueNotDefine;
	
	//
	private String mypos = null;
	public void setPos(String str){this.mypos = str;}
	
	/**
	 * Default Constructor
	 */
	public Cell() {
		super();
		this.expression = null;
		this.value = 0;
		this.valueNotDefine = true;
	}

	protected void assign(String expression) throws FxcelException {
		// keep the original content anyway
		this.expression = expression;

		char identifier = expression.charAt(0);
		if (identifier == ':') {
			return;
		} else if (identifier == '=') {
			String[] numberlike = expression.split("=|:|\\+|-|\\*|/|^|\\(|\\)");
			for(String num:numberlike) {
//				System.out.println(num);
				if(GeneralHandler.isCell(num)) 
					this.addDependent(Fxcel.getInstance().getCell(num));
			}
			
//			System.out.println(this.dependent);
			
			System.out.println("I'm checking on "+this);
			for(Cell dep: dependent) {
//				System.out.println(dep);
				if(dep.equals(this)) continue;
				if(dep.checkDep(this)) {
					//infinite reference
					throw new InfiniteReferenceException(dep, this);
				}
			}
			
			this.value = new GeneralHandler().handle(expression);
			this.valueNotDefine = false;
		}
	}
	
	public void addDependent(Cell cell) {
		dependent.add(cell);
	}
	
	public boolean checkDep(Cell cell) {
		if(dependent.contains(cell)) return true;
		System.out.println(this);
		System.out.println(this.dependent);
		for(Cell dep: dependent) {
			if (dep.checkDep(cell)) return true;
		}
		return false;
	}
	
	public String getExpression() {
		return this.expression;
	}
	
	public double getValue() throws InvalidCellException{
		if(valueNotDefine) {
			throw new InvalidCellException(this);
		}
		return this.value;
	}

	protected void clear() {
		this.expression = null;
		this.value = 0;
		this.valueNotDefine = true;
		for (Cell d : dependent) {
			d.update();
		}
		this.dependent.clear();
	}

	@Override
	public String toString() {
		String temp = mypos+": The expression is \""+ this.expression + "\"";
		if(!this.valueNotDefine) temp += ", The value is \""+ this.value + "\"";
		return temp;
	}

	/**
	 * Constructor with String input
	 *
	 * @param input the intended content
	 */
	protected void writeCell(String input) {
		this.expression = input;
	}

	@Override
	public void attach(Cell c) {
		for (Cell cell : list) {
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
		for (Cell c : list) {
			c.update();
		}
	}

	@Override
	public void update() {
		this.value = new GeneralHandler().handle(this.expression);
	}


	


}
