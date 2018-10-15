package fxcelHandler;

import java.util.Stack;

public abstract class ExpHandler {
	Stack<String> buffer;
	public abstract double handle(String expression);
}
