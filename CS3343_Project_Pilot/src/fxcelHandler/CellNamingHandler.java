package fxcelHandler;

//import fxcel.Cell;

public class CellNamingHandler extends ExpHandler {

	// Get column number
    public int getColumn (String str) {
        return getNumberOfLetter(str); // Consider to be useless as well
    }

    // Get row number
    public static int getRow(String str) {
    		int pos = 0;
    		for(int i = 0; i < str.length(); i++) {
    			char temp = str.charAt(i);
    			if(temp <= '9' && temp >= '0') {
    				pos = i;
    				break;
    			}
    		}
        int input = Integer.parseInt(str.substring(pos));
        if (input <= 0) {
            return -1;
        } else {
            return input;
        }
    }

    // Get enhanced column number
    public static int getColumnEnhanced(String str) {
       int column = getNumberOfLetter(str);
       if (column >= 0) {
           return column+1;
       } else {
           return -1;
       }
    }

    // Get enhanced row number
    public static int getRowEnhanced(String str) { // Consider to be useless?
        return getRow(str);
    }

    // Transfer string to a 26-radix number
    private static int getNumberOfLetter(String input) {
    		char temp;
    		int res = 0;
    		for(int i = 0; i < input.length(); i++) {
    			temp = input.charAt(i);
    			if(temp <= '9' && temp >= '0') break;
    			res *= 26;
    			res += temp - 'A';
    		}
    		
        return res;
    }
    
	@Override
	public double handle(String expression) {
		return 0;
	}

}
