import java.util.Scanner;

/**
 * CS312 Assignment 4 - Rock, Paper, Scissors
 *
 * On my honor, Sebastian Hernandez, this programming assignment is my own work,
 * I have not shared it with others and I will not share it in the future.
 *
 * A program to play Rock Paper Scissors
 *
 * Name: Sebastian Hernandez UTEID: sh55733
 *
 */

public class RockPaperScissors {
	public static final int ROCK = 1;
	public static final int PAPER = 2;
	public static final int SCISSORS = 3;

	public static void main(String[] args) {
		RandomPlayer computerPlayer = buildRandomPlayer(args);
		Scanner keyboard = new Scanner(System.in);

		String playerName = getPlayerName(keyboard);
		int totalRounds = getNumberOfRounds(keyboard, playerName);

		// Create an instance of Testing to call the getResult method
		Testing game = new Testing();

		playRounds(playerName, totalRounds, computerPlayer, keyboard, game);

		keyboard.close();
	}

	// Method to build RandomPlayer
	public static RandomPlayer buildRandomPlayer(String[] args) {
		if (args.length == 0) {
			return new RandomPlayer();
		} else {
			int seed = Integer.parseInt(args[0]);
			return new RandomPlayer(seed);
		}
	}

	// Get the player's name
	public static String getPlayerName(Scanner keyboard) {
		System.out.println("Welcome to ROCK PAPER SCISSORS. I, Computer, will be your opponent.");
		System.out.print("Please type in your name and press return: ");
		String playerName = keyboard.nextLine();
		System.out.println("");
		System.out.println("Welcome: " + playerName);
		return playerName;
	}

	public static int getNumberOfRounds(Scanner keyboard, String playerName) {
		System.out.println("");
		System.out.print("All right " + playerName + ".");
		System.out.println(" How many rounds would you like to play? ");
		System.out.print("Enter the number of rounds you want to play and press return: ");
		return keyboard.nextInt();
	}

	// Play the specified number of rounds
	public static void playRounds(String playerName, int totalRounds, RandomPlayer computerPlayer, Scanner keyboard,
			Testing gameInstance) {
		int userWins = 0;
		int computerWins = 0;
		int draws = 0;

		for (int round = 1; round <= totalRounds; round++) {
			System.out.println("\nRound " + round + ":");

			int playerChoice = getPlayerChoice(keyboard, playerName);
			int computerChoice = computerPlayer.getComputerChoice();

			// Determine the result of the round
			String result = gameInstance.getResult(playerName, playerChoice, computerChoice);

			// Update win counters
			if (result.contains("You win")) {
				userWins++;
			} else if (result.contains("I win")) {
				computerWins++;
			} else {
				draws++;
			}

			// Print the result
			System.out.println(result);
		}

		// Summary Results
		displaySummary(playerName, userWins, computerWins, draws, totalRounds);
	}

	// Player Input
	public static int getPlayerChoice(Scanner keyboard, String playerName) {
		System.out.println(playerName + ", please enter your choice for this round.");
		System.out.print("1 for ROCK, 2 for PAPER, and 3 for SCISSORS: ");
		int choice = keyboard.nextInt();
		while (choice < ROCK || choice > SCISSORS) {
			System.out.println("Invalid choice. Please choose 1 for ROCK, 2 for PAPER, or 3 for SCISSORS.");
			choice = keyboard.nextInt();
		}
		return choice;
	}

	// Convert choice integer to string
	public static String convertChoiceToString(int choice) {
		switch (choice) {
		case ROCK:
			return "ROCK";
		case PAPER:
			return "PAPER";
		case SCISSORS:
			return "SCISSORS";
		default:
			return "Invalid choice";
		}
	}

	// The logic of rock, paper, scissors
	public String getResult(String playerName, int playerChoice, int computerChoice) {
		final int ROCK = 1;
		final int PAPER = 2;
		final int SCISSORS = 3;

		// Determine the result
		if ((playerChoice == ROCK && computerChoice == SCISSORS)) {
			return "Computer picked SCISSORS, " + playerName + " picked ROCK.\n\nROCK breaks SCISSORS. You win.";
		} else if ((playerChoice == SCISSORS && computerChoice == PAPER)) {
			return "Computer picked PAPER, " + playerName + " picked SCISSORS.\n\nSCISSORS cut PAPER. You win.";
		} else if ((playerChoice == PAPER && computerChoice == ROCK)) {
			return "Computer picked ROCK, " + playerName + " picked PAPER.\n\nPAPER covers ROCK. You win.";
		} else if (playerChoice == computerChoice) {
			// Handle tie case
			String choiceName = (playerChoice == ROCK) ? "ROCK" : (playerChoice == PAPER) ? "PAPER" : "SCISSORS";
			return "Computer picked " + choiceName + ", " + playerName + " picked " + choiceName
					+ ".\n\nWe picked the same thing! This round is a draw.";
		} else {
			// Computer wins
			String computerChoiceName = (computerChoice == ROCK) ? "ROCK"
					: (computerChoice == PAPER) ? "PAPER" : "SCISSORS";
			String playerChoiceName = (playerChoice == ROCK) ? "ROCK" : (playerChoice == PAPER) ? "PAPER" : "SCISSORS";
			String winMessage = "";
			if (computerChoice == ROCK && playerChoice == SCISSORS) {
				winMessage = "ROCK breaks SCISSORS. I win.";
			} else if (computerChoice == PAPER && playerChoice == ROCK) {
				winMessage = "PAPER covers ROCK. I win.";
			} else if (computerChoice == SCISSORS && playerChoice == PAPER) {
				winMessage = "SCISSORS cut PAPER. I win.";
			}
			return "Computer picked " + computerChoiceName + ", " + playerName + " picked " + playerChoiceName + ".\n\n"
					+ winMessage;
		}
	}

	// Display the summary after all rounds
	public static void displaySummary(String playerName, int userWins, int computerWins, int draws, int totalRounds) {
		System.out.println("");
		System.out.println("");
		System.out.println("Number of games of ROCK PAPER SCISSORS: " + totalRounds);
		System.out.println("Number of times Computer won: " + computerWins);
		System.out.println("Number of times " + playerName + " won: " + userWins);
		System.out.println("Number of draws: " + draws);

		if (userWins > computerWins) {
			System.out.println("You, " + playerName + ", are a master at ROCK, PAPER, SCISSORS.");
		} else if (computerWins > userWins) {
			System.out.println("I, Computer, am a master at ROCK, PAPER, SCISSORS.");
		} else {
			System.out.println("We are evenly matched.");
		}
	}
}