package cs2115Handler;

public class ANDHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression) {
		setInput(expression);
		feed();
		String[] input = getInput().split(",");
		for(String celltext: input) {
			if(isFalseLike(celltext)) return 0;
		}
		return 1;
	}

}
