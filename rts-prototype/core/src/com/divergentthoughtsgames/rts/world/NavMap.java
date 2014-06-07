/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.world;

import com.divergentthoughtsgames.rts.nav.Node;

public class WorldMap
{
	public final int width;
	public final int height;
	
	private final Node[] navGraph;
	private Node[][] indexedNavGraph;
	
	public static Node getNode(WorldMap map, int x, int y)
	{
		Node[][] navGraph = map.indexedNavGraph;
		return navGraph[x / Node.SIZE][y / Node.SIZE];
	}
	
	public WorldMap(Node[] navGraph, int width, int height)
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
}
