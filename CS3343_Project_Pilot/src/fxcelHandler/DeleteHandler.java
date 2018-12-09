/**
 * City University of Hong Kong, Group 22
 * DeleteHandler.java
 * Delete operation for possible UI use.
 */

package fxcelHandler;

import fxcel.Cell;
import fxcel.Fxcel;

public class DeleteHandler extends ExpHandler {

	/**
	 * Delete the cell by the name
	 * @param cellName The name of the cell
	 */
	public static void handle(String cellName) {
		Cell tempCell = Fxcel.getInstance().getCell(cellName);
		tempCell.clear();
	}

}
