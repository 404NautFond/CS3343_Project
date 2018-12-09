/**
 * City University of Hong Kong, Group 22
 * MathHandler.java
 * Abstract handler class with overridden return <em>value</em> method
 */

package ma2172Handler;

import fxcelHandler.FuncHandler;

public abstract class MathHandler extends FuncHandler{
	protected static int[] factorial;
	
	/**
	 * Constructor of MathHandler, which will call a computation of factorial
	 */
	public MathHandler() {
		init();
	}

	/**
	 * Compute the first 20 factorial (from 0 to 19)
	 */
	private void init() {
		factorial = new int[20];
		factorial[0] = 1;
		for(int i = 1; i < 20; i++) {
			factorial[i] = factorial[i-1]*i;
		}
	}
}
