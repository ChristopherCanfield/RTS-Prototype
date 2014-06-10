package com.divergentthoughtsgames.rts.nav;

public class StraightLineHeuristic implements SearchHeuristic
{
	private static StraightLineHeuristic instance = new StraightLineHeuristic();
	
	public static StraightLineHeuristic get()
	{
		return instance;
	}
	
	@Override
	public double cost(Node start, Node end)
	{
		double startRow = start.getY();
		double endRow = end.getY();
		double startColumn = start.getX();
		double endColumn = end.getX();

		double rowSquared = (startRow - endRow) * (startRow - endRow);
		double columnSquared = (startColumn - endColumn) * (startColumn - endColumn);

		return Math.sqrt(rowSquared + columnSquared);
	}
}
