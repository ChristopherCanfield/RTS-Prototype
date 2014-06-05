package com.divergentthoughtsgames.rts.nav;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Node
{
	private List<Edge> edges = new ArrayList<Edge>(4);
	
	private float x;
	private float y;
	
	public Node(float x, float y)
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
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}

	public void draw(ShapeRenderer batch)
	{
		batch.circle(getX(), getY(), 5);
	}
}
