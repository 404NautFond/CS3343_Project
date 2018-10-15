package fxcelHandler;

import java.util.Stack;

public abstract class ExpHandler {
	Stack<String> buffer;
	abstract double handle(String expression);
}
