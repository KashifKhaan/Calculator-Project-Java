import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.awt.Color.BLUE;
public class Calculator implements ActionListener{
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10]; // 1 - 9 and 0
    JButton[] functionButtons = new JButton[8];
    JButton addBtn, subBtn, mulBtn, divBtn;
    JButton decBtn, equBtn, delBtn, clrBtn;
    JPanel panel;
    JButton save;
    // defining font
    Font myFont = new Font("Ink Free",Font.BOLD,30);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    // constructor
    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 650);
//        frame.setResizable(false);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false); // we can't type now

        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        decBtn = new JButton(".");
        equBtn = new JButton("=");
        delBtn = new JButton("Delete");
        clrBtn = new JButton("Clear");

        // add all of the btn to our array functionbtn;
        functionButtons[0] = addBtn;
        functionButtons[0].setBorder(BorderFactory.createLineBorder(BLUE, 2, true));
        functionButtons[1] = subBtn;
        functionButtons[2] = mulBtn;
        functionButtons[3] = divBtn;
        functionButtons[4] = decBtn;
        functionButtons[5] = equBtn;
        functionButtons[6] = delBtn;
        functionButtons[7] = clrBtn;
        // use for loop to iterate over all
        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setForeground(BLUE);
            functionButtons[i].setBackground(Color.LIGHT_GRAY);
            functionButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setForeground(BLUE);
            numberButtons[i].setBackground(Color.LIGHT_GRAY);
        }

        delBtn.setBounds(50, 430, 145, 50);
        clrBtn.setBounds(205, 430, 145, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.GRAY);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addBtn);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subBtn);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulBtn);
        panel.add(decBtn);
        panel.add(numberButtons[0]);
        panel.add(equBtn);
        panel.add(divBtn);
        frame.add(panel);
        frame.add(delBtn);
        frame.add(clrBtn);

        frame.add(textField);

        // wirting to a notepad file
        try {
            File file = new File("Result.txt");
            if (file.createNewFile()) {
                System.out.println("File Created Successfully");
                System.out.println("File Name: " + file.getName());
            } else {
                System.out.println("File Already Exist");
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }

        /// getting value from textfield which is at index 1
        final String[] y = new String[1];
        save = new JButton("Save");
        save.setBounds(60,540,160,40);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y[0] = (textField.getText().toString());
            }
        });
        frame.add(save);

        try {
            FileWriter write = new FileWriter("Result.txt");
            write.write(String.valueOf(y));
            write.close();
            System.out.println("Record Wrote Successfully");
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator cl = new Calculator();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        for (int i = 0; i < 10; i++){
            if (e.getSource() == numberButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decBtn){
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource() == addBtn){
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subBtn){
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == mulBtn){
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divBtn){
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if (e.getSource() == equBtn){
            num2 = Double.parseDouble(textField.getText());

            // or use the custom switch this is the enhanced version
            switch (operator) {
                case '+' -> result = (num1 + num2);
                case '-' -> result = (num1 - num2);
                case '*' -> result = (num1 * num2);
                case '/' -> result = (num1 / num2);
            }
            textField.setText(String.valueOf(result));
            num1 = result;
        }
        if (e.getSource() == clrBtn){
            textField.setText(null);
        }
        if (e.getSource() == delBtn){
            String string = textField.getText();
            textField.setText("");
            for (int i = 0; i < string.length()-1; i++){
                textField.setText(textField.getText() + string.charAt(i));
            }
        }
    }
}
