package fxcelException;

public class InvalidExpressionException extends Exception {

    private static final String TAG = "InvalidExpressionExcept";
    private static final String message = "Syntax error on your input !";

    public InvalidExpressionException() {
        //default
    }
}
