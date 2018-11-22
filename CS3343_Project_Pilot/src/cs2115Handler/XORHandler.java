package cs2115Handler;

public class XORHandler extends LogicHandler{
	@Override
	public double handleForDoubleReturn(String expression) {
		return new NOTHandler().handleForDoubleReturn(""+(int)new XNORHandler().handleForDoubleReturn(expression));
	}
}