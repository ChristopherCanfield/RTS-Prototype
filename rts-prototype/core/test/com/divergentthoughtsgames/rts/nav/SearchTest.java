/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.nav;

import static org.junit.Assert.*;

import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import com.divergentthoughtsgames.rts.App;

public class SearchTest
{

	@Test
	public void aStarEndNotConnected()
	{
		Node end = new Node(100, 10);
		Node[] navGraph = createNavGraph1();
		Queue<Node> path = Search.aStar(navGraph[0], end, StraightLineHeuristic.get());
		assertTrue(path.isEmpty());
	}
	
	@Test
	public void aStarStartNotConnected()
	{
		Node start = new Node(100, 10);
		Node[] navGraph = createNavGraph1();
		Queue<Node> path = Search.aStar(start, navGraph[0], StraightLineHeuristic.get());
		assertTrue(path.isEmpty());
	}
	
	@Test
	public void aStarSameStartEnd()
	{
		Node[] navGraph = createNavGraph1();
		Queue<Node> path = Search.aStar(navGraph[10], navGraph[10], StraightLineHeuristic.get());
		assertEquals(1, path.size());
		assertEquals(navGraph[10], path.peek());
	}
	
	@Test
	public void aStarStraightLine1()
	{
		Node[] navGraph = createNavGraph1();

		App.setDebug(true);
		Queue<Node> path = Search.aStar(navGraph[0], navGraph[7], StraightLineHeuristic.get());

		Node node = null;
		while (!path.isEmpty())
		{
			node = path.poll();
		}

		assertNotNull(node);
		assertEquals(navGraph[7], node);
	}
	
	@Test
	public void aStarStraightLine_diagonal1()
	{
		Node[] navGraph = createNavGraph2();
		
		Queue<Node> path = Search.aStar(navGraph[0], navGraph[5], StraightLineHeuristic.get());
		assertEquals(3, path.size());
		
		Node node = path.poll();
		assertEquals(navGraph[0], node);
		
		path.poll();
		node = path.poll();
		assertEquals(navGraph[5], node);
	}
	
	@Test
	public void aStarStraightLine_diagonal2()
	{
		Node[] navGraph = createNavGraph2();
		
		Queue<Node> path = Search.aStar(navGraph[5], navGraph[0], StraightLineHeuristic.get());
		assertEquals(3, path.size());
		
		Node node = path.poll();
		assertEquals(navGraph[5], node);
		
		path.poll();
		node = path.poll();
		assertEquals(navGraph[0], node);
	}
	
	@Test
	public void aStarStraightLine_diagonal3()
	{
		Node[] navGraph = createNavGraph2();
		
		Queue<Node> path = Search.aStar(navGraph[6], navGraph[2], StraightLineHeuristic.get());
		assertEquals(3, path.size());
		
		Node node = path.poll();
		assertEquals(navGraph[6], node);
		
		path.poll();
		node = path.poll();
		assertEquals(navGraph[2], node);
	}

	private static Node[] createNavGraph1()
	{
		Node[] navGraph = new Node[12];
		navGraph[0] = new Node(0, 0);	// 0
		navGraph[1] = new Node(0, 1);	// 1
		navGraph[2] = new Node(0, 2);	// 2
		navGraph[3] = new Node(0, 3);	// 3
		navGraph[4] = new Node(1, 0);	// 4
		navGraph[5] = new Node(1, 1);	// 5
		navGraph[6] = new Node(1, 2);	// 6
		navGraph[7] = new Node(1, 3);	// 7
		navGraph[8] = new Node(2, 0);	// 8
		navGraph[9] = new Node(2, 1);	// 9
		navGraph[10] = new Node(2, 2);	// 10
		navGraph[11] = new Node(2, 3);	// 11
		
		Edge edge_00_01 = new Edge(navGraph[0], navGraph[1], 1);
		Edge edge_00_10 = new Edge(navGraph[0], navGraph[4], 1);
		navGraph[0].addEdge(edge_00_01).addEdge(edge_00_10);
		
		Edge edge_01_02 = new Edge(navGraph[1], navGraph[2], 1);
		Edge edge_01_11 = new Edge(navGraph[1], navGraph[5], 1);
		navGraph[1].addEdge(edge_01_02).addEdge(edge_01_11);
		
		Edge edge_02_03 = new Edge(navGraph[2], navGraph[3], 1);
		Edge edge_02_12 = new Edge(navGraph[2], navGraph[6], 1);
		navGraph[2].addEdge(edge_02_03).addEdge(edge_02_12);
		
		Edge edge_03_13 = new Edge(navGraph[3], navGraph[7], 1);
		navGraph[3].addEdge(edge_03_13);
		
		Edge edge_10_11 = new Edge(navGraph[4], navGraph[5], 1);
		Edge edge_10_20 = new Edge(navGraph[4], navGraph[8], 1);
		navGraph[4].addEdge(edge_10_11).addEdge(edge_10_20);
		
		Edge edge_11_12 = new Edge(navGraph[5], navGraph[6], 1);
		Edge edge_11_21 = new Edge(navGraph[5], navGraph[9], 1);
		navGraph[5].addEdge(edge_11_12).addEdge(edge_11_21);
		
		Edge edge_12_13 = new Edge(navGraph[6], navGraph[7], 1);
		Edge edge_12_22 = new Edge(navGraph[6], navGraph[10], 1);
		navGraph[6].addEdge(edge_12_13).addEdge(edge_12_22);
		
		Edge edge_13_23 = new Edge(navGraph[7], navGraph[11], 1);
		navGraph[7].addEdge(edge_13_23);
		
		Edge edge_20_21 = new Edge(navGraph[8], navGraph[9], 1);
		navGraph[8].addEdge(edge_20_21);
		
		Edge edge_21_22 = new Edge(navGraph[9], navGraph[10], 1);
		navGraph[9].addEdge(edge_21_22);
		
		Edge edge_22_23 = new Edge(navGraph[10], navGraph[11], 1);
		navGraph[10].addEdge(edge_22_23);
		
		return navGraph;
	}
	
	static Node[] createNavGraph2()
	{
		Node[] navGraph = new Node[9];
		navGraph[0] = new Node(0, 0);	// 0
		navGraph[1] = new Node(0, 1);	// 1
		navGraph[2] = new Node(0, 2);	// 2
		navGraph[3] = new Node(1, 0);	// 3
		navGraph[4] = new Node(1, 1);	// 4
		navGraph[5] = new Node(1, 2);	// 5
		navGraph[6] = new Node(2, 0);	// 6
		navGraph[7] = new Node(2, 1);	// 7
		navGraph[8] = new Node(2, 2);	// 8
		
		Edge edge_00_01 = new Edge(navGraph[0], navGraph[1], 1);
		Edge edge_00_10 = new Edge(navGraph[0], navGraph[3], 1);
		Edge edge_00_11 = new Edge(navGraph[0], navGraph[4], 1.4);
		navGraph[0].addEdge(edge_00_01).addEdge(edge_00_10).addEdge(edge_00_11);
		
		Edge edge_01_02 = new Edge(navGraph[1], navGraph[2], 1);
		Edge edge_01_11 = new Edge(navGraph[1], navGraph[4], 1);
		Edge edge_01_10 = new Edge(navGraph[1], navGraph[3], 1.4);
		Edge edge_01_12 = new Edge(navGraph[1], navGraph[5], 1.4);
		navGraph[1].addEdge(edge_01_02).addEdge(edge_01_10).addEdge(edge_01_11).addEdge(edge_01_12);
		
		Edge edge_02_12 = new Edge(navGraph[2], navGraph[5], 1);
		Edge edge_02_11 = new Edge(navGraph[2], navGraph[4], 1.4);
		navGraph[2].addEdge(edge_02_12).addEdge(edge_02_11);
		
		Edge edge_10_11 = new Edge(navGraph[3], navGraph[4], 1);
		Edge edge_10_20 = new Edge(navGraph[3], navGraph[6], 1);
		Edge edge_10_21 = new Edge(navGraph[3], navGraph[7], 1.4);
		navGraph[3].addEdge(edge_10_11).addEdge(edge_10_20).addEdge(edge_10_21);
		
		Edge edge_11_12 = new Edge(navGraph[4], navGraph[5], 1);
		Edge edge_11_21 = new Edge(navGraph[4], navGraph[7], 1);
		Edge edge_11_20 = new Edge(navGraph[4], navGraph[6], 1.4);
		Edge edge_11_22 = new Edge(navGraph[4], navGraph[8], 1.4);
		navGraph[4].addEdge(edge_11_12).addEdge(edge_11_21).addEdge(edge_11_20).addEdge(edge_11_22);
		
		Edge edge_12_22 = new Edge(navGraph[5], navGraph[8], 1);
		navGraph[5].addEdge(edge_12_22);
		
		Edge edge_20_21 = new Edge(navGraph[6], navGraph[7], 1);
		navGraph[6].addEdge(edge_20_21);
		
		Edge edge_21_22 = new Edge(navGraph[7], navGraph[8], 1);
		navGraph[7].addEdge(edge_21_22);
		
		return navGraph;
	}
}
