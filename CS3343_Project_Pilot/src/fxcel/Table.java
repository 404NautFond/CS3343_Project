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
		return(result);
	}
	

	public boolean isCellEditable(int row, int col) { return true; }
	public void setValueAt(Object value, int row, int col) {
		ArrayList rowList = (ArrayList) data.get(row);
		
		if (col>=rowList.size()) {
			while (col>=rowList.size()) rowList.add(null);
		}
		rowList.set(col, value);
		fireTableCellUpdated(row, col);
	}

	public void addColumn(String name) {
		names.add(name);
		fireTableStructureChanged();
	}

	public int addRow(ArrayList row) {
		data.add(row);
		fireTableRowsInserted(data.size()-1, data.size()-1);
		return(data.size() -1);
	}
	
	public int addRow() {
		ArrayList row = new ArrayList();
		return(addRow(row));
	}


	public void deleteRow(int row) {
		if (row == -1) return;
		
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}
	
	
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
		if (elem.equals("\t")) row.add("");
		
		return(row);
	}
	
	
	private static void writeStrings(BufferedWriter out, Collection strings) throws IOException {
		Iterator it = strings.iterator();
		
		while (it.hasNext()) {
			String string = (String)it.next();
			if (string!=null) out.write(string);
			if (it.hasNext()) out.write('\t');
		}
		out.newLine();
	}	


	public void loadFile(File file) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			
			ArrayList first = stringToList(in.readLine());
			names = first;
			
			String line;
			data = new ArrayList();
			while ((line = in.readLine()) != null) {
				data.add(stringToList(line));
			}
			
			in.close();
			fireTableStructureChanged();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void saveToFile(File file) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
		 	
		 	writeStrings(out, names);
		 	
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


class FakeTableModel extends AbstractTableModel {
    public static final int SIZE = 10;
    
	public FakeTableModel() {
		super();
	}
	public String getColumnName(int col) {
		return("Column " + col);
	}
	public int getColumnCount() { return(SIZE); }
	public int getRowCount() { return(SIZE); }
	public Object getValueAt(int row, int col) {
	    return("row " + row + " col " + col);
	}	
}