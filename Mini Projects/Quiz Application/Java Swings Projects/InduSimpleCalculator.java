import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;

import java.net.URL;
import java.net.MalformedURLException;

import java.util.HashMap;


class InduProps {
    
    //Colors - Updated to white theme
    public static final Color DARK_COLOR = new Color(0xFFFFFFFF);  // White background
    public static final Color SURFACE_COLOR = new Color(0xFFF5F5F5); // Light gray surface
    public static final Color FORE_COLOR = new Color(0xFF333333);   // Dark gray text
    public static final Color DISABLED_COLOR = new Color(0xFFE0E0E0); // Light gray for disabled
    public static final Color RED_COLOR = new Color(0xFFE53935);    // Material Red
    public static final Color YELLOW_COLOR = new Color(0xFFFFB300); // Material Amber
    public static final Color GREEN_COLOR = new Color(0xFF43A047);  // Material Green
    public static final Color BLUE_COLOR = new Color(0xFF1E88E5);   // Material Blue

    //Set whether to see the steps on how the computer executes my algorithm
    public static final boolean SHOW_STEPS = false;

    /**
     * Create custom font with size
     */
    public static Font createFont(int size) {
        return new Font("Arial", Font.PLAIN, size); // Changed to plain for cleaner look
    }

    /**
     * Force capitalized strings
     */
    public static String forceCapitalize(String text) {
        String output = "";
        final String[] WORDS = text.split(" ");

        for (int i = 0; i < WORDS.length; i++) {
            output += String.valueOf(WORDS[i].charAt(0)).toUpperCase() + 
                     WORDS[i].substring(1, WORDS[i].length()).toLowerCase();

            if (i != WORDS.length - 1) {
                output += " ";
            }
        }

        return output;
    }

    /**
     * Reverse a string
     */
    public static String reverseString(String text) {
        return new StringBuilder(text).reverse().toString();
    }
}

class InduInputCalculator {
    private String exp = "";
    private char[] ops = { 'x', '/', '+', '-' };

    public InduInputCalculator(String input) {
        this.exp = input;
    }

    private boolean checkOperation(char ch) {
        for (int i = 0; i < this.ops.length; i++) {
            if (ch == this.ops[i]) {
                return true;
            }
        }
        return false;
    }

    private boolean hasOperator(String input) {
        for (int i = 1; i < input.length(); i++) {
            if (checkOperation(input.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private char getHighestOperator(String input) {
        final int mulIndex = input.indexOf("x", 1);
        final int divIndex = input.indexOf("/", 1);
        final int addIndex = input.indexOf("+", 1);
        final int subIndex = input.indexOf("-", 1);

        if (mulIndex != -1 && divIndex != -1) {
            return mulIndex < divIndex ? 'x' : '/';
        }

        if (mulIndex != -1 && (divIndex == -1 || addIndex == -1 || subIndex == -1)) {
            return 'x';
        }

        if (divIndex != -1 && (mulIndex == -1 || addIndex == -1 || subIndex == -1)) {
            return '/';
        }

        if (addIndex != -1 && subIndex != -1) {
            return addIndex < subIndex ? '+' : '-';
        }

        if (addIndex != -1 && (mulIndex == -1 || divIndex == -1 || subIndex == -1)) {
            return '+';
        }

        if (subIndex != -1 && (mulIndex == -1 || divIndex == -1 || addIndex == -1)) {
            return '-';
        }

        return ' ';
    }

    private HashMap<String, Object> getLROperand(String input, int start) {
        String leftOperand = "";
        String rightOperand = "";
        int startIndex = 0;
        int endIndex = 0;

        // Get left operand
        for (int j = start - 1; j >= 0; j--) {
            if (j - 1 < 0) {
                startIndex = 0;
            } else {
                startIndex = j + 1;
            } 

            char chp = input.charAt(j);
            
            if (checkOperation(chp)) {
                if (chp == '-' || chp == '+') {
                    char cha = j - 1 >= 0 ? input.charAt(j - 1) : ' ';

                    if (!Character.isDigit(cha)) {
                        leftOperand += chp;
                    }
                }
                break;
            }
            leftOperand += chp;
        }

        // Get right operand
        for (int j = start + 1; j < input.length(); j++) {
            if (j + 1 >= input.length()) {
                endIndex = input.length();
            } else {
                endIndex = j;
            } 

            char chp = input.charAt(j);

            if ((chp == '-' || chp == '+') && j == start + 1) {
                rightOperand += chp;
                continue;
            } else if (checkOperation(chp)) {
                break;
            }

            rightOperand += chp;
        }
        
        leftOperand = InduProps.reverseString(leftOperand);

        HashMap<String, Object> output = new HashMap<String, Object>();
        output.put("leftOperand", leftOperand);
        output.put("rightOperand", rightOperand);
        output.put("startIndex", startIndex);
        output.put("endIndex", endIndex);

        return output;
    }
    
    private String evaluate(String input) {
        if (!hasOperator(input)) {
            return input;
        }

        while (hasOperator(input)) {
            char op = getHighestOperator(input);

            for (int j = 0; j < input.length(); j++) {
                char ch = input.charAt(j);

                if ((ch == '-' || ch == '+') && j == 0) {
                    continue;
                }
                
                if (checkOperation(ch) && ch == op) {
                    HashMap<String, Object> val = getLROperand(input, j);
        
                    final String LEFT_OPERAND = (String)val.get("leftOperand");
                    final String RIGHT_OPERAND = (String)val.get("rightOperand");
                    final int START_INDEX = (int)val.get("startIndex");
                    final int END_INDEX = (int)val.get("endIndex");

                    if (InduProps.SHOW_STEPS) {
                        System.out.printf("Operator: %-3c Input: %-20s  Left: %-10s Right: %-10s Evaluated: %s\n", 
                            op, input, LEFT_OPERAND, RIGHT_OPERAND, input.substring(START_INDEX, END_INDEX));
                    }
                    
                    if (LEFT_OPERAND.length() == 0 || RIGHT_OPERAND.length() == 0) {
                        return "E: MALFORMED_EXPRESSION";
                    }
                    
                    try {
                        double leftOperand = Double.parseDouble(LEFT_OPERAND);
                        double rightOperand = Double.parseDouble(RIGHT_OPERAND);
                        double answer = 0.0;
        
                        switch (op) {
                            case 'x': answer = leftOperand * rightOperand; break;
                            case '/': answer = leftOperand / rightOperand; break;
                            case '+': answer = leftOperand + rightOperand; break;
                            case '-': answer = leftOperand - rightOperand; break;
                        }
                        
                        final String evaluatedInput = input.replace(input.substring(START_INDEX, END_INDEX), 
                            String.valueOf(answer));
                        return evaluate(evaluatedInput);
                    } catch (NumberFormatException e) {
                        return "E: " + e.getMessage();
                    }
                }
            }
        }
        return input;
    }

    public String calculate() {
        String FINAL_ANSWER = evaluate(this.exp);
        if (FINAL_ANSWER.endsWith(".0")) {
            FINAL_ANSWER = FINAL_ANSWER.substring(0, FINAL_ANSWER.length() - 2);
        }
        return FINAL_ANSWER;
    }
}

class InduButton extends JButton {
    private Color fgColor = InduProps.FORE_COLOR;

    public InduButton(String text) {
        setText(text);
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(InduProps.DISABLED_COLOR, 1)); // Added border
        setFont(InduProps.createFont(20));
        setCustomEnabled(false);
    }

    public InduButton(String text, Color fgColor) {
        this(text);
        this.fgColor = fgColor;
    }
    
    public void setCustomEnabled(boolean b) {
        setEnabled(b);
        if (b) {
            setBackground(InduProps.SURFACE_COLOR);
            setForeground(this.fgColor);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            setBackground(InduProps.DISABLED_COLOR);
            setForeground(Color.GRAY);
        }
    }
}

public class InduSimpleCalculator {
    private static final Dimension WIN_SIZE = new Dimension(450, 650);
    private static final Border PADDING = BorderFactory.createEmptyBorder(16, 16, 16, 16);
    private static final int POINT_LENGTH = 10;
    private static int mode = 0;
    private static boolean isOn = false;

    private static String convertDecimalToBinary(String dec) {
        if (dec.length() > 0) {
            String leftNum = "";
            String rightNum = "";

            try {
                final String[] splits = dec.split("\\.");
                long num = Long.parseLong(splits.length > 1 ? splits[0] : dec);
    
                while (num > 0) {
                    leftNum += num % 2;
                    num = Math.floorDiv(num, 2);
                }

                if (splits.length == 2) {
                    double pt = Double.parseDouble("0." + splits[1]);
                    int ptLength = 0;

                    while (pt != 0 && pt < 1) {
                        pt *= 2;
                        rightNum += (pt >= 1) ? "1" : "0";
                        if (pt > 1) {
                            pt -= Math.floor(pt);
                        }
                        ptLength++;
                        if (pt == 1.0 || ptLength >= POINT_LENGTH) {
                            break;
                        }
                    }
                } else if (splits.length > 2) {
                    return "-3";
                }
            } catch (NumberFormatException e) {
                return "-2";
            }
            
            if (leftNum.length() > 0 && rightNum.length() > 0) {
                return InduProps.reverseString(leftNum) + "." + rightNum;
            }
            return InduProps.reverseString(leftNum);
        }
        return "-1";
    }

    private static String convertBinaryToDecimal(String bin) {
        if (bin.length() > 0) {
            int leftBin = 0;
            double rightBin = 0;

            for (char b : bin.toCharArray()) {
                if (b != '0' && b != '1' && b != '.') {
                    return "-4";
                }
            }

            final String[] SPLITS = bin.split("\\.");
            final boolean hasFloating = SPLITS.length == 2;
            
            if (SPLITS.length <= 2) {
                try {
                    final int LENGTH = hasFloating ? SPLITS[0].length() : bin.length();
                    final String chToCompute = hasFloating ? SPLITS[0] : bin;
                    
                    for (int i = 1; i <= LENGTH; i++) {
                        if (chToCompute.charAt(i - 1) == '1') {
                            leftBin += (i == LENGTH) ? 1 : (int)Math.pow(2, LENGTH - i);
                        }
                    }

                    if (hasFloating) {
                        final String pt = SPLITS[1];
                        for (int i = 1; i <= pt.length(); i++) {
                            final String ch = String.valueOf(pt.charAt(i - 1));
                            final double ans = Integer.parseInt(ch) * Math.pow(2, -i);
                            rightBin += ans;
                        }
                        String finalPoint = String.valueOf(rightBin).replace("0.", "");
                        return String.valueOf(leftBin) + "." + finalPoint;
                    }
                    return String.valueOf(leftBin);
                } catch (Exception e) {
                    return "-2";
                }
            } else {
                return "-3";
            }
        }
        return "-1";
    }
    
    private static void calculate(JLabel label, JLabel inputPrevious, String input) {
        final String INPUT = input.replaceAll(" ", "");
        InduInputCalculator calculator = new InduInputCalculator(INPUT);
        final String ANSWER = calculator.calculate();

        if (ANSWER.startsWith("E: ")) {
            final String ERROR = InduProps.forceCapitalize(ANSWER.replace("E: ", "").replaceAll("_", " "));
            if (ERROR.contains("For Input String")) {
                inputPrevious.setText("Malformed Expression");
            } else {
                inputPrevious.setText(ERROR);
            }
            inputPrevious.setForeground(InduProps.RED_COLOR);
        } else {
            label.setText(ANSWER);
            inputPrevious.setForeground(InduProps.FORE_COLOR);
        }
    }

    private static int switchMode(JLabel normal, JLabel db, JLabel bd) {
        normal.setForeground(InduProps.FORE_COLOR);
        db.setForeground(InduProps.FORE_COLOR);
        bd.setForeground(InduProps.FORE_COLOR);

        mode++;
        switch (mode % 3) {
            case 0: normal.setForeground(InduProps.BLUE_COLOR); break;
            case 1: db.setForeground(InduProps.BLUE_COLOR); break;
            case 2: bd.setForeground(InduProps.BLUE_COLOR); break;
        }
        return mode % 3;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Indu Calculator");
        frame.setSize(WIN_SIZE);
        frame.setMinimumSize(WIN_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            URL image = new File(System.getProperty("user.dir") + "/indu-icon.png").toURI().toURL();
            if (image != null) {
                frame.setIconImage(new ImageIcon(image).getImage());
            }
        } catch (MalformedURLException e) {}
        
        // Layouts
        BorderLayout panelLayout = new BorderLayout();
        panelLayout.setVgap(16);
        GridLayout inputPanelLayout = new GridLayout(3, 1);
        inputPanelLayout.setVgap(8);
        GridLayout input1PanelLayout = new GridLayout(1, 3);
        GridBagLayout buttonsPanelLayout = new GridBagLayout();

        // Components
        JPanel panel = new JPanel(panelLayout);
        JPanel inputPanel = new JPanel(inputPanelLayout);
        JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
        JPanel input1Panel = new JPanel(input1PanelLayout);
        JPanel input2Panel = new JPanel(new GridLayout(1, 1));
        JPanel input3Panel = new JPanel(new GridLayout(1, 1));
        
        // Labels
        JLabel mode1 = new JLabel("Dec to Bin", SwingConstants.CENTER);
        JLabel mode2 = new JLabel("Bin to Dec", SwingConstants.CENTER);
        JLabel mode3 = new JLabel("Normal", SwingConstants.CENTER);
        JLabel inputLabel = new JLabel(" ", SwingConstants.RIGHT);
        JLabel inputPrevious = new JLabel(" ", SwingConstants.RIGHT);

        // Buttons
        InduButton addButton = new InduButton("+", InduProps.BLUE_COLOR);
        InduButton subButton = new InduButton("-", InduProps.BLUE_COLOR);
        InduButton mulButton = new InduButton("×", InduProps.BLUE_COLOR);
        InduButton divButton = new InduButton("÷", InduProps.BLUE_COLOR);
        InduButton num7Button = new InduButton("7", InduProps.GREEN_COLOR);
        InduButton num8Button = new InduButton("8", InduProps.GREEN_COLOR);
        InduButton num9Button = new InduButton("9", InduProps.GREEN_COLOR);
        InduButton clearButton = new InduButton("C", InduProps.RED_COLOR);
        InduButton num4Button = new InduButton("4", InduProps.GREEN_COLOR);
        InduButton num5Button = new InduButton("5", InduProps.GREEN_COLOR);
        InduButton num6Button = new InduButton("6", InduProps.GREEN_COLOR);
        InduButton switchModeButton = new InduButton("S/M", InduProps.YELLOW_COLOR);
        InduButton num1Button = new InduButton("1", InduProps.GREEN_COLOR);
        InduButton num2Button = new InduButton("2", InduProps.GREEN_COLOR);
        InduButton num3Button = new InduButton("3", InduProps.GREEN_COLOR);
        InduButton powerButton = new InduButton("On");
        InduButton num0Button = new InduButton("0", InduProps.GREEN_COLOR);
        InduButton dotButton = new InduButton(".", InduProps.BLUE_COLOR);
        InduButton equalsButton = new InduButton("=", InduProps.BLUE_COLOR);

        // Styles
        panel.setBorder(PADDING);
        panel.setBackground(InduProps.DARK_COLOR);

        mode1.setForeground(InduProps.FORE_COLOR);
        mode2.setForeground(InduProps.FORE_COLOR);
        mode3.setForeground(InduProps.FORE_COLOR);
        mode1.setFont(InduProps.createFont(14));
        mode2.setFont(InduProps.createFont(14));
        mode3.setFont(InduProps.createFont(14));

        inputPanel.setBorder(PADDING);
        inputPanel.setBackground(InduProps.SURFACE_COLOR);
        inputLabel.setFont(InduProps.createFont(28));
        inputLabel.setForeground(InduProps.FORE_COLOR);
        inputPrevious.setFont(InduProps.createFont(18));
        inputPrevious.setForeground(InduProps.FORE_COLOR);

        input1Panel.setBackground(InduProps.SURFACE_COLOR);
        input2Panel.setBackground(InduProps.SURFACE_COLOR);
        input3Panel.setBackground(InduProps.SURFACE_COLOR);

        buttonsPanel.setBorder(PADDING);
        buttonsPanel.setBackground(InduProps.SURFACE_COLOR);
        
        powerButton.setCustomEnabled(true);
        powerButton.setForeground(InduProps.GREEN_COLOR);

        // Event Listeners
        ActionListener numListener = e -> {
            Object src = e.getSource();
            String toAdd = "";
            
            if (src.equals(num0Button)) toAdd = "0";
            else if (src.equals(num1Button)) toAdd = "1";
            else if (src.equals(num2Button)) toAdd = "2";
            else if (src.equals(num3Button)) toAdd = "3";
            else if (src.equals(num4Button)) toAdd = "4";
            else if (src.equals(num5Button)) toAdd = "5";
            else if (src.equals(num6Button)) toAdd = "6";
            else if (src.equals(num7Button)) toAdd = "7";
            else if (src.equals(num8Button)) toAdd = "8";
            else if (src.equals(num9Button)) toAdd = "9";
            else if (src.equals(dotButton)) toAdd = ".";
            else if (src.equals(addButton)) toAdd = " + ";
            else if (src.equals(subButton)) toAdd = " - ";
            else if (src.equals(mulButton)) toAdd = " × ";
            else if (src.equals(divButton)) toAdd = " ÷ ";

            inputLabel.setText(inputLabel.getText() + toAdd);
        };

        num0Button.addActionListener(numListener);
        num1Button.addActionListener(numListener);
        num2Button.addActionListener(numListener);
        num3Button.addActionListener(numListener);
        num4Button.addActionListener(numListener);
        num5Button.addActionListener(numListener);
        num6Button.addActionListener(numListener);
        num7Button.addActionListener(numListener);
        num8Button.addActionListener(numListener);
        num9Button.addActionListener(numListener);
        dotButton.addActionListener(numListener);
        addButton.addActionListener(numListener);
        subButton.addActionListener(numListener);
        mulButton.addActionListener(numListener);
        divButton.addActionListener(numListener);

        equalsButton.addActionListener(e -> {
            final String EXPRESSION = inputLabel.getText().trim();
            switch (mode % 3) {
                case 0:
                    inputPrevious.setText(EXPRESSION);
                    calculate(inputLabel, inputPrevious, EXPRESSION);
                    break;
                case 1:
                    final String BIN = convertDecimalToBinary(EXPRESSION);
                    switch (BIN) {
                        case "-1": inputPrevious.setText("Please fill-in the field"); break;
                        case "-2": inputPrevious.setText("Please input only numbers"); break;
                        case "-3": inputPrevious.setText("Please input only one dot"); break;
                        default: inputPrevious.setText(BIN);
                    }
                    inputPrevious.setForeground(BIN.startsWith("-") ? InduProps.RED_COLOR : InduProps.FORE_COLOR);
                    break;
                case 2:
                    final String DECIMAL = convertBinaryToDecimal(EXPRESSION);
                    switch (DECIMAL) {
                        case "-1": inputPrevious.setText("Please fill-in the field"); break;
                        case "-2": inputPrevious.setText("An error occurred"); break;
                        case "-3": inputPrevious.setText("Please input only one dot"); break;
                        case "-4": inputPrevious.setText("Please input only 0 and 1"); break;
                        default: inputPrevious.setText(DECIMAL);
                    }
                    inputPrevious.setForeground(DECIMAL.startsWith("-") ? InduProps.RED_COLOR : InduProps.FORE_COLOR);
                    break;
            }
        });

        clearButton.addActionListener(e -> {
            inputLabel.setText(" ");
            inputPrevious.setText(" ");
        });

        switchModeButton.addActionListener(e -> {
            int mode = switchMode(mode3, mode1, mode2);
            inputLabel.setText(" ");
            inputPrevious.setText(" ");

            num2Button.setCustomEnabled(mode != 2);
            num3Button.setCustomEnabled(mode != 2);
            num4Button.setCustomEnabled(mode != 2);
            num5Button.setCustomEnabled(mode != 2);
            num6Button.setCustomEnabled(mode != 2);
            num7Button.setCustomEnabled(mode != 2);
            num8Button.setCustomEnabled(mode != 2);
            num9Button.setCustomEnabled(mode != 2);

            addButton.setCustomEnabled(mode == 0);
            subButton.setCustomEnabled(mode == 0);
            mulButton.setCustomEnabled(mode == 0);
            divButton.setCustomEnabled(mode == 0);
        }); 

        powerButton.addActionListener(e -> {
            isOn = !isOn;
            
            if (!isOn) {
                inputLabel.setText(" ");
                inputPrevious.setText(" ");
                mode = -1;
                switchMode(mode3, mode1, mode2);
                mode3.setForeground(InduProps.FORE_COLOR);
            } else {
                mode3.setForeground(InduProps.BLUE_COLOR);
            }

            num0Button.setCustomEnabled(isOn);
            num1Button.setCustomEnabled(isOn);
            num2Button.setCustomEnabled(isOn);
            num3Button.setCustomEnabled(isOn);
            num4Button.setCustomEnabled(isOn);
            num5Button.setCustomEnabled(isOn);
            num6Button.setCustomEnabled(isOn);
            num7Button.setCustomEnabled(isOn);
            num8Button.setCustomEnabled(isOn);
            num9Button.setCustomEnabled(isOn);

            addButton.setCustomEnabled(isOn);
            subButton.setCustomEnabled(isOn);
            mulButton.setCustomEnabled(isOn);
            divButton.setCustomEnabled(isOn);

            switchModeButton.setCustomEnabled(isOn);
            clearButton.setCustomEnabled(isOn);
            dotButton.setCustomEnabled(isOn);
            equalsButton.setCustomEnabled(isOn);

            powerButton.setText(isOn ? "Off" : "On");
            powerButton.setForeground(isOn ? InduProps.RED_COLOR : InduProps.GREEN_COLOR);
        });

        // Layout Components
        input1Panel.add(mode1);
        input1Panel.add(mode2);
        input1Panel.add(mode3);
        input2Panel.add(inputLabel);
        input3Panel.add(inputPrevious);
        inputPanel.add(input1Panel);
        inputPanel.add(input2Panel);
        inputPanel.add(input3Panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(8, 8, 8, 8);

        // Add buttons to panel
        buttonsPanel.add(addButton, gbc);
        gbc.gridx++; buttonsPanel.add(subButton, gbc);
        gbc.gridx++; buttonsPanel.add(mulButton, gbc);
        gbc.gridx++; buttonsPanel.add(divButton, gbc);

        gbc.gridx = 0; gbc.gridy++;
        buttonsPanel.add(num7Button, gbc);
        gbc.gridx++; buttonsPanel.add(num8Button, gbc);
        gbc.gridx++; buttonsPanel.add(num9Button, gbc);
        gbc.gridx++; buttonsPanel.add(clearButton, gbc);

        gbc.gridx = 0; gbc.gridy++;
        buttonsPanel.add(num4Button, gbc);
        gbc.gridx++; buttonsPanel.add(num5Button, gbc);
        gbc.gridx++; buttonsPanel.add(num6Button, gbc);
        gbc.gridx++; buttonsPanel.add(switchModeButton, gbc);

        gbc.gridx = 0; gbc.gridy++;
        buttonsPanel.add(num1Button, gbc);
        gbc.gridx++; buttonsPanel.add(num2Button, gbc);
        gbc.gridx++; buttonsPanel.add(num3Button, gbc);
        gbc.gridx++; buttonsPanel.add(powerButton, gbc);

        gbc.gridx = 0; gbc.gridy++;
        buttonsPanel.add(num0Button, gbc);
        gbc.gridx++; buttonsPanel.add(dotButton, gbc);
        gbc.gridx++; gbc.gridwidth = 2;
        buttonsPanel.add(equalsButton, gbc);
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }
}