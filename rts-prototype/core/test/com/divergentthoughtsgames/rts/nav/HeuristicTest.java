/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.nav;

import org.junit.Test;
import static org.junit.Assert.*;

public class HeuristicTest
{
	
	@Test
	public void straightLine1()
	{
		Node node1 = new Node(5, 1);
		Node node2 = new Node(15, 21);

		double heuristicDistance = new StraightLineHeuristic().cost(node1, node2);
		assertEquals(22.36f, heuristicDistance, 0.01f);
	}
	
	@Test
	public void straightLine2()
	{
		Node node1 = new Node(5, 1);
		Node node2 = new Node(15, 11);

		double heuristicDistance = new StraightLineHeuristic().cost(node1, node2);
		assertEquals(14.14f, heuristicDistance, 0.01f);
	}
	
	@Test
	public void straightLine3()
	{
		Node node1 = new Node(5, 1);
		Node node2 = new Node(25, 21);

		double heuristicDistance = new StraightLineHeuristic().cost(node1, node2);
		assertEquals(28.28f, heuristicDistance, 0.01f);
	}
	
	@Test
	public void straightLine4()
	{
		Node node1 = new Node(25, 21);
		Node node2 = new Node(5, 1);

		double heuristicDistance = new StraightLineHeuristic().cost(node1, node2);
		assertEquals(28.28f, heuristicDistance, 0.01f);
	}
}