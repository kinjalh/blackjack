package org.kj.blackjack.gameutils;

import org.kj.blackjack.gameio.OutputDevice;
import org.kj.blackjack.runner.GameResult;

/**
 * Keeps track of game statistics by counting the occurrence of each value of
 * GameResult.
 * 
 * @author Kinjal
 *
 */
public class GameStats {

	private int p1WinHigherSum;
	private int p2WinHigherSum;
	private int tieGameEqualSum;
	private int tieBothBust;
	private int p1WinOppBust;
	private int p2WinOppBust;

	/**
	 * Constructs a GameStats with the occurrence of each game result set to 0.
	 */
	public GameStats() {
		this.p1WinHigherSum = 0;
		this.p2WinHigherSum = 0;
		this.tieGameEqualSum = 0;
		this.tieBothBust = 0;
		this.p1WinOppBust = 0;
		this.p2WinOppBust = 0;
	}

	/**
	 * Records the occurrence of the GameResult.
	 * 
	 * @param result the result of the game
	 */
	public void recordResult(GameResult result) {
		switch (result) {
		case PLAYER_ONE_WIN_HIGHER_SUM:
			p1WinHigherSum++;
			break;
		case PLAYER_TWO_WIN_HIGHER_SUM:
			p2WinHigherSum++;
			break;
		case PLAYER_ONE_WIN_OPP_BUST:
			p1WinOppBust++;
			break;
		case PLAYER_TWO_WIN_OPP_BUST:
			p2WinOppBust++;
			break;
		case TIE_EQUAL_SUM:
			tieGameEqualSum++;
			break;
		case TIE_BOTH_BUST:
			tieBothBust++;
			break;
		default:
			break;
		}
	}

	/**
	 * Calls the output.displayStats() method with the occurrence counts stored in
	 * GameStats
	 * 
	 * @param output the output device which will display the information
	 */
	public void displayStats(OutputDevice output) {
		output.displayStats(p1WinHigherSum, p1WinOppBust, p2WinHigherSum, p2WinOppBust, tieGameEqualSum, tieBothBust);
	}
}
