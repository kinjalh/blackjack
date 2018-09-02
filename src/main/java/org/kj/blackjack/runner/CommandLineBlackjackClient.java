package org.kj.blackjack.runner;

import org.kj.blackjack.gameio.CommandLineInput;
import org.kj.blackjack.gameio.CommandLineOutput;
import org.kj.blackjack.gameio.InputDevice;
import org.kj.blackjack.gameio.OutputDevice;
import org.kj.blackjack.gameutils.GameStats;
import org.kj.blackjack.player.Player;
import org.kj.blackjack.player.PlayerFactory;
import org.kj.blackjack.player.PlayerType;

/**
 * Main class. Starts blackjack games from the command line. Lets the user
 * select the types of both player and their names. Then plays the specified
 * number of games.
 * 
 * @author Kinjal
 *
 */
public class CommandLineBlackjackClient implements BlackjackClient {

	/**
	 * Gets input from user from command line. Asks for Player 1 and Player 2's
	 * type, name, and the total number of games to play. Runs the specified number
	 * of games.
	 */
	@Override
	public void run() {
		InputDevice input = new CommandLineInput();
		OutputDevice output = new CommandLineOutput();

		Player p1 = getPlayer(input, output);
		Player p2 = getPlayer(input, output);

		output.promptNumGames();
		int numGames = input.getNum(1);

		BlackjackEngine engine = new BlackjackEngine();
		GameStats stats = new GameStats();
		BlackjackManager manager = new BlackjackManager(output, p1, p2, engine, stats);

		manager.playGames(numGames);
	}

	/**
	 * Gets a PlayerType and returns a corresponding player
	 * 
	 * @param input  the input device to get PlayerType
	 * @param output to prompt for PlayerType
	 * @return a Player of the specified type
	 */
	Player getPlayer(InputDevice input, OutputDevice output) {
		output.promptForPlayerType();
		PlayerType p1Type = input.getPlayerType();
		output.promptForName();
		String name = input.getStr();
		switch (p1Type) {
		case HUMAN_PLAYER:
			return PlayerFactory.getHumanPlayer(name, input, output);
		case SAFEPLAYBOT:
			return PlayerFactory.getSafePlayBot(name, output);
		default:
			return null;
		}
	}

	/**
	 * Constructs a new CommandLineBlackjackClient and runs.
	 * 
	 * @param args args is not used
	 */
	public static void main(String[] args) {
		BlackjackClient cmd = new CommandLineBlackjackClient();
		cmd.run();
	}
}
