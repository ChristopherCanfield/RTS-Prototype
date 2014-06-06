package com.divergentthoughtsgames.rts.nav;

import com.divergentthoughtsgames.rts.util.GameException;

public class Edge
{
	public double cost;
	
	private Node node1;
	private Node node2;

	public Edge(double cost)
	{
		this.cost = cost;
	}
	
	public Edge(Node node1, Node node2, double cost)
	{
		this.cost = cost;
		this.node1 = node1;
		this.node2 = node2;
	}
	
	public Edge addNode(Node node)
	{
		if (node.equals(node1) || node.equals(node2))
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
		if (node.equals(node1))
		{
			return node2;
		}
		else if (node.equals(node2))
		{
			return node1;
		}
		else
		{
			throw new GameException("Node is not connected to this edge.");
		}
	}
}
