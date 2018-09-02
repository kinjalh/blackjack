package org.kj.blackjack.runner;

import org.kj.blackjack.gameio.OutputDevice;
import org.kj.blackjack.gameutils.Action;
import org.kj.blackjack.gameutils.Deck;
import org.kj.blackjack.gameutils.GameStats;
import org.kj.blackjack.player.Player;

/**
 * Pits Players against each other in Blackjack games. Provides options to play
 * a set number of games, a single game, unlimited games, and can keep track of
 * statistics such as the number of times each player wins, loses, busts, or
 * ties.
 * 
 * @author Kinjal
 *
 */
class BlackjackManager {

	private OutputDevice output;
	private Player p1;
	private Player p2;
	private BlackjackEngine engine;
	private GameStats statTrack;

	/**
	 * Constructs a BlackjackManager with the given collaborators.
	 * 
	 * @param output    the output device used by this manager
	 * @param p1        the first player (goes first every turn)
	 * @param p2        the second player (goes second every turn)
	 * @param engine    the game engine
	 * @param statTrack a tracker for the game stats
	 */
	BlackjackManager(OutputDevice output, Player p1, Player p2, BlackjackEngine engine, 
						GameStats statTrack) {
		this.output = output;
		this.p1 = p1;
		this.p2 = p2;
		this.engine = engine;
		this.statTrack = statTrack;
	}

	/**
	 * Plays the specified number of games and then displays the game stats at the
	 * end.
	 * 
	 * @param numGames
	 */
	void playGames(int numGames) {
		for (int i = 0; i < numGames; i++) {
			playGame();
		}
		statTrack.displayStats(output);
	}

	/**
	 * Plays one game and records its result.
	 */
	void playGame() {
		output.displayIntro(p1, p2);
		output.splitTurn();

		Deck deck = new Deck();
		deck.shuffle();

		engine.dealHand(deck, p1);
		engine.dealHand(deck, p2);

		Action p1Action = Action.NO_ACTION;
		Action p2Action = Action.NO_ACTION;

		while (!engine.isGameOver(p1, p1Action, p2, p2Action)) {
			p1.displayDuringGame();
			output.promptForAction();
			p1Action = p1.playTurn(p2.handSize());
			engine.play(p1, p1Action, deck);
			output.splitTurn();

			p2.displayDuringGame();
			output.promptForAction();
			p2Action = p2.playTurn(p1.handSize());
			engine.play(p2, p2Action, deck);
			output.splitTurn();
		}

		p1.displayAfterGame();
		p2.displayAfterGame();
		output.displayResults(p1.name(), p2.name(), engine.evaluateWinner(p1, p2));
		statTrack.recordResult(engine.evaluateWinner(p1, p2));
		output.endOfGame();
	}
}
