package fxcelHandler;

import fxcel.Cell;

public class CellNamingHandler extends ExpHandler {
    // I think there still needs to implement a method to classify the input and the getters should be public
    // by Richard, 2018-10-01

	// Get column number
    public int getColumn (String str) {
        return getNumberOfLetter(str);
    }

    // Get row number
    public int getRow(String str) {
        int input = Integer.parseInt(str);
        if (input <= 0) {
            return -1;
        } else {
            return input;
        }
    }

    // Get enhanced column number
    public int getColumnEnhanced(String str) {
       int column = calculate26Radix(str);
       if (column > 0) {
           return column;
       } else {
           return -1;
       }
    }

    // Get enhanced row number
    public int getRowEnhanced(String str) { // Consider to be useless?
        return getRow(str);
    }

    // Get column text
    public String getColumnText(Cell cell) {
        return getLetterOfNumber(cell.getColumn());
    }

    // Get enhanced column text
    public String getColumnEnhancedText(Cell cell){
        int columnNumber = cell.getColumn();
        return calculateColumnText(columnNumber);
    }

    // Transfer string to a 26-radix number
    private int calculate26Radix(String input) {
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

    // Transfer a integer to corresponding column text
    private String calculateColumnText(int num) {
        if (num / 10 == 0) {
            return getLetterOfNumber(num);
        } else {
            return "" + calculateColumnText(num / 10) + getLetterOfNumber(num % 10);
        }
    }

    // Transfer letter to number
    private int getNumberOfLetter(String str) {
        int finalValue = str.charAt(0) - 'A';
        if (finalValue < 1 || finalValue > 26) {
            return -1;
        } else {
            return finalValue;
        }
    }

    // Transfer letter to number
    private String getLetterOfNumber(int num) {
        char finalValue = (char)('A' + num - 1);
        if (num < 1 || num > 26) {
            return null;
        } else {
            return String.valueOf(finalValue);
        }
    }
	@Override
	double handle(String expression) {
		// TODO Auto-generated method stub
		return 0;
	}

}
