package fxcel;

import java.util.Stack;

public class Calculator {
	private Stack<Double> opnd = new Stack<Double>();
	private Stack<Character> optr = new Stack<Character>();
	private int [][] pri = {					//[top]
			{ 1, 1,-1,-1,-1,-1,-1, 1, 1},	//+
			{ 1, 1,-1,-1,-1,-1,-1, 1, 1},	//-
			{ 1, 1, 1, 1,-1,-1,-1, 1, 1},	//*
			{ 1, 1, 1, 1,-1,-1,-1, 1, 1},	//\
			{ 1, 1, 1, 1, 1,-1,-1, 1, 1},	//^
			{ 1, 1, 1, 1, 1, 1,-2, 1, 1},	//!
			{-1,-1,-1,-1,-1,-1,-1, 0,-2},	//(
			{-2,-2,-2,-2,-2,-2,-2,-2,-2},	//)
			{-1,-1,-1,-1,-1,-1,-1,-2, 0}		//\0
 //[current]  +  -  *  /  ^  !  (  )  \0
	};
	private char[] dict = {'+','-','*','/','^','!','(',')','\0'};

	public static void main(String[] args) {
		Calculator my = new Calculator();
		String[] para = {"(","1","+","2","*","2",")"};
		System.out.println(my.compute(para));
	}

	public double compute(String[] args) {
		optr.push('\0');
		int i = 0;
		while(!optr.empty() && i < args.length) {
			if(isDigits(args[i])) {
				opnd.push(Double.parseDouble(args[i]));
				i++;
			} else {
				switch(orderBetween(optr.lastElement(),args[i].charAt(0))) {
				case -1:
					optr.push(args[i].charAt(0));
					i++;
					break;
				case 0:
					optr.pop();
					i++;
					break;
				case 1:
					char op = optr.pop();
					if('!' == op) opnd.push(calcu(op, opnd.pop()));
					else {
						double pOpnd2 = opnd.pop(), pOpnd1 = opnd.pop();
						opnd.push(calcu(pOpnd1, op, pOpnd2));
					}
				default:
					break;
				}
			}
		}
		return opnd.pop();
	}

	public double calcu(char sym, double num) {
		return 0;
	}

	public double calcu(double num1, char sym, double num2) {
		switch(sym) {
		case '+':
			return num1+num2;
		case '-':
			return num1-num2;
		case '*':
			return num1*num2;
		case '/':
			return (double)num1/num2;
		case '^':
			return Math.pow(num1, num2);
		default:
			return -1;
		}
	}

	//To be verified
	private boolean isDigits(String str) {
		return str.charAt(0) <= '9' && str.charAt(0) >= '0';
	}

	private int orderBetween(char op1, char op2) {
		int pos1 = -1, pos2 = -1;
		for(int i = 0; i < dict.length; i++) {
			if(dict[i] == op1) pos1 = i;
			if(dict[i] == op2) pos2 = i;
		}
		return pri[pos1][pos2];
	}

}
