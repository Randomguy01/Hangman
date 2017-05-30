import javax.swing.*;

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

    public static void main(String[] args) {
        boolean shouldPlay;
        do {
            playGame();//TODO: on restart need to clear lines and clear letters
            /*shouldPlay = contains(JOptionPane.showInputDialog(null, "Do you want to play again")
                    , 'y');*/
        } while (false); //TODO: Uncomment when finished ui
        //mFrame.dispose();//should close frame if user does not want to play again
    }

    private static void playGame() {
        mHangmanGame = new Hangman(JOptionPane.showInputDialog(null, "Enter phrase: "));
        mGui = new GUI(mFrame, mHangmanGame);
        mHangmanGame.setGUI(mGui);
        //mGui.drawHangman(0);
        while (mHangmanGame.getLives() > 0 && !mHangmanGame.hasWon() && false) {
            showMessage(mHangmanGame.guess(JOptionPane.showInputDialog(null, "Enter a guess").charAt(0))
                    + "\nYou have " + mHangmanGame.getLives() + " lives remaining");

            mGui.drawCorrectLetters(mHangmanGame.getCorrectLetters());
            mGui.drawStats();
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