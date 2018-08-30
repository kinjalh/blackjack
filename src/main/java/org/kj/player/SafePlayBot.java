package org.kj.player;

import java.util.Collection;

import org.kj.gameutils.Action;
import org.kj.config.Settings;

/**
 * A bot that plays its turn to prioritize not busting (going over
 * Settings.TARGET_SUM). The bot calculates the probability of busting and then
 * chooses to hit only if the probability is less than a certain probability
 * threshold.
 * 
 * @author Kinjal
 *
 */
class SafePlayBot extends Player {

	private double PROBABILITY_THRESHOLD;

	/**
	 * Constructs a bot with the given name. The probability threshold for hitting
	 * or standing is set to a value of 0.5 or 50%.
	 * 
	 * @param name the name of the bot
	 */
	SafePlayBot(String name) {
		super (name);
		PROBABILITY_THRESHOLD = 0.5;
	}

	/**
	 * Constructs a bot with the given probability threshold and name.
	 * 
	 * @param name      the name of the bot
	 * @param threshold the maximum tolerated chance of bust for which the bot will
	 *                  still hit
	 */
	SafePlayBot(String name, double threshold) {
		super(name);
		PROBABILITY_THRESHOLD = threshold;
	}

	// --------------------------------------------------------------------------

	/**
	 * Calculates the chance of a bust if another card is added to the hand. This
	 * assumes a standard deck size (52 total cards, some of which are in players
	 * hands), with aces = 1 and king = 13. This also computes using the worst case
	 * assumption that all of the cards that can make you bust are still in the deck
	 * (can be drawn and thus bust you) rather than in the opponents hand (thus
	 * keeping them away from you).
	 * 
	 * @param cards            the hand to find a chance of bust for
	 * @param opponentHandSize the number of cards the opponent is holding
	 * @return the probability of busting if a card is added from the deck to the
	 *         hand
	 */
	double chanceOfBust(Collection<Integer> cards, int opponentHandSize) {
		int count = 0;
		for (int i = Settings.MIN_CARD_VALUE; i <= Settings.MAX_CARD_VALUE; i++) {
			int freq = Settings.NUM_EACH_CARD;
			// if the card from the deck is in bot's hand, then its frequency in the deck
			// must be
			// lower than the original deck frequency (4)
			for (int val : cards) {
				if (val == i) {
					freq--;
				}
			}

			// if card value i will lead to a bust, then update count with i's frequency
			if (sum() + i > Settings.TARGET_SUM) {
				count += freq;
			}
		}

		double chance = (count + 0.0) / (Settings.MAX_DECK_SIZE - opponentHandSize - cards.size());
		if (chance > 1.0) {
			return 1.0;
		} else {
			return chance;
		}
	}

	/**
	 * Returns either an Action.HIT or Action.STAND depending on the chance of
	 * busting. If the current hand totals to exactly 21, then this bot has won or
	 * tied with another winner, so it returns Action.STAND. If the sum of the hand
	 * is so low that any card can be drawn without busting, the method returns
	 * Action.HIT. Otherwise, calculates the chance of bust if a card is added. If
	 * the chance of bust is lower than the probability threshold (50% default),
	 * then returns Action.HIT. If the chance of bust is greater than 50%, returns
	 * Action.STAND.
	 * 
	 * @param opponentHandSize the number of cards opponent has
	 * @return Action.HIT or Action.STAND depending on the results of the described
	 *         evaluation
	 */
	@Override
	public Action playTurn(int opponentHandSize) {
		// if got 21 exactly, then stand and win the game
		if (sum() == Settings.TARGET_SUM) {
			return Action.STAND;
			// if a hit with the max card value (13 a.k.a King) won't bust, then hit
		} else if (sum() + Settings.MAX_CARD_VALUE <= Settings.TARGET_SUM) {
			return Action.HIT;
		} else {
			// if chance of bust after hit is less than threshold, hit. Else stand
			if (chanceOfBust(getHand(), opponentHandSize) < PROBABILITY_THRESHOLD) {
				return Action.HIT;
			} else {
				return Action.STAND;
			}
		}
	}
}
