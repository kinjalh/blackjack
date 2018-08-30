package org.kj.runner;

import org.kj.config.Settings;
import org.kj.gameutils.Action;
import org.kj.gameutils.Deck;
import org.kj.player.Player;

/**
 * Manages game(s) of blackjack between 2 players on the command line. This is
 * played using a player 1 and player 2 system where at player 1 always makes
 * their turn before player 2. Provides option to track wins, losses, busts, and
 * ties for each player. Each instance of this class tracks can track statistics
 * indefinitely. The counters for the statistics can be reset to 0 at any time.
 * 
 * @author Kinjal
 *
 */
class CommandLineBlackjackManager extends BlackjackManager {

	private int p1Wins;
	private int p2Wins;
	private int p1Bust;
	private int p2Bust;
	private int p1Loss;
	private int p2Loss;
	private int tieGame;

	CommandLineBlackjackManager() {
		zeroStats();
	}

	/**
	 * Sets all stats (wins, losses, busts, and ties for each player) to 0.
	 */
	void zeroStats() {
		p1Wins = 0;
		p2Wins = 0;
		p1Bust = 0;
		p2Bust = 0;
		p1Loss = 0;
		p2Loss = 0;
		tieGame = 0;
	}

	/**
	 * Plays the specified number of games between the 2 players.
	 * 
	 * @param numGames the number of games to play
	 * @param p1       the first player
	 * @param p2       the second player
	 */
	@Override
	void playGames(int numGames, Player p1, Player p2, boolean statTrack) {
		for (int i = 0; i < numGames; i++) {
			playGame(p1, p2, statTrack);
		}
		if (statTrack) {
			displayStats();
		}
	}

	/**
	 * Plays one game between the 2 players. Each game starts with a fresh deck.
	 * This starts by displaying an introduction (confirms the names of the 2
	 * players). Then the game is played round by round. Player p1 always makes
	 * their move first.
	 * 
	 * @param p1 the first player
	 * @param p2 the second player
	 */
	@Override
	void playGame(Player p1, Player p2, boolean statTrack) {
		displayIntro(p1, p2);

		Deck deck = new Deck();
		deck.shuffle();
		dealHand(deck, p1);
		dealHand(deck, p2);

		boolean gameOver = false;
		do {
			Action p1Action = p1.playTurn(p2.handSize());
			if (p1Action.equals(Action.HIT)) {
				p1.addCard(deck.dealCard());
			}

			Action p2Action = p2.playTurn(p1.handSize());
			if (p2Action.equals(Action.HIT)) {
				p2.addCard(deck.dealCard());
			}

			gameOver = isGameOver(p1, p1Action, p2, p2Action);

			if (gameOver) {
				diagnoseResults(p1, p2, statTrack);
			}

		} while (!gameOver);
	}

	/**
	 * Prints the names of Player 1 and Player 2 and then prints a line of dashes to
	 * the command line.
	 * 
	 * @param p1 the first player
	 * @param p2 the second player
	 */
	void displayIntro(Player p1, Player p2) {
		System.out.println("Player 1 : " + p1.name());
		System.out.println("Player 2 : " + p2.name());
		System.out.println();
	}

	/**
	 * Diagnoses the results of the game, assuming that the game is over. The
	 * possible outcomes are: -Both players get the target score -Both players bust
	 * -One player busts (thus the other wins) -Neither player busts and one has a
	 * higher sum -Neither player busts and they have the same sum
	 * 
	 * @param p1 the first player
	 * @param p2 the second plyer
	 */
	void diagnoseResults(Player p1, Player p2, boolean statTrack) {
		System.out.println("Final hands:");
		System.out.println("\t" + p1.name() + " : " + p1.handToString() + "\tsum : " + p1.sum());
		System.out.println("\t" + p2.name() + " : " + p2.handToString() + "\tsum : " + p2.sum());
		if (p1.sum() == Settings.TARGET_SUM && p2.sum() == Settings.TARGET_SUM) {
			System.out.println("Tie game, both players got target score " + Settings.TARGET_SUM);
			if (statTrack) {
				tieGame++;
			}
		} else if (p1.sum() > Settings.TARGET_SUM && p2.sum() > Settings.TARGET_SUM) {
			System.out.println("Tie game, both players busted");
			if (statTrack) {
				tieGame++;
				p1Bust++;
				p2Bust++;
			}
		} else if (p1.sum() <= Settings.TARGET_SUM && p2.sum() > Settings.TARGET_SUM) {
			System.out.println(p1.name() + " wins since " + p2.name() + " busted");
			if (statTrack) {
				p1Wins++;
				p2Loss++;
				p2Bust++;
			}
		} else if (p1.sum() > Settings.TARGET_SUM && p2.sum() <= Settings.TARGET_SUM) {
			System.out.println(p2.name() + " wins since " + p1.name() + " busted");
			if (statTrack) {
				p1Loss++;
				p1Bust++;
				p2Wins++;
			}
		} else {
			if (p1.sum() > p2.sum()) {
				System.out.println(p1.name() + " wins due to higher sum");
				if (statTrack) {
					p1Wins++;
					p2Loss++;
				}
			} else if (p1.sum() < p2.sum()) {
				System.out.println(p2.name() + " wins due to higher sum");
				if (statTrack) {
					p1Loss++;
					p2Wins++;
				}
			} else {
				System.out.println("Tie game, both players have equal sum");
				if (statTrack) {
					tieGame++;
				}
			}
		}
		System.out.println("\n-----------------------------------------------------\n");
	}

	/**
	 * Displays the number of wins, losses, busts, and ties for each player. Note
	 * that if a player busts and ties the game, the game is counted as both a tie
	 * and a bust. If the player busts and loses the game, the game is counted as
	 * both a loss and a bust.
	 */
	void displayStats() {
		System.out.println("Player 1 :");
		System.out.println("\tWins : " + p1Wins + "\tLoss : " + p1Loss + "\tBust : " 
							+ p1Bust + "\tTie : " + tieGame);
		System.out.println("Player 2 :");
		System.out.println("\tWins : " + p2Wins + "\tLoss : " + p2Loss + "\tBust : " 
							+ p2Bust + "\tTie : " + tieGame);
	}
}
