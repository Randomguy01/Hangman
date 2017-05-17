import javax.swing.*;
import java.awt.*;

/*
 * Ian White
 * 5/15/2017
 * JDK 1.8
 * Program: 
 */
//boogly boo
public class Main extends JComponent {
    private static Hangman mHangmanGame;
    JFrame mFrame = new JFrame();
    Container mContainer = mFrame.getContentPane();
    Container mPhraseContainer = new Container();

    public static void main(String[] args) {
        Main Main = new Main();
        Main.setUp();
        boolean shouldPlay = true;
        do {
            playGame();
            shouldPlay = false;
            contains("Hi", 'g');
            if (contains(JOptionPane.showInputDialog(null, "Do you want to play again")
                    , 'y')) {
                shouldPlay = true;
            }
        }while (shouldPlay);

    }

    private static void playGame(){
        mHangmanGame = new Hangman(JOptionPane.showInputDialog(null, "Enter phrase: "));
        while (mHangmanGame.getLives() > 0 && !mHangmanGame.hasWon()) {
            showMessage(mHangmanGame.guess(JOptionPane.showInputDialog(null, "Enter a guess").charAt(0))
                    + "\nYou have " + mHangmanGame.getLives() + " lives remaining");
        }
        if (mHangmanGame.hasWon()) {
            showMessage(mHangmanGame.getWIN_MESSAGE());
        } else if (mHangmanGame.getLives() < 1) {
            showMessage(mHangmanGame.getNO_LIVES_MESSAGE());
        }
    }
    private static boolean contains(String string, char character){
        char[] stringArray = string.toLowerCase().toCharArray();
        for (char arrayChar : stringArray){
            if (arrayChar == character)
                return true;
        }
        return false;
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