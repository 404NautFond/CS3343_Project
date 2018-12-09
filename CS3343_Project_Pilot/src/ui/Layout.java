package ui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Layout extends JFrame {
	JTextField text;
    JButton button;
    JCheckBox checkBox1, checkBox2, checkBox3;
    JRadioButton s1, s2;
    ButtonGroup group;
    JComboBox<Object> comBox;
    JTextArea area;
    JPasswordField password;

    public Layout() {
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init() {

        add(new JLabel(":"));
        text = new JTextField(10);
        add(text);

        add(new JLabel("Check:"));
        button = new JButton("Yes");
        add(button);

        add(new JLabel("Functions:"));
        checkBox1 = new JCheckBox("CS2115");
        checkBox2 = new JCheckBox("MA2172");
        checkBox3 = new JCheckBox("math functions");
        add(checkBox1);
        add(checkBox2);
        add(checkBox3);

        add(new JLabel("Scope:"));
        group = new ButtonGroup();
        s1 = new JRadioButton("Current boxes");
        s2 = new JRadioButton("All boxes");
        group.add(s1);
        group.add(s2);
        add(s1);
        add(s2);

        add(new JLabel("CS2115:"));
        comBox = new JComboBox<>();
        comBox.addItem("AND");
        comBox.addItem("NAND");
        comBox.addItem("NOR");
        comBox.addItem("NOT");
        comBox.addItem("Octal");
        comBox.addItem("OR");
        comBox.addItem("XNOR");
        comBox.addItem("XOR");
        comBox.addItem("ASCII");
        comBox.addItem("Binary");
        comBox.addItem("Convert");
        comBox.addItem("Grey Coder");
        comBox.addItem("Hexadecimal");
        add(comBox);

        add(new JLabel("text"));
        area = new JTextArea(6, 13);
        add(new JScrollPane(area));
    }
    public static void main(String[] args) {
        Layout win = new Layout();
        win.setBounds(100, 100, 320, 310);
        win.setTitle("Fxcel");
    }
}