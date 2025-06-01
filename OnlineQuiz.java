
package onlinequiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class OnlineQuiz extends JFrame implements ActionListener {
    JLabel questionLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup bg;
    JButton nextButton, submitButton;
    int count = 0, score = 0;

    String[][] questions = {
            {"Which company developed Java?", "Microsoft", "Sun Microsystems", "Apple", "Oracle", "2"},
            {"Which keyword is used to inherit a class in Java?", "implement", "inherits", "extends", "interface", "3"},
            {"Which of these is a valid datatype in Java?", "num", "int", "integer", "decimal", "2"},
            {"Java is a _________ language.", "Compiled", "Interpreted", "Both", "None", "3"},
            {"Which method is used to start a thread in Java?", "start()", "run()", "execute()", "begin()", "1"}
    };

    OnlineQuiz() {
        setTitle("Online Quiz Application");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Java Quiz", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setOpaque(true);
        header.setBackground(new Color(52, 152, 219));
        header.setForeground(Color.white);
        header.setPreferredSize(new Dimension(700, 60));
        add(header, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        centerPanel.setBackground(Color.white);

        questionLabel = new JLabel("Question");
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(questionLabel);

        bg = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 16));
            bg.add(options[i]);
            centerPanel.add(options[i]);
        }

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.white);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 16));
        nextButton.setBackground(new Color(46, 204, 113));
        nextButton.setForeground(Color.white);
        nextButton.setFocusPainted(false);
        nextButton.addActionListener(this);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(231, 76, 60));
        submitButton.setForeground(Color.white);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(this);
        submitButton.setEnabled(false);

        bottomPanel.add(nextButton);
        bottomPanel.add(submitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        loadQuestion(count);

        setVisible(true);
    }

    public void loadQuestion(int index) {
        bg.clearSelection();
        questionLabel.setText("Q" + (index + 1) + ": " + questions[index][0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[index][i + 1]);
        }
    }

    public boolean checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                return (i + 1) == Integer.parseInt(questions[count][5]);
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (checkAnswer()) score++;

            count++;
            if (count == questions.length - 1) {
                nextButton.setEnabled(false);
                submitButton.setEnabled(true);
            }
            if (count < questions.length) {
                loadQuestion(count);
            }
        }

        if (e.getSource() == submitButton) {
            if (checkAnswer()) score++;
            JOptionPane.showMessageDialog(this, "Your Score: " + score + "/" + questions.length);
            System.exit(0);
        }
    }

   public static void main(String[] args) {
    System.out.println("Launching GUI...");
    SwingUtilities.invokeLater(OnlineQuiz::new);
}

}

