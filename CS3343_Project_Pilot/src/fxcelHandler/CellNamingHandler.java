package fxcelHandler;

import fxcel.Cell;

public class CellNamingHandler extends ExpHandler {
	// Get column number
    public int getColumn (String str) {
        return getNumberOfLetter(str);
    }

    // Get row number
    public static int getRow(String str) {
        int input = Integer.parseInt(str);
        if (input <= 0) {
            return -1;
        } else {
            return input;
        }
    }

    // Get enhanced column number
    public static int getColumnEnhanced(String str) {
       int column = calculate26Radix(str);
       if (column > 0) {
           return column;
       } else {
           return -1;
       }
    }

    // Get enhanced row number
    public static int getRowEnhanced(String str) { // Consider to be useless?
        return getRow(str);
    }

//    // Get column text
//    public String getColumnText(Cell cell) {
//        return getLetterOfNumber(cell.getColumn());
//    }
//
//    // Get enhanced column text
//    public String getColumnEnhancedText(Cell cell){
//        int columnNumber = cell.getColumn();
//        return calculateColumnText(columnNumber);
//    }

    // Transfer string to a 26-radix number
    private static int calculate26Radix(String input) {
        if (input.length() != 1) {
            int tempTransfer = 1;
            int decimalValue = 0;
           for (int i = input.length(); i > 0; i--) {
                decimalValue += getNumberOfLetter(String.valueOf(input.charAt(i))) * tempTransfer;
                tempTransfer *=26;
           }
           return decimalValue;
        } else {
            return getNumberOfLetter(input);
        }
    }

//    // Transfer a integer to corresponding column text
//    private String calculateColumnText(int num) {
//        if (num / 10 == 0) {
//            return getLetterOfNumber(num);
//        } else {
//            return "" + calculateColumnText(num / 10) + getLetterOfNumber(num % 10);
//        }
//    }

    // Transfer letter to number
    private static int getNumberOfLetter(String str) {
        int finalValue = str.charAt(0) - 'A';
        if (finalValue < 1 || finalValue > 26) {
            return -1;
        } else {
            return finalValue;
        }
    }

    // Transfer letter to number
//    private String getLetterOfNumber(int num) {
//        char finalValue = (char)('A' + num - 1);
//        if (num < 1 || num > 26) {
//            return null;
//        } else {
//            return String.valueOf(finalValue);
//        }
//    }
    
//    public static String getInformation(String str) {
//    		return getColumnEnhanced(str)+","+getRowEnhanced(str);
//    }
    
	@Override
	public double handle(String expression) {
		return 0;
	}

}
