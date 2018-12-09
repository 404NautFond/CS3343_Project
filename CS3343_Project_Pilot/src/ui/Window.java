package ui;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Window extends JFrame {
	JTextField text;
    ReaderListen listener;

    public Window() throws HeadlessException {
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    void init() {
        setLayout(new FlowLayout());
        text = new JTextField(10);
        listener = new ReaderListen();
        text.addActionListener(listener);
        add(text);
    }
}

class ReaderListen implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String fileName = e.getActionCommand();
        System.out.println(fileName + "Processed content as follows:");
        try {
            File file = new File(fileName);
            FileReader inOne = new FileReader(file);
            BufferedReader inTwo = new BufferedReader(inOne);
            String s = null;
            while ((s = inTwo.readLine()) != null) {
                System.out.println(s);
            }
            inOne.close();
            inTwo.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
