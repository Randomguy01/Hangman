import java.util.ArrayList;

/**
 * Ian White
 * Program:
 * ${Date}
 * JDK 1.8
 */
public class Hangman {
    private final String CORRECT_GUESS = "Your guess is correct";
    private final String INCORRECT_GUESS = "Your guess is incorrect";
    private final String DUPLICATE_GUESS = "You have already guessed ";
    private final String NO_LIVES_MESSAGE = "Sorry, you are out of lives. The correct phrase was: ";
    private final String WIN_MESSAGE = "CORRECT! The phrase was ";
    private String mPhrase;
    private int mGuesses = 6;
    private int mLives = 7;
    private ArrayList<Character> mGuessedLetters = new ArrayList<>();
    private char[] mPhraseArray;
    private ArrayList<Character> mCorrectLetters = new ArrayList<>();

    public Hangman(String phrase) {
        mPhrase = phrase;
        mPhraseArray = mPhrase.toLowerCase().toCharArray();
    }

    public String getPhrase() {
        return mPhrase;
    }

    public void setPhrase(String phrase) {
        mPhrase = phrase;
    }

    public int getGuesses() {
        return mGuesses;
    }

    public int getLives() {
        return mLives;
    }


    public ArrayList<Character> getGuessedLetters() {
        return mGuessedLetters;
    }

    public String guess(char letter){
        letter = Character.toLowerCase(letter);
        if (hasWon()){
            return WIN_MESSAGE + this.getPhrase();
        }else if (mLives < 1){
            return NO_LIVES_MESSAGE + this.getPhrase();
        } else if (contains(mPhraseArray, letter) && !isGuessed(letter)){
            mGuesses++;
            mCorrectLetters.add(letter);
            mGuessedLetters.add(letter);
            return CORRECT_GUESS;
        }else if (!isGuessed(letter)){
            mGuesses++;
            mGuessedLetters.add(letter);
            mLives--;
            return INCORRECT_GUESS;
        }else {
            return DUPLICATE_GUESS + letter;
        }

    }

    private boolean isGuessed(char letter){
        for (char guessedLetter : mGuessedLetters){
            if (guessedLetter == letter)
                return true;
        }
        return false;
    }

    private boolean contains(char[] array, char letter) {
        for (char position : array){
            if (position == letter){
                return true;
            }
        }
        return false;
    }

    private boolean contains(ArrayList<Character> array, Character letter){
        for (Character position : array){
            if (position == letter){
                return true;
            }
        }
        return false;
    }

    private boolean hasWon(){
        for (int i = 0; i < mPhrase.length(); i++){
            if (!contains(mGuessedLetters, mPhraseArray[i])){
                return false;
            }
        }
        return true;
    }
}
