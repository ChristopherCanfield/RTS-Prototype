package com.divergentthoughtsgames.rts.nav;

public class StraightLineHeuristic implements SearchHeuristic
{
	private static StraightLineHeuristic instance = new StraightLineHeuristic();
	
	public static StraightLineHeuristic getInstance()
	{
		return instance;
	}
	
	@Override
	public float cost(Node start, Node end)
	{
		float startRow = start.getY();
		float endRow = end.getY();
		float startColumn = start.getX();
		float endColumn = end.getX();

		float rowSquared = (startRow - endRow) * (startRow - endRow);
		float columnSquared = (startColumn - endColumn) * (startColumn - endColumn);

		return (float)Math.sqrt(rowSquared + columnSquared);
	}
}
