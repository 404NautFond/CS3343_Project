package ui;

import java.awt.EventQueue;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import fxcel.Fxcel;

public class cc extends JFrame {
	JTextField txt1, txt;
	JButton add, del, clear, export, help;
	
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
	
		public void run() {
		    cc frame = new cc();
		    frame.setVisible(true);
		}
		});
	}
	
	
	public cc() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setBounds(100, 100, 580, 242);
		
		setTitle("Fxcel");
		getContentPane().setLayout(null);
		txt1 = new JTextField(8);
		txt = new JTextField(8);
		add = new JButton("Add Row");
		del = new JButton("Delete Row");
		help = new JButton("Help");
		
		clear = new JButton("Clear All");
		export = new JButton("Export Computation result to .txt");
		
		
		JLabel label = new JLabel("F(x):");
		
		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(140, 160, 700, 200);
		
		scrollPane.add(txt);
		scrollPane.add(txt1);
		scrollPane.add(add);
		scrollPane.add(del);
		scrollPane.add(clear);
		scrollPane.add(export);
		
		txt.setBounds(140, 140, 55, 25);
		txt1.setBounds(234, 120, 120, 25);
		add.setBounds(360, 120, 150, 25);
		del.setBounds(520, 120, 150, 25);
		clear.setBounds(680, 120, 150, 25);
		clear.setBackground(Color.BLACK);
		clear.setForeground(Color.WHITE);
		help.setBounds(680, 90, 150, 25);
		help.setBackground(Color.BLUE);
		help.setForeground(Color.WHITE);
		label.setBounds(200, 105, 55, 55);
		
		export.setBounds(380, 380, 270, 50);

		getContentPane().add(export);

		
		getContentPane().add(scrollPane);
		getContentPane().add(txt);
		getContentPane().add(txt1);
		getContentPane().add(add);
		getContentPane().add(del);
		getContentPane().add(clear);
		getContentPane().add(help);
		getContentPane().add(label);
		
		final JTable table = new JTable();
		scrollPane.setViewportView(table);
		
		DefaultTableModel model = new DefaultTableModel() {
		public Class<?> getColumnClass(int column) {

		    switch (column) {
		    case 0:
		    	return Boolean.class;
		    case 1:
		    	return String.class;
		    case 2:
		    	return String.class;
		    case 3:
		    	return String.class;
		    case 4:
		    	return String.class;
		    case 5:
		    	return String.class;
		    case 6:
		    	return String.class;
		    default:
		    	return String.class;
		}

		}
		};
		table.setModel(model);
        
		
		model.addColumn("Export?");
		model.addColumn(" ");
		model.addColumn("A");
		model.addColumn("B");
		model.addColumn("C");
		model.addColumn("D");
		model.addColumn("E");
		model.addColumn("F");
		model.addColumn("G");
		model.addColumn("H");
		model.addColumn("I");
		model.addColumn("J");
		model.addColumn("K");

		for (int i = 0; i <= 10; i++) {
		    model.addRow(new Object[0]);
		    model.setValueAt(false, i, 0);
    		model.setValueAt((i+1), i, 1);
		}
		
		String[] dataItem = {"XOR", "AND", "NOR", "NAND", "OR","XNOR", "Octal", "Hexadecimal", "Logical", "GreyCoder", "Decimal", "Binary", "Combination", "Mean", "Permutation", "StandDevi", "Variance"};
		JButton btnGetRowSelected = new JButton("Export computation as .TXT");

		
	}
	
}
