import java.util.ArrayList;

/**
 * Ian White
 * Program:
 * ${Date}
 * JDK 1.8
 */
class Hangman {
    @SuppressWarnings("FieldCanBeLocal")
    private final String CORRECT_GUESS = "Your guess is correct";
    @SuppressWarnings("FieldCanBeLocal")
    private final String INCORRECT_GUESS = "Your guess is incorrect";
    @SuppressWarnings("FieldCanBeLocal")
    private final String DUPLICATE_GUESS = "You have already guessed ";
    private final String NO_LIVES_MESSAGE = "Sorry, you are out of lives. The correct phrase was: ";
    private final String WIN_MESSAGE = "CORRECT! The phrase was ";
    private final String mPhrase;
    private int mGuesses = 6;
    private int mLives = 7;
    private final ArrayList<Character> mGuessedLetters = new ArrayList<>();
    private final char[] mPhraseArray;
    private final Character[] mPhraseArrayNoSpaces;

    private GUI mGUI;

    public void setGUI(GUI GUI) {
        mGUI = GUI;
    }

    public ArrayList<Character> getCorrectLetters() {
        return mCorrectLetters;
    }

    private final ArrayList<Character> mCorrectLetters = new ArrayList<>();

    Hangman(String phrase) {
        mPhrase = phrase;
        mPhraseArray = mPhrase.toLowerCase().toCharArray();
        mPhraseArrayNoSpaces = removeWhite(mPhrase).toArray(new Character[removeWhite(mPhrase).size()]);
    }

    @SuppressWarnings("WeakerAccess")
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
        return WIN_MESSAGE + mPhrase;
    }

    public String getNO_LIVES_MESSAGE() {
        return NO_LIVES_MESSAGE + mPhrase;
    }


    public ArrayList<Character> getGuessedLetters() {
        return mGuessedLetters;
    }

    public String guess(char letter) {
        letter = Character.toLowerCase(letter);
        if (contains(mPhraseArrayNoSpaces, letter) && isNotGuessed(letter)) {
            mGuesses++;
            mCorrectLetters.add(letter);
            mGuessedLetters.add(letter);
            mGUI.crossOutLetters(mGuessedLetters);
            return CORRECT_GUESS;
        } else if (isNotGuessed(letter)) {
            mGuesses++;
            mGuessedLetters.add(letter);
            mLives--;
            mGUI.crossOutLetters(mGuessedLetters);
            return INCORRECT_GUESS;
        } else {
            return DUPLICATE_GUESS + letter;
        }

    }

    private boolean isNotGuessed(char letter) {
        for (char guessedLetter : mGuessedLetters) {
            if (guessedLetter == letter)
                return false;
        }
        return true;
    }

    private boolean contains(Character[] array, char letter) {
        for (char position : array) {
            if (position == letter) {
                return true;
            }
        }
        return false;
    }

    private boolean contains(ArrayList<Character> array, Character letter) {
        for (Character position : array) {
            if (position == letter) {
                return true;
            }
        }
        return false;
    }

    public boolean hasWon() {
        for (int i = 0; i < mPhrase.length() - 1; i++) {
            if (!contains(mGuessedLetters, mPhraseArrayNoSpaces[i])) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<Character> removeWhite(String phrase) {
        ArrayList<Character> noSpaces = new ArrayList<>();
        for (char character : phrase.toCharArray()) {
            if (character != ' ')
                noSpaces.add(Character.toLowerCase(character));
        }
        return noSpaces;
    }
}
