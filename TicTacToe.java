import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    JFrame frame = new JFrame("XO Game - Mohamed Nader");
    JButton[] buttons = new JButton[9];
    boolean xTurn = true; // عشان نبدل الأدوار

    public TicTacToe() {
        // إعدادات الشاشة
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3)); // شبكة 3x3

        // إنشاء الزراير
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 100));
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(Color.WHITE);

            // الحدث لما تضغط بالماوس
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    if (clickedButton.getText().equals("")) { // لو الزرار فاضي
                        if (xTurn) {
                            clickedButton.setText("X");
                            clickedButton.setForeground(Color.RED);
                        } else {
                            clickedButton.setText("O");
                            clickedButton.setForeground(Color.BLUE);
                        }
                        xTurn = !xTurn; // تبديل الدور
                        checkWin(); // فحص الفوز
                    }
                }
            });
            frame.add(buttons[i]);
        }
        frame.setVisible(true);
    }

    // منطق تحديد الفايز (مصفوفة ثنائية الأبعاد لمسارات الفوز)
    private void checkWin() {
        int[][] winningPositions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // الصفوف
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // الأعمدة
                {0, 4, 8}, {2, 4, 6}             // الأقطار
        };

        for (int[] pos : winningPositions) {
            String b1 = buttons[pos[0]].getText();
            String b2 = buttons[pos[1]].getText();
            String b3 = buttons[pos[2]].getText();

            if (!b1.equals("") && b1.equals(b2) && b2.equals(b3)) {
                JOptionPane.showMessageDialog(frame, "Player " + b1 + " Wins! 🏆\nDeveloped by Mohamed Nader");
                resetGame();
                return;
            }
        }

        // فحص التعادل
        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                draw = false;
                break;
            }
        }
        if (draw) {
            JOptionPane.showMessageDialog(frame, "It's a Draw! 🤝");
            resetGame();
        }
    }

    // تصفير اللعبة بعد الفوز أو التعادل
    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}