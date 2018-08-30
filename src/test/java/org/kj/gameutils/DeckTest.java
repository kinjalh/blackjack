package org.kj.gameutils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.kj.config.Settings;

public class DeckTest {

	@Test
	public void testShuffle() {
		Deck deck = new Deck();
		deck.shuffle();
		boolean allSame = true;
		int val = deck.dealCard();
		for (int i = 0; i < 3; i++) {
			if (deck.dealCard() != val) {
				allSame = false;
			}
		}
		assertTrue(!allSame);
	}

	@Test
	public void testUnshuffledDeck() {
		Deck deck = new Deck();
		for (int val = Settings.MIN_CARD_VALUE; val <= Settings.INITIAL_HAND_SIZE; val++) {
			for (int freq = 0; freq < Settings.NUM_EACH_CARD; freq++) {
				assertTrue(deck.dealCard() == val);
			}
		}
	}
}
