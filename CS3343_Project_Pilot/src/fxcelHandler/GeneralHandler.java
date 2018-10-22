package fxcelHandler;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.StringTokenizer;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.*;

public class GeneralHandler extends ExpHandler{
	public Stack<String> buffer = new Stack<String>();
	private ArrayList<String> tokens = new ArrayList<String>();

	private void feed(String expression) {
		StringTokenizer tokenizer = new StringTokenizer(expression,"=+-*/()^",true);
		while(tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken());
		}
	}

	/**
	 * Tester
	 * @param args
	 */
	public static void main(String[] args) {
		GeneralHandler my = new GeneralHandler();
		System.out.println(my.handle("=2+2/2"));
	}

	private double recursion() throws InvalidExpressionException{
		String temp = "";
		if(tokens.size() == 0) throw new InvalidExpressionException();
		while(!tokens.get(0).equals(")")) {
			temp = temp + tokens.remove(0);
		}
		tokens.remove(0);
		double res = -1;
		try {
			GeneralHandler tempHandler = new GeneralHandler();
			res = tempHandler.handle(temp);
			//			System.out.println("The recursion output is: " + res);
		} catch (NoSuchElementException e) {
			throw new InvalidExpressionException();
		} catch (EmptyStackException e) {
			throw new InvalidExpressionException();
		}
		return res;
	}

	@Override
	//TODO Any using of number 2 need to varify the type of the cell
	public double handle(String expression){
		feed(expression);
		String tempToken, sym, number1, number2;
		try {
			while(tokens.size() != 0) {
				try {
					tempToken = tokens.remove(0);
//					System.out.println(tempToken + "::Buffer::" + buffer.toString() + "::Tokens::" + tokens.toString());
					switch(tempToken) {
					case "=":
						continue;
					case "*":
					case "/":
					case "^":
						number2 = tokens.remove(0);
						if(number2.equals("(")) {
							number2 = (""+recursion());
						}
						sym = tempToken;
						number1 = buffer.pop();
						buffer.push(calcu(number1, sym, number2)+"");
						break;
					case "+":
					case "-":
						buffer.push(tempToken);
						break;
					case "(":
						buffer.push(""+recursion());
						break;
					case ")":
						throw new InvalidExpressionException();
					default:
						String tempOprand = (expandOperand(tempToken));
						if(tempOprand != null) buffer.push(tempOprand);
						
					}
				}catch(InvalidCellException e) {
					buffer.push("0");
				}
			}
		}catch(InvalidExpressionException e) {
			e.printStackTrace();
			return 0;
		}

		//		System.out.println(buffer.toString() + "::" + tokens.toString());

		while(buffer.size() > 1) {
			number2 = buffer.pop();
			sym = buffer.pop();
			number1 = buffer.pop();
			buffer.push(calcu(number1, sym, number2)+"");
		}

//		System.out.println(buffer.toString() + "::" + tokens.toString());
		return Double.parseDouble(buffer.lastElement());
	}

	private static String expandOperand(String operand) throws InvalidCellException {
		if(isNumeric(operand)) return operand;
		else if(isCell(operand)) {
			Cell targetCell = Fxcel.getInstance().getCell(operand);
			return (targetCell.getValue()+"");
		}
		return null;
	}

	public static double calcu(String number1, String sym, String number2) {
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


}
