/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.nav;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NodeTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void hashCode_Equals()
	{
		Node node1 = new Node(14, 20);
		Node node2 = new Node(14, 20);
		Node node3 = new Node(16, 3);
		Node node4 = new Node(20, 14);
		
		assertEquals(node1.hashCode(), node1.hashCode());
		assertEquals(node1.hashCode(), node2.hashCode());
		assertNotEquals(node1, node3);
		assertNotEquals(node1, node4);
		
		assertEquals(node1, node1);
		assertEquals(node1, node2);
		assertNotEquals(node1, node3);
		assertNotEquals(node1, node4);
	}

	@Test
	public void testAddEdgeGetEdges()
	{
		Edge edge = new Edge(1);
		Node node = new Node(16, 16);
		node.addEdge(edge);
		
		assertEquals(node.getEdges().size(), 1);
		assertSame(edge, node.getEdges().get(0));
	}

	@Test
	public void testGetXGetY()
	{
		Node node = new Node(5, 14);
		assertEquals(5, node.getX());
		assertEquals(14, node.getY());
	}

	@Test
	public void testGetRowIndexGetColumnIndex()
	{
		Node node = new Node(16, 64);
		assertEquals(16 / Node.SIZE, node.getColumnIndex());
		assertEquals(64 / Node.SIZE, node.getRowIndex());
	}
}
