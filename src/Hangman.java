import java.util.ArrayList;

/**
 * Ian White
 * Program:
 * ${Date}
 * JDK 1.8
 */
public class Hangman {
    private String mPhrase;
    private int mGuesses = 6;
    private int mLives;
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

    public boolean guess(char letter){
        letter = Character.toLowerCase(letter);
        if (contains(mPhraseArray, letter) && !isGuessed(letter)){
            mCorrectLetters.add(letter);
            mGuessedLetters.add(letter);
            return true;
        }else if (!isGuessed(letter)){
            mGuessedLetters.add(letter);
        }

        return false;
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
}
