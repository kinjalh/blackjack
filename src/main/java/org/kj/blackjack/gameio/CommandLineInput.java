package org.kj.blackjack.gameio;

import java.util.Scanner;

import org.kj.blackjack.gameutils.Action;
import org.kj.blackjack.player.PlayerType;

/**
 * Takes input from the command line. Does not prompt the user. Prompts must be
 * outputted separately.
 * 
 * @author Kinjal
 *
 */
public class CommandLineInput implements InputDevice {

	private Scanner keyboard;

	/**
	 * Constructs a CommandLineInput that can take input from the command line.
	 */
	public CommandLineInput() {
		keyboard = new Scanner(System.in);
	}

	/**
	 * Returns the next integer that is entered into the command line. The input
	 * must be an integer and it must be greater than or equal to min. If not, the
	 * CommandLineInput will wait until a valid input is entered.
	 * 
	 * @param min the minimum value to enter
	 * @return integer from command line
	 */
	@Override
	public int getNum(int min) {
		int num = 0;
		while (!keyboard.hasNextInt()) {
			keyboard.next();
		}
		num = keyboard.nextInt();
		if (num >= min) {
			return num;
		} else {
			return getNum(min);
		}
	}

	/**
	 * Returns the next string entered into the command line.
	 * 
	 * @return the next string from the command line
	 */
	@Override
	public String getStr() {
		return keyboard.next();
	}

	/**
	 * Returns an Action corresponding to the input keyphrase. If the input is 'h',
	 * returns Action.HIT. If the input is 's', returns Action.STAND. If the input
	 * is neither, waits for a valid input.
	 * 
	 * @return either Action.HIT or Action.STAND based on input
	 */
	@Override
	public Action getAction() {
		String input = keyboard.next();
		if (input.equalsIgnoreCase("h")) {
			return Action.HIT;
		} else if (input.equalsIgnoreCase("s")) {
			return Action.STAND;
		} else {
			return getAction();
		}
	}

	/**
	 * Returns a PlayerType based on input. If the input is 'hp', returns
	 * PlayerType.HUMAN_PLAYER. If input is 'spb', returns PlayerType.SAFEPLAYBOT.
	 * If input is neither, waits for a valid input.
	 * 
	 * @return PlayerType.HUMAN_PLAYER or PlayerType.SAFEPLAYBOT based on input
	 */
	@Override
	public PlayerType getPlayerType() {
		String input = keyboard.next();
		if (input.equalsIgnoreCase("hp")) {
			return PlayerType.HUMAN_PLAYER;
		} else if (input.equalsIgnoreCase("spb")) {
			return PlayerType.SAFEPLAYBOT;
		} else {
			return getPlayerType();
		}
	}
}
