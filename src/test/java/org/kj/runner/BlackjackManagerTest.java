package org.kj.runner;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kj.gameutils.Action;
import org.kj.player.Player;
import org.kj.player.PlayerFactory;

public class BlackjackManagerTest {
	
	@Test
	public void testGameOverBothPlayerHit() {
		Player p1 = PlayerFactory.getSafePlayBot("bot1");
		Player p2 = PlayerFactory.getSafePlayBot("bot2");
		p1.setHand(new ArrayList<Integer>());
		p2.setHand(new ArrayList<Integer>());
		BlackjackManager game = new CommandLineBlackjackManager();
		assertTrue(!game.isGameOver(p1, Action.HIT, p2, Action.HIT));
	}
	
	@Test
	public void testGameOverOnePlayerHitOneStand() {
		Player p1 = PlayerFactory.getSafePlayBot("bot1");
		Player p2 = PlayerFactory.getSafePlayBot("bot2");
		p1.setHand(new ArrayList<Integer>());
		p2.setHand(new ArrayList<Integer>());
		BlackjackManager game = new CommandLineBlackjackManager();
		assertTrue(!game.isGameOver(p1, Action.HIT, p2, Action.STAND));
	}
	
	@Test
	public void testGameOverBothPlayersStand() {
		Player p1 = PlayerFactory.getSafePlayBot("bot1");
		Player p2 = PlayerFactory.getSafePlayBot("bot2");
		p1.setHand(new ArrayList<Integer>());
		p2.setHand(new ArrayList<Integer>());
		BlackjackManager game = new CommandLineBlackjackManager();
		assertTrue(game.isGameOver(p1, Action.STAND, p2, Action.STAND));
	}
	
	@Test
	public void testGameOverBothPlayersBust() {
		Player p1 = PlayerFactory.getSafePlayBot("bot1");
		Player p2 = PlayerFactory.getSafePlayBot("bot2");
		List<Integer> p1Hand = new ArrayList<Integer>();
		p1Hand.add(12);
		p1Hand.add(13);
		List<Integer> p2Hand = new ArrayList<Integer>();
		p2Hand.add(12);
		p2Hand.add(13);
		p1.setHand(p1Hand);
		p2.setHand(p2Hand);
		BlackjackManager game = new CommandLineBlackjackManager();
		assertTrue(game.isGameOver(p1, Action.HIT, p2, Action.HIT));
	}
	
	@Test
	public void testGameOverOnePlayerBust() {
		Player p1 = PlayerFactory.getSafePlayBot("bot1");
		Player p2 = PlayerFactory.getSafePlayBot("bot2");
		List<Integer> p1Hand = new ArrayList<Integer>();
		p1Hand.add(1);
		p1Hand.add(13);
		List<Integer> p2Hand = new ArrayList<Integer>();
		p2Hand.add(12);
		p2Hand.add(13);
		p1.setHand(p1Hand);
		p2.setHand(p2Hand);
		BlackjackManager game = new CommandLineBlackjackManager();
		assertTrue(game.isGameOver(p1, Action.HIT, p2, Action.HIT));
	}
	
	@Test
	public void testGameOverNeitherPlayerBust() {
		Player p1 = PlayerFactory.getSafePlayBot("bot1");
		Player p2 = PlayerFactory.getSafePlayBot("bot2");
		List<Integer> p1Hand = new ArrayList<Integer>();
		p1Hand.add(1);
		p1Hand.add(13);
		List<Integer> p2Hand = new ArrayList<Integer>();
		p2Hand.add(12);
		p2Hand.add(3);
		p1.setHand(p1Hand);
		p2.setHand(p2Hand);
		BlackjackManager game = new CommandLineBlackjackManager();
		assertTrue(!game.isGameOver(p1, Action.HIT, p2, Action.HIT));
	}
}
