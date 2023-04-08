import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class Calculator{

    // --- Declare variables ---
    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;
    ButtonListener listener;

    double num1 = 0, num2 = 0, result = 0;
    char operator = '+';

    // --- instantiate font, colors, and border
    Font font = new Font("Roboto", Font.PLAIN, 30);
    Color bgColor = new Color(40, 40, 40);
    Color textfieldBgColor = new Color(24, 24, 25);
    Color fgColor = new Color(221, 221, 221);
    Color buttonBgColor = new Color(79, 79, 79);
    Border buttonBorder = BorderFactory.createRaisedSoftBevelBorder();

    // --- constructor ---
    Calculator(){

        // --- instantiate the frame ---
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(bgColor);

        // --- instantiate the textfield --- 
        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(font);
        textfield.setEditable(false);
        textfield.setBackground(textfieldBgColor);
        textfield.setForeground(fgColor);
        textfield.setBorder(null);

        listener = new ButtonListener();

        // --- instantiate function buttons --- 
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("[ - ]");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for(JButton button : functionButtons){
            button.addActionListener(listener);
            button.setFont(font);
            button.setFocusable(false);
            button.setBackground(buttonBgColor);
            button.setForeground(fgColor);
            button.setBorder(buttonBorder);
        }

        // negButton.setBounds(50, 430, 100, 50);
        // delButton.setBounds(150, 430, 100, 50);
        // clrButton.setBounds(250, 430, 100, 50);
        negButton.setBounds(50, 430, 95, 50);
        delButton.setBounds(152, 430, 95, 50);
        clrButton.setBounds(254, 430, 96, 50);

        // --- instantiate number buttons --- 
        for(int i = 0; i < 10; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(font);
            numberButtons[i].setFocusable(false);
            numberButtons[i].addActionListener(listener);
            numberButtons[i].setBackground(buttonBgColor);
            numberButtons[i].setForeground(fgColor);
            numberButtons[i].setBorder(buttonBorder);
        }

        // --- instantiate the panel ---
        // This panel is going to contains some of our buttons
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(bgColor);

        // --- adding buttons to panel ---
        for(int i = 7; i < 10; i++)
            panel.add(numberButtons[i]);
        panel.add(divButton);
        for(int i = 4; i < 7; i++)
            panel.add(numberButtons[i]);
        panel.add(mulButton);
        for(int i = 1; i < 4; i++)
            panel.add(numberButtons[i]);
        panel.add(subButton);
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(equButton);
        panel.add(addButton);

        // --- adding our components to frame ---
        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.setVisible(true);
    }

    class ButtonListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){

            if(textfield.getText().equals("Err")){
                textfield.setText("");
            }
            for(int i = 0; i < 10; i++){
                if(e.getSource() == numberButtons[i]){
                    textfield.setText(textfield.getText().concat(String.valueOf(i)));
                }
            }

            if(e.getSource() == decButton){
                if(textfield.getText().length() == 0){}
                else if(textfield.getText().contains(".")){}
                else
                    textfield.setText(textfield.getText().concat("."));
            }

            if(textfield.getText().length() != 0){
                if(e.getSource() == addButton){
                    num1 = Double.parseDouble(textfield.getText());
                    operator = '+';
                    textfield.setText("");
                }
                if(e.getSource() == subButton){
                    num1 = Double.parseDouble(textfield.getText());
                    operator = '-';
                    textfield.setText("");
                }
                if(e.getSource() == mulButton){
                    num1 = Double.parseDouble(textfield.getText());
                    operator = '*';
                    textfield.setText("");
                }
                if(e.getSource() == divButton){
                    num1 = Double.parseDouble(textfield.getText());
                    operator = '/';
                    textfield.setText("");
                }
            }

            if(e.getSource() == equButton){
                if(textfield.getText().length() != 0){
                    num2 = Double.parseDouble(textfield.getText());
                    if(operator == '/' && num2 == 0){
                        textfield.setText("Err");
                    }
                    else{
                        switch(operator){
                            case '+':
                                result = num1 + num2;
                                break;
                            case '-':
                                result = num1 - num2;
                                break;
                            case '*':
                                result = num1 * num2;
                                break;
                            case '/':
                                result = num1 / num2;
                                break;
                        }
                        textfield.setText(String.valueOf(result));
                    }
                }
            }

            if(e.getSource() == clrButton){
                textfield.setText("");
            }

            if(e.getSource() == delButton){
                String str = textfield.getText();
                if(str.length() != 0){
                    str = str.substring(0, str.length()-1);
                    textfield.setText(str);
                }
            }

            if(e.getSource() == negButton){
                if(textfield.getText().length() != 0){
                    double temp = Double.parseDouble(textfield.getText());
                    temp *= -1;
                    textfield.setText(String.valueOf(temp));
                }
            }
        }
    }

    public static void main(String[] args){
        new Calculator();
    }
}

