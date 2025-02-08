package PBL6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {
    private JFrame frame;
    private JTextField textField;
    private String operator;
    private double num1, num2, result;

    public CalculatorGUI() {
        frame = new JFrame("Calculator"); // Create the main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        textField = new JTextField(); // Create the text field for input and output
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel(); // Create the panel for buttons
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        frame.add(panel, BorderLayout.CENTER);

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text); // Create each button
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(new ButtonClickListener()); // Add action listener to buttons
            panel.add(button);
        }

        frame.setVisible(true); // Make the frame visible
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                textField.setText(textField.getText() + command); // Append number to text field
            } else if (command.equals("C")) {
                textField.setText(""); // Clear the text field
                num1 = num2 = result = 0;
            } else if (command.equals("=")) {
                num2 = Double.parseDouble(textField.getText()); // Parse the second number
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": result = num1 / num2; break;
                }
                textField.setText(String.valueOf(result)); // Display the result
            } else {
                num1 = Double.parseDouble(textField.getText()); // Parse the first number
                operator = command; // Store the operator
                textField.setText(""); // Clear the text field for the next input
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI(); // Create and display the calculator GUI
    }
}
