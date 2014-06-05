package com.divergentthoughtsgames.rts.nav;

/**
 * Heuristic used to calculate the Manhattan distance between two squares.
 * The Manhattan distance provides the grid-based distance between the squares.
 * @author Christopher D. Canfield
 */
public class ManhattanHeuristic implements SearchHeuristic
{
	@Override
	public float calculateCost(Node start, Node end)
	{
		float rowDiff = Math.abs(start.getY() - end.getY());
		float colDiff = Math.abs(start.getX() - end.getX());
		return (rowDiff + colDiff);
	}	
}