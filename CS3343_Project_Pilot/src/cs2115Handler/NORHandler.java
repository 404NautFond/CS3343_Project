package cs2115Handler;

public class NORHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) {
		return new NOTHandler().handleForDoubleReturn(""+(int)new ORHandler().handleForDoubleReturn(expression));
	}

}