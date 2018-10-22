package fxcelHandler;

import java.util.ArrayList;
import java.util.EmptyStackException;
//import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.StringTokenizer;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.*;

public class GeneralHandler extends ExpHandler{
	public Stack<String> buffer = new Stack<String>();
	private ArrayList<String> tokens = new ArrayList<String>();

	/**
	 * Tester
	 * @param args
	 */
	public static void main(String[] args) {
		GeneralHandler my = new GeneralHandler();
		System.out.println(my.handle("=SUM(1+1)"));
	}

	@Override
	public double handle(String expression){
		feed(expression);
		String tempToken, sym, number1, number2;
		try {
			while(tokens.size() != 0) {
				try {
					tempToken = tokens.remove(0);
					switch(tempToken) {
					/* ignore first "=" sign */
					case "=":
						continue;
					/* level 2 operators */
					case "*":
					case "/":
					case "^":
						number2 = tokens.remove(0);
						number2 = expand(number2);
						sym = tempToken;
						number1 = buffer.pop();
						buffer.push(calcu(number1, sym, number2)+"");
						break;
					/* level 1 operators */
					case "+":
					case "-":
						buffer.push(tempToken);
						break;
					/* recursion, to be included in the expand */
//					case "(":
//						buffer.push(recursion());
//						break;
					/* syntax error */
					case ")":
						throw new InvalidExpressionException();
					/* operands other than above */
					default:
						buffer.push(expand(tempToken));
					}
				}catch(InvalidCellException e) {
					buffer.push("0");
				}
			}
		}catch(InvalidExpressionException e) {
			e.printStackTrace();
			return 0;
		}

		lowestPriority();
		return Double.parseDouble(buffer.lastElement());
	}
	
	/**
	 * Initialize the handler by the expression
	 */
	private void feed(String expression) {
		StringTokenizer tokenizer = new StringTokenizer(expression,"=+-*/()^",true);
		while(tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken());
		}
	}
	
	/**
	 * Process the basic operations
	 * @param number1 The number before the sym
	 * @param sym The type of operation
	 * @param number2 The number after the sym
	 * @return The value of the operation
	 */
	public double calcu(String number1, String sym, String number2) {
		double num1 = Double.parseDouble(number1);
		double num2 = Double.parseDouble(number2);
		switch(sym) {
		case "+":
			return num1+num2;
		case "-":
			return num1-num2;
		case "*":
			return num1*num2;
		case "/":
			return (double)num1/num2;
		case "^":
			return Math.pow(num1, num2);
		default:
			return -1;
		}
	}

	/**
	 * Process the parenthesis content
	 * @return The only output token
	 * @throws InvalidExpressionException
	 */
	private String recursion() throws InvalidExpressionException{
		String temp = "";

		/* Syntax error detected */
		if(tokens.size() == 0) throw new InvalidExpressionException();
		
		// add parameters back to string
		while(!tokens.get(0).equals(")")) {
			temp = temp + tokens.remove(0);
		}
		tokens.remove(0);			// remove the ")"
		
		// define the output to 0
		double res = 0;
		try {
			// recursively call the handler
			GeneralHandler tempHandler = new GeneralHandler();
			res = tempHandler.handle(temp);
//		} catch (NoSuchElementException e) {
//			throw new InvalidExpressionException();
		} catch (EmptyStackException e) {
			throw new InvalidExpressionException();
		}
		return ""+res;
	}

	/**
	 * Process the level 1 priority operators
	 */
	private void lowestPriority() {
		String sym, number1, number2;
		// post-condition: only one token left
		while(buffer.size() > 1) {
			number2 = buffer.pop();
			sym = buffer.pop();
			try {
				number1 = buffer.pop();
				// specially handle negative values
				// TODO =-1+5
				if(sym.equals("-")) {
					if(!ExpHandler.isNumeric(number1)) {
						buffer.push(number1);
						number1 = "0";
					}					
				}
			}catch(EmptyStackException e) {
				number1 = "0";
			}
			buffer.push(calcu(number1, sym, number2)+"");
		}
	}

	/**
	 * Process the function content
	 * @param formula The formula keyword
	 * @param tokens The related parameters
	 * @return The function value
	 * @throws InvalidExpressionException
	 */
	private String compute(String formula, ArrayList<String> tokens) 
			throws InvalidExpressionException {
		// Gathering parameters
		String tempExpr = "";
		try {
			while(!tokens.get(0).equals(")")) {
				tempExpr = tempExpr + tokens.remove(0);
			}
			tokens.remove(0);
		} catch(IndexOutOfBoundsException e) {
			throw new InvalidExpressionException();
		}
		// Choosing formula type
		switch(formula) {
		case "SUM":
			return ""+new SumHandler().handle(tempExpr);
		}
		// If not match anything
		throw new InvalidExpressionException();
	}

	/**
	 * Process the Cell references, the Function references, and recursions
	 * @param operand The token to be evaluated
	 * @return The real value the piece of token taken
	 * @throws InvalidCellException
	 * @throws InvalidExpressionException
	 */
	private String expand(String operand) 
			throws InvalidCellException, InvalidExpressionException {
		if(operand.equals("(")) {
			return recursion();
		}else if(isNumeric(operand)) {
			return operand;
		}else if(isCell(operand)) {
			Cell targetCell = Fxcel.getInstance().getCell(operand);
			return (targetCell.getValue()+"");
		}else {
			return compute(operand, tokens);
		}
	}
	
}
