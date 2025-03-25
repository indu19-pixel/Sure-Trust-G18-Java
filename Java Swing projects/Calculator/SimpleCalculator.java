import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {
    
    private JTextField textField;

    
    public SimpleCalculator() {
        
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        textField = new JTextField();
        add(textField, BorderLayout.NORTH);

       
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            textField.setText(textField.getText() + command);
        } 
        
        else if (command.equals("C")) {
            textField.setText("");
        } 
       
        else if (command.equals("=")) {
            try {
                String[] tokens = textField.getText().split(" ");
                double num1 = Double.parseDouble(tokens[0]);
                double num2 = Double.parseDouble(tokens[2]);
                String operator = tokens[1];
                double result = 0;

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                }
                textField.setText(String.valueOf(result));
            } catch (Exception ex) {
                textField.setText("Error");
            }
        } 
        else {
            textField.setText(textField.getText() + " " + command + " ");
        }
    }
}