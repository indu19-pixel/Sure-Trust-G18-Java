// Keep all your import statements
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.net.URI;

public class QuizApp {
    public static void main(String[] args) {
        new LoginPage();
    }
}

// LOGIN PAGE
class LoginPage extends JFrame implements ActionListener {
    JTextField tfName;
    JButton btnLogin;

    LoginPage() {
        setTitle("Login Page");
        setLayout(null);
        getContentPane().setBackground(new Color(60, 90, 153));

        JLabel heading = new JLabel("Welcome to Quiz App", SwingConstants.CENTER);
        heading.setBounds(50, 30, 400, 40);
        heading.setFont(new Font("Verdana", Font.BOLD, 26));
        heading.setForeground(Color.WHITE);
        add(heading);

        JLabel lblName = new JLabel("Enter Your Name:");
        lblName.setBounds(50, 100, 200, 30);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblName.setForeground(Color.WHITE);
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(230, 100, 200, 30);
        tfName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(tfName);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(180, 160, 120, 40);
        btnLogin.setBackground(new Color(34, 139, 34));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLogin.addActionListener(this);
        add(btnLogin);

        setSize(500, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String name = tfName.getText().trim();
        if (!name.isEmpty()) {
            dispose();
            new RulesPage(name);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter your name.");
        }
    }
}

// RULES PAGE
class RulesPage extends JFrame implements ActionListener {
    String userName;
    JButton btnStart;

    RulesPage(String name) {
        this.userName = name;
        setTitle("Quiz Rules");
        setLayout(null);
        getContentPane().setBackground(new Color(44, 62, 80));

        JLabel lbl = new JLabel("Hello " + name + ", read the rules:");
        lbl.setBounds(50, 20, 500, 30);
        lbl.setFont(new Font("Arial", Font.BOLD, 22));
        lbl.setForeground(Color.WHITE);
        add(lbl);

        JTextArea rules = new JTextArea(
            "1. Each quiz has 5 questions.\n2. Each correct answer earns 1 point.\n3. No negative marking.\n4. 15 seconds per question.\n5. Share your score at the end!"
        );
        rules.setBounds(50, 70, 400, 150);
        rules.setFont(new Font("Consolas", Font.PLAIN, 18));
        rules.setForeground(Color.WHITE);
        rules.setBackground(new Color(52, 73, 94));
        rules.setEditable(false);
        add(rules);

        btnStart = new JButton("Proceed to Course Selection");
        btnStart.setBounds(120, 240, 250, 40);
        btnStart.setBackground(new Color(41, 128, 185));
        btnStart.setForeground(Color.WHITE);
        btnStart.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnStart.addActionListener(this);
        add(btnStart);

        setSize(500, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        dispose();
        new CourseSelectionPage(userName);
    }
}

// COURSE SELECTION PAGE
class CourseSelectionPage extends JFrame implements ActionListener {
    String userName;
    JComboBox<String> cbCourses;
    JButton btnProceed;

    CourseSelectionPage(String name) {
        userName = name;
        setTitle("Select Course");
        setLayout(null);
        getContentPane().setBackground(new Color(230, 126, 34));

        JLabel lbl = new JLabel("Choose a course to start the quiz:");
        lbl.setBounds(50, 40, 400, 30);
        lbl.setFont(new Font("Serif", Font.BOLD, 22));
        lbl.setForeground(Color.WHITE);
        add(lbl);

        String[] courses = {"Java", "Python", "DSA"};
        cbCourses = new JComboBox<>(courses);
        cbCourses.setBounds(50, 90, 300, 35);
        cbCourses.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(cbCourses);

        btnProceed = new JButton("Start Quiz");
        btnProceed.setBounds(100, 150, 200, 40);
        btnProceed.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnProceed.setBackground(Color.BLACK);
        btnProceed.setForeground(Color.WHITE);
        btnProceed.addActionListener(this);
        add(btnProceed);

        setSize(420, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String course = (String) cbCourses.getSelectedItem();
        dispose();
        new QuizPage(userName, course);
    }
}

// QUIZ PAGE
class QuizPage extends JFrame implements ActionListener {
    String userName, course;
    JLabel lblQuestion, lblTimer;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup bg;
    JButton btnNext;
    Timer timer;
    int timeLeft = 15;

    String[][] questions;
    String[] answers;
    int index = 0, score = 0;

    QuizPage(String name, String courseName) {
        userName = name;
        course = courseName;
        setTitle("Quiz - " + courseName);
        setLayout(null);
        getContentPane().setBackground(new Color(39, 174, 96));

        lblQuestion = new JLabel();
        lblQuestion.setBounds(30, 20, 600, 40);
        lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblQuestion.setForeground(Color.WHITE);
        add(lblQuestion);

        lblTimer = new JLabel("Time left: 15s");
        lblTimer.setBounds(500, 20, 140, 30);
        lblTimer.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTimer.setForeground(Color.YELLOW);
        add(lblTimer);

        bg = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBounds(50, 80 + i * 40, 500, 30);
            options[i].setBackground(new Color(39, 174, 96));
            options[i].setForeground(Color.WHITE);
            options[i].setFont(new Font("Tahoma", Font.PLAIN, 18));
            bg.add(options[i]);
            add(options[i]);
        }

        btnNext = new JButton("Next");
        btnNext.setBounds(240, 260, 150, 40);
        btnNext.setBackground(new Color(142, 68, 173));
        btnNext.setForeground(Color.WHITE);
        btnNext.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNext.addActionListener(this);
        add(btnNext);

        loadQuestions(course);
        displayQuestion();

        setSize(700, 370);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    void loadQuestions(String course) {
        switch (course) {
            case "Java":
                questions = new String[][] {
                    {"Java is a:", "Programming Language", "OS", "Compiler", "Debugger"},
                    {"Keyword for inheritance:", "extends", "implement", "super", "this"},
                    {"Java main method name:", "main", "start", "init", "begin"},
                    {"Not Java feature:", "Pointers", "OOP", "Portable", "Secure"},
                    {"JVM stands for:", "Java Virtual Machine", "Java Version Module", "Join Variable Method", "None"}
                };
                answers = new String[] {"Programming Language", "extends", "main", "Pointers", "Java Virtual Machine"};
                break;
            case "Python":
                questions = new String[][] {
                    {"Python is a:", "High-level", "Low-level", "Assembly", "Machine"},
                    {"Comment symbol:", "#", "//", "/* */", "<!-- -->"},
                    {"Print function:", "print()", "echo()", "log()", "write()"},
                    {"Python types:", "List", "Tuple", "Set", "All of these"},
                    {"Extension of Python files:", ".py", ".java", ".cpp", ".html"}
                };
                answers = new String[] {"High-level", "#", "print()", "All of these", ".py"};
                break;
            case "DSA":
                questions = new String[][] {
                    {"LIFO structure:", "Stack", "Queue", "Array", "List"},
                    {"Used in BFS:", "Queue", "Stack", "Tree", "Map"},
                    {"Not sorting algorithm:", "Binary Tree", "Bubble Sort", "Quick Sort", "Merge Sort"},
                    {"LinkedList uses:", "Nodes", "Arrays", "Stacks", "Queues"},
                    {"Not linear structure:", "Tree", "Array", "Queue", "Stack"}
                };
                answers = new String[] {"Stack", "Queue", "Binary Tree", "Nodes", "Tree"};
                break;
        }
    }

    void displayQuestion() {
        if (timer != null) timer.stop();
        timeLeft = 15;
        lblTimer.setText("Time left: 15s");

        bg.clearSelection();
        lblQuestion.setText("Q" + (index + 1) + ": " + questions[index][0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[index][i + 1]);
        }

        btnNext.setText(index == questions.length - 1 ? "Submit" : "Next");

        timer = new Timer(1000, e -> {
            timeLeft--;
            lblTimer.setText("Time left: " + timeLeft + "s");
            if (timeLeft <= 0) {
                timer.stop();
                nextQuestion();
            }
        });
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        timer.stop();
        nextQuestion();
    }

    void nextQuestion() {
        String selected = null;
        for (JRadioButton rb : options) {
            if (rb.isSelected()) {
                selected = rb.getText();
            }
        }

        if (selected != null && selected.equals(answers[index])) {
            score++;
        }

        index++;
        if (index < questions.length) {
            displayQuestion();
        } else {
            dispose();
            new ScorePage(userName, score, course);
        }
    }
}

// SCORE PAGE
class ScorePage extends JFrame {
    ScorePage(String userName, int score, String course) {
        setTitle("Your Score");
        setLayout(null);
        getContentPane().setBackground(new Color(52, 152, 219));

        JLabel lbl = new JLabel("Thank you " + userName + " for taking the " + course + " quiz!");
        lbl.setBounds(40, 30, 500, 30);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 18));
        lbl.setForeground(Color.WHITE);
        add(lbl);

        JLabel lblScore = new JLabel("Your Score: " + score + "/5");
        lblScore.setBounds(40, 70, 300, 30);
        lblScore.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblScore.setForeground(Color.YELLOW);
        add(lblScore);

        JButton btnCopy = new JButton("Copy Score");
        btnCopy.setBounds(40, 120, 140, 30);
        btnCopy.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCopy.setBackground(Color.BLACK);
        btnCopy.setForeground(Color.WHITE);
        btnCopy.addActionListener(e -> {
            String shareText = userName + " scored " + score + "/5 in " + course + " quiz!";
            StringSelection stringSelection = new StringSelection(shareText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            JOptionPane.showMessageDialog(this, "Score copied to clipboard!");
        });
        add(btnCopy);

        JButton btnWhatsapp = new JButton("Share via WhatsApp");
        btnWhatsapp.setBounds(200, 120, 200, 30);
        btnWhatsapp.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnWhatsapp.setBackground(new Color(37, 211, 102));
        btnWhatsapp.setForeground(Color.WHITE);
        btnWhatsapp.addActionListener(e -> {
            try {
                String message = userName + " scored " + score + "/5 in " + course + " quiz!";
                String url = "https://wa.me/?text=" + java.net.URLEncoder.encode(message, "UTF-8");
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Unable to open WhatsApp link.");
                ex.printStackTrace();
            }
        });
        add(btnWhatsapp);

        JButton btnRestart = new JButton("Restart Quiz");
        btnRestart.setBounds(40, 180, 150, 30);
        btnRestart.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnRestart.setBackground(new Color(52, 73, 94));
        btnRestart.setForeground(Color.WHITE);
        btnRestart.addActionListener(e -> {
            dispose();
            new LoginPage();
        });
        add(btnRestart);

        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(220, 180, 100, 30);
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnExit.setBackground(Color.RED);
        btnExit.setForeground(Color.WHITE);
        btnExit.addActionListener(e -> System.exit(0));
        add(btnExit);

        setSize(480, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
