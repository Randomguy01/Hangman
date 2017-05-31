import javax.swing.*;
import java.util.ArrayList;

/**
 * Ian White
 * Program: Class keeps stats and information to run game up to date
 * 5/15/17
 * JDK 1.8
 */
class Hangman {
    //Final String fields used to display information to user
    //JLabels can be formatted useing html. <br> is the same as \n
    private final String CORRECT_GUESS = "Your guess is correct";
    private final String INCORRECT_GUESS = "Your guess is incorrect";
    private final String DUPLICATE_GUESS = "You have already guessed ";
    private final String NO_LIVES_MESSAGE = "<html>Sorry, you are out of lives. <br>The correct phrase was: ";
    private final String WIN_MESSAGE = "YOU WIN!!!";
    //declare arrays and initialize arraylists
    private final String mPhrase;
    private final char[] mPhraseArray;
    private final ArrayList<Character> mGuessedLetters = new ArrayList<>();
    private final Character[] mPhraseArrayNoSpaces;
    private ArrayList<Character> mPhraseArayNoSpacesDuplicates = new ArrayList<>();
    private final ArrayList<Character> mCorrectLetters = new ArrayList<>();
    //initialize stats to default values
    private int mGuesses = 0;
    private int mLives = 9;
    private GUI mGUI;


    Hangman(String phrase) {//constructor used to set values of arraylists
        mPhrase = phrase;
        mPhraseArray = mPhrase.toLowerCase().toCharArray();
        mPhraseArrayNoSpaces = removeWhite(mPhrase).toArray(new Character[removeWhite(mPhrase).size()]);
        mPhraseArayNoSpacesDuplicates = removeDuplicates(mPhraseArrayNoSpaces);
    }

    //method removes any duplicate chars in Character array
    private ArrayList<Character> removeDuplicates(Character[] phraseArrayNoSpaces) {
        ArrayList<Character> output = new ArrayList<>();
        for (char character : phraseArrayNoSpaces) {
            if (!output.contains(character)) {
                output.add(character);
            }
        }
        return output;
    }

    /* ***************************** Getters and setters for necessary fields **************************************/
    public void setGUI(GUI GUI) {
        mGUI = GUI;
    }

    public ArrayList<Character> getCorrectLetters() {
        return mCorrectLetters;
    }

    public String getPhrase() {
        return mPhrase;
    }

    public int getGuesses() {
        return mGuesses;
    }

    public int getLives() {
        return mLives;
    }

    public String getWIN_MESSAGE() {
        return WIN_MESSAGE;
    }

    public String getNO_LIVES_MESSAGE() {
        return NO_LIVES_MESSAGE + mPhrase + "<html>";
    }

    //method takes user entered char and returns String message
    public String guess(char letter) {
        letter = Character.toLowerCase(letter);
        if (contains(mPhraseArrayNoSpaces, letter) && isNotGuessed(letter)) {
            mGuesses++;
            mCorrectLetters.add(letter);
            mGuessedLetters.add(letter);
            mGUI.crossOutLetters(mGuessedLetters);
            mGUI.drawCharacterSlots();
            return CORRECT_GUESS;
        } else if (isNotGuessed(letter)) {
            mGuesses++;
            mGuessedLetters.add(letter);
            mLives--;
            mGUI.crossOutLetters(mGuessedLetters);
            mGUI.drawCharacterSlots();
            mGUI.drawHangman(this.getLives());
            return INCORRECT_GUESS;
        } else {
            return DUPLICATE_GUESS + letter;
        }

    }

    //returns false if a letter has not been guessed
    private boolean isNotGuessed(char letter) {
        for (char guessedLetter : mGuessedLetters) {
            if (guessedLetter == letter)
                return false;
        }
        return true;
    }

    //method returns false if Character array does not contain letter
    private boolean contains(Character[] array, char letter) {
        for (char position : array) {
            if (position == letter) {
                return true;
            }
        }
        return false;
    }

    //overloaded method for char array
    private boolean contains(char[] array, char letter) {
        for (char position : array) {
            if (position == letter) {
                return true;
            }
        }
        return false;
    }

    //overloaded metod for ArrayList<Character>
    private boolean contains(ArrayList<Character> array, Character letter) {
        for (Character position : array) {
            if (position == letter) {
                return true;
            }
        }
        return false;
    }

    //returns boolean to check if user has won the game
    public boolean hasWon() {
        for (Character mPhraseArayNoSpacesDuplicate : mPhraseArayNoSpacesDuplicates) {
            if (!contains(mGuessedLetters, mPhraseArayNoSpacesDuplicate)) {
                return false;
            }
        }
        if (this
                .contains(JOptionPane.showInputDialog(null
                        , "Do you want to play again?").toCharArray(), 'y')) {

            mGUI.reset();
            Main.main(null);
        } else {
            System.exit(0);
        }
        return true;
    }

    //method removes white space from ArrayList of Characters
    private ArrayList<Character> removeWhite(String phrase) {
        ArrayList<Character> noSpaces = new ArrayList<>();
        for (char character : phrase.toCharArray()) {
            if (character != ' ')
                noSpaces.add(Character.toLowerCase(character));
        }
        return noSpaces;
    }

}
