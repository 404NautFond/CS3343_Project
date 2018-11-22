package commonHandler;

public class AverageHandler extends SumHandler {
	@Override
	public double handleForDoubleReturn(String expression){
		double sum = (new SumHandler()).handleForDoubleReturn(expression);
		double num = (new CountHandler()).handleForDoubleReturn(expression);
		return sum/num;
	}
}
