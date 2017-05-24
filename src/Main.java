import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
 * Ian White
 * 5/15/2017
 * JDK 1.8
 * Program: 
 */
//boogly boo boo hoo hoo
public class Main {
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
    private final Container mContainer = mFrame.getContentPane();
    private GridBagConstraints mGridBagConstraints = new GridBagConstraints();

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
        mGui.drawAlphabet();
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

    private static void drawCharacterSlots(Graphics g, String phrase) {
        final char[] phraseArray = phrase.toCharArray();
        g.setColor(Color.WHITE);
        for (int i = 0; i < phrase.length(); i++) {
            if (phraseArray[i] != ' ')
                g.drawLine((i * 50) + 20, 50, (i * 50) + 50, 50);
        }
    }

    private static void drawCorrectLetters(Graphics g, String phrase, ArrayList<Character> correctLetters) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        final char[] phraseArray = phrase.toLowerCase().toCharArray();
        for (int i = 0, x = 0; i < phrase.length() && x < correctLetters.size(); i++) {
            if (phraseArray[i] == correctLetters.get(x)) {
                for(int y = 0; y < getAllPositions(correctLetters.get(x)).size(); y++){
                    int something = getAllPositions(correctLetters.get(x).toString().charAt(0)).get(y);
                    g.drawString(correctLetters.get(x).toString().toUpperCase(), ((something) * 50) + 30, 40);
                }
                x++;
            }
        }
    }

    private static ArrayList<Integer> getAllPositions(char letter) {
        ArrayList<Integer> positions = new ArrayList<>();
        char[] phraseArray = mHangmanGame.getPhrase().toCharArray();
        for (int i = 0; i < phraseArray.length; i++) {
            if (phraseArray[i] == letter)
                positions.add(i);
        }
        return positions;
    }
}