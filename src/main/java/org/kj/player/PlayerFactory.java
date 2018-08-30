package org.kj.player;

import java.util.Scanner;

/**
 * Returns Player objects that are a certain type of subclass of player.
 * 
 * @author Kinjal
 *
 */
public class PlayerFactory {

	/**
	 * Returns a SafePlayBot with the specified name.
	 * 
	 * @param name the name of the bot
	 * @return a SafePlayBot
	 */
	public static Player getSafePlayBot(String name) {
		return new SafePlayBot(name);
	}

	/**
	 * Returns a SafePlayBot with the specified name and probability threshold.
	 * 
	 * @param name      the name of the bot
	 * @param threshold the probability threshold
	 * @return a SafePlayBot
	 */
	public static Player getSafePlayBot(String name, double threshold) {
		return new SafePlayBot(name, threshold);
	}

	/**
	 * Prompts on the command line for a name and returns a HumanPlayer with the
	 * specified name.
	 * 
	 * @param scan the scanner that reads the HumanPlayer's name
	 * @return a HumanPlayer
	 */
	public static Player getHumanPlayer(Scanner scan) {
		System.out.print("Enter your name : ");
		return new HumanPlayer(scan.next(), scan);
	}
}
