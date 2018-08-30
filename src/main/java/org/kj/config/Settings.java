package org.kj.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
	public static int TARGET_SUM = 21;

	/**
	 * The number of cards initially dealt to each player. Default value 2.
	 */
	public static int INITIAL_HAND_SIZE = 2;

	/**
	 * The minimum value of a card in a deck. Default value 1 (represents an ace).
	 */
	public static int MIN_CARD_VALUE = 1;

	/**
	 * The maximum value of a card in a deck. Default value 13 (represents a king).
	 */
	public static int MAX_CARD_VALUE = 13;

	/**
	 * The number of each card value in a deck. Default value 4.
	 */
	public static int NUM_EACH_CARD = 4;

	/**
	 * The maximum number of cards that may be in a deck. In essence the number of
	 * cards a deck has before any are dealt out. Default value 52.
	 */
	public static int MAX_DECK_SIZE = 52;

	/**
	 * Reads the specified file and reads in the values for the fields. The names of
	 * the fields must not be modified from their original name. For example, the
	 * property "target_sum" must always be written as such in the configurations
	 * file. The delimiters between the field and the value must be as described in
	 * Properties.
	 * 
	 * @param fileName the name of the file to read properties from
	 */
	public static void loadFromFile(String fileName) {
		Properties props = new Properties();
		try {
			InputStream ins = new FileInputStream(fileName);
			props.load(ins);
		} catch (IOException e) {
			throw new IllegalArgumentException("failed to either find or read file " + fileName);
		}

		TARGET_SUM = Integer.parseInt(props.getProperty("target_sum"));
		INITIAL_HAND_SIZE = Integer.parseInt(props.getProperty("initial_hand_size"));
		MIN_CARD_VALUE = Integer.parseInt(props.getProperty("min_card_value"));
		MAX_CARD_VALUE = Integer.parseInt(props.getProperty("max_card_value"));
		NUM_EACH_CARD = Integer.parseInt(props.getProperty("num_each_card"));
		MAX_DECK_SIZE = Integer.parseInt(props.getProperty("max_deck_size"));
	}

	/**
	 * Restores the fields to their default values.
	 */
	public static void restoreDefaults() {
		TARGET_SUM = 21;
		INITIAL_HAND_SIZE = 2;
		MIN_CARD_VALUE = 1;
		MAX_CARD_VALUE = 13;
		NUM_EACH_CARD = 4;
		MAX_DECK_SIZE = 52;
	}
}
