package com.divergentthoughtsgames.rts.nav;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.util.Coords;
import com.divergentthoughtsgames.rts.util.GameException;

public class Edge
{
	public double cost;
	
	private Node node1;
	private Node node2;
	
	private boolean passable;
	
	private double lastDrawn;

	public Edge(double cost)
	{
		this(null, null, cost);
	}
	
	public Edge(Node node1, Node node2, double cost)
	{
		this.cost = cost;
		this.node1 = node1;
		this.node2 = node2;
		
		if (node1 != null)
		{
			this.node1.addEdge(this);
		}
		if (node2 != null)
		{
			this.node2.addEdge(this);
		}
		
		passable = true;
	}
	
	/**
	 * Connects a node to this edge. Calls node.addEdge on the node to add this edge to
	 * the node as well.
	 * @param node the node to connect to this edge.
	 * @return reference to this edge.
	 */
	public Edge addNode(Node node)
	{
		if (node.equals(node1) || node.equals(node2))
		{
			return this;
		}
		
		if (node1 == null)
		{
			node1 = node;
			node1.addEdge(this);
		}
		else if (node2 == null)
		{
			node2 = node;
			node2.addEdge(this);
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
	
	public void setPassable(boolean val)
	{
		passable = val;
	}
	
	public boolean isPassable()
	{
		return passable;
	}
	
	public void draw(ShapeRenderer batch)
	{
		if (node1 != null && node2 != null)
		{
			if (App.getGameTime() > lastDrawn || !batch.getColor().equals(Node.DEFAULT_COLOR))
			{
				Vector3 node1Screen = Coords.worldToScreen(node1.getX(), node1.getY());
				Vector3 node2Screen = Coords.worldToScreen(node2.getX(), node2.getY());
				batch.line(node1Screen.x, node1Screen.y, node2Screen.x, node2Screen.y);
				lastDrawn = App.getGameTime();
			}
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[Edge: " )
				.append((node1 != null) ? node1.toString() : "")
				.append((node2 != null) ? node2.toString() : "")
				.append("]");
		return sb.toString();
	}
}
