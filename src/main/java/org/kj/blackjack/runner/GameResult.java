package org.kj.blackjack.runner;

/**
 * Represents all the possible results of a Blackjack Game between 2 players:
 * Player 1 wins due to higher sum Player 2 wins due to higher sum Player 1 wins
 * since opponent busted Player 2 wins since opponent busted Tie game, equal
 * sums and neither busted Tie game, both busted
 * 
 * @author Kinjal
 *
 */
public enum GameResult {
	PLAYER_ONE_WIN_HIGHER_SUM, 
	PLAYER_TWO_WIN_HIGHER_SUM, 
	PLAYER_ONE_WIN_OPP_BUST, 
	PLAYER_TWO_WIN_OPP_BUST,
	TIE_EQUAL_SUM, 
	TIE_BOTH_BUST;
}
