import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Hangman {
    private static Scanner input = new Scanner(System.in);

    private String word;
    private char[] charredWord;

    private char[] hitLetters;
    private ArrayList<Character> guessedLetters;

    private int hits;
    private int misses;

    public Hangman (String word) {
        this.word = word;
        this.charredWord = word.toCharArray();

        this.hitLetters = new char[charredWord.length];
        for(int i = 0; i < charredWord.length; i++) {this.hitLetters[i] = '_';}
        this.guessedLetters = new ArrayList<Character>();

        this.hits = 0;
        this.misses = 0;

        execute();
    }


    private void execute() {
        updateInfo();

        boolean isDone = false;
        do {isDone = round();} while (!isDone);

        if (misses == 6) {
            System.out.println("You lost! The word was \u001B[33m" + word); // "\u001B[33m" is an ANSI escape code to make the text yellow. 
        } else if (hits >= hitLetters.length) {
            System.out.println("You won! The word was \u001B[33m" + word);
        } else {
            System.out.println("Something wrong happened while checking if you won or lost");
            System.exit(1); // Identifying that the program exited with an error.
        }
        System.exit(0); // Identifying that the program exited without an error.
    }

    private boolean round() { // returns true if the player has won or lost
        boolean isValid = false;
        do {isValid = getLetter();} while (!isValid);

        updateInfo();

        if (misses >= 6) {
            return true;
        } else if (hits == hitLetters.length) {
            return true;
        }
        
        return false;
    }

    private void updateInfo() {
        System.out.print("\033[H\033[2J"); // "\033[H\033[2J" is an ANSI escape code to clear the terminal
        System.out.println(Arrays.toString(hitLetters));
        printHangman();

        System.out.print("Guessed letters: ");
        System.out.println(Arrays.toString(guessedLetters.toArray()));

        System.out.println("Hits: " + hits + " Misses: " + misses);
    }

    private boolean getLetter() {
        System.out.print("Guess a letter: ");
        char newChar = input.next().charAt(0);

        if (!Character.isAlphabetic(newChar)) {
            System.out.println("You must guess a letter.");
            return false;
        }

        for (int i = 0; i < guessedLetters.size(); i++) {
            if (guessedLetters.get(i) == newChar) {
                System.out.println("You have already guessed that letter.");
                return false;
            }
        }

        guessedLetters.add(newChar);

        boolean isCorrect = false;
        for (int i = 0; i < hitLetters.length; i++) {
            if (charredWord[i] == newChar) {
                hitLetters[i] = newChar;
                isCorrect = true;
                hits++;
            }
        }

        if (!isCorrect) {
            misses++;
        }

        return true;
    }

    private void printHangman() {
        switch (misses) {
            case 0:
                System.out.println("____   ");
                System.out.println("|/  ;  ");
                System.out.println("|      ");
                System.out.println("|      ");
                System.out.println("|      ");
                System.out.println("|______");
                break;
            case 1:
                System.out.println("____   ");
                System.out.println("|/  ;  ");
                System.out.println("|   O  ");
                System.out.println("|      ");
                System.out.println("|      ");
                System.out.println("|______");
                break;
            case 2:
                System.out.println("____   ");
                System.out.println("|/  ;  ");
                System.out.println("|   O  ");
                System.out.println("|  /   ");
                System.out.println("|      ");
                System.out.println("|______");
                break;
            case 3:
                System.out.println("____   ");
                System.out.println("|/  ;  ");
                System.out.println("|   O  ");
                System.out.println("|  /|  ");
                System.out.println("|      ");
                System.out.println("|______");
                break;
            case 4:
                System.out.println("____   ");
                System.out.println("|/  ;  ");
                System.out.println("|   O  ");
                System.out.println("|  /|\\");
                System.out.println("|      ");
                System.out.println("|______");
                break;
            case 5:
                System.out.println("____   ");
                System.out.println("|/  ;  ");
                System.out.println("|   O  ");
                System.out.println("|  /|\\");
                System.out.println("|  /   ");
                System.out.println("|______");
                break;
            case 6:
                System.out.println("____   ");
                System.out.println("|/  ;  ");
                System.out.println("|   O  ");
                System.out.println("|  /|\\");
                System.out.println("|  / \\");
                System.out.println("|______");
                break;
            default: 
                System.out.println("An error occured when attempting to print the hangman graphics.");
                System.exit(1);
        }
    }
}