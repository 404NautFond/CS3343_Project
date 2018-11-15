package ma2172Handler;

import commonHandler.CountHandler;
import fxcelException.InvalidExpressionException;
import fxcelHandler.CellNamingHandler;

public class VarianceHandler extends MathHandler {
	String input;
        @Override
        public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
    		this.input = expression;
                if(expression.contains(":"))
                        return calculateForColumnInput();
                else if(expression.contains(","))
                        return calculateForCommaInput();
                throw new InvalidExpressionException();
        }

        /**
         * Calculate the result from input expression with ":"
         * @return double result of the expression
         * @throws InvalidExpressionException 
         */
	protected double calculateForColumnInput() throws InvalidExpressionException { // 'cell : cell' input type
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
        
                for (int i = startRow-1; i<=endRow-1; i++) {
                        for (int j = startColumn-1; j <= endColumn-1; j++) {
                                double tempVal = getValueByPosition(i, j);
                                ex2 += tempVal*tempVal;
                        }
                }
                return ex2/count-mean*mean;
        }

        /**
         * Calculate the result from input expression with ","
         * @return double result of the expression
         * @throws InvalidExpressionException 
         */
	protected double calculateForCommaInput() throws InvalidExpressionException { // 'cell,cell' input type
                double ex2 = 0;
                double mean = new MeanHandler().handleForDoubleReturn(input);
                double count = new CountHandler().handleForDoubleReturn(input);
        
                String[] cellName = input.split(",");
                for (String singleCell: cellName) {
                        double tempVal = getValueFromStringLike(singleCell.trim());
                        ex2 += tempVal*tempVal;
                }
                return ex2/count-mean*mean;
        }

    
}
