package fxcelUI;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import fxcel.Fxcel;

public class GraphicFxcel extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -12145162782L;

	private List<List<JTextField>> table;
	private JLabel whereami;
	private JTextField formula;
	private Fxcel instance = Fxcel.getInstance();
	
	private JTextField currentCell;
	
	public static void main(String[] args) {
		GraphicFxcel gf = new GraphicFxcel();
		gf.setBounds(100, 100, 700, 430);
		gf.setTitle("Fxcel");
	}
	
	public GraphicFxcel() {
		this.init();
		this.setVisible(true);
		this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void init() {
		addMenu();
		addBody();
	}

	private void addMenu() {
		JMenuBar panel = new JMenuBar();
		panel.setBounds(0, 0, 700, 30);
		
		JMenu menu;
		JMenuItem item;
		
		/* System Menu Bar */
		
		menu = new JMenu("System");
		item = new JMenuItem("Information");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Group_22@CityU_CS3343_1819A", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menu.add(item);
		
		menu.addSeparator();
		
		item = new JMenuItem("Exit");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int res = JOptionPane.showConfirmDialog(null, "Sure to leave?", "Exit", JOptionPane.OK_CANCEL_OPTION);
				if (res == 0) System.exit(0);
			}
		});
		
		menu.add(item);
		panel.add(menu);
		
		/* Add second menu bar */
		
		menu = new JMenu("Operation");
		
		item = new JMenuItem("Add Row");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String rownumber = JOptionPane.showInputDialog("The new row is row (from 0)", 0);
				try {
					int row = Integer.parseInt(rownumber);
					instance.addRow(row);
					updateUI(currentCell);
					updateFxcel();
				} catch(Exception e) {
					// do nothing
				}
			}
		});
		menu.add(item);
		
		item = new JMenuItem("Add Column");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String colnumber = JOptionPane.showInputDialog("The new column is column (from 0)", 0);
				try {
					int col = Integer.parseInt(colnumber);
					instance.addCol(col);
					updateUI(currentCell);
					updateFxcel();
				} catch(Exception e) {
					// do nothing
				}
			}
		});
		menu.add(item);
		
		menu.addSeparator();
		
		item = new JMenuItem("Delete");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(currentCell != null) {
					instance.getCell(currentCell.getName()).clear();
					updateUI(currentCell);
					updateFxcel();
				}
			}
		});
		menu.add(item);
		
		item = new JMenuItem("Clear");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				instance.clear();
				updateUI(currentCell);
				updateFxcel();
			}
		});
		menu.add(item);
		panel.add(menu);
		
		/* Add dummy ones */
		menu = new JMenu("Common");
		menu.add(new JMenuItem("=SUM()"));
		menu.add(new JMenuItem("=AVE()"));
		menu.add(new JMenuItem("=MAX()"));
		menu.add(new JMenuItem("=MIN()"));
		menu.add(new JMenuItem("=COUNT()"));
		panel.add(menu);
		
		menu = new JMenu("Math");
		menu.add(new JMenuItem("=PERM()"));
		menu.add(new JMenuItem("=COMB()"));
		menu.add(new JMenuItem("=SD()"));
		menu.add(new JMenuItem("=VAR()"));
		menu.add(new JMenuItem("=MEAN()"));
		panel.add(menu);
		
		menu = new JMenu("Convert");
		menu.add(new JMenuItem("=BIN()"));
		menu.add(new JMenuItem("=DEC()"));
		menu.add(new JMenuItem("=OCT()"));
		menu.add(new JMenuItem("=HEX()"));
		menu.add(new JMenuItem("=ASCII()"));
		menu.add(new JMenuItem("=GREY()"));
		panel.add(menu);
		
		menu = new JMenu("Logic");
		menu.add(new JMenuItem("=AND()"));
		menu.add(new JMenuItem("=OR()"));
		menu.add(new JMenuItem("=NOT()"));
		menu.add(new JMenuItem("=XOR()"));
		menu.add(new JMenuItem("=NOR()"));
		menu.add(new JMenuItem("=XNOR()"));
		panel.add(menu);
		
		this.add(panel);
	}
	
	private void setLocation() {
		whereami = new JLabel("");
		whereami.setBounds(25, 60, 60, 30);
	}
	
	private void setFormulaBar() {
		formula = new JTextField();
		formula.setBounds(60, 40, 600, 30);
		formula.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					JTextField bar = (JTextField) arg0.getComponent();
					instance.writeCell(bar.getName(), bar.getText());
					updateFxcel();
				}catch(Exception e) {
					
				}
			}
		});
		formula.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					JTextField bar = (JTextField) arg0.getComponent();
					instance.writeCell(bar.getName(), bar.getText());
					updateFxcel();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
	}
	
	private void addListenerToCell(JTextField cell) {
		cell.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					updateFxcel((JTextField) arg0.getComponent());
					updateUI((JTextField) arg0.getComponent());
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		
		cell.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				updateFxcel((JTextField) arg0.getComponent());
				updateFxcel();
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				updateUI((JTextField) arg0.getComponent());
				currentCell = (JTextField) arg0.getComponent();
			}
		});
	}
	
	private void addBody() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.gray);
		
		JLabel lbl = new JLabel("Fxcel");
		lbl.setBounds(15, 40, 50, 30);
		panel.add(lbl);
		
		table = new ArrayList<List<JTextField>>();
		
		setLocation();
		panel.add(whereami);
		
		setFormulaBar();
		panel.add(formula);
		
		for(int i = 0; i < 10; i++) {
			table.add(new ArrayList<JTextField>());
			JLabel vert = new JLabel(""+(char) ('A'+i));
			JLabel hori = new JLabel(""+(i+1));
			
			vert.setBounds(85+60*i, 60, 60, 30);
			hori.setBounds(30, 90+30*i, 60, 30);
			panel.add(vert);
			panel.add(hori);
			
			for(int j = 0; j < 10; j++) {
				JTextField temp = new JTextField();
				temp.setBounds(60+60*i, 90+30*j, 60, 30);
				temp.setName((char) ('A'+i)+""+(j+1));
				addListenerToCell(temp);
				table.get(i).add(temp);
				panel.add(table.get(i).get(j));
			}
		}
		
		panel.setBounds(0, 30, 600, 300);
		this.add(panel);
	}
	
	public void updateUI(JTextField field) {
		if(field != null) {
			String name = field.getName();
			whereami.setText(name);
			formula.setName(name);
			formula.setText(instance.getCellExpression(name));
		}
	}
	
	public void updateFxcel() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				JTextField temp = table.get(i).get(j);
				temp.setText(instance.getTextualValue(temp.getName()));
			}
		}
	}
	
	public void updateFxcel(JTextField field) {
		String name = field.getName();
		
		if(instance.getTextualValue(name) == null || !instance.getTextualValue(name).equals(field.getText()))
			instance.writeCell(name, field.getText());
		
		field.setText(instance.getTextualValue(name));
	}
	
}
