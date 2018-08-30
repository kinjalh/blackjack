package org.kj.runner;

import java.util.Scanner;

import org.kj.config.Settings;
import org.kj.player.Player;
import org.kj.player.PlayerFactory;

/**
 * Allows use of the command line to play games of blackjack. The user is
 * allowed to pit humans or bots against each other. The user also gets to
 * choose how many games of blackjack are played and if game statistics are
 * tracked or not.
 * 
 * @author Kinjal
 *
 */
class CommandLineBlackjackClient implements BlackjackClient {

	/**
	 * Pits human player against SafePlayBot. The bot's name is set to bot and the
	 * human is player 1 (the human makes their moves before the bot).
	 * 
	 * @param numGames     the number of games to be played
	 * @param statTracking true if game statistics should be tracked
	 */
	void playHumanVersusSafePlayBot(int numGames, boolean statTracking, Scanner keyboard) {
		Player human = PlayerFactory.getHumanPlayer(keyboard);
		Player bot = PlayerFactory.getSafePlayBot("bot");
		BlackjackManager game = new CommandLineBlackjackManager();
		game.playGames(numGames, human, bot, statTracking);
	}

	/**
	 * Pits 2 human players versus each other. Note that to do this on a single
	 * computer, the 2 players must share the same command line.
	 * 
	 * @param numGames     the number of games to be played
	 * @param statTracking true if game statistics should be tracked
	 */
	void playHumanVersusHuman(int numGames, boolean statTracking, Scanner keyboard) {
		Player human1 = PlayerFactory.getHumanPlayer(keyboard);
		Player human2 = PlayerFactory.getHumanPlayer(keyboard);
		BlackjackManager game = new CommandLineBlackjackManager();
		game.playGames(numGames, human1, human2, statTracking);
	}

	/**
	 * Pits 2 SafePlayBots against each other. The bots are named bot1 and bot2,
	 * with bot1 going first each turn.
	 * 
	 * @param numGames     the number of games to be played
	 * @param statTracking true if game statistics should be tracked
	 */
	void playSafePlayBotVersusSafePlayBot(int numGames, boolean statTracking) {
		Player bot1 = PlayerFactory.getSafePlayBot("bot1");
		Player bot2 = PlayerFactory.getSafePlayBot("bot2");
		BlackjackManager game = new CommandLineBlackjackManager();
		game.playGames(numGames, bot1, bot2, statTracking);
	}

	/**
	 * Prompts the user for the type of game to be played (bot v. bot, person v.
	 * person, person v. bot). Then prompts the user for the number of games and
	 * asks if statistics should be tracked or not. After the specified number of
	 * games have been played, the user is prompted again. This cycle repeats until
	 * the user terminates the program.
	 */
	@Override
	public void run() {
		Settings.loadFromFile("../conf/blackjack.properties");
		Scanner keyboard = new Scanner(System.in);
		printIntro();
		while (true) {
			int gameChoice = getGameChoice(keyboard);
			int numGames = getNumGames(keyboard);
			boolean statTracking = getStatTracking(keyboard);
			switch (gameChoice) {
			case 1:
				playHumanVersusSafePlayBot(numGames, statTracking, keyboard);
				break;
			case 2:
				playHumanVersusHuman(numGames, statTracking, keyboard);
				break;
			case 3:
				playSafePlayBotVersusSafePlayBot(numGames, statTracking);
				break;
			default:
				System.out.println("exiting game");
				break;
			}
		}
	}

	/**
	 * Prints the Welcome to Blackjack message.
	 */
	void printIntro() {
		System.out.println("Welcome to 2-player command line blackjack");
	}

	/**
	 * Prompts the user to enter what type of game they want (bot v bot, person v
	 * person, person v bot). If the user does not enter a valid choice, prompts the
	 * user again.
	 * 
	 * @param scan the scanner to get user input
	 * @return 1 for person vs bot, 2 for person vs person, 3 for bot vs bot
	 */
	int getGameChoice(Scanner scan) {
		System.out.println("Enter 1 to play human(you) against SafePlayBot");
		System.out.println("Enter 2 to play human versus human");
		System.out.println("Enter 3 to pit 2 SafePlayBots against each other");
		System.out.println("Enter choice here : ");
		int choice = scan.nextInt();
		if (choice < 1 || choice > 3) {
			return getGameChoice(scan);
		} else {
			return choice;
		}
	}

	/**
	 * Prompts the user for the number of games. Note that if the user enters a
	 * number 0 or less, the program will proceed but no games will be played.
	 * 
	 * @param scan the scanner to get user input
	 * @return the number of games to play
	 */
	int getNumGames(Scanner scan) {
		System.out.println("How many games would you like to play : ");
		return scan.nextInt();
	}

	/**
	 * Prompts the user to enter true or false to track statistics for the games.
	 * 
	 * @param scan the scanner to get user input
	 * @return the number of games to play
	 */
	boolean getStatTracking(Scanner scan) {
		System.out.println("Would you like to track game statistics? Enter true or false : ");
		return scan.nextBoolean();
	}

	/**
	 * Creates an instance of CommandLineBlackjackClient and runs.
	 */
	public static void main(String[] args) {
		BlackjackClient client = new CommandLineBlackjackClient();
		client.run();
	}
}
