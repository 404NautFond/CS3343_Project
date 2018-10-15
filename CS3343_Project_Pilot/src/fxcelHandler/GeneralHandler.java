package fxcelHandler;

import java.util.Stack;
import java.util.StringTokenizer;
//
//import fxcel.Calculator;
//import fxcelException.FxcelException;
//import fxcelException.NonParsableException;

public class GeneralHandler extends ExpHandler{
	public Stack<String> buffer;
	@Override
	public double handle(String expression) {
		boolean flag = false;
		StringTokenizer token = new StringTokenizer(expression,"=+-*/()",true);
		while(token.hasMoreTokens()) {
			
		}
		return 0;
	}
}
