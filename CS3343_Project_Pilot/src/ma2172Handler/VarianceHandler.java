package ma2172Handler;

import commonHandler.CountHandler;
import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;
import fxcelHandler.CellNamingHandler;
import fxcelHandler.GeneralHandler;

public class VarianceHandler extends MathHandler {
	String input;
    @Override
    public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
    		this.input = expression;
        if(expression.contains(":"))
        		return calculateForInputType1();
        else if(expression.contains(","))
        		return calculateForInputType2();
        throw new InvalidExpressionException();
    }

    private double calculateForInputType1() throws InvalidExpressionException { // 'cell : cell' input type
        double ex2 = 0;
        int startRow;
        int startColumn;
        int endRow;
        int endColumn;
        String[] temp = columnHandler(input);
        
        double mean = new MeanHandler().handleForDoubleReturn(input);
        double count = new CountHandler().handleForDoubleReturn(input);
        
        startRow = CellNamingHandler.getRowEnhanced(temp[0]);
        startColumn = CellNamingHandler.getColumnEnhanced(temp[0]);
        endRow = CellNamingHandler.getRowEnhanced(temp[1]);
        endColumn = CellNamingHandler.getColumnEnhanced(temp[1]);

//        System.out.println(startRow+" "+startColumn+" "+endRow+" "+endColumn);
        
        for (int i = startRow-1; i<=endRow-1; i++) {
            for (int j = startColumn-1; j <= endColumn-1; j++) {
            		double tempVal = calculateValueForSingleCell(Fxcel.getInstance().getCell(i, j));
                ex2 += tempVal*tempVal;
            }
        }
        return ex2/count-mean*mean;
    }

    private double calculateForInputType2() throws InvalidExpressionException { // 'cell,cell' input type
        double ex2 = 0;
        double mean = new MeanHandler().handleForDoubleReturn(input);
        double count = new CountHandler().handleForDoubleReturn(input);
        
        String[] cellName = input.split(",");
        for (String singleCell: cellName) {
        		double tempVal = calculateValueForSingleCell(singleCell.trim());
            ex2 += tempVal*tempVal;
        }
        return ex2/count-mean*mean;
    }

    
}
