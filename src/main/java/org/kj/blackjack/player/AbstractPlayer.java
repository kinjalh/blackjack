package org.kj.blackjack.player;

import java.util.Collection;

import org.kj.blackjack.gameio.OutputDevice;
import org.kj.blackjack.gameutils.Action;

/**
 * Contains utility methods for a Blackjack player as well as abstract methods
 * for the player's most complex action, which is to choose either HIT or STAND.
 * 
 * @author Kinjal
 *
 */
public abstract class AbstractPlayer implements Player {

	private String name;
	private Collection<Integer> hand;
	private OutputDevice output;

	/**
	 * Constructs a Player with the given name
	 * 
	 * @param name   the player's name
	 * @param output the output device this player will use to display its own
	 *               information
	 */
	public AbstractPlayer(String name, OutputDevice output) {
		this.name = name;
		this.output = output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kj.blackjack.player.Player#setHand(java.util.Collection)
	 */
	@Override
	public void setHand(Collection<Integer> hand) {
		this.hand = hand;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kj.blackjack.player.Player#addCard(int)
	 */
	@Override
	public void addCard(int val) {
		hand.add(val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kj.blackjack.player.Player#sum()
	 */
	@Override
	public int sum() {
		int sum = 0;
		for (int val : hand) {
			sum += val;
		}
		return sum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kj.blackjack.player.Player#handSize()
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kj.blackjack.player.Player#name()
	 */
	@Override
	public String name() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kj.blackjack.player.Player#handToString()
	 */
	@Override
	public String handToString() {
		return hand.toString();
	}

	/**
	 * Returns the output device of this Player
	 */
	OutputDevice getOutput() {
		return output;
	}

	/*
	 * Plays turn by returning either Action.HIT or Action.STAND.
	 * 
	 * @param opponentHandSize the number of cards the opponent has
	 */
	@Override
	public abstract Action playTurn(int opponentHandSize);

	/*
	 * Displays the state of this Player during the game. Only reveals whatever
	 * information should be seen by other players.
	 */
	@Override
	public abstract void displayDuringGame();

	/*
	 * Displays the state of this Player after the game. Shows all relevant
	 * information (name and hand)
	 */
	@Override
	public abstract void displayAfterGame();
}
