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
        playGame();//single method call in main to play program
    }

    private static void playGame() {
        String phrase;
        do {//ask user for the phrase until their input is valid
            phrase = JOptionPane.showInputDialog(null, "Enter the phrase: ");
        } while (phrase.equals(" ") || phrase.length() < 1);
        mHangmanGame = new Hangman(phrase);//creates new game object with phrase as argumenrt
        mGui = new GUI(mFrame, mHangmanGame);//create new gui object with frame and hangman game as argument
        mHangmanGame.setGUI(mGui);//set the hangman gui field to current gui object
        mGui.drawTheAlphabet();//initial drawing ofcharacter slots
        mGui.drawCharacterSlots();//initial drawing of character slots
        mGui.updateStats();//initial stats are displayed
    }

}