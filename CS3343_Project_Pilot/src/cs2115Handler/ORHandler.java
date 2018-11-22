package cs2115Handler;

public class ORHandler extends LogicHandler{

	@Override
	public double handleForDoubleReturn(String expression){
		setInput(expression);
		feed();
		String[] input = getInput().split(",");
		for(String celltext: input) {
			if(isTrueLike(celltext)) return 1;
		}
		return 0;
	}

}