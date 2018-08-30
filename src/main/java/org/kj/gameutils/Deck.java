package org.kj.gameutils;

import java.util.ArrayList;
import java.util.List;

import org.kj.config.Settings;

/**
 * Deck of cards with integer values for the cards. Thus cards have the value
 * but no suite. Uses {@link Settings} for the minimum value of cards, maximum
 * value of cards, and number of each card in the deck.
 * 
 * @author Kinjal
 *
 */
public class Deck {

	private List<Integer> deck;

	/**
	 * Creates a deck and populates it based on the fields in {@link Settings}.
	 */
	public Deck() {
		deck = new ArrayList<Integer>();
		fillDeck();
	}

	/**
	 * Fills the deck with Settings.NUM_EACH_CARD of each card ranging from
	 * Settings.MIN_CARD_VALUE to SETTINGS.MAX_CARD_VALUE.
	 */
	private void fillDeck() {
		for (int val = Settings.MIN_CARD_VALUE; val <= Settings.MAX_CARD_VALUE; val++) {
			for (int freq = 0; freq < Settings.NUM_EACH_CARD; freq++) {
				deck.add(val);
			}
		}
	}

	/**
	 * Randomly shuffles the deck.
	 */
	public void shuffle() {
		for (int i = deck.size() - 1; i >= 0; i--) {
			int swapInd = (int) (Math.random() * i);	// get a random index
			int tmp = deck.get(swapInd);	// swap it with the current card
			deck.set(swapInd, deck.get(i));
			deck.set(i, tmp);
		}
	}

	/**
	 * Checks if the deck is empty or not.
	 * @return true if deck is empty, false otherwise
	 */
	public boolean isEmpty() {
		return deck.isEmpty();
	}

	/**
	 * Removes a card from the deck and returns it.
	 * @return a card that has been removed from the deck.
	 * @throws IllegalStateException if the deck is empty.
	 */
	public int dealCard() {
		if (isEmpty()) {
			throw new IllegalStateException("The deck is empty");
		}
		return deck.remove(0);
	}
}
