/*
 * Before the UI runs, this file can help to do a console version I/O
 * For UI designer, this part is not finished yet and many functions are not embedded
 * Even some information is not needed as well
 */
package fxcel;

import java.util.Scanner;

import fxcelException.FxcelException;
import fxcelHandler.ExpHandler;
import fxcelHandler.GeneralHandler;

public class Invoker {
	private static Fxcel instance = Fxcel.getInstance();

	private static String welcome = "Fxcel - Group 22\n Enter \"help|-h\" for instructions";
	private static String instructions = 
			"Following functions are provided:\n\n"
					+ "assign|-a [row number] [column number] [expression]\tfor enter into a cell\n"
					+ "result|-r [row number] [column number]\t\t\tfor getting the result to be displayed\n"
					+ "value|-v [row number] [column number]\t\t\tfor getting the double value to be displayed\n"
					+ "expression|-e [row number] [column number]\t\tfor getting the input\n"
					+ "*all the [row number] [column number] can be replaced by the cell name, e.g. A2 refers to the row=2 and col=1\n"
					+ "\nexit|-q \t\t\t\t\t\t\tquit the program\n\n"
					+ "[Cell Name] \t\t\t\t\t\tSame as -r [Cell Name]\n"
					+ "[Cell Name] [expression] \t\t\t\tSame as -a [Cell Name] [expression]\n"
					+ "\nfunctions\t\t\t\t\t\tlist all the avaiable function call\n"
					+ "print|-p\t\t\t\t\t\tprint the 10*10 cell\n";

	private static String functions =
			"Name\tValue\tComma\tColumn\n"
					+ "SUM\tY\tY\tY\n"
					+ "AVE\tY\tY\tY\n"
					+ "MIN\tY\tY\tY\n"
					+ "MAX\tY\tY\tY\n"
					+ "COUNT\tY\tY\tY\n"
					+ "COMB\tY\tY\tN\n"
					+ "PERM\tY\tY\tN\n"
					+ "MEAN\tY\tY\tY\n"
					+ "SD\tY\tY\tY\n"
					+ "VAR\tY\tY\tY\n"
					+ "Logic\tY\tY\tN\n"
					+ "Convert\tY\tY\tN\n";

	/**
	 * The invoker main method
	 * @param args The default
	 */
	public static void main(String[] args) {
		boolean end = false;
		Scanner reader = new Scanner(System.in);
		System.out.println(welcome);
		System.out.print(">>");
		String instruction = reader.nextLine();
		String[] tokens = instruction.split(" ");
		while(!end) {
			try {
				switch(tokens[0]) {
				case "functions":
					System.out.println(functions);
					break;
				case "help":
				case "-h":
					System.out.println(instructions);
					break;
				case "resize":
					instance.resize(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
					break;
				case "assign":
				case "-a":
					if(tokens.length == 4) {
						instance.writeCell(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);
						System.out.println("Set: "+instance.getTextualValue(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
					}else if(tokens.length == 3) {
						instance.writeCell(tokens[1], tokens[2]);
						System.out.println("Set: "+instance.getTextualValue(tokens[1]));
					}
					break;
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
				case "print":
				case "-p":
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							String temp = "";
							if(instance.getTextualValue(i,j) != null) temp = instance.getTextualValue(i,j);
							System.out.print("["+temp+"]\t");
						}
						System.out.println();
					}
					break;
				default:
					if(ExpHandler.isCell(tokens[0])) {
						if(tokens.length == 1) {
							System.out.println(instance.getTextualValue(tokens[0]));
						}else if(tokens.length == 2) {
							instance.writeCell(tokens[0], tokens[1]);
							System.out.println("Set: "+instance.getTextualValue(tokens[0]));
						}
					} else if(tokens.length == 1 && tokens[0].charAt(0) == '=') {
						System.out.println(new GeneralHandler().handleForDoubleReturn(tokens[0]));
					}
					else System.out.println("Token " + tokens[0] + " is not covered.");
				}
			}
			catch(ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e1) {
				//do nothing
				System.out.print(">>");
			}
			catch(FxcelException e2) {
				//not good
				System.out.println("Syntax Error.");
			}
			catch(Exception e3){
				System.out.println("Unexpected Error: ");
				e3.printStackTrace();
			}
			finally {
				System.out.print(">>");
				instruction = reader.nextLine();
				tokens = instruction.split(" ");
			}
		}
		reader.close();
	}
}
