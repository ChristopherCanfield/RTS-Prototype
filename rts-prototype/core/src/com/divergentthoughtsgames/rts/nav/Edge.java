package com.divergentthoughtsgames.rts.nav;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.divergentthoughtsgames.rts.util.Coords;
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
		
		this.node1.addEdge(this);
		this.node2.addEdge(this);
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
	
	public void draw(ShapeRenderer batch)
	{
		if (node1 != null && node2 != null)
		{
			Vector3 node1Screen = Coords.worldToScreen(node1.getX(), node1.getY());
			Vector3 node2Screen = Coords.worldToScreen(node2.getX(), node2.getY());
			batch.line(node1Screen.x, node1Screen.y, node2Screen.x, node2Screen.y);
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
