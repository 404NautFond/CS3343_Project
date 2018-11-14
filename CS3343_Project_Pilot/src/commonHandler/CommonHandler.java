package commonHandler;

import fxcelHandler.FuncHandler;

public abstract class CommonHandler extends FuncHandler {
	/**
	 * For expression with ";"
	 * @return The value of such expression
	 */
	protected abstract double calculateForColumnInput();
	
	/**
	 * For expression with ","
	 * @return The value of such expression
	 */
	protected abstract double calculateForCommaInput();
}
