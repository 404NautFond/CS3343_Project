package fxcelUI;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;
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
	
	public static void main(String[] args) {
		GraphicFxcel gf = new GraphicFxcel();
		gf.setBounds(100, 100, 700, 400);
		gf.setTitle("Fxcel");
	}
	
	public GraphicFxcel() {
		this.init();
		this.setVisible(true);
		this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void init() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.gray);
		
		table = new ArrayList<List<JTextField>>();
		
		whereami = new JLabel("");
		whereami.setBounds(25, 30, 60, 30);
		panel.add(whereami);
		
		formula = new JTextField();
		formula.setBounds(60, 10, 600, 30);
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
		panel.add(formula);
		
		for(int i = 0; i < 10; i++) {
			table.add(new ArrayList<JTextField>());
			JLabel vert = new JLabel(""+(char) ('A'+i));
			JLabel hori = new JLabel(""+(i+1));
			
			vert.setBounds(85+60*i, 30, 60, 30);
			hori.setBounds(30, 60+30*i, 60, 30);
			panel.add(vert);
			panel.add(hori);
			
			for(int j = 0; j < 10; j++) {
				JTextField temp = new JTextField();
				temp.setBounds(60+60*i, 60+30*j, 60, 30);
				temp.setName((char) ('A'+i)+""+(j+1));
				
				temp.addKeyListener(new KeyListener() {

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
				
				temp.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent arg0) {
						updateFxcel((JTextField) arg0.getComponent());
						updateFxcel();
					}
					
					@Override
					public void focusGained(FocusEvent arg0) {
						updateUI((JTextField) arg0.getComponent());
					}
				});
				table.get(i).add(temp);
				panel.add(table.get(i).get(j));
			}
		}
		
		panel.setBounds(0, 0, 600, 300);
		this.add(panel);
	}
	
	public void updateUI(JTextField field) {
		String name = field.getName();
		whereami.setText(name);
		formula.setName(name);
		formula.setText(instance.getCellExpression(name));
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
