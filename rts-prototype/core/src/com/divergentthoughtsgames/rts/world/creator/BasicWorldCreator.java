/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.world.creator;

import java.util.ArrayList;

import com.divergentthoughtsgames.rts.nav.Edge;
import com.divergentthoughtsgames.rts.nav.Node;
import com.divergentthoughtsgames.rts.world.NavMap;
import com.divergentthoughtsgames.rts.world.World;

/**
 * Creates a nav graph arranged in a grid, using the width and height parameters provided.
 * Node size is retrieved from the Node.SIZE constant.
 * @author Christopher D. Canfield
 */
public class BasicWorldCreator
{
	public static World create(int width, int height)
	{
		int rows = height / Node.SIZE;
		int columns = width / Node.SIZE;
		
		ArrayList<Node> nodeList = new ArrayList<Node>();
		Node[][] nodes = new Node[rows][columns];
		
		// Build the navigation graph. 0,0 = bottom left.
		for (int row = 0; row < rows; ++row)
		{
			for (int column = 0; column < columns; ++column)
			{
				Node node = new Node(column * Node.SIZE + Node.HALF_SIZE, row * Node.SIZE + Node.HALF_SIZE);
				nodeList.add(node);
				nodes[row][column] = node;
				addEdges(nodes, row, column, columns);
			}
		}
		
		World world = new World();
		Node[] navGraph = nodeList.toArray(new Node[nodeList.size()]);
		NavMap navMap = new NavMap(navGraph, width, height);
		world.setNavMap(navMap);
		
		return world;
	}
	
	private static void addEdges(Node[][] nodes, int row, int column, int columns)
	{
		Node node = nodes[row][column];
		
		// Up
		if (row > 0)
		{
			Edge edge = new Edge(1);
			edge.addNode(node).addNode(nodes[row - 1][column]);
		}
		
		// Left
		if (column > 0)
		{
			Edge edge = new Edge(1);
			edge.addNode(node).addNode(nodes[row][column - 1]);
		}
		
		// Upper-Left
		if (row > 0 && column > 0)
		{
			Edge edge = new Edge(1);
			edge.addNode(node).addNode(nodes[row - 1][column - 1]);
		}
		
		// Upper-Right
		if (row > 0 && column < columns - 1)
		{
			if (nodes[row - 1][column + 1] != null)
			{
				Edge edge = new Edge(1);
				edge.addNode(node).addNode(nodes[row - 1][column + 1]);
			}
		}
	}
}
