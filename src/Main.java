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
public class Main extends JComponent {
    @SuppressWarnings("FieldCanBeLocal")
    private static Hangman mHangmanGame;
    private final JFrame mFrame = new JFrame("Hangman");
    private final Container mContainer = mFrame.getContentPane();
    private JPanel mParentPanel = new JPanel(new GridBagLayout());//Main Panel to hold all subpanels
    private static JPanel mPhrasePanel = new JPanel(),
            mFigurePanel = new JPanel(),
            mAlphabetPanel = new JPanel(),
            mStatsPanel = new JPanel();
    private GridBagConstraints mGridBagConstraints = new GridBagConstraints();

    public static void main(String[] args) {
        Main Main = new Main();
        Main.setUp();
        boolean shouldPlay;
        do {
            playGame();
            shouldPlay = contains(JOptionPane.showInputDialog(null, "Do you want to play again")
                    , 'y');
        } while (shouldPlay); //TODO: Uncomment when finished ui

    }

    private static void playGame() {
        mHangmanGame = new Hangman(JOptionPane.showInputDialog(null, "Enter phrase: "));
        drawCharacterSlots(mPhrasePanel.getGraphics(), mHangmanGame.getPhrase());
        while (mHangmanGame.getLives() > 0 && !mHangmanGame.hasWon()) {
            showMessage(mHangmanGame.guess(JOptionPane.showInputDialog(null, "Enter a guess").charAt(0))
                    + "\nYou have " + mHangmanGame.getLives() + " lives remaining");
            drawCorrectLetters(mPhrasePanel.getGraphics(), mHangmanGame.getPhrase(), mHangmanGame.getCorrectLetters());
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
        for (int i = 0, x = 0; i < phrase.length(); i++) {
            if (phraseArray[i] == correctLetters.get(x)) {//TODO: add support to print same letter in every place it appears
                g.drawString(correctLetters.get(x).toString().toUpperCase(), (i * 50) + 30, 40);
                x++;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {

    }

    public void setUp() {
        //Set up constraints for mPhrasePanel
        mGridBagConstraints.gridx = 0;
        mGridBagConstraints.gridy = 2;
        mGridBagConstraints.weightx = 1.0;
        mGridBagConstraints.weighty = 0.4;
        mGridBagConstraints.fill = GridBagConstraints.BOTH;
        mGridBagConstraints.gridwidth = 2;
        //set up panel
        mPhrasePanel.setBackground(Color.BLUE);
        mParentPanel.add(mPhrasePanel, mGridBagConstraints);

        //Set up constraints for mFigurePanel
        mGridBagConstraints.gridx = 0;
        mGridBagConstraints.gridy = 1;
        mGridBagConstraints.weightx = 1.0;
        mGridBagConstraints.weighty = 1.0;
        mGridBagConstraints.fill = GridBagConstraints.BOTH;
        mGridBagConstraints.gridwidth = 2;
        //set up panel
        mFigurePanel.setBackground(Color.RED);
        mParentPanel.add(mFigurePanel, mGridBagConstraints);

        //Set up constraints for mAlphabetPanel
        mGridBagConstraints.gridx = 0;
        mGridBagConstraints.gridy = 0;
        mGridBagConstraints.weightx = 1.0;
        mGridBagConstraints.weighty = 0.35;
        mGridBagConstraints.fill = GridBagConstraints.BOTH;
        mGridBagConstraints.gridwidth = 1;
        //set up panel
        mAlphabetPanel.setBackground(Color.GREEN);
        mParentPanel.add(mAlphabetPanel, mGridBagConstraints);

        //Set up constraints for mStatsPanel
        mGridBagConstraints.gridx = 1;
        mGridBagConstraints.gridy = 0;
        mGridBagConstraints.weightx = 1.0;
        mGridBagConstraints.weighty = 0.35;
        mGridBagConstraints.fill = GridBagConstraints.BOTH;
        mGridBagConstraints.gridwidth = 1;
        //set up panel
        mStatsPanel.setBackground(Color.MAGENTA);
        mParentPanel.add(mStatsPanel, mGridBagConstraints);

        mContainer.add(this);
        mContainer.add(mParentPanel);
//        mFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mFrame.setSize(500, 500);
        mFrame.setIconImage(null);//TODO: make a hangman icon image
        mFrame.setUndecorated(false);//@Eli, we can change this later if we want to
        mFrame.setLocationRelativeTo(null);
        mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mFrame.setVisible(true);
    }
}