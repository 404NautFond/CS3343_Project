package fxcel;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {
	private static Stack<Double> opnd = new Stack<Double>();
	private static Stack<Character> optr = new Stack<Character>();
	private static int [][] pri = {					//[top]
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
	private static char[] dict = {'+','-','*','/','^','!','(',')','\0'};

//	public static void main(String[] args) {
//		Calculator my = new Calculator();
//		String[] para = {"(","1","+","2","*","2",")"};
//		System.out.println(my.compute(para));
//	}
	
	public static double computeToken(StringTokenizer token) {
		ArrayList<String> args = new ArrayList<String>();
		while(token.hasMoreTokens()) {
			args.add(token.nextToken());
		}
		System.out.println(args);
		String[] arr = args.toArray(new String[args.size()]);
		return compute(arr);
	}

	public static double compute(String[] args) {
		optr.push('\0');
		int i = 0;
		if(args[0].equals("=")) i++;
		
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
		if(optr.size() != 1) {
			char op = optr.pop();
			if('!' == op) opnd.push(calcu(op, opnd.pop()));
			else {
				double pOpnd2 = opnd.pop(), pOpnd1 = opnd.pop();
				opnd.push(calcu(pOpnd1, op, pOpnd2));
			}
		}
//		if(optr.size() != 1) {
//			char op = optr.pop();
//			opnd.push(calcu(op, opnd.pop()));
//		}
		//System.out.println(optr);
		//System.out.println(opnd);
		return opnd.pop();
	}

	private static double factorial(double num) {
		double res = 1;
		for(int i = 1; i <= num; i++) {
			res *= i;
		}
		//System.out.println("Yeah");
		return res;
	}
	
	public static double calcu(char sym, double num) {
		if((int)num != num) {
			//throw new Exception();
		}
		return factorial(num);
	}

	public static double calcu(double num1, char sym, double num2) {
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
	private static boolean isDigits(String str) {
		return str.charAt(0) <= '9' && str.charAt(0) >= '0';
	}

	private static int orderBetween(char op1, char op2) {
		int pos1 = -1, pos2 = -1;
		for(int i = 0; i < dict.length; i++) {
			if(dict[i] == op1) pos1 = i;
			if(dict[i] == op2) pos2 = i;
		}
		return pri[pos1][pos2];
	}

}
