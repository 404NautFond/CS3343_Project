/**
 * City University of Hong Kong, Group 22
 * GeneralHandler.java
 * The root algorithm processing class.
 */

package fxcelHandler;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.EmptyStackException;
import java.util.Hashtable;
import java.util.Stack;
import java.util.StringTokenizer;

import commonHandler.*;
import cs2115Handler.*;
import fxcel.Fxcel;
import fxcelException.*;
import ma2172Handler.*;

public class GeneralHandler extends FuncHandler{
	private Stack<String> buffer = new Stack<String>();
	private ArrayList<String> tokens = new ArrayList<String>();
	protected static final Dictionary<String, FuncHandler> call = new Hashtable<String, FuncHandler>();

	public GeneralHandler() {
		call.put("SUM", new SumHandler());
		call.put("AVE", new AverageHandler());
		call.put("MIN", new MinHandler());
		call.put("MAX", new MaxHandler());
		call.put("COUNT", new CountHandler());

		call.put("COMB", new CombinitionHandler());
		call.put("PERM", new PermutationHandler());
		call.put("MEAN", new MeanHandler());
		call.put("SD", new StandDeviHandler());
		call.put("VAR", new VarianceHandler());

		call.put("BIN", new BinaryHandler());
		call.put("OCT", new OctalHandler());
		call.put("DEC", new DecimalHandler());
		call.put("HEX", new HexadecimalHandler());

		call.put("ASCII", new ASCIIHandler());
		call.put("GREY", new GreyCodeHandler());

		call.put("AND", new ANDHandler());
		call.put("OR", new ORHandler());
		call.put("NOT", new NOTHandler());
		call.put("NAND", new NANDHandler());
		call.put("NOR", new NORHandler());
		call.put("XOR", new XORHandler());
		call.put("XNOR", new XNORHandler());
	}

	public String handlerForStringReturn(String expression) {
		feed(expression);
		// ignore the '=' in the beginning
		FuncHandler hand = call.get(tokens.get(1));
		tokens.clear();
		if(hand instanceof ConvertHandler) {
			return ((ConvertHandler)hand).handleForStringReturn(expression);
		} else if(hand instanceof LogicHandler) {
			return (handleForDoubleReturn(expression)==1)?"TRUE":"FALSE";
		} else{
			return handleForDoubleReturn(expression)+"";
		}
	}

	@Override
	public double handleForDoubleReturn(String expression){
		feed(expression);
		String tempToken, sym, number1, number2;
		while(tokens.size() != 0) {
			tempToken = tokens.remove(0);
			switch(tempToken) {
			/* ignore first "=" sign */
			case "=":
			case " ":
				continue;
				/* level 2 operators */
			case "*":
			case "/":
				//					case "^":
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
		}

		lowestPriority();
		double result = Double.parseDouble(buffer.lastElement());
		buffer.clear();
		tokens.clear();
		return result;
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
		if(tokens.size() == 0)
			throw new InvalidExpressionException();

		// add parameters back to string
		temp = processPair(tokens);
		tokens.remove(0);			// remove the ")"

		// define the output to 0
		double res = 0;
		try {
			// recursively call the handler
			GeneralHandler tempHandler = new GeneralHandler();
			res = tempHandler.handleForDoubleReturn(temp);
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
				// specially handleForDoubleReturn negative values
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
	 * Extracted method for pairs
	 * @param tokens The tokens the expression contains
	 * @return The result String
	 */
	private String processPair(ArrayList<String> tokens) {
		String tempExpr = "";
		int lock = 1;
		while(lock != 0) {
			if(tokens.get(0).equals(")")) {
				lock--;
				if(lock == 0) break;
			}
			else if(tokens.get(0).equals("(")) lock++;
			tempExpr = tempExpr + tokens.remove(0);
		}
		return tempExpr;
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
		//		System.out.println(tokens);
		try {
			tokens.remove(0);
			tempExpr = processPair(tokens);
			tokens.remove(0);
		} catch(IndexOutOfBoundsException e) {
			throw new InvalidExpressionException();
		}
		try {
			String result = call.get(formula).handleForDoubleReturn(tempExpr)+"";
			return result;
		}catch(Exception e) {
//			e.printStackTrace();
			throw new InvalidExpressionException();
		}
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
		operand = operand.trim();
		if(operand.equals("(")) {
			return recursion();
		}else if(isNumeric(operand)) {
			return operand;
		}else if(isCell(operand)) {
			return (Fxcel.getInstance().getCellValue(operand)+"");
		}else {
			return compute(operand, tokens);
		}
	}

}
