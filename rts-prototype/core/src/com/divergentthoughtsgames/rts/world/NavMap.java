/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.world;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.divergentthoughtsgames.rts.nav.Node;

public class NavMap
{
	public final int width;
	public final int height;
	
	private final Node[] navGraph;
	private Node[][] indexedNavGraph;
	
	public static Node getNode(NavMap map, int x, int y)
	{
		Node[][] navGraph = map.indexedNavGraph;
		return navGraph[x / Node.SIZE][y / Node.SIZE];
	}
	
	public NavMap(Node[] navGraph, int width, int height)
	{
		this.navGraph = navGraph;
		
		this.width = width;
		this.height = height;

		indexedNavGraph = new Node[width][height];
		for (Node node : navGraph)
		{
			indexedNavGraph[node.getColumnIndex()][node.getRowIndex()] = node;
		}
	}
	
	public Node[] getNavGraph()
	{
		return navGraph;
	}
	
	public void draw(ShapeRenderer renderer)
	{
		for (final Node node : navGraph)
		{
			node.draw(renderer);
		}
	}
}
