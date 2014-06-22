package com.divergentthoughtsgames.rts.nav;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;

import com.divergentthoughtsgames.rts.App;

/**
 * Contains a method that implements the A* search algorithm.
 * @author Christopher D. Canfield
 */
public abstract class Search
{
	/**
	 * Performs an A* search between the start and end node. Returns a a SearchResult
	 * object, which contains a queue of nodes representing the path from 
	 * start to end, as well as the set of searched nodes.
	 * @param start The initial node.
	 * @param end The final node.
	 * @param heuristic The heuristic function to use when calculating the A* "h" value.
	 * @return A queue of nodes representing the path from the start node to
	 * the end node.
	 */
	public static Queue<Node> aStar(Node start, Node end, SearchHeuristic heuristic)
	{
		// Instantiate the frontier priority queue and searched set. The frontier 
		// is used to identify nodes that are at the edge of the explored zone. The
		// lowest cost of these nodes will then have their edges searched.
		// The searched set ensures that a previously explored node is not re-explored.
		PriorityQueue<SearchNode> frontier = new PriorityQueue<SearchNode>();
		Set<SearchNode> searched = new HashSet<SearchNode>();
		
		// Wrap the start node in the SearchNode decorator.
		SearchNode startNode = 
				new SearchNode(start, null, 0, heuristic.cost(start, end));
		
		// Add the start node to the frontier priority queue and the searched set.
		// The searched set ensures that the start node isn't searched
		searched.add(startNode);
		frontier.add(startNode);
		
		// Loop through the frontier nodes. If all nodes are searched and
		// no path to the exit is found, then no path is possible, and null
		// will be returned.
		while (!frontier.isEmpty())
		{
			SearchNode lowestCost = frontier.remove();
			
			// Check if the lowest cost equals the end node. If it does, 
			// the algorithm has reached the end, so construct the path using
			// the linked list of parents, and return the result.
			if (lowestCost.equals(end))
			{
				return constructPath(lowestCost);
			}
			
			// Iterate through each node that is connected to the current lowest cost node.
			for (Edge edge : lowestCost.getEdges())
			{
				if (edge.isPassable())
				{
					Node currentNode = edge.getOppositeNode(lowestCost);
					
					// Calculate the g (from start) cost of the lowest cost node by 
					// taking the parent's g cost and adding 1 to it.
					double g = edge.cost + lowestCost.getG();
	
					// Calculate the h (heuristic) cost of the lowest cost node
					// to the end node.
					double h = heuristic.cost(currentNode, end);
					
					// Wrap the edge in the SearchNode decorator, so the parent and 
					// costs can be stored with it.
					SearchNode edgeSearchNode = new SearchNode(currentNode, lowestCost, g, h);
					
					// Determine if the edge has already been searched.
					if (!searched.contains(edgeSearchNode))
					{
						frontier.add(edgeSearchNode);
						
						// Add the edge to the searched set.
						searched.add(edgeSearchNode);
					}
				}
			}
		}
		
		// Return an empty list if no path can be found from 
		// the start node to the end node.
		return new LinkedList<Node>();
	}
	
	/**
	 * Constructs a path from the final SearchNode back to the start node. This
	 * should be used with the Queue<Path> returned by the A* algorithm.
	 * @param finalNodeInPath The last node in the path returned by the A* algorithm.
	 * @return The path, starting at the start node to the end node.
	 */
	private static Queue<Node> constructPath(SearchNode finalNodeInPath)
	{
		Deque<Node> path = new ArrayDeque<Node>();
		SearchNode currentPathNode = finalNodeInPath;
		
		// Loop through the nodes until there are no more parents.
		while (currentPathNode != null)
		{
			// Add the current node to the path.
			path.addFirst(currentPathNode);
			
			if (App.debugEnabled()) System.out.print(currentPathNode.toString());
			
			// Set the current node reference to the parent of the 
			// node that was just added.
			currentPathNode = currentPathNode.getParent();
		}
		return path;
	}

	/**
	 * Finds the closest passable node to startNode.
	 * @param startNode
	 * @return
	 */
	public static Node findPassableNodeBfs(Node startNode)
	{
		// Java 8.
		return breadthFirstSearch(startNode, (Object node) -> {
			return ((Node)node).isPassable();
		});
	}
	
	public static Node findUnclaimedNodeBfs(Node startNode, HashSet<Node> claimedNodes)
	{
		// Java 8.
		return breadthFirstSearch(startNode, (Object node) -> {
			return (!claimedNodes.contains(node));
		});
	}
	
	// Note: Predicate is a Java 8 interface.
	public static Node breadthFirstSearch(Node startNode, Predicate<Object> goal)
	{
		Queue<Node> unexplored = new LinkedList<Node>();
		unexplored.add(startNode);
		
		HashSet<Node> searched = new HashSet<Node>();
		searched.add(startNode);
		
		while (!unexplored.isEmpty())
		{
			Node current = unexplored.remove();
			if (goal.test(current))
			{
				return current;
			}
			
			for (Edge e : current.getEdges())
			{
				Node adjacent = e.getOppositeNode(current);
				if (!searched.contains(adjacent))
				{
					unexplored.add(adjacent);
					searched.add(adjacent);
				}
			}
		}
		
		return null;
	}
}
