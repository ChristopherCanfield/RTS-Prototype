package com.divergentthoughtsgames.train.nav;

import java.util.ArrayList;

public class Edge
{
	public float cost;
	
	private ArrayList<Node> connected = new ArrayList<Node>(8);

	public Edge(float cost)
	{
		this.cost = cost;
	}
	
	public Edge addNode(Node node)
	{
		for (Node n : connected)
		{
			if (n == node)
			{
				return this;
			}
		}
		
		connected.add(node);
		return this;
	}
}
