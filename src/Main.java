import javax.swing.*;
import java.awt.*;

/*
 * Ian White
 * 5/15/2017
 * JDK 1.8
 * Program: 
 */
//boogly boo boo hoo hoo
class Main {
    private static final JFrame mFrame = new JFrame("Hangman");
    @SuppressWarnings("FieldCanBeLocal")
    private static Hangman mHangmanGame;
    private static GUI mGui;
    private static JPanel mParentPanel = new JPanel(new GridBagLayout());//Main Panel to hold all subpanels
    private static JPanel mPhrasePanel = new JPanel(),
            mFigurePanel = new JPanel(),
            mAlphabetPanel = new JPanel(),
            mStatsPanel = new JPanel();
    private static JPanel[] mPanels = {mParentPanel, mPhrasePanel, mFigurePanel, mAlphabetPanel, mStatsPanel};

    public static void main(String[] args) {
        boolean shouldPlay;
        do {
            playGame();//TODO: on restart need to clear lines and clear letters
            shouldPlay = contains(JOptionPane.showInputDialog(null, "Do you want to play again")
                    , 'y');
        } while (shouldPlay); //TODO: Uncomment when finished ui
        mFrame.dispose();//should close frame if user does not want to play again
    }

    private static void playGame() {
        mHangmanGame = new Hangman(JOptionPane.showInputDialog(null, "Enter phrase: "));
        mGui = new GUI(mFrame, mPanels, mHangmanGame);
        mHangmanGame.setGUI(mGui);
        while (mHangmanGame.getLives() > 0 && !mHangmanGame.hasWon()) {
            showMessage(mHangmanGame.guess(JOptionPane.showInputDialog(null, "Enter a guess").charAt(0))
                    + "\nYou have " + mHangmanGame.getLives() + " lives remaining");
            mGui.drawCorrectLetters(mHangmanGame.getCorrectLetters());
            if (mHangmanGame.hasWon()) {
                showMessage(mHangmanGame.getWIN_MESSAGE());
                break;
            } else if (mHangmanGame.getLives() < 1) {
                showMessage(mHangmanGame.getNO_LIVES_MESSAGE());
                break;
            }
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
}