/**
 * City University of Hong Kong, Group 22
 * FxcelException.java
 * Dummy FxcelException class. Will not be checked as extending runtime exceptions.
 */

package fxcelException;

public abstract class FxcelException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7292606047255785661L;

	@Override
	public String toString() {
		return "FxcelException by Group 22";
	}
}
