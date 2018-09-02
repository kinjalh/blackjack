package org.kj.blackjack.config;

/**
 * Stores the values of game constants. Includes the target sum, starting hand
 * size, minimum and maximum card value, number of each card in deck, and
 * maximum deck size. The constants have default values but can also be set by
 * reading in from a file. The constants can also be reset to their default
 * values at any time.
 * 
 * @author Kinjal
 *
 */
public class Settings {

	/**
	 * The target sum. If a player's hand equals this sum then they win the game (or
	 * tie if the other player also got the same sum). If the player gets over the
	 * target sum, then the player busts. Default value 21.
	 */
	public static final int TARGET_SUM = 21;

	/**
	 * The number of cards initially dealt to each player. Default value 2.
	 */
	public static final int INITIAL_HAND_SIZE = 2;

	/**
	 * The minimum value of a card in a deck. Default value 1 (represents an ace).
	 */
	public static final int MIN_CARD_VALUE = 1;

	/**
	 * The maximum value of a card in a deck. Default value 13 (represents a king).
	 */
	public static final int MAX_CARD_VALUE = 13;

	/**
	 * The number of each card value in a deck. Default value 4.
	 */
	public static final int NUM_EACH_CARD = 4;

	/**
	 * The maximum number of cards that may be in a deck. In essence the number of
	 * cards a deck has before any are dealt out. Default value 52.
	 */
	public static final int MAX_DECK_SIZE = 52;
}
