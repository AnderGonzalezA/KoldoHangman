import java.util.Scanner;
import com.zubiri.hangman.*;
public class KoldoHangman {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the famous HANGMAN game.");
		System.out.println("You will have to guess which surname of the people in class is hidden between the voids.");
		System.out.println(
				"For this, you will be able to enter 3 letters that may appear in the surname, or not. After this, you will only have a chance to guess the surname and win the game.");
		boolean playAgain = true;
		while (playAgain == true) {
			Words words = new Words();
			words.addWord(new Word("lazkano"));
			words.addWord(new Word("intxausti"));
			words.addWord(new Word("artola"));
			words.addWord(new Word("ortiz"));
			words.addWord(new Word("gonzalez"));
			Word randomWord = words.getRandomWord();
			for (int i=0;i<randomWord.getWord().length();i++) {
				System.out.print("_ ");
			}
			Letters letters = new Letters();
			while (letters.getLettersCounter() < 3) {
				String entered = sc.nextLine().toLowerCase();
				char letterEntered=entered.charAt(0);
				letters.setPossibleLetter(entered);
				if (randomWord.hasLetter(letterEntered)) {
					System.out.println("You were right!");
				}else {
					System.out.println("Ups");
				}
				randomWord.printUnderscores(letters);
				if (letters.getLettersCounter() == 1) {
					System.out.println("You have two letters left.");
				} else if (letters.getLettersCounter() == 2) {
					System.out.println("You have just a letter left.");
				}
			}
			System.out.println("It's the time, you have to guess the word.");
			boolean wordEntered = false;
			while (wordEntered == false) {
				String[] playerWordArray = sc.nextLine().split(" ");
				String playerWord = playerWordArray[0];
				if (randomWord.setWord(playerWord)) {
					wordEntered=true;
					if (randomWord.checkWord(playerWord)) {
						System.out.println("Congrats, you won the game.");
					} else {
						System.out.println("I'm sorry, you lost the game.");
					}
				}else {
					System.out.println("Please, enter a word.");
				}
			}
			System.out.println("Would you like to play again?(y/n)");
			boolean askAgain = true;
			while (askAgain == true) {
				String back = sc.next().toLowerCase();
				sc.nextLine();
				switch (back) {
				case "y":
					askAgain = false;
					break;
				case "n":
					playAgain = false;
					askAgain = false;
					break;
				default:
					System.out.println("Please, select a possible value(y/n)");
					break;
				}
			}
		}
		sc.close();
	}
}
