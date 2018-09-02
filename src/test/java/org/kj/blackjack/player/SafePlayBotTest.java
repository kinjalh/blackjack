package org.kj.blackjack.player;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.kj.blackjack.gameutils.Action;
import org.kj.blackjack.player.PlayerFactory;
import org.kj.blackjack.player.SafePlayBot;

public class SafePlayBotTest {

	@Test
	public void testChancesOfBustWithEmptyHand() {
		SafePlayBot bot = (SafePlayBot) PlayerFactory.getSafePlayBot("bot", null);
		bot.setHand(new ArrayList<Integer>());
		double chanceOfBust = bot.chanceOfBust(new ArrayList<Integer>(), 2);
		assertTrue(Math.abs(chanceOfBust) < 0.001);
	}

	@Test
	public void testChancesOfBustWithSumUnderTarget() {
		SafePlayBot bot = (SafePlayBot) PlayerFactory.getSafePlayBot("bot", null);
		bot.setHand(new ArrayList<Integer>());
		bot.addCard(4);
		bot.addCard(5);
		double chanceOfBust = bot.chanceOfBust(bot.getHand(), 0);
		assertTrue(Math.abs(chanceOfBust - 0.08) < 0.001);
	}

	@Test
	public void testChancesOfBustWithSumEqualsTarget() {
		SafePlayBot bot = (SafePlayBot) PlayerFactory.getSafePlayBot("bot", null);
		bot.setHand(new ArrayList<Integer>());
		bot.addCard(12);
		bot.addCard(9);
		double chanceOfBust = bot.chanceOfBust(bot.getHand(), 2);
		assertTrue(Math.abs(chanceOfBust - 1.0) < 0.001);
	}
	
	@Test
	public void testPlayTurnWithEmptyHand() {
		SafePlayBot bot = (SafePlayBot) PlayerFactory.getSafePlayBot("bot", null);
		bot.setHand(new ArrayList<Integer>());
		assertTrue(bot.playTurn(0).equals(Action.HIT));
	}
	
	@Test
	public void testPlayTurnWithSumEqualsTarget() {
		SafePlayBot bot = (SafePlayBot) PlayerFactory.getSafePlayBot("bot", null);
		bot.setHand(new ArrayList<Integer>());
		bot.addCard(12);
		bot.addCard(9);
		assertTrue(bot.playTurn(0).equals(Action.STAND));
	}
	
	@Test
	public void testPlayTurnWithProbabilityThresholdOne() {
		SafePlayBot bot = (SafePlayBot) PlayerFactory.getSafePlayBot("bot", null);
		bot.setHand(new ArrayList<Integer>());
		bot.addCard(12);
		bot.addCard(9);
		assertTrue(bot.playTurn(0).equals(Action.STAND));
	}
}
