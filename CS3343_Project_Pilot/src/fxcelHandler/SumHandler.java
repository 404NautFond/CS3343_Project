package fxcelHandler;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.InfiniteReferenceException;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;

public class SumHandler extends FuncHandler {

	private String input;
	
	public static void main() {
		SumHandler test = new SumHandler();
		Fxcel ins = Fxcel.getInstance();
		ins.writeCell(0, 0, "=1");
		ins.writeCell(1, 0, "=2");
		try {
			System.out.println(test.handleForDoubleReturn("A1:A2"));
		} catch (InvalidExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    @Override
    public double handleForDoubleReturn(String expression) throws InvalidExpressionException {
    		this.input = expression;
        if(expression.contains(":"))
        		return calculateForInputType1();
        else if(expression.contains(","))
        		return calculateForInputType2();
        throw new InvalidExpressionException();
    }
//    @Override
//    public double handleForDoubleReturn(String expression) {
//    		this.input = expression;
//        double result = 0;
//        char[] inputExpression;
//        inputExpression = expression.toCharArray();
////        this.resultCell = resultCell;
//        for (char character : inputExpression) {
//            if (character == ':') {
//                result = calculateForInputType1();
//                break;
//            }
//            if (character == ',') {
//                result = calculateForInputType2();
//                break;
//            }
//            System.out.println("Anything");
//        }
//        return result;
//    }

    private double calculateForInputType1() { // 'cell : cell' input type
        double result = 0;
        int startRow;
        int startColumn;
        int endRow;
        int endColumn;
        String[] temp = columnHandler(input);
        
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

    private double calculateForInputType2() { // 'cell,cell' input type
        double result = 0;
        String[] cellName = input.split(",");
        for (String singleCell: cellName) {
            result += calculateValueForSingleCell(Fxcel.getInstance().getCell(singleCell.trim()));
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
