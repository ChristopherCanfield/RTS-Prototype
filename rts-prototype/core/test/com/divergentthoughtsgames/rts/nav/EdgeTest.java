/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.nav;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testEdgeDouble()
	{
		Edge edge = new Edge(1.4);
		assertEquals(1.4, edge.cost, 0.01);
	}

	@Test
	public void testEdgeNodeNodeDoubleGetOpposite()
	{
		Node node = new Node(0, 0);
		Node node2 = new Node(1, 0);
		Edge edge = new Edge(node, node2, 2.4);
		
		assertEquals(2.4, edge.cost, 0.01);
		assertEquals(node, edge.getOppositeNode(node2));
		assertEquals(node2, edge.getOppositeNode(node));
	}

	@Test
	public void testAddNode()
	{
		Edge edge = new Edge(1.4);
		Node node = new Node(0, 0);
		Node node2 = new Node(1, 0);
		edge.addNode(node).addNode(node2);
		
		assertEquals(node, edge.getOppositeNode(node2));
		assertEquals(node2, edge.getOppositeNode(node));
	}
}
