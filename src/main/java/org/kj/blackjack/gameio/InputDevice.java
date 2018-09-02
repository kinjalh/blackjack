package org.kj.blackjack.gameio;

import org.kj.blackjack.gameutils.Action;
import org.kj.blackjack.player.PlayerType;

/**
 * Reads and returns input from an implementation-dependent input source.
 * 
 * @author Kinjal
 *
 */
public interface InputDevice {

	/**
	 * Reads and returns a number greater than or equal to min. If input is invalid
	 * (either wrong format or wrong numerical value), should read the next input.
	 * 
	 * @param min the minimum value of the number to return
	 * @return a number greater than or equal to min
	 */
	int getNum(int min);

	/**
	 * Returns a string.
	 * 
	 * @return a string
	 */
	String getStr();

	/**
	 * Returns an Action.
	 * 
	 * @return an Action
	 */
	Action getAction();

	/**
	 * Returns a PlayerType
	 * 
	 * @return a PlayerType
	 */
	PlayerType getPlayerType();
}
