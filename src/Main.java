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
    private JPanel mParentPanel = new JPanel(new GridBagLayout());//Main Panel to hold all subpanels
    private JPanel mPhrasePanel = new JPanel(),
            mFigurePanel = new JPanel(),
            mAlphabetPanel = new JPanel(),
            mStatsPanel = new JPanel();
    private GridBagConstraints mGridBagConstraints = new GridBagConstraints();

    public static void main(String[] args) {
        Main Main = new Main();
        Main.setUp();
        boolean shouldPlay;
//        do {
//            playGame();
//            shouldPlay = contains(JOptionPane.showInputDialog(null, "Do you want to play again")
//                    , 'y');
//        } while (shouldPlay); TODO: Uncomment when finished ui

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
        //Set up constraints for mPhrasePanel
        mGridBagConstraints.gridx = 0;
        mGridBagConstraints.gridy = 2;
        mGridBagConstraints.weightx = 1.0;
        mGridBagConstraints.weighty = 0.5;
        mGridBagConstraints.fill = GridBagConstraints.BOTH;
        //set up panel
        mPhrasePanel.setBackground(Color.BLUE);
        mParentPanel.add(mPhrasePanel, mGridBagConstraints);

        //Set up constraints for mFigurePanel
        mGridBagConstraints.gridx = 0;
        mGridBagConstraints.gridy = 1;
        mGridBagConstraints.weightx = 1.0;
        mGridBagConstraints.weighty = 1.0;
        mGridBagConstraints.fill = GridBagConstraints.BOTH;
        //set up panel
        mFigurePanel.setBackground(Color.RED);
        mParentPanel.add(mFigurePanel, mGridBagConstraints);


        mContainer.add(this);
        mContainer.add(mParentPanel);
        mFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mFrame.setIconImage(null);//TODO: make a hangman icon image
        mFrame.setUndecorated(false);//@Eli, we can change this later if we want to
        mFrame.setLocationRelativeTo(null);
        mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mFrame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {

    }

    private void drawCharacterSlots(Graphics g, String mPhrase, char correctLetters[]) {
        //TODO: use for loop to draw apropriate lines
    }
}