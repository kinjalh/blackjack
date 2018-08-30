package org.kj.player;

import java.util.Collection;

import org.kj.gameutils.Action;

/**
 * Contains utility methods for a Blackjack player as well as abstract methods
 * for the player's most complex action, which is to choose either HIT or STAND.
 * 
 * @author Kinjal
 *
 */
public abstract class Player {

	private String name;
	private Collection<Integer> hand;

	/**
	 * Constructs a Player with the given name
	 * 
	 * @param name the player's name
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * Sets the player's hand to the specified Collection of Integers (cards).
	 * 
	 * @param hand the Integers (cards) the player is holding
	 */
	public void setHand(Collection<Integer> hand) {
		this.hand = hand;
	}

	/**
	 * Adds the specified card to the Player's hand.
	 * 
	 * @param val the value of the card to add
	 */
	public void addCard(int val) {
		hand.add(val);
	}

	/**
	 * Sums the values of all the cards the player has in their hand.
	 * 
	 * @return the sum
	 */
	public int sum() {
		int sum = 0;
		for (int val : hand) {
			sum += val;
		}
		return sum;
	}

	/**
	 * Returns the hand size (number of cards the Player is holding).
	 * 
	 * @return the hand size
	 */
	public int handSize() {
		return hand.size();
	}

	/**
	 * Returns the player's hand (The collection of cards). This should not be
	 * viewed by other players.
	 * 
	 * @return the hand
	 */
	Collection<Integer> getHand() {
		return hand;
	}

	/**
	 * Returns the player's name
	 * 
	 * @return the name
	 */
	public String name() {
		return name;
	}

	/**
	 * Returns the hand as a String. See {@link Collection}
	 * 
	 * @return string representation of hand
	 */
	public String handToString() {
		return hand.toString();
	}

	/**
	 * Returns either Action.HIT or Action.STAND. How the player chooses one or the
	 * other is dependent on implementation.
	 * 
	 * @param opponentHandSize number of cards opponent has
	 * @return Action.HIT or Action.STAND
	 */
	public abstract Action playTurn(int opponentHandSize);
}
