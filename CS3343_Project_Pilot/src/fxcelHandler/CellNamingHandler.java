package fxcelHandler;

public class CellNamingHandler extends ExpHandler {

    /**
     * Extract the row number from the String
     * @param str The string to be parsed
     * @return
     */
    public static int getRowEnhanced(String str) {
    		int pos = 0;
    		for(int i = 0; i < str.length(); i++) {
    			char temp = str.charAt(i);
    			if(temp <= '9' && temp >= '0') {
    				pos = i;
    				break;
    			}
    		}
        int input = Integer.parseInt(str.substring(pos));
        if (input <= 0) {				// Invalid Cell Reference
            return -1;
        } else {
            return input;
        }
    }

    /**
     * Extract the column number from the String
     * @param str The string to be parsed
     * @return
     */
    public static int getColumnEnhanced(String str) {
       int column = getNumberOfLetter(str);
       if (column >= 0) {
           return column+1;
       } else {
           return -1;					// Invalid Cell Reference
       }
    }

    /**
     * Transfer string to a 26-radix number
     * @param input The String to be changed
     * @return The position of the column
     */
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

}
