package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import java.io.File;
import fxcel.Cell;
import fxcel.Fxcel;
import fxcel.Invoker;
import fxcel.Observer;
import fxcel.Subject;
import java.io.*;
import java.awt.event.*;

public class Invoker2 {
	JFileChooser fileDialog;
    JMenuBar menubar;
    JMenu menu;
    JMenuItem itemSave, itemOpen;
    JTextArea text;
    FileReader fileReader;
    JDialog dialog;


    void init() {
        text = new JTextArea(10, 10);
        text.setFont(new Font("Arial", Font.PLAIN, 20));

        menubar = new JMenuBar();
        menu = new JMenu("File");
        itemSave = new JMenuItem("Save changes");

        menu.add(itemSave);
        menu.add(itemOpen);

        menubar.add(menu); 
        fileDialog = new JFileChooser();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemSave) {
        	dialog.setVisible(true);
            String str = dialog.getTitle();
        }
    }
}
