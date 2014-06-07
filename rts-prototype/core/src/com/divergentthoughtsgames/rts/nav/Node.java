package com.divergentthoughtsgames.rts.nav;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Node
{
	public static int SIZE = 32;
	public static int HALF_SIZE = SIZE / 2;
	
	protected List<Edge> edges = new ArrayList<Edge>(4);
	
	private int x;
	private int y;
	
	/**
	 * Constructs a navigation graph node.
	 * @param x the center x point of the node.
	 * @param y the center y point of the node.
	 */
	public Node(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Adds the specified edge, if it is not already connected to this node.
	 * Note: does not call edge.addNode.
	 * @param edge the edge to connect to this node.
	 * @return reference to this node.
	 */
	public Node addEdge(Edge edge)
	{
		if (!edges.contains(edge))
		{
			edges.add(edge);
		}
		return this;
	}
	
	public List<Edge> getEdges()
	{
		return edges;
	}
	
	/**
	 * Returns the center x value of the node.
	 * @return the center x value of the node.
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Returns the center y value of the node.
	 * @return the center y value of the node.
	 */
	public int getY()
	{
		return y;
	}
	
	public int getRowIndex()
	{
		return y / SIZE;
	}
	
	public int getColumnIndex()
	{
		return x / SIZE;
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
