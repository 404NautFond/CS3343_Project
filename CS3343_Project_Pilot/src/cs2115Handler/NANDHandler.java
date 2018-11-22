package cs2115Handler;

public class NANDHandler extends LogicHandler {

	@Override
	public double handleForDoubleReturn(String expression) {
		return new NOTHandler().handleForDoubleReturn(""+(int)new ANDHandler().handleForDoubleReturn(expression));
	}

}
