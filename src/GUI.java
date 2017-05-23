import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Ian White
 * Program:
 * ${Date}
 * JDK 1.8
 */
public class GUI {
    public final int PARENT_PANEL = 0,
            PHRASE_PANEL = 1,
            FIGURE_PANEL = 2,
            ALPHABET_PANEL = 3,
            STATS_PANEL = 4;
    private JFrame mFrame;
    private JPanel[] mPanels;
    private GridBagConstraints mConstraints = new GridBagConstraints();
    private Hangman mHangman;

    public GUI(JFrame frame, JPanel[] panels, Hangman game) {
        mFrame = frame;
        mPanels = panels;
        mHangman = game;
        setUp();
        drawCharacterSlots(mHangman.getPhrase());
        System.out.println("Set up ");
    }

    private void setUp() {
        //Set up constraints for mPhrasePanel
        mConstraints.gridx = 0;
        mConstraints.gridy = 2;
        mConstraints.weightx = 1.0;
        mConstraints.weighty = 0.4;
        mConstraints.fill = GridBagConstraints.BOTH;
        mConstraints.gridwidth = 2;
        //set up panel
        mPanels[PHRASE_PANEL].setBackground(Color.BLUE);
        mPanels[PARENT_PANEL].add(mPanels[PHRASE_PANEL], mConstraints);

        //Set up constraints for mFigurePanel
        mConstraints.gridx = 0;
        mConstraints.gridy = 1;
        mConstraints.weightx = 1.0;
        mConstraints.weighty = 1.0;
        mConstraints.fill = GridBagConstraints.BOTH;
        mConstraints.gridwidth = 2;
        //set up panel
        mPanels[FIGURE_PANEL].setBackground(Color.RED);
        mPanels[PARENT_PANEL].add(mPanels[FIGURE_PANEL], mConstraints);

        //Set up constraints for mAlphabetPanel
        mConstraints.gridx = 0;
        mConstraints.gridy = 0;
        mConstraints.weightx = 1.0;
        mConstraints.weighty = 0.35;
        mConstraints.fill = GridBagConstraints.BOTH;
        mConstraints.gridwidth = 1;
        //set up panel
        mPanels[ALPHABET_PANEL].setBackground(Color.GREEN);
        mPanels[PARENT_PANEL].add(mPanels[ALPHABET_PANEL], mConstraints);

        //Set up constraints for mStatsPanel
        mConstraints.gridx = 1;
        mConstraints.gridy = 0;
        mConstraints.weightx = 1.0;
        mConstraints.weighty = 0.35;
        mConstraints.fill = GridBagConstraints.BOTH;
        mConstraints.gridwidth = 1;
        //set up panel
        mPanels[STATS_PANEL].setBackground(Color.MAGENTA);
        mPanels[PARENT_PANEL].add(mPanels[STATS_PANEL], mConstraints);

        mFrame.getContentPane().add(mPanels[PARENT_PANEL]);
//        mFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mFrame.setSize(700, 700);
        mFrame.setIconImage(null);//TODO: make a hangman icon image
        mFrame.setUndecorated(false);//@Eli, we can change this later if we want to
        mFrame.setLocationRelativeTo(null);
        mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mFrame.setVisible(true);
    }


    public void drawCharacterSlots(String phrase) {
        Graphics g = mPanels[PHRASE_PANEL].getGraphics();
        final char[] phraseArray = phrase.toCharArray();
        g.setColor(Color.WHITE);
        for (int i = 0; i < phrase.length(); i++) {
            if (phraseArray[i] != ' ')
                g.drawLine((i * 50) + 20, 50, (i * 50) + 50, 50);
        }
    }

    private void drawCorrectLetters(String phrase, ArrayList<Character> correctLetters) {
        Graphics g = mPanels[PHRASE_PANEL].getGraphics();
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        final char[] phraseArray = phrase.toLowerCase().toCharArray();
        for (int i = 0, x = 0; i < phrase.length() && x < correctLetters.size(); i++) {
            if (phraseArray[i] == correctLetters.get(x)) {
                for (int y = 0; y < getAllPositions(correctLetters.get(x)).size(); y++) {
                    int something = getAllPositions(correctLetters.get(x).toString().charAt(0)).get(y);
                    g.drawString(correctLetters.get(x).toString().toUpperCase(), ((something) * 50) + 30, 40);
                }
                x++;
            }
        }
    }

    private ArrayList<Integer> getAllPositions(char letter) {
        ArrayList<Integer> positions = new ArrayList<>();
        char[] phraseArray = mHangman.getPhrase().toCharArray();
        for (int i = 0; i < phraseArray.length; i++) {
            if (phraseArray[i] == letter)
                positions.add(i);
        }
        return positions;
    }


}
