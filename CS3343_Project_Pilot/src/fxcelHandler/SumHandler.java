package fxcelHandler;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.InfiniteReferenceException;
import fxcelException.InvalidCellException;

public class SumHandler extends FuncHandler {

    private Cell resultCell;

    @Override
    public double handle(String expression, Cell resultCell) {
//    		return 2;
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

        temp = input.split(":");
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

    private double calculateForInputType2(String input) { // 'cell,cell' input type
        double result = 0;
        String[] cellName;

        cellName = input.split(",");
        for (String singleCell: cellName) {
            try {
                result += calculateValueForSingleCell(Fxcel.getInstance().getCell(singleCell));
            } catch (InfiniteReferenceException e) {
                e.printStackTrace();
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
