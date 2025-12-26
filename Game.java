import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    private static boolean xTurn = true;

    public static void main(String[] args) {
        // Setboard
        // add buttons and a background image
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Background bg = new Background();
        frame.setContentPane(bg);

        frame.setLayout(new GridLayout(3, 3, 20, 20));

        // Create 3x3 button array
        JButton[][] buttons = new JButton[3][3];

        // Load and resize image
        ImageIcon icon = new ImageIcon("C:/Aditya/DSA in JAVA/Tic Tac Toe game/wp4292444-wood-texture-wallpapers.jpg"); // ✅
                                                                                                                        // your
                                                                                                                        // image
                                                                                                                        // file
                                                                                                                        // here
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH); // Resize to 100x100
        ImageIcon resizedIcon = new ImageIcon(img);

        // Fill buttons with resized image
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Font font = new Font("DialogInput", Font.BOLD, 60);
                buttons[row][col] = new JButton("");
                buttons[row][col].setIcon(resizedIcon);
                buttons[row][col].setFont(font);
                buttons[row][col].setForeground(new Color(88, 27, 9));
                buttons[row][col].setVerticalTextPosition(SwingConstants.CENTER);
                buttons[row][col].setHorizontalTextPosition(SwingConstants.CENTER);

                frame.add(buttons[row][col]);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        JButton btnclicked = (JButton) e.getSource();
                        if (xTurn) {
                            btnclicked.setText("X"); // Human move
                            xTurn = false;

                            // Check after human move
                            if (checkGameOver(buttons)) {
                                return;
                            }

                            // AI move
                            makeAIMove(buttons);
                            xTurn = true;

                            // Check after AI move
                            checkGameOver(buttons);
                        }

                    }
                });
            }

        }

        frame.setVisible(true);

        // board setted up
        // Now time for game logic

    }

    public static void reset(JButton[][] buttons) {
        xTurn = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    private static boolean isDraw(JButton[][] buttons) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals(""))
                    return false;
        return true;
    }

    private static void makeAIMove(JButton[][] buttons) {
        int[] move = findBestMove(buttons);

        if (move[0] != -1 && move[1] != -1) {
            buttons[move[0]][move[1]].setText("O");
        }
    }

    // Check if there are moves left
    static boolean isMovesLeft(JButton[][] buttons) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals(""))
                    return true;
        return false;
    }

    static int evaluate(JButton[][] buttons) {

        // Rows
        for (int row = 0; row < 3; row++) {
            if (!buttons[row][0].getText().equals("") &&
                    buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                    buttons[row][1].getText().equals(buttons[row][2].getText())) {

                if (buttons[row][0].getText().equals("O"))
                    return +10;
                if (buttons[row][0].getText().equals("X"))
                    return -10;
            }
        }

        // Columns
        for (int col = 0; col < 3; col++) {
            if (!buttons[0][col].getText().equals("") &&
                    buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                    buttons[1][col].getText().equals(buttons[2][col].getText())) {

                if (buttons[0][col].getText().equals("O"))
                    return +10;
                if (buttons[0][col].getText().equals("X"))
                    return -10;
            }
        }

        // Diagonals
        if (!buttons[0][0].getText().equals("") &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) {

            if (buttons[0][0].getText().equals("O"))
                return +10;
            if (buttons[0][0].getText().equals("X"))
                return -10;
        }

        if (!buttons[0][2].getText().equals("") &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText())) {

            if (buttons[0][2].getText().equals("O"))
                return +10;
            if (buttons[0][2].getText().equals("X"))
                return -10;
        }

        return 0;
    }

    static int minimax(JButton[][] buttons, int depth, boolean isMax) {

        int score = evaluate(buttons);

        // Terminal states
        if (score == 10)
            return score - depth; // previously: score-depth
        if (score == -10)
            return score + depth; // previously: score+depth
        if (!isMovesLeft(buttons))
            return 0;

        if (isMax) { // AI's move
            int best = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j].getText().equals("")) {
                        buttons[i][j].setText("O");
                        best = Math.max(best, minimax(buttons, depth + 1, !isMax));
                        buttons[i][j].setText("");
                    }
                }
            }
            return best;
        } else { // Human's move
            int best = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j].getText().equals("")) {
                        buttons[i][j].setText("X");
                        best = Math.min(best, minimax(buttons, depth + 1, !isMax));
                        buttons[i][j].setText("");
                    }
                }
            }
            return best;
        }
    }

    static int[] findBestMove(JButton[][] buttons) {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = { -1, -1 };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    buttons[i][j].setText("O");
                    int moveVal = minimax(buttons, 0, false);
                    buttons[i][j].setText("");

                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    public static boolean checkGameOver(JButton[][] buttons) {
        String winner = null;

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            // Rows
            if (!buttons[i][0].getText().equals("") &&
                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText())) {
                winner = buttons[i][0].getText();
            }

            // Columns
            if (!buttons[0][i].getText().equals("") &&
                    buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText())) {
                winner = buttons[0][i].getText();
            }
        }

        // Diagonals
        if (!buttons[0][0].getText().equals("") &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) {
            winner = buttons[0][0].getText();
        }

        if (!buttons[0][2].getText().equals("") &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText())) {
            winner = buttons[0][2].getText();
        }
        if (winner != null) {
            JOptionPane.showMessageDialog(null, "Player: " + winner + " wins!");
            reset(buttons);
            return true;
        } else if (isDraw(buttons)) {
            JOptionPane.showMessageDialog(null, "Draw!");
            reset(buttons);
            return true;
        }
        return false;
    }

}

class Background extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // clear background

        Image img = Toolkit.getDefaultToolkit()
                .getImage("C:/Aditya/DSA in JAVA/Tic Tac Toe game/wp4292422-wood-texture-wallpapers.jpg");
        g.drawImage(img, 0, 0, 400, 400, this);
    }
}
