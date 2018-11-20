/*
 * Before the UI runs, this file can help to do a console version I/O
 */
package fxcel;

import java.util.Scanner;

import fxcelHandler.ExpHandler;

public class Invoker {
	private static Fxcel instance = Fxcel.getInstance();
	
	private static String welcome = "Fxcel - Group 22\n Enter \"help|-h\" for instructions";
	private static String instructions = 
			"Following functions are provided:\n\n"
			+ "assign|-a [row number] [column number] [expression]\tfor enter into a cell\n"
			+ "result|-r [row number] [column number]\t\t\tfor getting the result to be displayed\n"
			+ "value|-v [row number] [column number]\t\t\tfor getting the double value to be displayed\n"
			+ "expression|-e [row number] [column number]\t\tfor getting the input\n"
			+ "\nall the [row number] [column number] can be replaced by the cell name, e.g. A2 refers to the row=2 and col=1\n"
			+ "\nexit|-q \t\t\t\t\t\t\tquit the program\n\n"
			+ "[Cell Name] \t\t\t\t\t\tSame as -r [Cell Name]\n"
			+ "[Cell Name] [expression] \t\t\t\tSame as -a [Cell Name] [expression]\n";
	/**
	 * The invoker main method
	 * @param args The default
	 */
	public static void main(String[] args) {
		boolean end = false;
		Scanner reader = new Scanner(System.in);
		System.out.println(welcome);
		String instruction = reader.nextLine();
		String[] tokens = instruction.split(" ");
		while(!end) {
			switch(tokens[0]) {
			case "help":
			case "-h":
				System.out.println(instructions);
				break;
			case "resize":
				instance.resize(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
				break;
			case "assign":
			case "-a":
				if(tokens.length == 4)
					instance.writeCell(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);
				else if(tokens.length == 3)
					instance.writeCell(tokens[1], tokens[2]);
				//ignore break
			case "result":
			case "-r":
				if(tokens.length == 3)
					System.out.println(instance.getTextualValue(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
				else if(tokens.length == 2)
					System.out.println(instance.getTextualValue(tokens[1]));
				break;
			case "value":
			case "-v":
				if(tokens.length == 3)
					System.out.println(instance.getCellValue(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
				else if(tokens.length == 2)
					System.out.println(instance.getCellValue(tokens[1]));
				break;
			case "expression":
			case "-e":
				if(tokens.length == 3)
					System.out.println(instance.getCellExpression(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
				else if(tokens.length == 2)
					System.out.println(instance.getCellExpression(tokens[1]));
				break;
			case "exit":
			case "-q":
				end = true;
				System.out.println("See you next time!");
				System.exit(0);
				break;
			default:
				if(ExpHandler.isCell(tokens[0])) {
					if(tokens.length == 1) {
						System.out.println(instance.getTextualValue(tokens[0]));
					}else if(tokens.length == 2) {
						instance.writeCell(tokens[0], tokens[1]);
						System.out.println(instance.getTextualValue(tokens[0]));
					}
				} 
				else System.out.println("Token " + tokens[0] + " is not covered.");
			}
			instruction = reader.nextLine();
			tokens = instruction.split(" ");
		}
		reader.close();
	}
}
