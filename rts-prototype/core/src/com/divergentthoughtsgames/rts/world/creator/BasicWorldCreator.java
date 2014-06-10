/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.world.creator;

import java.util.ArrayList;

import com.divergentthoughtsgames.rts.nav.Edge;
import com.divergentthoughtsgames.rts.nav.Node;
import com.divergentthoughtsgames.rts.world.World;

/**
 * Creates a nav graph arranged in a grid, using the width and height parameters provided.
 * Node size is retrieved from the Node.SIZE constant.
 * @author Christopher D. Canfield
 */
public class BasicWorldCreator
{

	// TODO (6/7/2014): implement this.
	public World create(int width, int height)
	{
		int rows = height / Node.SIZE - 1;
		int columns = width / Node.SIZE - 1;
		
		ArrayList<Node> nodeList = new ArrayList<Node>();
		Node[][] nodes = new Node[rows][columns];
		
		for (int row = 0; row < rows; ++row)
		{
			for (int column = 0; column < columns; ++column)
			{
				Node node = new Node(column * Node.SIZE + Node.HALF_SIZE, row * Node.SIZE + Node.HALF_SIZE)
				nodeList.add(node);
				nodes[row][column] = node;
				addEdges(nodes, row, column, rows, columns);
			}
		}
	}
	
	private static void addEdges(Node[][] nodes, int row, int column, int rows, int columns)
	{
		Node node = nodes[row][column];
		
		if (row > 0)
		{
			Edge edge = new Edge(1);
			edge.addNode(node).addNode(nodes[row - 1][column]);
		}
		if (row < rows)
		{
			Edge edge = new Edge(1);
			edge.addNode(node).addNode(nodes[row + 1][column]);
		}
		if (column > 0)
		{
			Edge edge = new Edge(1);
			edge.addNode(node).addNode(nodes[row][column - 1]);
		}
		if (column < columns)
		{
			Edge edge = new Edge(1);
			edge.addNode(node).addNode(nodes[row][column + 1]);
		}
	}
}
