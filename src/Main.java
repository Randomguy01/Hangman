import javax.swing.*;

/*
 * Ian White
 * 5/15/2017
 * JDK 1.8
 * Program: 
 */
//boogly boo boo hoo hoo
class Main {
    private static final JFrame mFrame = new JFrame("Hangman");
    private static Hangman mHangmanGame;
    private static GUI mGui;

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        String phrase;
        do {
            phrase = JOptionPane.showInputDialog(null, "Enter the phrase: ");
            System.out.println("You must enter a phrase or word");
        } while (phrase.equals(" ") || phrase.length() < 1);
        mHangmanGame = new Hangman(phrase);
        mGui = new GUI(mFrame, mHangmanGame);
        mHangmanGame.setGUI(mGui);
        mGui.drawTheAlphabet();
        mGui.drawCharacterSlots();
        mGui.updateStats();
    }

}