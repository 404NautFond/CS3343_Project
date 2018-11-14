package fxcelHandler;

import fxcel.Cell;
import fxcel.Fxcel;

public class DeleteHandler extends ExpHandler {

	/**
	 * Delete the cell by the name
	 * @param cellName The name of the cell
	 */
	public void handle(String cellName) {
		Cell tempCell = Fxcel.getInstance().getCell(cellName);
		tempCell.clear();
	}

}
