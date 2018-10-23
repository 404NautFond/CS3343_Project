package fxcelHandler;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.InfiniteReferenceException;
import fxcelException.InvalidCellException;

public class SumHandler_LI extends SumHandler {

    @Override
    public double handleForDoubleReturn(String expression) {
        double result = 0;
        char[] inputExpression;
        inputExpression = expression.toCharArray();
        for (char character : inputExpression) {
            if (character == ':') {
                result = calculateForInputType1(expression);
                break;
            }
            if (character == ',') {
                result = calculateForInputType2(expression);
                break;
            }
        }
        return result;
    }
    @Override
    public double handleForDoubleReturn(String expression, Cell resultCell) {
        double result = 0;
        char[] inputExpression;
        inputExpression = expression.toCharArray();
        this.resultCell = resultCell;
        for (char character : inputExpression) {
            if (character == ':') {
                result = calculateForInputType1(expression);
                break;
            }
            if (character == ',') {
                result = calculateForInputType2(expression);
                break;
            }
            System.out.println("Anything");
        }
        return result;
    }

    private double calculateForInputType1(String input) { // 'cell : cell' input type
        double result = 0;
        int startRow;
        int startColumn;
        int endRow;
        int endColumn;
        String[] temp;
        temp = input.trim().split(":");
        startRow = CellNamingHandler.getRowEnhanced(temp[0]);
        startColumn = CellNamingHandler.getColumnEnhanced(temp[0]);
        endRow = CellNamingHandler.getRowEnhanced(temp[1]);
        endColumn = CellNamingHandler.getColumnEnhanced(temp[1]);

//        System.out.println(startRow+" "+startColumn+" "+endRow+" "+endColumn);
        
        for (int i = startRow-1; i<=endRow-1; i++) {
            for (int j = startColumn-1; j <= endColumn-1; j++) {
                result += calculateValueForSingleCell(Fxcel.getInstance().getCell(i, j));
            }
        }
        return result;
    }

    private double calculateForInputType2(String input) { // 'cell,cell' input type
        double result = 0;
        String[] cellName;

        cellName = input.split(",");
        for (String singleCell: cellName) {
            try {
               checkDependentForSingleCell(Fxcel.getInstance().getCell(singleCell));
            } catch (InfiniteReferenceException e) {
                e.printStackTrace();
            }
            result += calculateValueForSingleCell(Fxcel.getInstance().getCell(singleCell));
        }
        return result;
    }

    protected double calculateValueForSingleCell(Cell thisCell) {
        try {
            return thisCell.getValue();
        } catch (InvalidCellException e) {
//            e.printStackTrace();
            return 0;
        }
    }
}
