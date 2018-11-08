package ma2172Handler;

import fxcelHandler.FuncHandler;

public abstract class MathHandler extends FuncHandler{
	protected static int[] factorial;

	public MathHandler() {
		init();
	}

	public void init() {
		factorial = new int[20];
		factorial[0] = 1;
		for(int i = 1; i < 20; i++) {
			factorial[i] = factorial[i-1]*i;
		}
	}
}
