package org.kj.player;

import java.util.Collection;
import java.util.Scanner;

import org.kj.gameutils.Action;

/**
 * Interacts with a person through command line. Prompts player for their
 * {@link Action} and displays information to them. At each stage the player is
 * shown their own hand and the number of cards their opponent is currently
 * holding.
 * 
 * @author Kinjal
 *
 */
class HumanPlayer extends Player {

	private Scanner scan;

	/**
	 * Constructs a HumanPlayer with the specified name. The scanner is used to get
	 * input from the player.
	 * 
	 * @param name the name of the player
	 * @param scan the scanner that gets player input
	 */
	public HumanPlayer(String name, Scanner scan) {
		super(name);
		this.scan = scan;
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
		displayInformation(getHand(), opponentHandSize);
		return getInput();
	}

	/**
	 * Promps the player to enter either hit or stand (NOT case sensetive). Keeps
	 * prompting the player until one of the two Actions is chosen.
	 * 
	 * @return either hit or stand
	 */
	Action getInput() {
		System.out.print("Enter hit to draw another card and stand to stop drawing cards: ");
		String input = scan.next();
		if (input.equalsIgnoreCase(Action.HIT.toString())) {
			return Action.HIT;
		} else if (input.equalsIgnoreCase(Action.STAND.toString())) {
			return Action.STAND;
		} else {
			return getInput();
		}
	}

	/**
	 * Prints the player's current hand and the opponent's hand size to the command
	 * line.
	 * 
	 * @param hand             the player's cards
	 * @param opponentHandSize the number of cards the opponent has
	 */
	void displayInformation(Collection<Integer> hand, int opponentHandSize) {
		System.out.println("your hand: " + hand.toString());
		System.out.println("opponent has " + opponentHandSize + " cards");
		System.out.println();
	}
}
