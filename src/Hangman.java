import java.util.ArrayList;

/**
 * Ian White
 * Program:
 * ${Date}
 * JDK 1.8
 */
public class Hangman {
    private static String mPhrase;
    private static int mGuesses = 6;
    private static int mLives;
    private static ArrayList<Character> mGuessedLetters = new ArrayList<>();
    private static char[] mPhraseArray;

    public Hangman(String phrase) {
        mPhrase = phrase;
    }

    public static String getPhrase() {
        return mPhrase;
    }

    public static void setPhrase(String phrase) {
        Hangman.mPhrase = phrase;
    }

    public static int getGuesses() {
        return mGuesses;
    }

    public static int getLives() {
        return mLives;
    }

    public static ArrayList<Character> getGuessedLetters() {
        return mGuessedLetters;
    }

    public static boolean guess(char letter){
        mGuessedLetters.add(letter);

        for (int i = 0; i < mPhraseArray.length; i++) {
            if (mPhraseArray[i] == letter && !isGuessed(letter)){

            }
        }

        return false;
    }

    private static boolean isGuessed(char letter){
        for (char guessedLetter : mGuessedLetters){
            if (guessedLetter == letter)
                return true;
        }
        return false;
    }
}
