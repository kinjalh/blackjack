package org.kj.blackjack.player;

import org.kj.blackjack.gameio.InputDevice;
import org.kj.blackjack.gameio.OutputDevice;
import org.kj.blackjack.gameutils.Action;

/**
 * Interacts with a person through command line. Prompts player for their
 * {@link Action} and displays information to them. At each stage the player is
 * shown their own hand and the number of cards their opponent is currently
 * holding.
 * 
 * @author Kinjal
 *
 */
class HumanPlayer extends AbstractPlayer {

	private InputDevice input;

	/**
	 * Constructs a HumanPlayer with the specified name. The scanner is used to get
	 * input from the player.
	 * 
	 * @param name  the name of the player
	 * @param input the scanner that gets player input
	 */
	public HumanPlayer(String name, InputDevice input, OutputDevice output) {
		super(name, output);
		this.input = input;
	}

	/**
	 * Displays the player's current hand and the number of cards the opponent has.
	 * Then the player is prompted to choose either hit or stand.
	 * 
	 * @param opponentHandSize the number of cards the opponent has
	 * @return either Action.HIT or Action.STAND depending on the player choice
	 */
	@Override
	public Action playTurn(int opponentHandSize) {
		return input.getAction();
	}

	/**
	 * Shows the name and hand of this player. Since this is a human player, the
	 * hand and name must be visually seen by the human playing.
	 */
	@Override
	public void displayDuringGame() {
		getOutput().displayHand(name(), getHand());
	}

	/**
	 * Displays the name and hand of this player.
	 */
	@Override
	public void displayAfterGame() {
		getOutput().displayHand(name(), getHand());
	}
}
