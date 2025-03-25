import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;

public class AgeCalculator extends JFrame implements ActionListener {
    
    private JTextField birthDateField;
    private JButton calculateButton;
    private JLabel resultLabel;

 
    public AgeCalculator() {
        
        setTitle("Age Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

       
        JLabel birthDateLabel = new JLabel("Enter your birth date (YYYY-MM-DD):");
        birthDateField = new JTextField(10);
        calculateButton = new JButton("Calculate Age");
        resultLabel = new JLabel("");

        calculateButton.addActionListener(this);

        add(birthDateLabel);
        add(birthDateField);
        add(calculateButton);
        add(resultLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AgeCalculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String birthDateString = birthDateField.getText();

        try {
          
            LocalDate birthDate = LocalDate.parse(birthDateString);
            LocalDate currentDate = LocalDate.now();

            
            Period age = Period.between(birthDate, currentDate);
            int years = age.getYears();
            int months = age.getMonths();
            int days = age.getDays();

            
            resultLabel.setText("Age: " + years + " years, " + months + " months, " + days + " days");
        } catch (Exception ex) {
            resultLabel.setText("Invalid date format! Use YYYY-MM-DD.");
        }
    }
}