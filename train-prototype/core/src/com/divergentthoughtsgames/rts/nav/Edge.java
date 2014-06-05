package com.divergentthoughtsgames.rts.nav;

import com.divergentthoughtsgames.rts.util.GameException;

public class Edge
{
	public float cost;
	
	private Node node1;
	private Node node2;

	public Edge(float cost)
	{
		this.cost = cost;
	}
	
	public Edge addNode(Node node)
	{
		if (node1 == node || node2 == node)
		{
			return this;
		}
		
		if (node1 == null)
		{
			node1 = node;
		}
		else if (node2 == null)
		{
			node2 = node;
		}
		else
		{
			throw new GameException("Edge already has two connections.");
		}
		
		return this;
	}
	
	public Node getOppositeNode(Node node)
	{
		if (node == node1)
		{
			return node2;
		}
		else if (node == node2)
		{
			return node1;
		}
		else
		{
			throw new GameException("Node is not connected to this edge.");
		}
	}
}
