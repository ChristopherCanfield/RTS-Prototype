package com.divergentthoughtsgames.train.nav;

import java.util.ArrayList;
import java.util.List;

public class Node
{
	private List<Edge> edges = new ArrayList<Edge>(4);
	
	public Node addEdge(Edge edge)
	{
		edges.add(edge);
		return this;
	}
	
	public List<Edge> getEdges()
	{
		return edges;
	}
}
