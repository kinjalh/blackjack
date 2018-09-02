package org.kj.blackjack.player;

import java.util.Collection;

import org.kj.blackjack.gameutils.Action;

/**
 * Player of blackjack game. Each player has a name and hand.
 * 
 * @author Kinjal
 *
 */
public interface Player {

	/**
	 * Sets the player's hand to the specified Collection of Integers (cards).
	 * 
	 * @param hand the Integers (cards) the player is holding
	 */
	void setHand(Collection<Integer> hand);

	/**
	 * Adds the specified card to the Player's hand.
	 * 
	 * @param val the value of the card to add
	 */
	void addCard(int val);

	/**
	 * Sums the values of all the cards the player has in their hand.
	 * 
	 * @return the sum
	 */
	int sum();

	/**
	 * Returns the hand size (number of cards the Player is holding).
	 * 
	 * @return the hand size
	 */
	int handSize();

	/**
	 * Returns the player's name
	 * 
	 * @return the name
	 */
	String name();

	/**
	 * Returns the hand as a String. See {@link Collection}
	 * 
	 * @return string representation of hand
	 */
	String handToString();

	/**
	 * Returns either Action.HIT or Action.STAND. How the player chooses one or the
	 * other is dependent on implementation.
	 * 
	 * @param opponentHandSize number of cards opponent has
	 * @return Action.HIT or Action.STAND
	 */
	Action playTurn(int opponentHandSize);

	/**
	 * Displays information during the game. This display should show only
	 * information that is essential for the current state of the game.
	 */
	void displayDuringGame();

	/**
	 * Displays information after game. This should show the player's name and hand.
	 */
	void displayAfterGame();

}