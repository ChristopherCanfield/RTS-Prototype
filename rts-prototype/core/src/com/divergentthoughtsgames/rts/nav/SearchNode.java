package com.divergentthoughtsgames.rts.nav;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Wraps a maze node with additional information required when searching
 * for a path.
 * @author Christopher D. Canfield
 */
public class SearchNode extends Node implements Comparable<SearchNode>
{
	// The parent to this node in the path.
	private SearchNode parent;
	
	// The node's cumulative cost from the start node to this node.
	private double g;
	// The node's heuristic cost to the end node.
	private double h;
	
	/**
	 * Wraps a Node with additional information needed during the A* search.
	 * @param underlyingNode The node that will be wrapped.
	 * @param parent The node that lead to this node.
	 * @param g The node's cumulative cost from the start node to this node.
	 * @param h The node's heuristic cost to the end node.
	 */
	public SearchNode(Node underlyingNode, SearchNode parent, double g, double h)
	{
		super(underlyingNode.getX(), underlyingNode.getY());
		this.parent = parent;
		this.g = g;
		this.h = h;
		this.edges = underlyingNode.edges;
	}
	
	/**
	 * Returns the parent of this node in the path.
	 * @return This search node's parent.
	 */
	public SearchNode getParent()
	{
		return parent;
	}
	
	/**
	 * Returns the cumulative cost from the start node to this node.
	 * @return The cumulative cost from the start node to this node.
	 */
	public double getG()
	{
		return g;
	}
	
	/**
	 * Returns the heuristic cost from the end node to this node.
	 * @return The heuristic cost from the end node to this node.
	 */
	public double getH()
	{
		return h;
	}

	@Override
	public void draw(ShapeRenderer batch)
	{
		Color originalColor = batch.getColor().cpy();
		batch.setColor(Color.BLUE);
		super.draw(batch);
		batch.setColor(originalColor);
	}
	
	
	/**
	 * The compareTo method is required for the A* algorithm's priority queue.
	 */
	@Override
	public int compareTo(SearchNode other)
	{
		double cost = g + h;
		double otherCost = other.g + other.h;
		return (cost < otherCost) ? -1 : 
				(cost > otherCost) ? 1 : 0;
	}
}
