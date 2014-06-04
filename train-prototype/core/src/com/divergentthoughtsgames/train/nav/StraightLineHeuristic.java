package com.divergentthoughtsgames.train.nav;

public class StraightLineHeuristic implements SearchHeuristic
{

	@Override
	public float calculateCost(Node start, Node end)
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
