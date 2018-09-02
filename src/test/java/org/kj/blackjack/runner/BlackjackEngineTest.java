package org.kj.blackjack.runner;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kj.blackjack.gameutils.Action;
import org.kj.blackjack.player.Player;
import org.kj.blackjack.player.PlayerFactory;
import org.kj.blackjack.runner.BlackjackEngine;
import org.kj.blackjack.runner.GameResult;

public class BlackjackEngineTest {

	@Test
	public void testGameOverBothBust() {
		BlackjackEngine engine = new BlackjackEngine();
		Player p1 = PlayerFactory.getSafePlayBot("bot-1", null);
		List<Integer> hand1 = new ArrayList<Integer>();
		hand1.add(12);
		hand1.add(13);
		p1.setHand(hand1);
		Player p2 = PlayerFactory.getSafePlayBot("bot-2", null);
		List<Integer> hand2 = new ArrayList<Integer>();
		hand2.add(12);
		hand2.add(13);
		p2.setHand(hand2);
		assertTrue(engine.isGameOver(p1, Action.HIT, p2, Action.HIT));
	}
	
	@Test
	public void testGameOverOneBust() {
		BlackjackEngine engine = new BlackjackEngine();
		Player p1 = PlayerFactory.getSafePlayBot("bot-1", null);
		List<Integer> hand1 = new ArrayList<Integer>();
		hand1.add(1);
		hand1.add(13);
		p1.setHand(hand1);
		Player p2 = PlayerFactory.getSafePlayBot("bot-2", null);
		List<Integer> hand2 = new ArrayList<Integer>();
		hand2.add(12);
		hand2.add(13);
		p2.setHand(hand2);
		assertTrue(engine.isGameOver(p1, Action.HIT, p2, Action.HIT));
	}
	
	@Test
	public void testGameOverBothStand() {
		BlackjackEngine engine = new BlackjackEngine();
		Player p1 = PlayerFactory.getSafePlayBot("bot-1", null);
		List<Integer> hand1 = new ArrayList<Integer>();
		hand1.add(1);
		hand1.add(13);
		p1.setHand(hand1);
		Player p2 = PlayerFactory.getSafePlayBot("bot-2", null);
		List<Integer> hand2 = new ArrayList<Integer>();
		hand2.add(2);
		hand2.add(13);
		p2.setHand(hand2);
		assertTrue(engine.isGameOver(p1, Action.STAND, p2, Action.STAND));
	}
	
	@Test
	public void testGameOverOneStand() {
		BlackjackEngine engine = new BlackjackEngine();
		Player p1 = PlayerFactory.getSafePlayBot("bot-1", null);
		List<Integer> hand1 = new ArrayList<Integer>();
		hand1.add(1);
		hand1.add(13);
		p1.setHand(hand1);
		Player p2 = PlayerFactory.getSafePlayBot("bot-2", null);
		List<Integer> hand2 = new ArrayList<Integer>();
		hand2.add(2);
		hand2.add(13);
		p2.setHand(hand2);
		assertTrue(!engine.isGameOver(p1, Action.STAND, p2, Action.HIT));
	}
	
	@Test
	public void testGameOverNoStandNoBust() {
		BlackjackEngine engine = new BlackjackEngine();
		Player p1 = PlayerFactory.getSafePlayBot("bot-1", null);
		List<Integer> hand1 = new ArrayList<Integer>();
		hand1.add(1);
		hand1.add(13);
		p1.setHand(hand1);
		Player p2 = PlayerFactory.getSafePlayBot("bot-2", null);
		List<Integer> hand2 = new ArrayList<Integer>();
		hand2.add(2);
		hand2.add(13);
		p2.setHand(hand2);
		assertTrue(!engine.isGameOver(p1, Action.HIT, p2, Action.STAND));
	}
	
	//--------------------------------------------------------------------------------
	
	@Test
	public void testEvaluateWinnerBothBust() {
		BlackjackEngine engine = new BlackjackEngine();
		Player p1 = PlayerFactory.getSafePlayBot("bot-1", null);
		List<Integer> hand1 = new ArrayList<Integer>();
		hand1.add(12);
		hand1.add(13);
		p1.setHand(hand1);
		Player p2 = PlayerFactory.getSafePlayBot("bot-2", null);
		List<Integer> hand2 = new ArrayList<Integer>();
		hand2.add(11);
		hand2.add(13);
		p2.setHand(hand2);
		assertTrue(engine.evaluateWinner(p1, p2).equals(GameResult.TIE_BOTH_BUST));
	}
	
	@Test
	public void testEvaluateWinnerPlayerOneBust() {
		BlackjackEngine engine = new BlackjackEngine();
		Player p1 = PlayerFactory.getSafePlayBot("bot-1", null);
		List<Integer> hand1 = new ArrayList<Integer>();
		hand1.add(12);
		hand1.add(13);
		p1.setHand(hand1);
		Player p2 = PlayerFactory.getSafePlayBot("bot-2", null);
		List<Integer> hand2 = new ArrayList<Integer>();
		hand2.add(1);
		hand2.add(13);
		p2.setHand(hand2);
		assertTrue(engine.evaluateWinner(p1, p2).equals(GameResult.PLAYER_TWO_WIN_OPP_BUST));
	}
	
	@Test
	public void testEvaluateWinnerPlayerTwoBust() {
		BlackjackEngine engine = new BlackjackEngine();
		Player p1 = PlayerFactory.getSafePlayBot("bot-1", null);
		List<Integer> hand1 = new ArrayList<Integer>();
		hand1.add(2);
		hand1.add(13);
		p1.setHand(hand1);
		Player p2 = PlayerFactory.getSafePlayBot("bot-2", null);
		List<Integer> hand2 = new ArrayList<Integer>();
		hand2.add(11);
		hand2.add(13);
		p2.setHand(hand2);
		assertTrue(engine.evaluateWinner(p1, p2).equals(GameResult.PLAYER_ONE_WIN_OPP_BUST));
	}
	
	@Test
	public void testEvaluateWinnerPlayerOneHigherSum() {
		BlackjackEngine engine = new BlackjackEngine();
		Player p1 = PlayerFactory.getSafePlayBot("bot-1", null);
		List<Integer> hand1 = new ArrayList<Integer>();
		hand1.add(12);
		hand1.add(3);
		p1.setHand(hand1);
		Player p2 = PlayerFactory.getSafePlayBot("bot-2", null);
		List<Integer> hand2 = new ArrayList<Integer>();
		hand2.add(2);
		hand2.add(3);
		p2.setHand(hand2);
		assertTrue(engine.evaluateWinner(p1, p2).equals(GameResult.PLAYER_ONE_WIN_HIGHER_SUM));
	}
	
	@Test
	public void testEvaluateWinnerPlayerTwoHigherSum() {
		BlackjackEngine engine = new BlackjackEngine();
		Player p1 = PlayerFactory.getSafePlayBot("bot-1", null);
		List<Integer> hand1 = new ArrayList<Integer>();
		hand1.add(2);
		hand1.add(3);
		p1.setHand(hand1);
		Player p2 = PlayerFactory.getSafePlayBot("bot-2", null);
		List<Integer> hand2 = new ArrayList<Integer>();
		hand2.add(11);
		hand2.add(3);
		p2.setHand(hand2);
		assertTrue(engine.evaluateWinner(p1, p2).equals(GameResult.PLAYER_TWO_WIN_HIGHER_SUM));
	}
	
	@Test
	public void testEvaluateWinnerEqualSum() {
		BlackjackEngine engine = new BlackjackEngine();
		Player p1 = PlayerFactory.getSafePlayBot("bot-1", null);
		List<Integer> hand1 = new ArrayList<Integer>();
		hand1.add(12);
		hand1.add(3);
		p1.setHand(hand1);
		Player p2 = PlayerFactory.getSafePlayBot("bot-2", null);
		List<Integer> hand2 = new ArrayList<Integer>();
		hand2.add(11);
		hand2.add(4);
		p2.setHand(hand2);
		assertTrue(engine.evaluateWinner(p1, p2).equals(GameResult.TIE_EQUAL_SUM));
	}
}
