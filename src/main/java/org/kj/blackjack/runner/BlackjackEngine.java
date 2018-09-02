package org.kj.blackjack.runner;

import java.util.ArrayList;
import java.util.List;

import org.kj.blackjack.config.Settings;
import org.kj.blackjack.gameutils.Action;
import org.kj.blackjack.gameutils.Deck;
import org.kj.blackjack.player.Player;

/**
 * Makes game decisions for blackjack and performs game operations. Uses the
 * values in the Settings class.
 * 
 * @author Kinjal
 *
 */
public class BlackjackEngine {

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
	 * Deals hand of cards from deck to player. This constructs a new hand for the
	 * player, so if they previously had a hand it will be lost.
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
	 * Evaluates the result of the game and returns one of the values from the
	 * GameResult enum.
	 * 
	 * @param p1 the first player
	 * @param p2 the second player
	 * @return a GameResult
	 */
	GameResult evaluateWinner(Player p1, Player p2) {
		if (p1.sum() > Settings.TARGET_SUM && p2.sum() > Settings.TARGET_SUM) {
			return GameResult.TIE_BOTH_BUST;
		} else if (p1.sum() > Settings.TARGET_SUM) {
			return GameResult.PLAYER_TWO_WIN_OPP_BUST;
		} else if (p2.sum() > Settings.TARGET_SUM) {
			return GameResult.PLAYER_ONE_WIN_OPP_BUST;
		} else {
			if (p1.sum() > p2.sum()) {
				return GameResult.PLAYER_ONE_WIN_HIGHER_SUM;
			} else if (p2.sum() > p1.sum()) {
				return GameResult.PLAYER_TWO_WIN_HIGHER_SUM;
			} else {
				return GameResult.TIE_EQUAL_SUM;
			}
		}
	}

	/**
	 * If the player chooses to hit, then deals the player a new card from the deck.
	 * 
	 * @param p      the player
	 * @param choice the Action the player chose
	 * @param deck   the deck to deal from
	 */
	public void play(Player p, Action choice, Deck deck) {
		if (choice.equals(Action.HIT)) {
			p.addCard(deck.dealCard());
		}
	}
}
