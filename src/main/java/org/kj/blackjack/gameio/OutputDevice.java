package org.kj.blackjack.gameio;

import java.util.Collection;

import org.kj.blackjack.player.Player;
import org.kj.blackjack.runner.GameResult;

/**
 * Displays outputs to an implementation-dependent output source.
 * 
 * @author Kinjal
 *
 */
public interface OutputDevice {

	/**
	 * Displays an introduction with the Players' names
	 * 
	 * @param p1 the first player
	 * @param p2 the second player
	 */
	public void displayIntro(Player p1, Player p2);

	/**
	 * Prompts for an input action.
	 */
	public void promptForAction();

	/**
	 * Displays the result of the game
	 * 
	 * @param p1     the name of Player 1
	 * @param p2     the name of Player 2
	 * @param result the result of the game
	 */
	public void displayResults(String p1, String p2, GameResult result);

	/**
	 * Displays the name and the corresponding hand of cards.
	 * 
	 * @param name the name of the player
	 * @param hand the cards in the player's hand
	 */
	public void displayHand(String name, Collection<Integer> hand);

	/**
	 * Displays the name and the corresponding hand size.
	 * 
	 * @param name     the name of the player
	 * @param handSize the number of cards in the player's hand
	 */
	void displayHandSize(String name, int handSize);

	/**
	 * Displays an indication that it is now a different player's turn.
	 */
	public void splitTurn();

	/**
	 * Displays an indication that the current game is over.
	 */
	public void endOfGame();

	/**
	 * Prompts for an input corresponding to a PlayerType
	 */
	public void promptForPlayerType();

	/**
	 * Prompts for an input name
	 */
	public void promptForName();

	/**
	 * Prompts for the number of games
	 */
	public void promptNumGames();

	/**
	 * Displays the game statistics. Shows the occurrence of each type of game result
	 * in GameResult.
	 * 
	 * @param p1WinHigherSum  the number of times Player 1 won due to a higher sum
	 * @param p1WinOppBust    the number of times Player 1 won due to opponent
	 *                        busting
	 * @param p2WinHigherSum  the number of times Player 2 won due to a higher sum
	 * @param p2WinOppBust    the number of times Player 2 won due to opponent
	 *                        busting
	 * @param tieGameEqualSum the number of times the game was tied by both players
	 *                        having equal scores and not busting
	 * @param tieBothBust     the number of times the game was tied by both players
	 *                        busting
	 */
	public void displayStats(int p1WinHigherSum, int p1WinOppBust, int p2WinHigherSum, int p2WinOppBust,
			int tieGameEqualSum, int tieBothBust);
}
