import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Ian White
 * Program:
 * ${Date}
 * JDK 1.8
 */
class GUI {
    private final int PARENT_PANEL = 0,
            PHRASE_PANEL = 1,
            FIGURE_PANEL = 2,
            ALPHABET_PANEL = 3,
            STATS_PANEL = 4,
            GUESS_PANEL = 5;
    private final int LIVES_LABEL = 0,
            GUESSES_LABEL = 1;
    private final JPanel[] mPanels =
            {new JPanel(new GridBagLayout()),
                    new JPanel(),
                    new JPanel(),
                    new JPanel(),
                    new JPanel(new GridBagLayout()),
                    new JPanel(new GridBagLayout())};
    private final JLabel[] mStatsLabels =
            {new JLabel("Lives: "),
                    new JLabel("Guesses: ")};
    private final GridBagConstraints mConstraints = new GridBagConstraints();
    private final GridBagConstraints mGuessConstraints = new GridBagConstraints();
    private final JFrame mFrame;
    private final Hangman mHangman;
    private final JTextField mGuessField = new JTextField();
    private final JLabel mGuessLabel = new JLabel("Enter a guess");
    private final JButton mSubmitButton = new JButton("Submit");
    private final int BOTTOM_POST = 0,
            MIDDLE_POST = 1,
            TOP_POST = 2,
            HEAD = 3,
            BODY = 4,
            LEFT_ARM = 5,
            RIGHT_ARM = 6,
            LEFT_LEG = 7,
            RIGHT_LEG = 8;


    public GUI(JFrame frame, Hangman game) {
        mFrame = frame;
        mHangman = game;
        setUp();
        drawTheAlphabet();
        drawCharacterSlots();
        drawStats();
    }

    private void setUp() {
        //Set up constraints for mPhrasePanel
        mConstraints.gridx = 0;
        mConstraints.gridy = 3;//Might need to be 3
        mConstraints.weightx = 1.0;
        mConstraints.weighty = 0.2;
        mConstraints.fill = GridBagConstraints.BOTH;
        mConstraints.gridwidth = 3;
        mConstraints.gridheight = 1;
        //set up panel
        mPanels[PHRASE_PANEL].setBackground(Color.BLUE);
        mPanels[PARENT_PANEL].add(mPanels[PHRASE_PANEL], mConstraints);

        //Set up constraints for mFigurePanel
        mConstraints.gridx = 2;
        mConstraints.gridy = 0;
        mConstraints.weightx = 0.6;
        mConstraints.weighty = 0.8;
        mConstraints.fill = GridBagConstraints.BOTH;
        mConstraints.gridheight = 3;
        mConstraints.gridwidth = 1;
        //set up panel
        mPanels[FIGURE_PANEL].setBackground(Color.RED);
        mPanels[PARENT_PANEL].add(mPanels[FIGURE_PANEL], mConstraints);

        //Set up constraints for mAlphabetPanel
        mConstraints.gridx = 0;
        mConstraints.gridy = 0;
        mConstraints.weightx = 0.4;
        mConstraints.weighty = 0.2;
        mConstraints.fill = GridBagConstraints.BOTH;
        mConstraints.gridwidth = 2;
        mConstraints.gridheight = 1;
        //set up panel
        mPanels[ALPHABET_PANEL].setBackground(Color.GREEN);
        mPanels[PARENT_PANEL].add(mPanels[ALPHABET_PANEL], mConstraints);

        //Set up constraints for mStatsPanel
        mConstraints.gridx = 0;
        mConstraints.gridy = 1;
        mConstraints.weightx = 0.4;
        mConstraints.weighty = 0.25;
        mConstraints.fill = GridBagConstraints.BOTH;
        mConstraints.gridwidth = 2;
        mConstraints.gridheight = 1;
        /* ********************************* Set up Stats Panel Constraints **************************************** */


        mPanels[STATS_PANEL].setBackground(Color.MAGENTA);
        mPanels[PARENT_PANEL].add(mPanels[STATS_PANEL], mConstraints);

        //set up constraints for mGuessPanel
        mConstraints.gridx = 0;
        mConstraints.gridy = 2;
        mConstraints.weightx = 0.4;
        mConstraints.weighty = 0.25;
        mConstraints.gridwidth = 2;
        mConstraints.gridheight = 1;
        /* ********************************* Set up Guess Panel Constraints **************************************** */
        //set up constraints for mGuessLabel
        mGuessConstraints.gridx = 0;
        mGuessConstraints.gridy = 0;
        mGuessConstraints.fill = GridBagConstraints.NONE;
        mGuessConstraints.gridwidth = 1;
        mGuessConstraints.gridheight = 1;
        mGuessConstraints.weightx = 1.0;
        mGuessConstraints.weighty = 1.0;
        //set up panel
        mGuessLabel.setText("Enter a guess: ");
        mPanels[GUESS_PANEL].add(mGuessLabel, mGuessConstraints);

        //set up constraints for mGuessField
        mGuessConstraints.gridx = 0;
        mGuessConstraints.gridy = 1;
        mGuessConstraints.fill = GridBagConstraints.HORIZONTAL;
        mGuessConstraints.insets = new Insets(0, 20, 0, 20);
        mGuessConstraints.weightx = 1.0;
        mGuessConstraints.weighty = 1.0;
        //set up field
        mPanels[GUESS_PANEL].add(mGuessField, mGuessConstraints);

        //set up constraints for mSubmitButton
        mGuessConstraints.gridx = 0;
        mGuessConstraints.gridy = 2;
        mGuessConstraints.fill = GridBagConstraints.NONE;
        mGuessConstraints.weightx = 0.25;
        mGuessConstraints.weighty = 1.0;
        //set up button
        mGuessConstraints.insets = new Insets(0, 0, 0, 0);
        mSubmitButton.addActionListener(e -> {
            mHangman.guess(mGuessField.getText().charAt(0));
            mGuessField.setText("");
        });
        mPanels[GUESS_PANEL].add(mSubmitButton, mGuessConstraints);
        mPanels[PARENT_PANEL].add(mPanels[GUESS_PANEL], mConstraints);

        mFrame.add(mPanels[PARENT_PANEL]);
//        mFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mFrame.setSize(1024, 768);
        mFrame.setIconImage(null);//TODO: make a hangman icon image
        mFrame.setUndecorated(false);//@Eli, we can change this later if we want to
        mFrame.setLocationRelativeTo(null);
        mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mFrame.setVisible(true);
    }

    public void drawCharacterSlots() {
        Graphics g = mPanels[PHRASE_PANEL].getGraphics();
        char[] phraseArray = mHangman.getPhrase().toCharArray();
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        g.setColor(Color.WHITE);
        for (int i = 0; i < mHangman.getPhrase().length(); i++) {
            if (!Character.isWhitespace(phraseArray[i])) {
                g.drawLine((i * 50) + 20, 50, (i * 50) + 50, 50);
            }
        }
        mPanels[PHRASE_PANEL].validate();
    }

    public void drawCorrectLetters(ArrayList<Character> correctLetters) {
        final Graphics g = mPanels[PHRASE_PANEL].getGraphics();
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        final char[] phraseArray = mHangman.getPhrase().toLowerCase().toCharArray();
        for (int i = 0, x = 0; i < mHangman.getPhrase().length() && x < correctLetters.size(); i++) {
            if (phraseArray[i] == correctLetters.get(x)) {
                for (int y = 0; y < getAllPositions(correctLetters.get(x)).size(); y++) {
                    int position = getAllPositions(correctLetters.get(x).toString().charAt(0)).get(y);
                    g.drawString(correctLetters.get(x).toString().toUpperCase(), ((position) * 50) + 30, 40);
                }
                x++;
            }
        }
        mPanels[PHRASE_PANEL].validate();
    }

    private ArrayList<Integer> getAllPositions(char letter) {
        letter = Character.toLowerCase(letter);
        ArrayList<Integer> positions = new ArrayList<>();
        char[] phraseArray = mHangman.getPhrase().toLowerCase().toCharArray();
        for (int i = 0; i < phraseArray.length; i++) {
            if (phraseArray[i] == letter)
                positions.add(i);
        }
        return positions;
    }

    public void drawTheAlphabet() {
        Graphics g = mPanels[ALPHABET_PANEL].getGraphics();
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.setColor(Color.BLACK);
        for (int i = 0, row = 0, x = 0; i < 26; i++, x++) {
            if (i % 8 == 0 && i != 0) {
                row++;
                x = 0;
            }
            g.drawString(getCharFromInt(i) + "", (x * 35) + 25, (row * 25) + 25);
        }
        mPanels[ALPHABET_PANEL].validate();
    }

    private char getCharFromInt(int position) {
        switch (position) {
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'D';
            case 4:
                return 'E';
            case 5:
                return 'F';
            case 6:
                return 'G';
            case 7:
                return 'H';
            case 8:
                return 'I';
            case 9:
                return 'J';
            case 10:
                return 'K';
            case 11:
                return 'L';
            case 12:
                return 'M';
            case 13:
                return 'N';
            case 14:
                return 'O';
            case 15:
                return 'P';
            case 16:
                return 'Q';
            case 17:
                return 'R';
            case 18:
                return 'S';
            case 19:
                return 'T';
            case 20:
                return 'U';
            case 21:
                return 'V';
            case 22:
                return 'W';
            case 23:
                return 'X';
            case 24:
                return 'Y';
            case 25:
                return 'Z';
            default:
                return 'A';
        }
    }

    private int getIntFromChar(char letter) {
        letter = Character.toUpperCase(letter);
        switch (letter) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            case 'J':
                return 9;
            case 'K':
                return 10;
            case 'L':
                return 11;
            case 'M':
                return 12;
            case 'N':
                return 13;
            case 'O':
                return 14;
            case 'P':
                return 15;
            case 'Q':
                return 16;
            case 'R':
                return 17;
            case 'S':
                return 18;
            case 'T':
                return 19;
            case 'U':
                return 20;
            case 'V':
                return 21;
            case 'W':
                return 22;
            case 'X':
                return 23;
            case 'Y':
                return 24;
            case 'Z':
                return 25;
            default:
                return -1;

        }
    }

    public void crossOutLetters(ArrayList<Character> letters) {
        ArrayList<Integer> positions = new ArrayList<>();
        for (char letter : letters)
            positions.add(getIntFromChar(letter));
        Graphics g = mPanels[ALPHABET_PANEL].getGraphics();
        int row;
        for (int position : positions) {
            row = position / 8;
            int x = position % 8;
            g.drawLine((x * 35) + 25,//TODO: Much fixing
                    (row * 25) + 5,
                    (x * 35) + 45,
                    (row * 25) + 25);
        }
    }

    @Deprecated
    public void drawStats() {
        mPanels[STATS_PANEL].invalidate();
        Graphics g = mPanels[STATS_PANEL].getGraphics();
        mPanels[STATS_PANEL].update(g);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.setColor(Color.BLACK);
        g.drawString("Lives: " + mHangman.getLives(), 20, 40);
        g.drawString("Guesses: " + mHangman.getGuesses(), 20, 80);
        mPanels[STATS_PANEL].validate();
    }

    public void drawHangman(int lives) {
        mPanels[FIGURE_PANEL].invalidate();
        Graphics g = mPanels[FIGURE_PANEL].getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        mPanels[FIGURE_PANEL].update(g);

        g.setColor(Color.BLACK);
        System.out.println(lives);
        switch (lives) {
            case 0:
                g2d.setStroke(new BasicStroke(3));
                drawFigurePart(RIGHT_LEG, g2d);
            case 1:
                g2d.setStroke(new BasicStroke(3));
                drawFigurePart(LEFT_LEG, g2d);
            case 2:
                g2d.setStroke(new BasicStroke(3));
                drawFigurePart(RIGHT_ARM, g2d);
            case 3:
                g2d.setStroke(new BasicStroke(3));
                drawFigurePart(LEFT_ARM, g2d);
            case 4:
                g2d.setStroke(new BasicStroke(3));
                drawFigurePart(BODY, g2d);
            case 5:
                g2d.setStroke(new BasicStroke(3));
                drawFigurePart(HEAD, g2d);
            case 6:
                g2d.setStroke(new BasicStroke(5));
                drawFigurePart(TOP_POST, g2d);
            case 7:
                g2d.setStroke(new BasicStroke(5));
                drawFigurePart(MIDDLE_POST, g2d);
            case 8:
                g2d.setStroke(new BasicStroke(5));
                drawFigurePart(BOTTOM_POST, g2d);
        }

        mPanels[FIGURE_PANEL].validate();
    }

    public void updateStats(int lives, int guesses) {

    }

    private void drawFigurePart(@NotNull int part, Graphics2D g) {
        switch (part) {
            case BOTTOM_POST:
                g.drawLine(275, 580, 500, 580);
                break;
            case MIDDLE_POST:
                g.drawLine(387, 580, 387, 100);
                break;
            case TOP_POST:
                g.drawLine(387, 100, 225, 100);
                g.drawLine(225, 100, 225, 125);
                break;
            case HEAD:
                g.drawOval(187, 125, 75, 75);
                break;
            case BODY:
                g.drawLine(225, 200, 225, 375);
                break;
            case LEFT_ARM:
                g.drawLine(225, 250, 150, 175);
                break;
            case RIGHT_ARM:
                g.drawLine(225, 250, 300, 175);
                break;
            case LEFT_LEG:
                g.drawLine(225, 375, 145, 455);
                break;
            case RIGHT_LEG:
                g.drawLine(225, 375, 305, 455);
                break;
        }
    }

}
