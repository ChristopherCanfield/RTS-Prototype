package com.divergentthoughtsgames.rts.nav;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Node
{
	protected List<Edge> edges = new ArrayList<Edge>(4);
	
	private int x;
	private int y;
	
	public Node(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Node addEdge(Edge edge)
	{
		edges.add(edge);
		return this;
	}
	
	public List<Edge> getEdges()
	{
		return edges;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}

	public void draw(ShapeRenderer batch)
	{
		batch.circle(getX(), getY(), 5);
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (!(other instanceof Node))
		{
			return false;
		}
		Node otherNode = (Node)other;
		return (x == otherNode.x &&
				y == otherNode.y);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(x, y);
	}
}
