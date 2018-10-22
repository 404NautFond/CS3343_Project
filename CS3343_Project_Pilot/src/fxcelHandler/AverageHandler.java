package fxcelHandler;

import fxcel.Cell;

public class AverageHandler extends FuncHandler {

    @Override
    double handle(String expression, Cell resultCell) {
        double result;
        result = new SumHandler().handle(expression, resultCell);
        return result;
    }
}
