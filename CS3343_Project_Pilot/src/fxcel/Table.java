package fxcel;

import java.awt.*;
import javax.swing.*;

import javax.swing.table.*;

import java.util.*;
import java.io.*;


class BasicTableModel extends AbstractTableModel {
	private ArrayList names;	
	private ArrayList data;
	
	
	public BasicTableModel() {
		super();
		names = new ArrayList();
		data = new ArrayList();
	}


	// Basic Model overrides
	public String getColumnName(int col) {
		return (String) names.get(col);
	}
	public int getColumnCount() { return(names.size()); }
	public int getRowCount() { return(data.size()); }
	public Object getValueAt(int row, int col) {
		ArrayList rowList = (ArrayList) data.get(row);
		String result = null;
		if (col<rowList.size()) {
			result = (String)rowList.get(col);
		}
		
		// _apparently_ it's ok to return null for a "blank" cell
		return(result);
	}
	
	
	// Support writing
	public boolean isCellEditable(int row, int col) { return true; }
	public void setValueAt(Object value, int row, int col) {
		ArrayList rowList = (ArrayList) data.get(row);
		
		// make this row long enough
		if (col>=rowList.size()) {
			while (col>=rowList.size()) rowList.add(null);
		}

		rowList.set(col, value);
		
		// notify model listeners of cell change
		fireTableCellUpdated(row, col);
	}

	// Adds the given column to the right hand side of the model
	public void addColumn(String name) {
		names.add(name);
		fireTableStructureChanged();
		/*
		 At present, TableModelListener does not have a more specific
		 notification for changing the number of columns.
		*/
	}

	// Adds the given row, returns the new row index
	public int addRow(ArrayList row) {
		data.add(row);
		fireTableRowsInserted(data.size()-1, data.size()-1);
		return(data.size() -1);
	}
	
	// Adds an empty row, returns the new row index
	public int addRow() {
		// Create a new row with nothing in it
		ArrayList row = new ArrayList();
		return(addRow(row));
	}


	// Deletes the given row
	public void deleteRow(int row) {
		if (row == -1) return;
		
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}
	
	
	/*
	 Utility.
	 Given a text line of tab-delimited strings, build
	 an ArrayList of the strings.
	*/
	private static ArrayList stringToList(String string) {
		StringTokenizer tokenizer = new StringTokenizer(string, "\t", true);
		ArrayList row = new ArrayList();
		String elem = null;
		String last = null;
		while(tokenizer.hasMoreTokens()) {
			last = elem;
			elem = tokenizer.nextToken();
			if (!elem.equals("\t")) row.add(elem);
			else if (last.equals("\t")) row.add("");
		}
		if (elem.equals("\t")) row.add(""); // tricky: notice final element
		
		return(row);
	}
	
	/*
	 Utility
	 Given a collection of strings, writes them out as a line of text, separated by tabs.
	 Null strings are interpreted as a zero-length strings.
	*/
	private static void writeStrings(BufferedWriter out, Collection strings) throws IOException {
		Iterator it = strings.iterator();
		
		while (it.hasNext()) {
			String string = (String)it.next();
			if (string!=null) out.write(string);
			if (it.hasNext()) out.write('\t');
		}
		out.newLine();
	}	

	/*
	 Loads the whole model from a file of tab-delimited text.
	*/
	public void loadFile(File file) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			
			// read the column names
			ArrayList first = stringToList(in.readLine());
			names = first;
			
			// each line makes a row in the data model
			String line;
			data = new ArrayList();
			while ((line = in.readLine()) != null) {
				data.add(stringToList(line));
			}
			
			in.close();
			
			// Send notifications that the whole table is now different
			fireTableStructureChanged();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/*
	 Saves the model to the given file as tab-delimited text.
	*/
	public void saveToFile(File file) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
		 	
		 	// write the column names
		 	writeStrings(out, names);
		 	
		 	// write all the data
		 	for (int i=0; i<data.size(); i++) {
		 		writeStrings(out, (ArrayList) data.get(i));
		 	}
		 	
		 	out.close();
		 }
		 catch (IOException e) {
		 	e.printStackTrace();
		 }
	}
	 	
}



/*
 A little example of a TableModel that appears
 to contain by responding to the basic TableModel messages.
 Each cell appears to have the value "row 5 col 6".
*/
class FakeTableModel extends AbstractTableModel {
    public static final int SIZE = 10;
    
	public FakeTableModel() {
		super();
	}


	// Basic Model overrides
	public String getColumnName(int col) {
		return("Column " + col);
	}
	public int getColumnCount() { return(SIZE); }
	public int getRowCount() { return(SIZE); }
	public Object getValueAt(int row, int col) {
	    return("row " + row + " col " + col);
	}
	
	// False is actually the default, so the following is not necessary...
	//public boolean isCellEditable(int row, int col) { return false; }
}