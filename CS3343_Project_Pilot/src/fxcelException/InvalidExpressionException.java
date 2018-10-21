package fxcelException;

public class InvalidExpressionException extends FxcelException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8632828627478065351L;
	private static final String TAG = "InvalidExpressionExcept";
    private static final String message = "Syntax error on your input !";

    public InvalidExpressionException() {
        //default
    }
}
