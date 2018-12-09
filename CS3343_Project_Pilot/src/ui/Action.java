package ui;

import javax.swing.event.*;
import javax.swing.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// input monitor
public class Action extends JFrame {
	JTextArea inputText, showText;

    public void setInputText(JTextArea text) {
        inputText = text;
    }

    public void setShowText(JTextArea text) {
        showText = text;
    }

    public void removeUpdate(JTextArea e) {
        changedUpdate(e);
    }

    private void changedUpdate(JTextArea e) {
    	String str = inputText.getText();
	}

	public void insertUpdate(JTextArea e) {
        changedUpdate(e);
    }
}