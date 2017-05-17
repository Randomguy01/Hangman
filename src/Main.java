import com.sun.java.accessibility.util.TopLevelWindowListener;

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
    Container mPhraseContainer = new Container();

    public static void main(String[] args) {
        Main Main = new Main();
        Main.setUp();
        //newHangmanGame();
        //TODO: Add ability to play again
    }

    private static void newHangmanGame(){
        mHangmanGame = new Hangman(JOptionPane.showInputDialog(null, "Enter phrase: "));
        while (mHangmanGame.getLives() > 0){
            showMessage(mHangmanGame.guess(JOptionPane.showInputDialog(null, "Enter a guess").charAt(0))
                    + "\nYou have " + mHangmanGame.getLives() + " lives remaining");
        }
    }

    public void setUp() {
        mContainer.setBackground(Color.WHITE);
        mContainer.add(this);
        this.add(mPhraseContainer);
        mFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mFrame.setUndecorated(true);
        mFrame.setLocationRelativeTo(null);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setVisible(true);
    }

    private static void showMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void paintComponent(Graphics g) {
        mPhraseContainer.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height/4);
        mPhraseContainer.setBackground(Color.BLACK);
    }
    public void drawCharacterSlots(Graphics g, String mPhrase, char correctLetters[]){
    }
}