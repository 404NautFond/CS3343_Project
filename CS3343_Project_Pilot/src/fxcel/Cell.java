package fxcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import fxcelException.*;
import fxcelHandler.*;

public class Cell extends Subject implements Observer {
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 1088607801192340601L;
	private String expression;
	private double value;	//default is 0 when computing, but not display
	private List<Cell> dependent;

	private boolean valueNotDefine;
	
	/**
	 * Default Constructor
	 */
	protected Cell() {
		super();
		this.expression = null;
		this.valueNotDefine = true;
	}

	protected void assign(String expression) {
		char identifier = expression.charAt(0);
		if (identifier == ':') {
			this.expression = expression;
		} else if (identifier == '=') {
			String[] numberlike = expression.split("=:+-*/^()");
			for(String num:numberlike) {
				if(GeneralHandler.isCell(num)) dependent.add(Fxcel.getInstance().getCell(num));
			}
			
			//TODO: Check the dependency
			
			this.value = new GeneralHandler().handle(expression);
			this.valueNotDefine = false;
		} else {
			this.expression = expression;
		}
	}

	public String getExpression() {
		return this.expression;
	}
	
	public double getValue() throws InvalidCellException{
		if(!valueNotDefine) 
			throw new InvalidCellException(this);
		return this.value;
	}

	public boolean checkDep(Cell c) {
		for (Cell dep : dependent) {
			if (dep == c) {
				if (this != c) {
					return false;
				}
			}
			if (!dep.checkDep(c)) {
				return false;
			}
		}
		return true;
	}

	protected void clear() {
		this.expression = null;
		for (Cell d : dependent) {
			d = null;
		}
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
		// TODO Auto-generated method stub
		//        this.value = cal.calculate(expressionArray);
	}

	public void addDependent(Cell cell) {
		dependent.add(cell);
	}

}
