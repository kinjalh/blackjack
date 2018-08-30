package org.kj.runner;

import java.util.ArrayList;
import java.util.List;

import org.kj.config.Settings;
import org.kj.gameutils.Action;
import org.kj.gameutils.Deck;
import org.kj.player.Player;

/**
 * Pits Players against each other in Blackjack games. Provides options
 * to play a set number of games, a single game, unlimited games, and can keep
 * track of statistics such as the number of times each player wins, loses, busts, or
 * ties.
 * 
 * @author Kinjal
 *
 */
abstract class BlackjackManager {

	/**
	 * Checks if the game is over. If both players have busted or if both players
	 * have chosen to stand (not receive any more cards), then the game is over.
	 * Otherwise it is not over.
	 * 
	 * @param p1       the first player
	 * @param p1Action the first player's choice of Action (either HIT or STAND)
	 * @param p2       the second player
	 * @param p2Action the second player's choice of Action (either HIT or STAND)
	 * @return true if the game is over, false otherwise
	 */
	boolean isGameOver(Player p1, Action p1Action, Player p2, Action p2Action) {
		if (p1Action.equals(Action.STAND) && p2Action.equals(Action.STAND)) {
			return true;
		}
		if (p1.sum() > Settings.TARGET_SUM || p2.sum() > Settings.TARGET_SUM) {
			return true;
		}
		return false;
	}

	/**
	 * Deals a hand to the player. The number of cards dealt is the number specified
	 * by Settings.INITIAL_HAND_SIZE.
	 * 
	 * @param deck the deck to deal from
	 * @param p    the player to deal to
	 */
	void dealHand(Deck deck, Player p) {
		List<Integer> hand = new ArrayList<Integer>();
		for (int i = 0; i < Settings.INITIAL_HAND_SIZE; i++) {
			hand.add(deck.dealCard());
		}
		p.setHand(hand);
	}

	/**
	 * Plays the specified number of games between players p1 and p2. The game
	 * should follow the rules described in {@link README} but how the information
	 * is presented and how input is received is dependent on implementation.
	 * 
	 * @param numGames the number of games to play
	 * @param p1       the first player (this player always does their playTurn
	 *                 before the other)
	 * @param p2       the second player (this player always does their playTurn
	 *                 after the other)
	 */
	abstract void playGames(int numGames, Player p1, Player p2, boolean statTrack);

	/**
	 * Plays one game between players p1 and p2. The game should follow the rules
	 * described in {@link README} but how the information is presented and how
	 * input is received is dependent on implementation.
	 * 
	 * @param p1 the first player (this player always does their playTurn before the
	 *           other)
	 * @param p2 the second player (this player always does their playTurn after the
	 *           other)
	 */
	abstract void playGame(Player p1, Player p2, boolean statTrack);
}
