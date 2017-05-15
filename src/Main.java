import javax.swing.*;
import java.awt.*;

/*
 * Ian White
 * 5/15/2017
 * JDK 1.8
 * Program: 
 */

public class Main extends JComponent {
    private static Hangman mHangmanGame;
    JFrame mFrame = new JFrame();
    Container mContainer = mFrame.getContentPane();

    public static void main(String[] args) {
        Main Main = new Main();
        //Main.setUp();
        mHangmanGame = new Hangman(JOptionPane.showInputDialog(null, "Enter phrase: "));
        while (true){
           showMessage(mHangmanGame.guess(JOptionPane.showInputDialog(null, "Enter a guess").charAt(0)));
        }
    }

    public void setUp() {
        mContainer.setBackground(Color.WHITE);
        mContainer.add(this);

        mFrame.setSize(500, 500);
        mFrame.setLocationRelativeTo(null);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setVisible(true);
    }

    private static void showMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void paintComponent(Graphics g) {

    }
}