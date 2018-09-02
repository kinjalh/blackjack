package org.kj.blackjack.player;

import org.kj.blackjack.gameio.InputDevice;
import org.kj.blackjack.gameio.OutputDevice;

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
	 * @param name   the name of the bot
	 * @param output the output device the player uses
	 * @return a SafePlayBot
	 */
	public static Player getSafePlayBot(String name, OutputDevice output) {
		return new SafePlayBot(name, output);
	}

	/**
	 * Prompts on the command line for a name and returns a HumanPlayer with the
	 * specified name.
	 * 
	 * @param name   the Player's name
	 * @param input  the input device the player uses
	 * @param output the output device the player uses
	 * @return a HumanPlayer
	 */
	public static Player getHumanPlayer(String name, InputDevice input, OutputDevice output) {
		return new HumanPlayer(name, input, output);
	}
}
