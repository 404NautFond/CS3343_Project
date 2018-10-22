package fxcelHandler;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.InfiniteReferenceException;
import fxcelException.InvalidCellException;

public class SumHandler extends FuncHandler {

    private Cell resultCell;
    private char[] inputExpression;

    @Override
    public double handle(String expression) {
//    		return 2;
        double result = 0;
        inputExpression = expression.toCharArray();
        resultCell = getResultCell();
        for (char character : inputExpression) {
            if (character == ':') {
                result = calculateForInputType1(expression);
                break;
            }
            if (character == ',') {
                result = calculateForInputType2();
                break;
            }
        }
        return result;
    }

    private Cell getResultCell() {
        String nameOfResultCell = "";
        for (int i = inputExpression.length - 1; i > 0; i--) {
            if (inputExpression[i] != '=') {
                nameOfResultCell = inputExpression[i] + nameOfResultCell;
            }
        }
        return Fxcel.getInstance().getCell(nameOfResultCell);
    }

    private double calculateForInputType1(String input) { // 'cell : cell' input type
        double result = 0;
        int startRow;
        int startColumn;
        int endRow;
        int endColumn;
        String[] temp;

        temp = input.split(":|=");
        startRow = CellNamingHandler.getRowEnhanced(temp[0]);
        startColumn = CellNamingHandler.getColumnEnhanced(temp[0]);
        endRow = CellNamingHandler.getRowEnhanced(temp[1]);
        endColumn = CellNamingHandler.getColumnEnhanced(temp[1]);

        for (int i = startRow; i<=endRow; i++) {
            for (int j = startColumn; j < endColumn; j++) {
                Cell tempCell = Fxcel.getInstance().getCell(i, j);
                try {
                    result += calculateValueForSingleCell(Fxcel.getInstance().getCell(i, j));
                } catch (InfiniteReferenceException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    private double calculateForInputType2() { // 'cell,cell' input type
        double result = 0;
        String cellName = "";
        for (char character : inputExpression) {
            if (character != ',' && character != '=') {
                cellName += character;
            } else {
                try {
                    result += calculateValueForSingleCell(Fxcel.getInstance().getCell(cellName));
                    cellName = "";
                } catch (InfiniteReferenceException e) {
                    e.printStackTrace();
                }
                if (character == '=')
                    break;
            }
        }
        return result;
    }

    private double calculateValueForSingleCell(Cell thisCell) throws InfiniteReferenceException {
        if (thisCell.checkDep(resultCell)) {
            thisCell.addDependent(resultCell);
            resultCell.addDependent(thisCell);
        } else {
            throw new InfiniteReferenceException(thisCell, resultCell);
        }
        try {
            return thisCell.getValue();
        } catch (InvalidCellException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
