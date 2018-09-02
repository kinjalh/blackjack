package org.kj.blackjack.gameio;

import java.util.Collection;

import org.kj.blackjack.player.Player;
import org.kj.blackjack.runner.GameResult;

/**
 * Prints outputs to command line. Does not read input at all.
 * @author Kinjal
 *
 */
public class CommandLineOutput implements OutputDevice {

	/**
	 * Prints the names of Player 1 and Player 2 in the format:
	 * 
	 * @param p1 Player 1
	 * @param p2 Player 2
	 */
	@Override
	public void displayIntro(Player p1, Player p2) {
		System.out.println("Player 1 : " + p1.name());
		System.out.println("Player 2 : " + p2.name());
	}

	/**
	 * Prompts the player to input 'h' or 's' to hit or stand (respectively). Tells
	 * user that input is not case-sensitive.
	 */
	@Override
	public void promptForAction() {
		System.out.println("Input 'h' to hit or 's' to stand (not case-sensetive)");
	}

	/**
	 * Displays the game result. Prints which player won (or they tied) and why the
	 * result was what it was (opponent busted or had higher sum).
	 * 
	 * @param p1     the name of Player 1
	 * @param p2     the name of Player 2
	 * @param result the result of the game
	 */
	@Override
	public void displayResults(String p1, String p2, GameResult result) {
		switch (result) {
		case TIE_BOTH_BUST:
			System.out.println("TIE: both players busted");
			break;
		case TIE_EQUAL_SUM:
			System.out.println("TIE: both equal sum");
			break;
		case PLAYER_ONE_WIN_OPP_BUST:
			System.out.println(p1 + " WINS: " + p2 + " busted");
			break;
		case PLAYER_TWO_WIN_OPP_BUST:
			System.out.println(p2 + " WINS: " + p1 + " busted");
			break;
		case PLAYER_ONE_WIN_HIGHER_SUM:
			System.out.println(p1 + " WINS: due to higher sum");
			break;
		case PLAYER_TWO_WIN_HIGHER_SUM:
			System.out.println(p2 + " WINS: due to higher sum");
			break;
		default:
			break;
		}
	}

	/**
	 * Prints the name and hand.
	 * 
	 * @param name the name of the player who has the hand
	 * @param hand the hand of cards to print
	 */
	@Override
	public void displayHand(String name, Collection<Integer> hand) {
		System.out.println(name + "'s hand : " + hand.toString());
	}

	/**
	 * Prints the name of the player and their hand size
	 * 
	 * @param name     the name of the player
	 * @param handSize the number of cards the player has in their hand
	 */
	@Override
	public void displayHandSize(String name, int handSize) {
		System.out.println(name + " has " + handSize + " cards");
	}

	/**
	 * Prints a blank line (used to separate chunks of printing, such as between the
	 * turns of the players)
	 */
	@Override
	public void splitTurn() {
		System.out.println();
	}

	/**
	 * Prints a line of dashes. Used to denote the end of one game
	 */
	@Override
	public void endOfGame() {
		System.out.println("-----------------------------------------------");
	}

	/**
	 * Prompts an input of 'hp' for humand player type and 'spb' for SafePlayBot.
	 * This does not read the input, only prints the prompt
	 */
	@Override
	public void promptForPlayerType() {
		System.out.println("Player types : 'hp' for human player or 'spb' for SafePlayBot");
	}

	/**
	 * Prompts for a name to be entered. This does not read the input, only prints
	 * the prompt.
	 */
	@Override
	public void promptForName() {
		System.out.println("Enter name : ");
	}

	/**
	 * Prompts for the number of games. This does not read the input, only prints
	 * the prompt.
	 */
	@Override
	public void promptNumGames() {
		System.out.println("Number of games : ");
	}

	/**
	 * Prints the game statistics. Shows the occurrence of each type of game result
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
	@Override
	public void displayStats(int p1WinHigherSum, int p1WinOppBust, int p2WinHigherSum, int p2WinOppBust,
			int tieGameEqualSum, int tieBothBust) {
		System.out.println("Player 1's Wins : ");
		System.out.println("\tWins due to higher sum : " + p1WinHigherSum + "\tWins due to opp bust : " + p1WinOppBust);
		System.out.println("Player 2's Wins : ");
		System.out.println("\tWins due to higher sum : " + p2WinHigherSum + "\tWins due to opp bust : " + p2WinOppBust);
		System.out.println("Tie games : ");
		System.out.println("\tTie equal sum : " + tieGameEqualSum + "\tTie both bust : " + tieBothBust);
	}
}
