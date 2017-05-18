import javax.swing.*;
import java.awt.*;

/*
 * Ian White
 * 5/15/2017
 * JDK 1.8
 * Program: 
 */
//boogly boo boo hoo hoo
public class Main extends JComponent {
    @SuppressWarnings("FieldCanBeLocal")
    private static Hangman mHangmanGame;
    private final JFrame mFrame = new JFrame();
    private final Container mContainer = mFrame.getContentPane();
    JPanel mParentPanel = new JPanel(new GridBagLayout());//Main Panel to hold all subpanels
    JPanel mPhrasePanel, mFigurePanel, mAlphabetPanel, mStatsPanel = new JPanel();

    public static void main(String[] args) {
        Main Main = new Main();
        Main.setUp();
        boolean shouldPlay;
        do {
            playGame();
            shouldPlay = false;
            contains("Hi", 'g');
            if (contains(JOptionPane.showInputDialog(null, "Do you want to play again")
                    , 'y')) {
                shouldPlay = true;
            }
        } while (shouldPlay);

    }

    private static void playGame() {
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

    private static boolean contains(String string, char character) {
        char[] stringArray = string.toLowerCase().toCharArray();
        for (char arrayChar : stringArray) {
            if (arrayChar == character)
                return true;
        }
        return false;
    }

    private static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void setUp() {


        mContainer.add(this);
        mFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mFrame.setUndecorated(true);
        mFrame.setLocationRelativeTo(null);
        mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mFrame.setVisible(true);
        mFrame.add(mContainer);
    }

    @Override
    public void paintComponent(Graphics g) {

    }

    public void drawCharacterSlots(Graphics g, String mPhrase, char correctLetters[]) {
        //TODO: use for loop to draw apropriate lines
    }
}